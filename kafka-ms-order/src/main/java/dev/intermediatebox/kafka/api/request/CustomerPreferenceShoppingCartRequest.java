package dev.intermediatebox.kafka.api.request;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CustomerPreferenceShoppingCartRequest {
	private String customerId;
	private String itemName;
	private int cartAmount;
}
