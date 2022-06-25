package dev.intermediatebox.kafka.broker.consumer;

import dev.intermediatebox.kafka.broker.message.OrderMessage;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
public class OrderConsumer {

  private static final Logger log = LoggerFactory.getLogger(OrderConsumer.class);

  @KafkaListener(topics = "t-commodity-order")
  public void listen(ConsumerRecord<String, OrderMessage> consumerRecord) {
    var headers = consumerRecord.headers();
    var orderMessage = consumerRecord.value();

    log.info("Processing order {}, item {}, credit card number {}", orderMessage.getOrderNumber(),
        orderMessage.getItemName(), orderMessage.getCreditCardNumber());
    log.info("Headers:");
    headers.forEach(header -> {
      log.info("key {}, value {}", header.key(), header.value().toString());
    });

    var bonusPercentage = Integer.parseInt(
        ObjectUtils.isEmpty(headers.lastHeader("surpriseBonus").value()) ? "0" : new String(headers.lastHeader("surpriseBonus").value())
    );
    var bonusAmount = (bonusPercentage / 100) * orderMessage.getPrice() * orderMessage.getQuantity();

    log.info("Bonus amount is {}", bonusAmount);
  }
}
