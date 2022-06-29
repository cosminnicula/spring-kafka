package dev.intermediatebox.kafka.broker.producer;

import dev.intermediatebox.kafka.broker.message.PremiumUserMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class PremiumUserProducer {
	@Autowired
	private KafkaTemplate<String, PremiumUserMessage> kafkaTemplate;

	public void publish(PremiumUserMessage message) {
		kafkaTemplate.send("t-commodity-premium-user", message.getUsername(), message);
	}
}
