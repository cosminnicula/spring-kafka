package dev.intermediatebox.kafka.broker.scheduler;

import java.time.LocalTime;

import dev.intermediatebox.avro.data.EmployeeForward;
import dev.intermediatebox.kafka.broker.producer.EmployeeForwardProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

//@Service
public class EmployeeForwardScheduler {
//	@Autowired
	private EmployeeForwardProducer producer;

	@Scheduled(fixedRate = 1000)
	public void publish() {
		var now = LocalTime.now();
		var data = EmployeeForward.newBuilder().setFirstName("First name " + now).setLastName("Last name " + now)
				.setEmail("Email " + now)
				.build();

		producer.send(data);
	}
}
