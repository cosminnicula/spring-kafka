package dev.intermediatebox.kafka.broker.message;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class PersonMessage {
	private int personId;
	private String fullName;
	private String email;
	private List<AddressMessage> addresses;
}
