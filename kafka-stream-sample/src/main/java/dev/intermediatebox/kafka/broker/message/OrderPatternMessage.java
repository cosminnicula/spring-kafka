package dev.intermediatebox.kafka.broker.message;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class OrderPatternMessage {
  private String itemName;

  private long totalItemAmount;

  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
  private LocalDateTime orderDateTime;

  private String orderLocation;

  private String orderNumber;
}
