package dev.intermediatebox.kafka.broker.api.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BasicDataFiveRequest {
	private int alphaElementsCount;
	private int betaElementsCount;
}
