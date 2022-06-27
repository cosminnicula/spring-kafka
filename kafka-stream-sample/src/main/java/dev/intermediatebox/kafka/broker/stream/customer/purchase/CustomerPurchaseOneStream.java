package dev.intermediatebox.kafka.broker.stream.customer.purchase;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class CustomerPurchaseOneStream {
  @Bean
  public KStream<String, String> kstreamCustomerPurchaseAll(StreamsBuilder builder) {
    var customerPurchaseMobileStream = builder.stream("t-commodity-customer-purchase-mobile",
        Consumed.with(Serdes.String(), Serdes.String()));
    var customerPurchaseWebStream = builder.stream("t-commodity-customer-purchase-web",
        Consumed.with(Serdes.String(), Serdes.String()));

    customerPurchaseMobileStream.merge(customerPurchaseWebStream).to("t-commodity-customer-purchase-all");

    return customerPurchaseMobileStream;
  }
}
