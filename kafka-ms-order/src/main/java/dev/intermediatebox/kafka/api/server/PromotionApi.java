package dev.intermediatebox.kafka.api.server;

import dev.intermediatebox.kafka.api.request.PromotionRequest;
import dev.intermediatebox.kafka.command.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/promotion")
public class PromotionApi {
  @Autowired
  PromotionService promotionService;

  @PostMapping(value = "")
  public ResponseEntity<String> create(@RequestBody PromotionRequest promotionRequest) {
    promotionService.createPromotion(promotionRequest);

    return ResponseEntity.status(HttpStatus.CREATED).body(promotionRequest.getPromotionCode());
  }
}
