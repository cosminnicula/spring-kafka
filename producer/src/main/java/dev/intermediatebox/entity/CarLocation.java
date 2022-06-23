package dev.intermediatebox.entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CarLocation {
  private String carId;
  private long timestamp;
  private int distance;
}

