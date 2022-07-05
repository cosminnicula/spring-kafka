package dev.intermediatebox.kafka.broker.consumer;

import dev.intermediatebox.avro.data.EmployeeForward;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//@Service
public class EmployeeForwardConsumer {

	private static final Logger LOG = LoggerFactory.getLogger(EmployeeForwardConsumer.class);

//	@KafkaListener(topics = "sc-employee-forward")
	public void listen(ConsumerRecord<String, EmployeeForward> record) {
		LOG.info("{} : {}", record.key(), record.value());
	}

}
