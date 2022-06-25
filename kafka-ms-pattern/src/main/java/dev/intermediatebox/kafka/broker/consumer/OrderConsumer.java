package dev.intermediatebox.kafka.broker.consumer;

import dev.intermediatebox.kafka.broker.message.OrderMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderConsumer {
  private static final Logger log = LoggerFactory.getLogger(OrderConsumer.class);

  @KafkaListener(topics = "t-commodity-order")
  public void listen(OrderMessage orderMessage) {
    // simulate processing
    var totalItemAmount = orderMessage.getPrice() * orderMessage.getQuantity();

    log.info("Processing order {}, item {}, credit card number {}. Total amount for this item is {}",
        orderMessage.getOrderNumber(), orderMessage.getItemName(), orderMessage.getCreditCardNumber(),
        totalItemAmount);
  }
}
