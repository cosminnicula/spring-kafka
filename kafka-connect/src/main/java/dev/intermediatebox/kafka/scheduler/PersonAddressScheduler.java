package dev.intermediatebox.kafka.scheduler;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import dev.intermediatebox.kafka.broker.message.AddressMessage;
import dev.intermediatebox.kafka.broker.message.PersonMessage;
import dev.intermediatebox.kafka.broker.producer.PersonProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.javafaker.Faker;

@Service
public class PersonAddressScheduler {
	private Faker faker = Faker.instance();
	
	@Autowired
	private PersonProducer producer;
	
	private AddressMessage fakeAddress() {
		var addr = new AddressMessage();
		
		addr.setAddressId(faker.number().numberBetween(1, 90000));
		addr.setAddress(faker.address().streetAddress());
		addr.setCity(faker.address().city());
		addr.setPostalCode(faker.address().zipCode());
		
		return addr;
	}
	
	private PersonMessage fakePerson() {
		var person = new PersonMessage();
		var addresses = new ArrayList<AddressMessage>();
		
		person.setPersonId(faker.number().numberBetween(1, 90000));
		person.setAddresses(addresses);
		person.setEmail(faker.internet().emailAddress());
		person.setFullName(faker.name().fullName());
		
		for (int i = 0; i < ThreadLocalRandom.current().nextInt(4); i++) {
			addresses.add(fakeAddress());
		}
		
		return person;
	}
	
	@Scheduled(fixedRate = 5000)
	public void publishDummyPerson() throws JsonProcessingException {
		producer.publish(fakePerson());
	}
}
