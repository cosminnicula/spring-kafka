package dev.intermediatebox.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.intermediatebox.entity.FoodOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

//@Service
public class FoodOrderConsumer {
  private static final Logger log = LoggerFactory.getLogger(FoodOrderConsumer.class);

  private static final int MAX_ORDER_AMOUNT = 4;

  @Autowired
  private ObjectMapper objectMapper;

  @KafkaListener(topics = "t-food-order", errorHandler = "foodOrderErrorHandler")
  public void consume(String message) throws JsonProcessingException {
    var foodOrder = objectMapper.readValue(message, FoodOrder.class);

    if (foodOrder.getAmount() > MAX_ORDER_AMOUNT) {
      throw new IllegalArgumentException("Order amount should be < " + MAX_ORDER_AMOUNT);
    }

    log.info("Processing food order: {}", foodOrder);
  }
}
