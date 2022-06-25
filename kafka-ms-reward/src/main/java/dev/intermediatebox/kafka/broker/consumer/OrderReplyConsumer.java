package dev.intermediatebox.kafka.broker.consumer;

import dev.intermediatebox.kafka.broker.message.OrderMessage;
import dev.intermediatebox.kafka.broker.message.OrderReplyMessage;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

// Notice the difference from OrderConsumer
// - @SendTo annotation
// - OrderReplyMessage return type, instead of void
@Service
public class OrderReplyConsumer {

  private static final Logger log = LoggerFactory.getLogger(OrderReplyConsumer.class);

  @KafkaListener(topics = "t-commodity-order")
  @SendTo("t-commodity-order-reply")
  public OrderReplyMessage listen(ConsumerRecord<String, OrderMessage> consumerRecord) {
    var headers = consumerRecord.headers();
    var orderMessage = consumerRecord.value();

    log.info("Processing order {}, item {}, credit card number {}", orderMessage.getOrderNumber(),
        orderMessage.getItemName(), orderMessage.getCreditCardNumber());
    log.info("Headers:");
    headers.forEach(header -> {
      log.info("key {}, value {}", header.key(), new String(header.value()));
    });

    var bonusPercentage = Integer.parseInt(
        ObjectUtils.isEmpty(headers.lastHeader("surpriseBonus").value()) ? "0" : new String(headers.lastHeader("surpriseBonus").value())
    );
    var bonusAmount = (bonusPercentage / 100) * orderMessage.getPrice() * orderMessage.getQuantity();

    log.info("Bonus amount is {}", bonusAmount);

    var replyMessage = new OrderReplyMessage();
    replyMessage.setReplyMessage("Order " + orderMessage.getOrderNumber() + ", item " + orderMessage.getItemName() + " processed");
    return replyMessage;
  }
}
