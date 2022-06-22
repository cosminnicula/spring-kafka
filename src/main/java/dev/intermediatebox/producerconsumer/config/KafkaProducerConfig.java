package dev.intermediatebox.producerconsumer.config;

import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.ProducerListener;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {
  @Value("${spring.kafka.bootstrap-servers}")
  private String bootstrapServers;

  @Bean
  public ProducerFactory<String, String> producerFactory() {
    DefaultKafkaProducerFactory defaultKafkaProducerFactory = new DefaultKafkaProducerFactory<>(producerProps());
    defaultKafkaProducerFactory.addListener(new ProducerFactory.Listener() {
      @Override
      public void producerAdded(String id, Producer producer) {
        // called when kafkaTemplate.send is called for the first time
        ProducerFactory.Listener.super.producerAdded(id, producer);
      }

      @Override
      public void producerRemoved(String id, Producer producer) {
        ProducerFactory.Listener.super.producerRemoved(id, producer);
      }
    });

    return defaultKafkaProducerFactory;
  }

  private Map<String, Object> producerProps() {
    Map<String, Object> props = new HashMap<>();
    props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
    props.put(ProducerConfig.LINGER_MS_CONFIG, 10);
    props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
    props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
    return props;
  }

  @Bean
  public KafkaTemplate<String, String> kafkaTemplate(ProducerFactory<String, String> producerFactory) {
    KafkaTemplate kafkaTemplate = new KafkaTemplate<String, String>(producerFactory);
    kafkaTemplate.setProducerListener(new ProducerListener() {
      @Override
      public void onSuccess(ProducerRecord producerRecord, RecordMetadata recordMetadata) {
        ProducerListener.super.onSuccess(producerRecord, recordMetadata);
      }

      @Override
      public void onError(ProducerRecord producerRecord, RecordMetadata recordMetadata, Exception exception) {
        ProducerListener.super.onError(producerRecord, recordMetadata, exception);
      }
    });
    return kafkaTemplate;
  }
}
