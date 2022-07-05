package dev.intermediatebox.kafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableKafkaStreams
public class KafkaAvroConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaAvroConsumerApplication.class, args);
	}

}
