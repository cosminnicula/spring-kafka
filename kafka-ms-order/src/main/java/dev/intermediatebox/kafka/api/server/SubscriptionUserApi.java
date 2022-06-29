package dev.intermediatebox.kafka.api.server;

import dev.intermediatebox.kafka.api.request.SubscriptionUserRequest;
import dev.intermediatebox.kafka.command.service.SubscriptionUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/subscription/user")
public class SubscriptionUserApi {
	@Autowired
	private SubscriptionUserService service;

	@PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> createUser(@RequestBody SubscriptionUserRequest request) {
		service.createUser(request);

		return ResponseEntity.status(HttpStatus.CREATED).body("Subscription user created, username : "
				+ request.getUsername() + ", duration : " + request.getDuration());
	}
}
