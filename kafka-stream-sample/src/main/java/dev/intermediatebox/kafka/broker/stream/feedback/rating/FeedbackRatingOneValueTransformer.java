package dev.intermediatebox.kafka.broker.stream.feedback.rating;

import java.util.Optional;

import dev.intermediatebox.kafka.broker.message.FeedbackMessage;
import dev.intermediatebox.kafka.broker.message.FeedbackRatingOneMessage;
import org.apache.commons.lang3.StringUtils;
import org.apache.kafka.streams.kstream.ValueTransformer;
import org.apache.kafka.streams.processor.ProcessorContext;
import org.apache.kafka.streams.state.KeyValueStore;

public class FeedbackRatingOneValueTransformer implements ValueTransformer<FeedbackMessage, FeedbackRatingOneMessage> {

	private ProcessorContext processorContext;

	private final String stateStoreName;

	private KeyValueStore<String, FeedbackRatingOneStoreValue> ratingStateStore;

	public FeedbackRatingOneValueTransformer(String stateStoreName) {
		if (StringUtils.isEmpty(stateStoreName)) {
			throw new IllegalArgumentException("stateStoreName must not empty");
		}

		this.stateStoreName = stateStoreName;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void init(ProcessorContext context) {
		this.processorContext = context;
		this.ratingStateStore = this.processorContext.getStateStore(stateStoreName);
	}

	@Override
	public FeedbackRatingOneMessage transform(FeedbackMessage value) {
		// if state store does not currently hold any data for current location, create new instance of FeedbackRatingOneStoreValue with default sum = 0 and count = 0
		var storeValue = Optional.ofNullable(ratingStateStore.get(value.getLocation()))
				.orElse(new FeedbackRatingOneStoreValue());

		var newSumRating = storeValue.getSumRating() + value.getRating();
		storeValue.setSumRating(newSumRating);

		var newCountRating = storeValue.getCountRating() + 1;
		storeValue.setCountRating(newCountRating);

		ratingStateStore.put(value.getLocation(), storeValue);

		var branchRating = new FeedbackRatingOneMessage();
		branchRating.setLocation(value.getLocation());

		double averageRating = Math.round((double) newSumRating / newCountRating * 10d) / 10d;
		branchRating.setAverageRating(averageRating);

		return branchRating;
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub

	}

}
