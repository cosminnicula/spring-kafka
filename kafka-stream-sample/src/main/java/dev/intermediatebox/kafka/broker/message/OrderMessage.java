package dev.intermediatebox.kafka.broker.message;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrderMessage {
	private String orderLocation;
	private String orderNumber;
	private String creditCardNumber;
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private LocalDateTime orderDateTime;

	private String itemName;
	private int price;
	private int quantity;

	public OrderMessage copy() {
		var copy = new OrderMessage();
		copy.setCreditCardNumber(this.getCreditCardNumber());
		copy.setItemName(this.getItemName());
		copy.setOrderDateTime(this.getOrderDateTime());
		copy.setOrderLocation(this.getOrderLocation());
		copy.setOrderNumber(this.getOrderNumber());
		copy.setQuantity(this.getQuantity());
		copy.setPrice(this.getPrice());
		return copy;
	}
}
