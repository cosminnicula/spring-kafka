package dev.intermediatebox.entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class PaymentRequest {
  private String paymentNumber;
  private int amount;
  private String currency;
  private String notes;
  private String transactionType;
}
