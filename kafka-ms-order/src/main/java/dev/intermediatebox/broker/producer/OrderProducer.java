package dev.intermediatebox.broker.producer;

import dev.intermediatebox.broker.message.OrderMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderProducer {
  @Autowired
  private KafkaTemplate<String, OrderMessage> kafkaTemplate;

  public void publish(OrderMessage orderMessage) {
    kafkaTemplate.send("t-commodity-order", orderMessage.getOrderNumber(), orderMessage);
  }
}
