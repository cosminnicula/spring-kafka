package dev.intermediatebox.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.intermediatebox.entity.Commodity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

//@Service
public class CommodityNotificationConsumer {
  private static final Logger log = LoggerFactory.getLogger(CommodityNotificationConsumer.class);

  @Autowired
  private ObjectMapper objectMapper;

  @KafkaListener(topics = "t-commodity", groupId = "cg-notification") // overrides group-id: default-spring-consumer from application.yml
  public void consume(String message) throws JsonProcessingException {
    var commodity = objectMapper.readValue(message, Commodity.class);
    log.info("Notification consumer is consuming: {}", commodity);
  }
}
