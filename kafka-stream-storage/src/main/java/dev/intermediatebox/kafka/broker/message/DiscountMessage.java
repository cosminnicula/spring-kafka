package dev.intermediatebox.kafka.broker.message;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class DiscountMessage {
	private String discountCode;
	private int discountPercentage;
}
