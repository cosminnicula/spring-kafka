package dev.intermediatebox.kafka.broker.message;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@ToString
public class MarketingSalesMessage {
	private int salesId;
	private int salesAmount;
	private String salesCurrency;

	@JsonProperty("sales_date")
	private int salesEpochDays;

	private String customerEmail;
}
