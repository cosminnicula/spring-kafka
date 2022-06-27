package dev.intermediatebox.kafka.api.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CustomerPurchaseWebRequest {

	private int purchaseAmount;
	
	private String browser;
	
	private String operatingSystem;
}
