package dev.intermediatebox.kafka.broker.message;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@ToString
public class BasicDataFourMessage {
	private String[] myStringArray;
	private List<Integer> myIntegerList;
	private Set<Double> myDoubleSet;
}
