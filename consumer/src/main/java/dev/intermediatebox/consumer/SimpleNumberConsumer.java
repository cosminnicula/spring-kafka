package dev.intermediatebox.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.intermediatebox.entity.PurchaseRequest;
import dev.intermediatebox.entity.SimpleNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class SimpleNumberConsumer {
  private static final Logger log = LoggerFactory.getLogger(SimpleNumberConsumer.class);

  @Autowired
  private ObjectMapper objectMapper;

  @KafkaListener(topics = "t-simple-number")
  public void consume(String message) throws JsonProcessingException {
    var simpleNumber = objectMapper.readValue(message, SimpleNumber.class);

    if (simpleNumber.getNumber() % 2 != 0) {
      throw new IllegalArgumentException("Odd number exception " + simpleNumber.getNumber());
    }

    log.info("Processing simpleNumber {}", simpleNumber);
  }
}
