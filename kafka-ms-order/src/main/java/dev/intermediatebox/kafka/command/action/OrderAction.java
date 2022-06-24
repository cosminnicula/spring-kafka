package dev.intermediatebox.kafka.command.action;

import dev.intermediatebox.kafka.api.request.OrderItemRequest;
import dev.intermediatebox.kafka.api.request.OrderRequest;
import dev.intermediatebox.kafka.broker.message.OrderMessage;
import dev.intermediatebox.kafka.broker.producer.OrderProducer;
import dev.intermediatebox.kafka.entity.Order;
import dev.intermediatebox.kafka.entity.OrderItem;
import dev.intermediatebox.kafka.repository.OrderItemRepository;
import dev.intermediatebox.kafka.repository.OrderRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Component
public class OrderAction {
  @Autowired
  private OrderProducer orderProducer;

  @Autowired
  private OrderRepository orderRepository;

  @Autowired
  OrderItemRepository orderItemRepository;

  public Order convertToOrder(OrderRequest request) {
    var order = new Order();

    order.setCreditCardNumber(request.getCreditCardNumber());
    order.setOrderLocation(request.getOrderLocation());
    order.setOrderDateTime(LocalDateTime.now());
    order.setOrderNumber(RandomStringUtils.randomAlphanumeric(8).toUpperCase());

    var items = request.getItems().stream().map(this::convertToOrderItem).collect(Collectors.toList());
    items.forEach(item -> item.setOrder(order));

    order.setItems(items);

    return order;
  }

  public OrderItem convertToOrderItem(OrderItemRequest request) {
    var orderItem = new OrderItem();

    orderItem.setItemName(request.getItemName());
    orderItem.setPrice(request.getPrice());
    orderItem.setQuantity(request.getQuantity());

    return orderItem;
  }

  public void saveToDatabase(Order order) {
    orderRepository.save(order);
    order.getItems().forEach(orderItemRepository::save);
  }

  public void publishToKafka(OrderItem orderItem) {
    var orderMessage = new OrderMessage();

    orderMessage.setItemName(orderItem.getItemName());
    orderMessage.setPrice(orderItem.getPrice());
    orderMessage.setQuantity(orderItem.getQuantity());

    var order = orderItem.getOrder();
    orderMessage.setOrderDateTime(order.getOrderDateTime());
    orderMessage.setOrderLocation(order.getOrderLocation());
    orderMessage.setOrderNumber(order.getOrderNumber());
    orderMessage.setCreditCardNumber(order.getCreditCardNumber());

    orderProducer.publish(orderMessage);
  }
}
