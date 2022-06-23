package dev.intermediatebox.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.intermediatebox.entity.CarLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

//@Service
public class CarLocationConsumer {
  private static final Logger log = LoggerFactory.getLogger(CarLocationConsumer.class);

  @Autowired
  private ObjectMapper objectMapper;

  @KafkaListener(topics = "t-location", groupId = "cg-location-all")
  public void consumeAll(String message) throws JsonProcessingException {
    var carLocation = objectMapper.readValue(message, CarLocation.class);
    log.info("Consume all: {}", carLocation);
  }

  @KafkaListener(topics = "t-location", groupId = "cg-location-custom", containerFactory = "customLocationContainerFactory")
  public void consumeCustomDistance(String message) throws JsonProcessingException {
    var carLocation = objectMapper.readValue(message, CarLocation.class);
    log.info("Consume distance > 100: {}", carLocation);
  }
}
