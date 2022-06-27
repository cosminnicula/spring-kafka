package dev.intermediatebox.kafka.broker.stream.customer.preference;

import dev.intermediatebox.kafka.broker.message.CustomerPreferenceAggregateMessage;
import dev.intermediatebox.kafka.broker.message.CustomerPreferenceShoppingCartMessage;
import org.apache.kafka.streams.kstream.Aggregator;


public class CustomerPreferenceShoppingCartAggregator
		implements Aggregator<String, CustomerPreferenceShoppingCartMessage, CustomerPreferenceAggregateMessage> {

	@Override
	public CustomerPreferenceAggregateMessage apply(String key, CustomerPreferenceShoppingCartMessage value,
																									CustomerPreferenceAggregateMessage aggregate) {
		aggregate.putShoppingCartItem(value.getItemName(), value.getCartDatetime());

		return aggregate;
	}

}
