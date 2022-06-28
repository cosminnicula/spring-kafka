package dev.intermediatebox.kafka.broker.stream.feedback.rating;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;
import java.util.TreeMap;

@Getter
@Setter
@ToString
public class FeedbackRatingTwoStoreValue {
	private Map<Integer, Long> ratingMap = new TreeMap<>();
}
