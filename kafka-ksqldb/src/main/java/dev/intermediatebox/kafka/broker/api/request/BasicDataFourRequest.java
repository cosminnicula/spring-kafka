package dev.intermediatebox.kafka.broker.api.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BasicDataFourRequest {
	private int arrayElementsCount;
	private int listElementsCount;
	private int setElementsCount;
}
