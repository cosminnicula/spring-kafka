package dev.intermediatebox.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.intermediatebox.entity.Commodity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

//@Service
public class CommodityDashboardConsumer {
  private static final Logger log = LoggerFactory.getLogger(CommodityDashboardConsumer.class);

  @Autowired
  private ObjectMapper objectMapper;

  @KafkaListener(topics = "t-commodity", groupId = "cg-dashboard") // overrides group-id: default-spring-consumer from application.yml
  public void consume(String message) throws JsonProcessingException, InterruptedException {
    var commodity = objectMapper.readValue(message, Commodity.class);

//    // this random delay will not affect the speed of the CommodityNotificationConsumer, as consumer groups are independent
//    var randomDelayMs = ThreadLocalRandom.current().nextLong(500, 2000);
//    TimeUnit.MILLISECONDS.sleep(randomDelayMs);

    log.info("Dashboard consumer is consuming: {}", commodity);
  }
}
