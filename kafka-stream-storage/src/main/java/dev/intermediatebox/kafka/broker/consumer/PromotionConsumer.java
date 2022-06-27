package dev.intermediatebox.kafka.broker.consumer;

import dev.intermediatebox.kafka.broker.message.DiscountMessage;
import dev.intermediatebox.kafka.broker.message.PromotionMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@KafkaListener(topics = "t-commodity-promotion")
public class PromotionConsumer {
  private static final Logger log = LoggerFactory.getLogger(PromotionConsumer.class);

  @KafkaHandler
  public void listenPromotion(PromotionMessage promotionMessage) {
    log.info("Processing promotion {}", promotionMessage);
  }

  @KafkaHandler
  public void listenDiscount(DiscountMessage discountMessage) {
    log.info("Processing discount {}", discountMessage);

  }
}