package dev.intermediatebox.kafka.broker.message;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CustomerPurchaseWebMessage {

	private String purchaseNumber;

	private int purchaseAmount;

	private String browser;

	private String operatingSystem;
}
