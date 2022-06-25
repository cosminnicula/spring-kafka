package dev.intermediatebox.kafka.api.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DiscountRequest {
	private String discountCode;
	private int discountPercentage;
}
