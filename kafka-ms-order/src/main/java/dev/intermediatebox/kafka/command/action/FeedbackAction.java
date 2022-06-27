package dev.intermediatebox.kafka.command.action;

import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dev.intermediatebox.kafka.api.request.FeedbackRequest;
import dev.intermediatebox.kafka.broker.message.FeedbackMessage;
import dev.intermediatebox.kafka.broker.producer.FeedbackProducer;

@Component
public class FeedbackAction {

	@Autowired
	private FeedbackProducer producer;

	public void publishToKafka(FeedbackRequest request) {
		var message = new FeedbackMessage();
		message.setFeedback(request.getFeedback());
		message.setLocation(request.getLocation());
		message.setRating(request.getRating());
		// random date time between last 7 days - now
		message.setFeedbackDateTime(LocalDateTime.now().minusHours(ThreadLocalRandom.current().nextLong(7 * 7)));

		producer.publish(message);
	}

}
