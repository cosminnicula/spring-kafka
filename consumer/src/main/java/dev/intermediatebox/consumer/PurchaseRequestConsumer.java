package dev.intermediatebox.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.benmanes.caffeine.cache.Cache;
import dev.intermediatebox.entity.PurchaseRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Optional;

//@Service
public class PurchaseRequestConsumer {
  private static final Logger log = LoggerFactory.getLogger(PurchaseRequestConsumer.class);

  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  Cache<Integer, Boolean> cache;

  private boolean isExistsInCache(int purchaseRequestId) {
    return Optional.ofNullable(cache.getIfPresent(purchaseRequestId)).orElse(false);
  }

  @KafkaListener(topics = "t-purchase-request")
  public void consume(String message) throws JsonProcessingException {
    var purchaseRequest = objectMapper.readValue(message, PurchaseRequest.class);

    if (isExistsInCache(purchaseRequest.getId())) {
      log.info("Skipping processing cached {}", purchaseRequest);
      return;
    }

    log.info("Processing {}", purchaseRequest);

    cache.put(purchaseRequest.getId(), true);
  }
}
