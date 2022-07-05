package dev.intermediatebox.kafka.broker.message;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BasicDataCountryMessage {
	private String countryName;
	private String currencyCode;
	private Integer population;
}
