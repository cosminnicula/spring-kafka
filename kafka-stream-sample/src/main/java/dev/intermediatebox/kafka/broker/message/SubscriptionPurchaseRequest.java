package dev.intermediatebox.kafka.broker.message;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubscriptionPurchaseRequest {
	private String subscriptionNumber;

	private String username;
}
