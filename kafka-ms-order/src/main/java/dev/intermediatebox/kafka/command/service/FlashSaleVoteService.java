package dev.intermediatebox.kafka.command.service;

import dev.intermediatebox.kafka.api.request.FlashSaleVoteRequest;
import dev.intermediatebox.kafka.command.action.FlashSaleVoteAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FlashSaleVoteService {
	@Autowired
	private FlashSaleVoteAction action;

	public void createFlashSaleVote(FlashSaleVoteRequest request) {
		action.publishToKafka(request);
	}
}
