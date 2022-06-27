package dev.intermediatebox.kafka.broker.message;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@ToString
public class CustomerPreferenceAggregateMessage {

	private Map<String, String> wishlistItems;

	private Map<String, String> shoppingCartItems;

	public CustomerPreferenceAggregateMessage() {
		this.wishlistItems = new HashMap<>();
		this.shoppingCartItems = new HashMap<>();
	}

	public void putShoppingCartItem(String itemName, LocalDateTime lastDateTime) {
		this.shoppingCartItems.put(itemName, DateTimeFormatter.ISO_DATE_TIME.format(lastDateTime));
	}

	public void putWishlistItem(String itemName, LocalDateTime lastDateTime) {
		this.wishlistItems.put(itemName, DateTimeFormatter.ISO_DATE_TIME.format(lastDateTime));
	}
}
