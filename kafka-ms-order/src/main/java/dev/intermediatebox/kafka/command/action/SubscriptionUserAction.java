package dev.intermediatebox.kafka.command.action;

import dev.intermediatebox.kafka.api.request.SubscriptionUserRequest;
import dev.intermediatebox.kafka.broker.message.SubscriptionUserMessage;
import dev.intermediatebox.kafka.broker.producer.SubscriptionUserProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SubscriptionUserAction {
	@Autowired
	private SubscriptionUserProducer producer;

	public void publishToKafka(SubscriptionUserRequest request) {
		var message = new SubscriptionUserMessage();

		message.setDuration(request.getDuration());
		message.setUsername(request.getUsername());

		producer.publish(message);
	}
}
