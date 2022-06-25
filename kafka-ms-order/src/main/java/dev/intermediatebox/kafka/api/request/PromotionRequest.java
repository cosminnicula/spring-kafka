package dev.intermediatebox.kafka.api.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PromotionRequest {
  private String promotionCode;
}
