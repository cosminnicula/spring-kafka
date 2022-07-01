package dev.intermediatebox.kafka.broker.message;

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
public class KafkaConnectPersonTargetMessage {
	private int personId;
	private String fullName;
	private String email;
	private int addressId;
	private String address;
	private String city;
	private String postalCode;
}
