package dev.intermediatebox.kafka.command.action;

import dev.intermediatebox.kafka.api.request.PremiumUserRequest;
import dev.intermediatebox.kafka.broker.message.PremiumUserMessage;
import dev.intermediatebox.kafka.broker.producer.PremiumUserProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PremiumUserAction {

	@Autowired
	private PremiumUserProducer producer;

	public void publishToKafka(PremiumUserRequest request) {
		var message = new PremiumUserMessage();

		message.setUsername(request.getUsername());
		message.setLevel(request.getLevel());

		producer.publish(message);
	}
}
