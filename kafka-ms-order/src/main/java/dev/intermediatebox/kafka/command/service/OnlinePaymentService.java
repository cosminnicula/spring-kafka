package dev.intermediatebox.kafka.command.service;

import dev.intermediatebox.kafka.api.request.OnlinePaymentRequest;
import dev.intermediatebox.kafka.command.action.OnlinePaymentAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OnlinePaymentService {
	@Autowired
	private OnlinePaymentAction action;

	public void pay(OnlinePaymentRequest request) {
		action.publishPaymentToKafka(request);
	}
}
