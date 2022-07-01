package dev.intermediatebox.kafka.broker.message;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import dev.intermediatebox.kafka.broker.schema.KafkaConnectSchema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@ToString
public class KafkaConnectMessage<T> {

	private KafkaConnectSchema schema;
	private T payload;
}
