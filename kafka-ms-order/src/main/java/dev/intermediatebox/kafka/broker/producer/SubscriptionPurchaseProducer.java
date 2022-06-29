package dev.intermediatebox.kafka.broker.producer;

import dev.intermediatebox.kafka.broker.message.SubscriptionPurchaseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionPurchaseProducer {
	@Autowired
	private KafkaTemplate<String, SubscriptionPurchaseMessage> kafkaTemplate;

	public void publish(SubscriptionPurchaseMessage message) {
		kafkaTemplate.send("t-commodity-subscription-purchase", message.getUsername(), message);
	}
}
