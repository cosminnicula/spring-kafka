package dev.intermediatebox.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.intermediatebox.entity.SimpleNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class SimpleNumberProducer {
  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  private KafkaTemplate<String, String> kafkaTemplate;

  public void send(SimpleNumber simpleNumber) throws JsonProcessingException {
    var json = objectMapper.writeValueAsString(simpleNumber);
    kafkaTemplate.send("t-simple-number", json);
  }
}
