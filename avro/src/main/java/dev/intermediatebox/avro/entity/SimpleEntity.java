package dev.intermediatebox.avro.entity;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class SimpleEntity {
	private String simpleString;
	private float simpleFloat;
	private boolean simpleBoolean;
	private LocalDate simpleDate;
	private BigDecimal simpleDecimal;
}
