package dev.intermediatebox.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.intermediatebox.entity.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Image2Producer {
  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  private KafkaTemplate<String, String> kafkaTemplate;

  public void send(Image image, int partition) throws JsonProcessingException {
    var json = objectMapper.writeValueAsString(image);
    kafkaTemplate.send("t-image-2", partition, image.getType(), json);
  }
}
