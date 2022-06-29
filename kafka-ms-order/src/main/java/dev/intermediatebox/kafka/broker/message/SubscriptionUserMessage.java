package dev.intermediatebox.kafka.broker.message;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubscriptionUserMessage {
	private String duration;

	private String username;
}
