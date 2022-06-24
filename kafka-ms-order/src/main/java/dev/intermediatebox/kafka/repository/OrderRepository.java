package dev.intermediatebox.kafka.repository;

import dev.intermediatebox.kafka.entity.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository  extends CrudRepository<Order, Integer> {
}
