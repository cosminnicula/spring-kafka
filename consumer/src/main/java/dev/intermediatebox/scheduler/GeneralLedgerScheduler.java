package dev.intermediatebox.scheduler;

import dev.intermediatebox.consumer.GeneralLedgerConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

//@Service
public class GeneralLedgerScheduler {
  @Autowired
  private KafkaListenerEndpointRegistry registry;

  private static final Logger log = LoggerFactory.getLogger(GeneralLedgerScheduler.class);

  @Scheduled(cron = "35 20 * * * ?")
  public void stop() {
    log.info("Stopping GeneralLedger consumer");
    registry.getListenerContainer("general-ledger-1").pause(); // see GeneralLedgerConsumer -> consumeOne's id
  }

  @Scheduled(cron = "37 20 * * * ?")
  @Scheduled
  public void start() {
    log.info("Starting GeneralLedger consumer");
    registry.getListenerContainer("general-ledger-1").resume();
  }
}
