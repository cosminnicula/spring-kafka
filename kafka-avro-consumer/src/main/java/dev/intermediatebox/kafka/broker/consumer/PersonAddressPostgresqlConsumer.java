package dev.intermediatebox.kafka.broker.consumer;

import dev.intermediatebox.avro.data.PersonAddressPostgresql;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;

//@Service
public class PersonAddressPostgresqlConsumer {
	private static final Logger log = LoggerFactory.getLogger(PersonAddressPostgresqlConsumer.class);
	
	@KafkaListener(topics = "sc-person-address-postgresql")
	public void listen(ConsumerRecord<String, PersonAddressPostgresql> record) {
		log.info("{} : {}", record.key(), record.value());
	}
}
