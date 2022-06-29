package dev.intermediatebox.kafka.broker.message;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SubscriptionOfferMessage {

	private String username;
	private String duration;
	private String subscriptionNumber;
}
