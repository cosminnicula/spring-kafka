package dev.intermediatebox.kafka.broker.stream.premium;

import java.util.List;

import dev.intermediatebox.kafka.broker.message.PremiumOfferMessage;
import dev.intermediatebox.kafka.broker.message.PremiumPurchaseMessage;
import dev.intermediatebox.kafka.broker.message.PremiumUserMessage;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.Joined;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.serializer.JsonSerde;

//@Configuration
public class PremiumOfferOneStream {
	@Bean
	public KStream<String, PremiumOfferMessage> kstreamPremiumOffer(StreamsBuilder builder) {
		var stringSerde = Serdes.String();
		var purchaseSerde = new JsonSerde<>(PremiumPurchaseMessage.class);
		var userSerde = new JsonSerde<>(PremiumUserMessage.class);
		var offerSerde = new JsonSerde<>(PremiumOfferMessage.class);

		var purchaseStream = builder.stream("t-commodity-premium-purchase", Consumed.with(stringSerde, purchaseSerde))
				.selectKey((k, v) -> v.getUsername());

		var filterLevel = List.of("gold", "diamond");

		var userTable = builder.table("t-commodity-premium-user", Consumed.with(stringSerde, userSerde))
				.filter((k, v) -> filterLevel.contains(v.getLevel().toLowerCase()));

		var offerStream = purchaseStream.join(userTable, this::joiner,
				Joined.with(stringSerde, purchaseSerde, userSerde));

		offerStream.to("t-commodity-premium-offer-one", Produced.with(stringSerde, offerSerde));
		
		return offerStream;
	}

	private PremiumOfferMessage joiner(PremiumPurchaseMessage purchase, PremiumUserMessage user) {
		var result = new PremiumOfferMessage();

		result.setUsername(purchase.getUsername());
		result.setPurchaseNumber(purchase.getPurchaseNumber());
		result.setLevel(user.getLevel());

		return result;
	}
}
