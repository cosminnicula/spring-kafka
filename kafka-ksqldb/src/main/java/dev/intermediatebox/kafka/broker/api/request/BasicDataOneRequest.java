package dev.intermediatebox.kafka.broker.api.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
public class BasicDataOneRequest {
	private boolean myBoolean;
	private String myString;
	private String myAnotherString;
	private int myInteger;
	private long myLong;
	private float myFloat;
	private double myDouble;
	private BigDecimal myBigDecimal;
}
