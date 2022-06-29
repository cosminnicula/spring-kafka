package dev.intermediatebox.kafka.command.action;

import dev.intermediatebox.kafka.api.request.WebColorVoteRequest;
import dev.intermediatebox.kafka.broker.message.WebColorVoteMessage;
import dev.intermediatebox.kafka.broker.producer.WebColorVoteProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WebColorVoteAction {
	@Autowired
	private WebColorVoteProducer producer;

	public void publishToKafka(WebColorVoteRequest request) {
		var message = new WebColorVoteMessage();

		message.setUsername(request.getUsername());
		message.setColor(request.getColor());
		message.setVoteDateTime(request.getVoteDateTime());

		producer.publish(message);
	}
}
