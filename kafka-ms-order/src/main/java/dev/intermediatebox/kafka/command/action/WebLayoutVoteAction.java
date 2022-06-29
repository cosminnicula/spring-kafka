package dev.intermediatebox.kafka.command.action;

import dev.intermediatebox.kafka.api.request.WebLayoutVoteRequest;
import dev.intermediatebox.kafka.broker.message.WebLayoutVoteMessage;
import dev.intermediatebox.kafka.broker.producer.WebLayoutVoteProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WebLayoutVoteAction {

	@Autowired
	private WebLayoutVoteProducer producer;

	public void publishToKafka(WebLayoutVoteRequest request) {
		var message = new WebLayoutVoteMessage();

		message.setUsername(request.getUsername());
		message.setLayout(request.getLayout());
		message.setVoteDateTime(request.getVoteDateTime());

		producer.publish(message);
	}

}
