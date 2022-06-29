package dev.intermediatebox.kafka.command.action;

import dev.intermediatebox.kafka.api.request.OnlineOrderRequest;
import dev.intermediatebox.kafka.broker.message.OnlineOrderMessage;
import dev.intermediatebox.kafka.broker.producer.OnlineOrderProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OnlineOrderAction {

	@Autowired
	private OnlineOrderProducer producer;

	public void publishToKafka(OnlineOrderRequest request) {
		var message = new OnlineOrderMessage();

		message.setOnlineOrderNumber(request.getOnlineOrderNumber());
		message.setOrderDateTime(request.getOrderDateTime());
		message.setTotalAmount(request.getTotalAmount());
		message.setUsername(request.getUsername().toLowerCase());

		producer.publish(message);
	}

}
