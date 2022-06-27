package dev.intermediatebox.kafka.broker.stream.customer.purchase;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

//@Configuration
public class CustomerPurchaseTwoStream {
  @Bean
  public KStream<String, String> kstreamCustomerPurchaseAll(StreamsBuilder builder) {
    var topics = List.of("t-commodity-customer-purchase-mobile", "t-commodity-customer-purchase-web");

    var customerPurchaseAllStream = builder.stream(topics,
        Consumed.with(Serdes.String(), Serdes.String()));

    customerPurchaseAllStream.to("t-commodity-customer-purchase-all");

    return customerPurchaseAllStream;
  }
}
