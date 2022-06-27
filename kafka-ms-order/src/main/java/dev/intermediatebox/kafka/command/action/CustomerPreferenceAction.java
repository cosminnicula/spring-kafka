package dev.intermediatebox.kafka.command.action;

import java.time.LocalDateTime;

import dev.intermediatebox.kafka.api.request.CustomerPreferenceShoppingCartRequest;
import dev.intermediatebox.kafka.api.request.CustomerPreferenceWishlistRequest;
import dev.intermediatebox.kafka.broker.message.CustomerPreferenceShoppingCartMessage;
import dev.intermediatebox.kafka.broker.message.CustomerPreferenceWishlistMessage;
import dev.intermediatebox.kafka.broker.producer.CustomerPreferenceProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerPreferenceAction {

	@Autowired
	private CustomerPreferenceProducer producer;

	public void publishShoppingCart(CustomerPreferenceShoppingCartRequest request) {
		var message = new CustomerPreferenceShoppingCartMessage(request.getCustomerId(), request.getItemName(),
				request.getCartAmount(), LocalDateTime.now());

		producer.publishShoppingCart(message);
	}

	public void publishWishlist(CustomerPreferenceWishlistRequest request) {
		var message = new CustomerPreferenceWishlistMessage(request.getCustomerId(), request.getItemName(),
				LocalDateTime.now());

		producer.publishWishlist(message);
	}

}
