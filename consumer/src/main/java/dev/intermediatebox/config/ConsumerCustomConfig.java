package dev.intermediatebox.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.intermediatebox.entity.CarLocation;
import dev.intermediatebox.error.handler.GlobalErrorHandler;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.TopicPartition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.ConcurrentKafkaListenerContainerFactoryConfigurer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.util.backoff.FixedBackOff;

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

  // filtering
  @Bean(name = "customLocationContainerFactory")
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

  @Bean(name = "kafkaListenerContainerFactory")
  public ConcurrentKafkaListenerContainerFactory<Object, Object> kafkaListenerContainerFactory(
      ConcurrentKafkaListenerContainerFactoryConfigurer configurer
  ) {
    var factory = new ConcurrentKafkaListenerContainerFactory<>();
    configurer.configure(factory, consumerFactory());

    factory.setErrorHandler(new GlobalErrorHandler());

    return factory;
  }

  @Bean(name = "imageRetryContainerFactory")
  public ConcurrentKafkaListenerContainerFactory<Object, Object> imageRetryContainerFactory(
      ConcurrentKafkaListenerContainerFactoryConfigurer configurer
  ) {
    var factory = new ConcurrentKafkaListenerContainerFactory<>();
    configurer.configure(factory, consumerFactory());

    factory.setCommonErrorHandler(new DefaultErrorHandler(new FixedBackOff(10_000, 3)));

    return factory;
  }

  @Bean(name = "invoiceDeadLetterContainerFactory")
  public ConcurrentKafkaListenerContainerFactory<Object, Object> invoiceDeadLetterContainerFactory(
      ConcurrentKafkaListenerContainerFactoryConfigurer configurer,
      KafkaTemplate<String, String> kafkaTemplate
  ) {
    var factory = new ConcurrentKafkaListenerContainerFactory<>();
    configurer.configure(factory, consumerFactory());

    var recoverer = new DeadLetterPublishingRecoverer(
        kafkaTemplate, ((consumerRecord, e) -> new TopicPartition("t-invoice-dead-letter", consumerRecord.partition()))
    );

    factory.setCommonErrorHandler(new DefaultErrorHandler(recoverer, new FixedBackOff(3000, 5)));

    return factory;
  }
}
