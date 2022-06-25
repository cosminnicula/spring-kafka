package dev.intermediatebox.kafka.command.service;

import dev.intermediatebox.kafka.api.request.DiscountRequest;
import dev.intermediatebox.kafka.command.action.DiscountAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiscountService {
	@Autowired
	private DiscountAction action;

	public void createDiscount(DiscountRequest request) {
		action.publishToKafka(request);
	}

}
