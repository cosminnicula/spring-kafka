package dev.intermediatebox.kafka.broker.producer;

import dev.intermediatebox.kafka.broker.message.OrderMessage;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.Header;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.ArrayList;

@Service
public class OrderProducer {
  private static final Logger log = LoggerFactory.getLogger(OrderProducer.class);

  @Autowired
  private KafkaTemplate<String, OrderMessage> kafkaTemplate;

  public void publish(OrderMessage orderMessage) {
    var producerRecord = buildProducerRecord(orderMessage);

    kafkaTemplate.send(producerRecord)
        .addCallback(new ListenableFutureCallback<SendResult<String, OrderMessage>>() {
          @Override
          public void onSuccess(SendResult<String, OrderMessage> result) {
            log.info("Order {}, item {} published successfully", orderMessage.getOrderNumber(), orderMessage.getItemName());
          }

          @Override
          public void onFailure(Throwable ex) {
            log.warn("Order {}, item {} failed to publish {}", orderMessage.getOrderNumber(), orderMessage.getItemName(),
                ex.getMessage());
          }
        });
    log.info("Dummy log for order {}, item {}", orderMessage.getOrderNumber(), orderMessage.getItemName());
  }

  private ProducerRecord<String, OrderMessage> buildProducerRecord(OrderMessage orderMessage) {
    // if branch location starts with A, the bonus is 25%, otherwise is 15%
    var surpriseBonus = StringUtils.startsWithIgnoreCase(orderMessage.getOrderLocation(), "A") ? 25 : 15;

    var headers = new ArrayList<Header>();
    var surpriseBonusHeader = new RecordHeader("surpriseBonus", Integer.toString(surpriseBonus).getBytes());
    headers.add(surpriseBonusHeader);

    return new ProducerRecord<String, OrderMessage>("t-commodity-order",null, orderMessage.getOrderNumber(), orderMessage, headers);
  }
}
