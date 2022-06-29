package dev.intermediatebox.kafka.broker.message;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PremiumPurchaseMessage {
	private String item;

	private String purchaseNumber;

	private String username;
}
