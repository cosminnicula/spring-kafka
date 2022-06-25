package dev.intermediatebox.kafka.command.service;

import dev.intermediatebox.kafka.api.request.PromotionRequest;
import dev.intermediatebox.kafka.command.action.PromotionAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PromotionService {
  @Autowired
  PromotionAction promotionAction;

  public void createPromotion(PromotionRequest request) {
    promotionAction.publishToKafka(request);
  }
}
