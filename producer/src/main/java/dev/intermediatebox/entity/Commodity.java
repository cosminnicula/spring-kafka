package dev.intermediatebox.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Commodity {
  private String name;
  @Setter(AccessLevel.NONE)
  private double price;
  private String measurement;
  private long timestamp;

  public Commodity(String name, double price, String measurement, long timestamp) {
    super();
    this.name = name;
    this.setPrice(price);
    this.measurement = measurement;
    this.timestamp = timestamp;
  }

  public void setPrice(double price) {
    // round to 2 decimals
    this.price = Math.round(price * 100d) / 100d;
  }
}
