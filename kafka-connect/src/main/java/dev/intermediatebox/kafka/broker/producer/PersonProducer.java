package dev.intermediatebox.kafka.broker.producer;

import dev.intermediatebox.kafka.broker.message.PersonMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class PersonProducer {
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	public void publish(PersonMessage message) throws JsonProcessingException {
		var str = objectMapper.writeValueAsString(message);
		kafkaTemplate.send("t-person-address-custom", str);
	}
}
