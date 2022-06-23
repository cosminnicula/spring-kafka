package dev.intermediatebox.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.intermediatebox.entity.CarLocation;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.ConcurrentKafkaListenerContainerFactoryConfigurer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.adapter.RecordFilterStrategy;

@Configuration
public class ConsumerCustomConfig {
  @Autowired
  private KafkaProperties kafkaProperties;

  @Autowired
  private ObjectMapper objectMapper;

  @Bean
  ConsumerFactory<Object, Object> consumerFactory() {
    var properties = kafkaProperties.buildConsumerProperties();
    properties.put(ConsumerConfig.METADATA_MAX_AGE_CONFIG, "120000"); // 2 minutes
    return new DefaultKafkaConsumerFactory<>(properties);
  }

  @Bean(name="customLocationContainerFactory")
  public ConcurrentKafkaListenerContainerFactory<Object, Object> customLocationContainerFactory(
      ConcurrentKafkaListenerContainerFactoryConfigurer configurer
  ) {
    var factory = new ConcurrentKafkaListenerContainerFactory<>();
    configurer.configure(factory, consumerFactory());

    factory.setRecordFilterStrategy(consumerRecord -> {
      try {
        var carLocation = objectMapper.readValue(consumerRecord.value().toString(), CarLocation.class);
        return carLocation.getDistance() < 100; // discard on true
      } catch (JsonProcessingException e) {
        return false;
      }
    });

    return factory;
  }

}
