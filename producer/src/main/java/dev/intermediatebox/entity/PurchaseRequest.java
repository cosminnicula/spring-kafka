package dev.intermediatebox.entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class PurchaseRequest {
  private int id;
  private String prNumber;
  private int amount;
  private String currency;
}
