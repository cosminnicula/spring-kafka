package dev.intermediatebox.kafka.broker.stream.feedback;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import dev.intermediatebox.kafka.broker.message.FeedbackMessage;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.ValueMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.serializer.JsonSerde;

//@Configuration
public class FeedbackOneStream {

  private static final Set<String> GOOD_WORDS = Set.of("happy", "good", "helpful");

  @Bean
  public KStream<String, String> kstreamFeedback(StreamsBuilder builder) {
    var stringSerde = Serdes.String();
    var feedbackSerde = new JsonSerde<>(FeedbackMessage.class);

    var goodFeedbackStream = builder
        .stream("t-commodity-feedback", Consumed.with(stringSerde, feedbackSerde))
        .flatMapValues(mapperGoodWords());

    goodFeedbackStream.to("t-commodity-feedback-one-good");

    return goodFeedbackStream;
  }

  private ValueMapper<FeedbackMessage, Iterable<String>> mapperGoodWords() {
    return feedbackMessage -> Arrays
        .asList(feedbackMessage.getFeedback().replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+"))
        .stream().filter(word -> GOOD_WORDS.contains(word)).distinct().collect(Collectors.toList());
  }

}
