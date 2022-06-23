package dev.intermediatebox.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.intermediatebox.entity.Image;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ImageConsumer {
  private static final Logger log = LoggerFactory.getLogger(ImageConsumer.class);

  @Autowired
  private ObjectMapper objectMapper;

  @KafkaListener(topics = "t-image", containerFactory = "imageRetryContainerFactory", concurrency = "2")
  public void consume(ConsumerRecord<String, String> consumerRecord) throws JsonProcessingException {
    var image = objectMapper.readValue(consumerRecord.value(), Image.class);

    if (image.getType().equals("svg")) {
      log.warn("Image exception on partition {} for image {}", consumerRecord.partition(), image);
      throw new IllegalArgumentException("Simulate failed API call");
    }

    log.info("Processing on partition {} for image {}", consumerRecord.partition(), image);
  }
}
