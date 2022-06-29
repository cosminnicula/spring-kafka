package dev.intermediatebox.kafka.api.server;

import dev.intermediatebox.kafka.api.request.PremiumPurchaseRequest;
import dev.intermediatebox.kafka.command.service.PremiumPurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/premium/purchase")
public class PremiumPurchaseApi {

	@Autowired
	private PremiumPurchaseService service;

	@PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> createUser(@RequestBody PremiumPurchaseRequest request) {
		service.createPurchase(request);

		if (request.getUsername().equalsIgnoreCase("babi")) {
			throw new NullPointerException();
		}

		return ResponseEntity.status(HttpStatus.CREATED).body("Premium purchase created, username : "
				+ request.getUsername() + ", item : " + request.getItem() + " number : " + request.getPurchaseNumber());
	}

}
