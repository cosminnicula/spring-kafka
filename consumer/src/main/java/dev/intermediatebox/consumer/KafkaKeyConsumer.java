package dev.intermediatebox.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

//@Service
public class KafkaKeyConsumer {
  private static final Logger log = LoggerFactory.getLogger(KafkaKeyConsumer.class);

  // by default, concurrency is 1, which means that only 1 consumer (1 thread) is allocated to the 3 partitions
  // with concurrency="2", we have 2 consumers allocated to 3 partitions, which means 1 consumer will consume messages from 2 partitions, and the other consumer from 1 partition
  // with concurrency="3", we have 1 consumer per partition (3 consumers == 3 threads) => processing 3 messages/s
  // with concurrency="4", 1 consumer will remain idle
  @KafkaListener(topics = "t-multi-partitions", concurrency = "3")
  public void consume(ConsumerRecord<String, String> consumerRecord) throws InterruptedException {
    log.info("Key: {}, Partition: {}, Message: {}", consumerRecord.key(), consumerRecord.partition(), consumerRecord.value());
    TimeUnit.SECONDS.sleep(1);
  }
}
