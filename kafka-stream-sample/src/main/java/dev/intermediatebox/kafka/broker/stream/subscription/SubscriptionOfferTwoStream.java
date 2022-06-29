package dev.intermediatebox.kafka.broker.stream.subscription;

import dev.intermediatebox.kafka.broker.message.SubscriptionOfferMessage;
import dev.intermediatebox.kafka.broker.message.SubscriptionPurchaseMessage;
import dev.intermediatebox.kafka.broker.message.SubscriptionUserMessage;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.serializer.JsonSerde;

//@Configuration
public class SubscriptionOfferTwoStream {

	@Bean
	public KStream<String, SubscriptionOfferMessage> kstreamSubscriptionOfferTwo(StreamsBuilder builder) {
		var stringSerde = Serdes.String();
		var purchaseSerde = new JsonSerde<>(SubscriptionPurchaseMessage.class);
		var userSerde = new JsonSerde<>(SubscriptionUserMessage.class);
		var offerSerde = new JsonSerde<>(SubscriptionOfferMessage.class);

		var purchaseStream = builder.stream("t-commodity-subscription-purchase",
				Consumed.with(stringSerde, purchaseSerde));

		var userTable = builder.globalTable("t-commodity-subscription-user", Consumed.with(stringSerde, userSerde));

		var offerStream = purchaseStream.join(userTable, (key, value) -> key, this::joiner);

		offerStream.to("t-commodity-subscription-offer-two", Produced.with(stringSerde, offerSerde));

		return offerStream;
	}

	private SubscriptionOfferMessage joiner(SubscriptionPurchaseMessage purchase, SubscriptionUserMessage user) {
		var result = new SubscriptionOfferMessage();

		result.setUsername(purchase.getUsername());
		result.setSubscriptionNumber(purchase.getSubscriptionNumber());
		result.setDuration(user.getDuration());

		return result;
	}
}
