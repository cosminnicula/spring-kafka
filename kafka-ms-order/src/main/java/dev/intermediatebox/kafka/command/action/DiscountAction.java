package dev.intermediatebox.kafka.command.action;

import dev.intermediatebox.kafka.api.request.DiscountRequest;
import dev.intermediatebox.kafka.broker.message.DiscountMessage;
import dev.intermediatebox.kafka.broker.producer.DiscountProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DiscountAction {

	@Autowired
	private DiscountProducer producer;

	public void publishToKafka(DiscountRequest request) {
		var message = new DiscountMessage(request.getDiscountCode(), request.getDiscountPercentage());
		producer.publish(message);
	}

}
