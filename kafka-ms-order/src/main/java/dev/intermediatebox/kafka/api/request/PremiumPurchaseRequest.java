package dev.intermediatebox.kafka.api.request;

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
