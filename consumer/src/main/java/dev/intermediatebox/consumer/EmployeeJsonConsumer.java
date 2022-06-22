package dev.intermediatebox.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.intermediatebox.entity.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

//@Service
public class EmployeeJsonConsumer {
  private static final Logger log = LoggerFactory.getLogger(EmployeeJsonConsumer.class);

  @Autowired
  private ObjectMapper objectMapper;

  @KafkaListener(topics = "t-employee")
  public void consume(String message) throws JsonProcessingException {
    var employee = objectMapper.readValue(message, Employee.class);
    log.info("Employee is: {}", employee);
  }
}
