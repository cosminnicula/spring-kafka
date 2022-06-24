package dev.intermediatebox.kafka.api.response;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {
  private String orderNumber;
}
