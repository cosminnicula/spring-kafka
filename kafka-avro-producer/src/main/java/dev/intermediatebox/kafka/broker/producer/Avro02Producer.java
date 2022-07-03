package dev.intermediatebox.kafka.broker.producer;

import dev.intermediatebox.avro.data.Avro02;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Avro02Producer {
	@Autowired
	private KafkaTemplate<String, Avro02> kafkaTemplate;
	
	public void send(Avro02 data) {
		kafkaTemplate.send("sc-avro02", data);
	}
}
