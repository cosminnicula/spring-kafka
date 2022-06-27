package dev.intermediatebox.kafka.broker.stream.feedback;

import dev.intermediatebox.kafka.broker.message.FeedbackMessage;
import dev.intermediatebox.kafka.broker.message.PromotionMessage;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.serializer.JsonSerde;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

//@Configuration
public class FeedbackFourStream {

  private static final Set<String> GOOD_WORDS = Set.of("happy", "good", "helpful");

  private static final Set<String> BAD_WORDS = Set.of("angry", "sad", "bad");

  @Bean
  public KStream<String, FeedbackMessage> kstreamFeedback(StreamsBuilder builder) {
    var stringSerde = Serdes.String();
    var feedbackSerde = new JsonSerde<>(FeedbackMessage.class);

    var sourceStream = builder.stream("t-commodity-feedback", Consumed.with(stringSerde, feedbackSerde));

    sourceStream.flatMap(splitWords()).split()
        .branch(isGoodWord(), Branched.withConsumer(ks -> {
          ks.to("t-commodity-feedback-four-good");
          ks.groupByKey(Grouped.with(Serdes.String(), Serdes.String())).count().toStream().to("t-commodity-feedback-four-good-count");
          ks.print(Printed.<String, String> toSysOut().withLabel("[Good feedback]"));
        }))
        .branch(isBadWord(), Branched.withConsumer(ks -> {
          ks.to("t-commodity-feedback-four-bad");
          ks.groupByKey(Grouped.with(Serdes.String(), Serdes.String())).count().toStream().to("t-commodity-feedback-four-bad-count");
          ks.print(Printed.<String, String> toSysOut().withLabel("[Bad feedback]"));
        }));

    sourceStream.print(Printed.<String, FeedbackMessage> toSysOut().withLabel("[Stream output]"));

    return sourceStream;
  }

  private KeyValueMapper<String, FeedbackMessage, Iterable<KeyValue<String, String>>> splitWords() {
    return (key, value) -> Arrays.asList(value.getFeedback().replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+")).stream()
        .distinct().map(word -> KeyValue.pair(value.getLocation(), word)).collect(Collectors.toList());
  }

  private Predicate<String, String> isGoodWord() {
    return ((key, value) -> GOOD_WORDS.contains(value));
  }

  private Predicate<String, String> isBadWord() {
    return ((key, value) -> BAD_WORDS.contains(value));
  }
}
