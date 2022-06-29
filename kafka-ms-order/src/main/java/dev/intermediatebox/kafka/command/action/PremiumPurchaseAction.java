package dev.intermediatebox.kafka.command.action;

import dev.intermediatebox.kafka.api.request.PremiumPurchaseRequest;
import dev.intermediatebox.kafka.broker.message.PremiumPurchaseMessage;
import dev.intermediatebox.kafka.broker.producer.PremiumPurchaseProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PremiumPurchaseAction {
	@Autowired
	private PremiumPurchaseProducer producer;

	public void publishToKafka(PremiumPurchaseRequest request) {
		var message = new PremiumPurchaseMessage();

		message.setUsername(request.getUsername());
		message.setItem(request.getItem());
		message.setPurchaseNumber(request.getPurchaseNumber());

		producer.publish(message);
	}
}
