package dev.intermediatebox.kafka.command.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.intermediatebox.kafka.api.request.FeedbackRequest;
import dev.intermediatebox.kafka.command.action.FeedbackAction;

@Service
public class FeedbackService {

	@Autowired
	private FeedbackAction action;

	public void createFeedback(FeedbackRequest request) {
		action.publishToKafka(request);
	}

}
