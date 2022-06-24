package dev.intermediatebox.kafka.api.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class OrderRequest {
  private String orderLocation;
  private String creditCardNumber;
  private List<OrderItemRequest> items;
}
