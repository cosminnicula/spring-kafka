package dev.intermediatebox.kafka.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JsonConfig {
  @Bean
  public ObjectMapper objectMapper() {
    var om = new ObjectMapper();
    om.findAndRegisterModules();
    return om;
  }
}