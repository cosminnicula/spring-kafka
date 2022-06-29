package dev.intermediatebox.kafka.broker.message;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PremiumOfferMessage {
	private String username;
	private String level;
	private String purchaseNumber;
}
