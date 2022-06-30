package dev.intermediatebox.kafka.broker.message;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PremiumUserMessage {

	private String level;

	private String username;
}
