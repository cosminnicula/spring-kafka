package dev.intermediatebox.kafka.api.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubscriptionPurchaseRequest {
	private String subscriptionNumber;

	private String username;
}
