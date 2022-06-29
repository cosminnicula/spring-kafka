package dev.intermediatebox.kafka.command.service;

import dev.intermediatebox.kafka.api.request.OnlineOrderRequest;
import dev.intermediatebox.kafka.command.action.OnlineOrderAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OnlineOrderService {

	@Autowired
	private OnlineOrderAction action;

	public void saveOnlineOrder(OnlineOrderRequest request) {
		action.publishToKafka(request);
	}
}
