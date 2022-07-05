package dev.intermediatebox.kafka.broker.api.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BasicDataCountryRequest {
	private String countryName;
	private String currencyCode;
	private int population;
}
