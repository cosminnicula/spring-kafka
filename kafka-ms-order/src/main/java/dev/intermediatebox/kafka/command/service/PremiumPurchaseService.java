package dev.intermediatebox.kafka.command.service;

import dev.intermediatebox.kafka.api.request.PremiumPurchaseRequest;
import dev.intermediatebox.kafka.command.action.PremiumPurchaseAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PremiumPurchaseService {

	@Autowired
	private PremiumPurchaseAction action;

	public void createPurchase(PremiumPurchaseRequest request) {
		action.publishToKafka(request);
	}
}
