package dev.intermediatebox.kafka.broker.producer;

import dev.intermediatebox.kafka.broker.message.SubscriptionUserMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionUserProducer {
	@Autowired
	private KafkaTemplate<String, SubscriptionUserMessage> kafkaTemplate;

	public void publish(SubscriptionUserMessage message) {
		kafkaTemplate.send("t-commodity-subscription-user", message.getUsername(), message);
	}
}
