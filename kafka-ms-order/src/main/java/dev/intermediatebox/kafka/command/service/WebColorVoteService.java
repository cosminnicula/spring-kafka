package dev.intermediatebox.kafka.command.service;

import dev.intermediatebox.kafka.api.request.WebColorVoteRequest;
import dev.intermediatebox.kafka.command.action.WebColorVoteAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WebColorVoteService {
	@Autowired
	private WebColorVoteAction action;

	public void createColorVote(WebColorVoteRequest request) {
		action.publishToKafka(request);
	}
}
