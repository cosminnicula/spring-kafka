package dev.intermediatebox.kafka.command.service;

import dev.intermediatebox.kafka.api.request.PremiumUserRequest;
import dev.intermediatebox.kafka.command.action.PremiumUserAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PremiumUserService {
	@Autowired
	private PremiumUserAction action;

	public void createUser(PremiumUserRequest request) {
		action.publishToKafka(request);
	}
}
