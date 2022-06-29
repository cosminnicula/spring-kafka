package dev.intermediatebox.kafka.command.service;

import dev.intermediatebox.kafka.api.request.WebLayoutVoteRequest;
import dev.intermediatebox.kafka.command.action.WebLayoutVoteAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WebLayoutVoteService {
	@Autowired
	private WebLayoutVoteAction action;

	public void createLayoutVote(WebLayoutVoteRequest request) {
		action.publishToKafka(request);
	}
}
