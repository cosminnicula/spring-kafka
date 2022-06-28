package dev.intermediatebox.kafka.broker.message;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

@Getter
@Setter
@ToString
public class FeedbackRatingTwoMessage {
	private String location;
	private double averageRating;
	private Map<Integer, Long> ratingMap;
}
