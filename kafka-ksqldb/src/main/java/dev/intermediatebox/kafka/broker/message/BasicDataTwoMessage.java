package dev.intermediatebox.kafka.broker.message;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BasicDataTwoMessage {
	private long myEpochDay;
	private long myMillisOfDay;
	private long myEpochMillis;
}
