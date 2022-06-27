package dev.intermediatebox.kafka.broker.stream.customer.preference;

import dev.intermediatebox.kafka.broker.message.CustomerPreferenceAggregateMessage;
import dev.intermediatebox.kafka.broker.message.CustomerPreferenceWishlistMessage;
import org.apache.kafka.streams.kstream.Aggregator;

public class CustomerPreferenceWishlistAggregator
		implements Aggregator<String, CustomerPreferenceWishlistMessage, CustomerPreferenceAggregateMessage> {

	@Override
	public CustomerPreferenceAggregateMessage apply(String key, CustomerPreferenceWishlistMessage value,
			CustomerPreferenceAggregateMessage aggregate) {
		aggregate.putWishlistItem(value.getItemName(), value.getWishlistDatetime());

		return aggregate;
	}
}
