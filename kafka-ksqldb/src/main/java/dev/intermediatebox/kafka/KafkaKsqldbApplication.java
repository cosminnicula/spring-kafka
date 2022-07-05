package dev.intermediatebox.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KafkaKsqldbApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(KafkaKsqldbApplication.class, args);
	}

	private static final Logger log = LoggerFactory.getLogger(KafkaKsqldbApplication.class);

	@Override
	public void run(String... args) throws Exception {
	}
}
