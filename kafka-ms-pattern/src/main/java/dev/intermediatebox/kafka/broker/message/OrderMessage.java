package dev.intermediatebox.kafka.broker.message;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class OrderMessage {
  private String orderLocation;
  private String orderNumber;
  private String creditCardNumber;
  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
  private LocalDateTime orderDateTime;

  private String itemName;
  private int price;
  private int quantity;
}
