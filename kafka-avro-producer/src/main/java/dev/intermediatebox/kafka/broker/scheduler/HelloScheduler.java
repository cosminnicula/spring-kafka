package dev.intermediatebox.kafka.broker.scheduler;

import java.time.LocalTime;
import java.util.concurrent.ThreadLocalRandom;

import dev.intermediatebox.avro.data.Hello;
import dev.intermediatebox.kafka.broker.producer.HelloProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

//@Service
public class HelloScheduler {
	@Autowired
	private HelloProducer producer;

	@Scheduled(fixedRate = 1000)
	public void publish() {
		var data = Hello.newBuilder().setMyIntField(ThreadLocalRandom.current().nextInt())
				.setMyStringField("Now is : " + LocalTime.now()).build();
		
		producer.send(data);
	}

}
