package dev.intermediatebox.kafka.broker.message;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PromotionMessage {
  private String promotionCode;
}
