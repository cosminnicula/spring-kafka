package dev.intermediatebox.kafka.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
@ToString
public class Order {
  @Id
  @GeneratedValue
  private int orderId;

  @Column
  private String orderNumber;

  @Column
  private String orderLocation;

  @Column
  private LocalDateTime orderDateTime;

  @Column
  private String creditCardNumber;

  @OneToMany(mappedBy = "order")
  private List<OrderItem> items;
}
