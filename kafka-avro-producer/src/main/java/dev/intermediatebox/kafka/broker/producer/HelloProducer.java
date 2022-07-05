package dev.intermediatebox.kafka.broker.producer;

import dev.intermediatebox.avro.data.Hello;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

//@Service
public class HelloProducer {
	@Autowired
	private KafkaTemplate<String, Hello> kafkaTemplate;
	
	public void send(Hello data) {
		kafkaTemplate.send("sc-hello", data);
	}
}
