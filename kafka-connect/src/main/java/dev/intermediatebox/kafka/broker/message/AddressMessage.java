package dev.intermediatebox.kafka.broker.message;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AddressMessage {
	private int addressId;
	private String address;
	private String city;
	private String postalCode;
}
