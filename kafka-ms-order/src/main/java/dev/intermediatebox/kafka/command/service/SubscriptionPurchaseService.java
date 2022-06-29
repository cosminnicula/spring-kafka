package dev.intermediatebox.kafka.command.service;

import dev.intermediatebox.kafka.api.request.SubscriptionPurchaseRequest;
import dev.intermediatebox.kafka.command.action.SubscriptionPurchaseAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionPurchaseService {
	@Autowired
	private SubscriptionPurchaseAction action;

	public void createPurchase(SubscriptionPurchaseRequest request) {
		action.publishToKafka(request);
	}
}
