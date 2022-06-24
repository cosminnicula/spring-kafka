package dev.intermediatebox.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.intermediatebox.entity.Invoice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

//@Service
public class InvoiceConsumer {
  private static final Logger log = LoggerFactory.getLogger(InvoiceConsumer.class);

  @Autowired
  private ObjectMapper objectMapper;

  @KafkaListener(topics = "t-invoice", concurrency = "2", containerFactory = "invoiceDeadLetterContainerFactory")
  public void consume(String message) throws JsonProcessingException {
    var invoice = objectMapper.readValue(message, Invoice.class);

    if (invoice.getAmount() == 0) {
      throw new IllegalArgumentException("Invoice amount cannot be 0" + invoice);
    }
  }
}
