package dev.intermediatebox.kafka.broker.consumer;

import dev.intermediatebox.avro.data.EmployeeBackward;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

//@Service
public class EmployeeBackwardConsumer {
  private static final Logger log = LoggerFactory.getLogger(EmployeeBackwardConsumer.class);

  @KafkaListener(topics = "sc-employee-backward")
  public void listen(ConsumerRecord<String, EmployeeBackward> record) {
    log.info("{} : {}", record.key(), record.value());
  }
}
