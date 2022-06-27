package dev.intermediatebox.kafka.broker.stream.promotion;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Printed;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class PromotionUppercaseStream {

  @Bean
  public KStream<String, String> kstreamPromotionUppercase(StreamsBuilder builder) {
    var sourceStream = builder.stream("t-commodity-promotion", Consumed.with(Serdes.String(), Serdes.String()));
    var uppercaseStream = sourceStream.mapValues(s -> s.toUpperCase());

    uppercaseStream.to("t-commodity-promotion-uppercase");

    sourceStream.print(Printed.<String, String>toSysOut().withLabel("Original stream"));
    uppercaseStream.print(Printed.<String, String>toSysOut().withLabel("Uppercase stream"));

    return sourceStream;
  }
}
