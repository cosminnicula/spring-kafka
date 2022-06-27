package dev.intermediatebox.kafka.api.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CustomerPreferenceWishlistRequest {

	private String customerId;
	private String itemName;
}
