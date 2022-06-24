package dev.intermediatebox.kafka.command.service;

import dev.intermediatebox.kafka.api.request.OrderRequest;
import dev.intermediatebox.kafka.command.action.OrderAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
  @Autowired
  private OrderAction orderAction;
  public String saveOrder(OrderRequest request) {
    var order = orderAction.convertToOrder(request);
    orderAction.saveToDatabase(order);

    // flatten message and publish
    order.getItems().forEach(orderAction::publishToKafka);

    return order.getOrderNumber();
  }
}
