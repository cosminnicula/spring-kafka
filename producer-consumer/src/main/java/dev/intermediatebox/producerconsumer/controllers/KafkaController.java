package dev.intermediatebox.producerconsumer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/kafka")
public class KafkaController {
  @Autowired
  private KafkaListenerEndpointRegistry registry;

  @Autowired
  private KafkaAdmin admin;

  @GetMapping
  public void get() {
    System.out.println("spring-kafka");
  }
}
