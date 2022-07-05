package dev.intermediatebox.kafka.broker.producer;

import dev.intermediatebox.avro.data.PersonAddressPostgresql;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

//@Service
public class PersonPostgresqlProducer {
	@Autowired
	private KafkaTemplate<String, PersonAddressPostgresql> kafkaTemplate;
	
	public void publish(PersonAddressPostgresql data) {
		kafkaTemplate.send("sc-person-address-postgresql", data);
	}
}
