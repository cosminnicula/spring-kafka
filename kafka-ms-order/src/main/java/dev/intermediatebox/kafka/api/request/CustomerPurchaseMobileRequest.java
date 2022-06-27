package dev.intermediatebox.kafka.api.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CustomerPurchaseMobileRequest {
	@Getter
	@Setter
	@ToString
	public static class Location {
		private double latitude;

		private double longitude;
	}

	private int purchaseAmount;

	private String mobileAppVersion;

	private String operatingSystem;

	private Location location;
}
