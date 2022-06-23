package dev.intermediatebox.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FoodOrder {
  private int amount;
  private String item;
}
