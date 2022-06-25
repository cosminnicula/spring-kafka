package dev.intermediatebox.kafka.broker.producer;

import dev.intermediatebox.kafka.broker.message.PromotionMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class PromotionProducer {
  private static final Logger log = LoggerFactory.getLogger(PromotionProducer.class);

  @Autowired
  private KafkaTemplate<String, PromotionMessage> kafkaTemplate;

  public void publish(PromotionMessage promotionMessage) {
    // synchronous (not recommended)
    try {
      var sendResult = kafkaTemplate.send("t-commodity-promotion", promotionMessage).get();
      log.info("Successfully published {}", sendResult.getProducerRecord().value());
    } catch (InterruptedException | ExecutionException e) {
      log.error("Error publishing {}, {}", promotionMessage, e.getMessage());
    }
  }
}
