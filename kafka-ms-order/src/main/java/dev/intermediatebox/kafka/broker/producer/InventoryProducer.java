package dev.intermediatebox.kafka.broker.producer;

import dev.intermediatebox.kafka.broker.message.InventoryMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class InventoryProducer {
	@Autowired
	private KafkaTemplate<String, InventoryMessage> kafkaTemplate;

	public void publish(InventoryMessage message) {
		kafkaTemplate.send("t-commodity-inventory", message.getItem(), message);
	}
}
