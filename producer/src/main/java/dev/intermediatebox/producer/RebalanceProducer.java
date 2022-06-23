package dev.intermediatebox.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

//@Service
public class RebalanceProducer {
  private static final Logger log = LoggerFactory.getLogger(RebalanceProducer.class);
  @Autowired
  private KafkaTemplate<String, String> kafkaTemplate;

  private AtomicInteger counter = new AtomicInteger();

  @Scheduled(fixedRate = 1000)
  public void sendMessage() {
    var i = counter.incrementAndGet();
    log.info("i: " + i);
    kafkaTemplate.send("t-rebalance", "counter: " + i);
  }
}
