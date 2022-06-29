package dev.intermediatebox.kafka.api.server;

import dev.intermediatebox.kafka.api.request.OnlineOrderRequest;
import dev.intermediatebox.kafka.command.service.OnlineOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/order/online")
public class OnlineOrderApi {

	@Autowired
	private OnlineOrderService service;

	@PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> createOnlineOrder(@RequestBody OnlineOrderRequest request) {
		service.saveOnlineOrder(request);

		return ResponseEntity.ok().body("Saved online order " + request.getOnlineOrderNumber());
	}

}
