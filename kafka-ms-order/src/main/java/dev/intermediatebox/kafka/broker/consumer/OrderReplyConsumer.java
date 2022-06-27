package dev.intermediatebox.kafka.broker.consumer;

import dev.intermediatebox.kafka.broker.message.OrderReplyMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

//@Service
public class OrderReplyConsumer {
  private static final Logger log = LoggerFactory.getLogger(OrderReplyConsumer.class);

  @KafkaListener(topics = "t-commodity-order-reply")
  public void listen(OrderReplyMessage orderReplyMessage) {
    log.info("Reply message received: {}", orderReplyMessage);
  }
}
