package dev.intermediatebox.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.intermediatebox.entity.Commodity;
import dev.intermediatebox.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

//@Service
public class CommodityProducer {
  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  private KafkaTemplate<String, String> kafkaTemplate;

  public void sendMessage(Commodity commodity) throws JsonProcessingException {
    var json = objectMapper.writeValueAsString(commodity);
    kafkaTemplate.send("t-commodity", commodity.getName(), json);
  }
}
