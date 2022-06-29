package dev.intermediatebox.kafka.api.server;

import dev.intermediatebox.kafka.api.request.WebLayoutVoteRequest;
import dev.intermediatebox.kafka.command.service.WebLayoutVoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/web/layout/vote")
public class WebLayoutVoteApi {

	@Autowired
	private WebLayoutVoteService service;

	@PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> createPrimary(@RequestBody WebLayoutVoteRequest request) {
		service.createLayoutVote(request);

		return ResponseEntity.status(HttpStatus.CREATED).body("Layout vote created with layout : " + request.getLayout()
				+ ", by username : " + request.getUsername());
	}

}
