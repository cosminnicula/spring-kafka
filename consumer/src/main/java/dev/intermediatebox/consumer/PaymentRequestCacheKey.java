package dev.intermediatebox.consumer;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class PaymentRequestCacheKey {
  private String paymentNumber;
  private int amount;
  private String transactionType;
}
