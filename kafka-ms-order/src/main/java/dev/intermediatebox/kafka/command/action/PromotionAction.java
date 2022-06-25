package dev.intermediatebox.kafka.command.action;

import dev.intermediatebox.kafka.api.request.PromotionRequest;
import dev.intermediatebox.kafka.broker.message.PromotionMessage;
import dev.intermediatebox.kafka.broker.producer.PromotionProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PromotionAction {
  @Autowired
  private PromotionProducer promotionProducer;

  public void publishToKafka(PromotionRequest request) {
    var message = new PromotionMessage(request.getPromotionCode());
    promotionProducer.publish(message);
  }
}
