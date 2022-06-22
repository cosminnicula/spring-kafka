package dev.intermediatebox.producerconsumer.listeners;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class SampleTopicListener {
  @KafkaListener(id = "sampletopiclistener", topics = "sampletopic", groupId = "group1")
  public void listen(String data) {
    System.out.println("Message: " + data);
  }
}
