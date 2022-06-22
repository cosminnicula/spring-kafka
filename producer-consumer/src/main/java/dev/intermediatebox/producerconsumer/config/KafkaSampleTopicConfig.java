package dev.intermediatebox.producerconsumer.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaSampleTopicConfig {
  @Bean
  public NewTopic sampleTopic() {
    return TopicBuilder.name("sampletopic").build();
  }
}
