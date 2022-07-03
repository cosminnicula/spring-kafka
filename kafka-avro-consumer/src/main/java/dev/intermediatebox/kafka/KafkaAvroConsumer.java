package dev.intermediatebox.kafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KafkaAvroConsumer {

	public static void main(String[] args) {
		SpringApplication.run(KafkaAvroConsumer.class, args);
	}

}
