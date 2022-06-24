package dev.intermediatebox.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "order_items")
@Getter
@Setter
@ToString
public class OrderItem
{
  @Id
  @GeneratedValue
  private int orderItemId;

  @Column
  private String itemName;

  @Column
  private int price;

  @Column
  private int quantity;

  @JoinColumn(name = "order_id")
  @ManyToOne
  private Order order;
}
