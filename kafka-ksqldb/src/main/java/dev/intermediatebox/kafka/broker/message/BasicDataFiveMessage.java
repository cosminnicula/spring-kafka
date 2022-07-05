package dev.intermediatebox.kafka.broker.message;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;
import java.util.UUID;

@Getter
@Setter
@ToString
public class BasicDataFiveMessage {
	private Map<Integer, String> myMapAlpha;
	private Map<UUID, String> myMapBeta;
}
