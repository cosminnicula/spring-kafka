package dev.intermediatebox.kafka.broker.message;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@ToString
public class KafkaConnectPersonMessageSnakeCase {
	private int personId;
	private String fullName;
	private String email;
	private List<KafkaConnectAddressMessageSnakeCase> addresses;
}
