package dev.intermediatebox.kafka.broker.consumer;

import dev.intermediatebox.kafka.broker.message.PromotionMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class PromotionUppercaseConsumer {
  private static final Logger LOG = LoggerFactory.getLogger(PromotionUppercaseConsumer.class);

  @KafkaListener(topics = "t-commodity-promotion-uppercase")
  public void listenPromotionUppercase(PromotionMessage message) {
    LOG.info("Processing uppercase promotion : {}", message);
  }
}
