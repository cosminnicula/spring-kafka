package dev.intermediatebox.kafka.command.service;

import dev.intermediatebox.kafka.api.request.SubscriptionUserRequest;
import dev.intermediatebox.kafka.command.action.SubscriptionUserAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionUserService {
	@Autowired
	private SubscriptionUserAction action;

	public void createUser(SubscriptionUserRequest request) {
		action.publishToKafka(request);
	}
}
