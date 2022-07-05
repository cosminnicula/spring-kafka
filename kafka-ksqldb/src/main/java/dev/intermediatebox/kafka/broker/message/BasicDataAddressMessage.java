package dev.intermediatebox.kafka.broker.message;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BasicDataAddressMessage {
	private String streetAddress;
	private String country;
	private BasicDataLocationMessage location;
}
