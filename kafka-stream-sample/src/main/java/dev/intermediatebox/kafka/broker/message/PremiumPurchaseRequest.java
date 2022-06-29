package dev.intermediatebox.kafka.broker.message;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class PremiumPurchaseRequest {
	private String item;

	private String purchaseNumber;

	private String username;
}
