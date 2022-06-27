package dev.intermediatebox.kafka.broker.message;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CustomerPurchaseMobileMessage {
	@Getter
	@Setter
	@AllArgsConstructor
	public static class Location {
		private double latitude;

		private double longitude;
	}

	private String purchaseNumber;

	private int purchaseAmount;

	private String mobileAppVersion;

	private String operatingSystem;

	private Location location;
}
