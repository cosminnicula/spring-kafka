package dev.intermediatebox.kafka.broker.scheduler;

import java.util.concurrent.ThreadLocalRandom;

import dev.intermediatebox.avro.data.PersonAddressPostgresql;
import dev.intermediatebox.kafka.broker.producer.PersonPostgresqlProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import com.github.javafaker.Faker;
import org.springframework.stereotype.Service;

//@Service
public class PersonAddressScheduler {

	private Faker faker = Faker.instance();

	@Autowired
	private PersonPostgresqlProducer producer;

	private PersonAddressPostgresql fakePerson() {
		var person = new PersonAddressPostgresql();

		person.setPersonId(Integer.toString(ThreadLocalRandom.current().nextInt(1, 1000000)));
		person.setEmail(faker.internet().emailAddress());
		person.setFullName(faker.name().fullName());

		person.setAddressId(Integer.toString(ThreadLocalRandom.current().nextInt(1, 1000000)));
		person.setAddress(faker.address().streetAddress());
		person.setCity(faker.address().city());
		person.setPostalCode(faker.address().zipCode());

		return person;
	}
	
	@Scheduled(fixedRate = 2000)
	public void publishFakePerson() {
		producer.publish(fakePerson());
	}

}
