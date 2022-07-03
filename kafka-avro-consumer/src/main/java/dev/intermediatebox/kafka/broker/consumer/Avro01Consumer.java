package dev.intermediatebox.kafka.broker.consumer;

import dev.intermediatebox.avro.data.Avro01;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

//@Service
public class Avro01Consumer {
	private static final Logger log = LoggerFactory.getLogger(Avro01Consumer.class);

	@KafkaListener(topics = "sc-avro01")
	public void listen(ConsumerRecord<String, Avro01> record) {
		log.info("{} : {}", record.key(), record.value());
	}
}
