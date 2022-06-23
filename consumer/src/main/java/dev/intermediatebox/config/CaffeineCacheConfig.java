package dev.intermediatebox.config;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import dev.intermediatebox.consumer.PaymentRequestCacheKey;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class CaffeineCacheConfig {
  @Bean
  public Cache<Integer, Boolean> cachePurchaseRequest() {
    return Caffeine.newBuilder().expireAfterWrite(Duration.ofMinutes(2)).maximumSize(1000).build();
  }

  @Bean
  public Cache<PaymentRequestCacheKey, Boolean> cachePaymentRequest() {
    return Caffeine.newBuilder().expireAfterWrite(Duration.ofMinutes(2)).maximumSize(1000).build();
  }
}
