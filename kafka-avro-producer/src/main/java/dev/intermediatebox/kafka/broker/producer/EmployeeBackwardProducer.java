package dev.intermediatebox.kafka.broker.producer;

import dev.intermediatebox.avro.data.EmployeeBackward;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

//@Service
public class EmployeeBackwardProducer {
	@Autowired
	private KafkaTemplate<String, EmployeeBackward> kafkaTemplate;
	
	public void send(EmployeeBackward data) {
		kafkaTemplate.send("sc-employee-backward", data);
	}
	
}
