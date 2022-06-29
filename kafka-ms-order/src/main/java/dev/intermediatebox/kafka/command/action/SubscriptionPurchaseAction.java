package dev.intermediatebox.kafka.command.action;

import dev.intermediatebox.kafka.api.request.SubscriptionPurchaseRequest;
import dev.intermediatebox.kafka.broker.message.SubscriptionPurchaseMessage;
import dev.intermediatebox.kafka.broker.producer.SubscriptionPurchaseProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SubscriptionPurchaseAction {
	@Autowired
	private SubscriptionPurchaseProducer producer;

	public void publishToKafka(SubscriptionPurchaseRequest request) {
		var message = new SubscriptionPurchaseMessage();

		message.setSubscriptionNumber(request.getSubscriptionNumber());
		message.setUsername(request.getUsername());

		producer.publish(message);
	}
}
