package dev.intermediatebox.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.benmanes.caffeine.cache.Cache;
import dev.intermediatebox.entity.PaymentRequest;
import dev.intermediatebox.entity.PurchaseRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Optional;

//@Service
public class PaymentRequestConsumer {
  private static final Logger log = LoggerFactory.getLogger(PaymentRequestConsumer.class);

  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  Cache<PaymentRequestCacheKey, Boolean> cache;

  private boolean isExistsInCache(PaymentRequestCacheKey key) {
    return Optional.ofNullable(cache.getIfPresent(key)).orElse(false);
  }

  @KafkaListener(topics = "t-payment-request")
  public void consume(String message) throws JsonProcessingException {
    var paymentRequest = objectMapper.readValue(message, PaymentRequest.class);

    var cacheKey = new PaymentRequestCacheKey(paymentRequest.getPaymentNumber(),
        paymentRequest.getAmount(), paymentRequest.getTransactionType());

    if (isExistsInCache(cacheKey)) {
      log.info("Skipping processing cached {}", paymentRequest);
      return;
    }

    log.info("Processing {}", paymentRequest);

    cache.put(cacheKey, true);
  }
}
