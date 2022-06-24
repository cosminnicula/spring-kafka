package dev.intermediatebox.kafka.broker.producer;

import dev.intermediatebox.kafka.broker.message.OrderMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
public class OrderProducer {
  private static final Logger log = LoggerFactory.getLogger(OrderProducer.class);

  @Autowired
  private KafkaTemplate<String, OrderMessage> kafkaTemplate;

  public void publish(OrderMessage orderMessage) {
    kafkaTemplate.send("t-commodity-order", orderMessage.getOrderNumber(), orderMessage)
        .addCallback(new ListenableFutureCallback<SendResult<String, OrderMessage>>() {
          @Override
          public void onSuccess(SendResult<String, OrderMessage> result) {
            log.info("Order {}, item {} published successfully", orderMessage.getOrderNumber(), orderMessage.getItemName());
          }

          @Override
          public void onFailure(Throwable ex) {
            log.warn("Order {}, item {} failed to publish {}", orderMessage.getOrderNumber(), orderMessage.getItemName(),
                ex.getMessage());
          }
        });
    log.info("Dummy log for order {}, item {}", orderMessage.getOrderNumber(), orderMessage.getItemName());
  }
}
