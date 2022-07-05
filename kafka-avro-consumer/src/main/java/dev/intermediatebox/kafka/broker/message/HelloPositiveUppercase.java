package dev.intermediatebox.kafka.broker.message;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class HelloPositiveUppercase {
	private int positiveInt;
	
	private String uppercaseString;
}
