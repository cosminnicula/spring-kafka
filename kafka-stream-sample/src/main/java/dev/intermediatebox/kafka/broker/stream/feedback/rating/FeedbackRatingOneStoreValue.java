package dev.intermediatebox.kafka.broker.stream.feedback.rating;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FeedbackRatingOneStoreValue {
	private long countRating;
	private long sumRating;
}
