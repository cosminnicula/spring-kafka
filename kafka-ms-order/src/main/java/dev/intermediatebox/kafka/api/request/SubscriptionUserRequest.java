package dev.intermediatebox.kafka.api.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubscriptionUserRequest {
	private String duration;

	private String username;
}
