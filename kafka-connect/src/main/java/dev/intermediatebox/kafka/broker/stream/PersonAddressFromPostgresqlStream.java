package dev.intermediatebox.kafka.broker.stream;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PersonAddressFromPostgresqlStream {

	@Bean
	public KStream<String, String> kstreamPersonAddressFromPostgresql(StreamsBuilder builder) {
		var sourceStream = builder.stream("t-person-address-postgresql",
				Consumed.with(Serdes.String(), Serdes.String()));

		sourceStream.to("t-person-address-target", Produced.with(Serdes.String(), Serdes.String()));
		
		return sourceStream;
	}

}
