package dev.intermediatebox.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

//@Service
public class GeneralLedgerConsumer {
  private static final Logger log = LoggerFactory.getLogger(GeneralLedgerConsumer.class);

  @KafkaListener(id = "general-ledger-1", topics = "t-general-ledger")
  public void consumeOne(String message) {
    log.info("From consumerOne {}", message);
  }

  @KafkaListener(id = "general-ledger-2", topics = "t-general-ledger")
  public void consumeTwo(String message) {
    log.info("From consumerTwo {}", message);
  }
}
