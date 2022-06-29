package dev.intermediatebox.kafka.command.action;

import java.time.LocalDateTime;

import dev.intermediatebox.kafka.api.request.OnlinePaymentRequest;
import dev.intermediatebox.kafka.broker.message.OnlinePaymentMessage;
import dev.intermediatebox.kafka.broker.producer.OnlinePaymentProducer;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OnlinePaymentAction {

	@Autowired
	private OnlinePaymentProducer producer;

	public void publishPaymentToKafka(OnlinePaymentRequest request) {
		var message = new OnlinePaymentMessage();

		message.setOnlineOrderNumber(request.getOnlineOrderNumber());
		message.setPaymentNumber("PAY-" + RandomStringUtils.randomAlphanumeric(6).toUpperCase());
		message.setPaymentDateTime(
				request.getPaymentDateTime() == null ? LocalDateTime.now() : request.getPaymentDateTime());
		message.setPaymentMethod(request.getPaymentMethod());

		producer.publish(message);
	}

}
