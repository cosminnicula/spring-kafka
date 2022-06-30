package dev.intermediatebox.kafka.broker.message;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PremiumPurchaseMessage {

	private String item;

	private String purchaseNumber;

	private String username;
}
