package dev.intermediatebox.kafka.api.server;

import dev.intermediatebox.kafka.api.request.FlashSaleVoteRequest;
import dev.intermediatebox.kafka.command.service.FlashSaleVoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/flashsale/vote")
public class FlashSaleVoteApi {
	@Autowired
	private FlashSaleVoteService service;

	@PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> create(@RequestBody FlashSaleVoteRequest request) {
		service.createFlashSaleVote(request);

		return ResponseEntity.status(HttpStatus.CREATED).body("Flash sale vote created for customer : "
				+ request.getCustomerId() + ", item : " + request.getItemName());
	}
}
