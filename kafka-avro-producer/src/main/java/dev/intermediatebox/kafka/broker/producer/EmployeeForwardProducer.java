package dev.intermediatebox.kafka.broker.producer;

import dev.intermediatebox.avro.data.EmployeeForward;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

//@Service
public class EmployeeForwardProducer {
	@Autowired
	private KafkaTemplate<String, EmployeeForward> kafkaTemplate;
	
	public void send(EmployeeForward data) {
		kafkaTemplate.send("sc-employee-forward", data);
	}
}
