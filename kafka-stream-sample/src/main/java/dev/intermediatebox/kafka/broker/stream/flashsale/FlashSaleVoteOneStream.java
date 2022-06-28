package dev.intermediatebox.kafka.broker.stream.flashsale;

import dev.intermediatebox.kafka.broker.message.FlashSaleVoteMessage;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.serializer.JsonSerde;

//@Configuration
public class FlashSaleVoteOneStream {

	@Bean
	public KStream<String, String> flashSaleVote(StreamsBuilder builder) {
		var stringSerde = Serdes.String();
		var flashSaleVoteSerde = new JsonSerde<>(FlashSaleVoteMessage.class);

		var flashSaleVoteStream = builder
				.stream("t-commodity-flashsale-vote", Consumed.with(stringSerde, flashSaleVoteSerde))
				.map((key, value) -> KeyValue.pair(value.getCustomerId(), value.getItemName()));

		flashSaleVoteStream.to("t-commodity-flashsale-vote-user-item");

		builder.table("t-commodity-flashsale-vote-user-item", Consumed.with(stringSerde, stringSerde))
				.groupBy((customerId, itemName) -> KeyValue.pair(itemName, itemName)).count().toStream()
				.to("t-commodity-flashsale-vote-one-result");
		
		return flashSaleVoteStream;
	}

}
