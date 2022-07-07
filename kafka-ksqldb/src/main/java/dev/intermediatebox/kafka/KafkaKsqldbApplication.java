package dev.intermediatebox.kafka;

import dev.intermediatebox.kafka.ksqldb.client.BasicJavaClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KafkaKsqldbApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(KafkaKsqldbApplication.class, args);
	}

	private static final Logger log = LoggerFactory.getLogger(KafkaKsqldbApplication.class);

	@Autowired
	private BasicJavaClient basicJavaClient;

	@Override
	public void run(String... args) throws Exception {
//		basicJavaClient.createStream();
//		basicJavaClient.describeStream();
//		basicJavaClient.listObjects();
//
//		for (int i = 0; i < 5; i++) {
//			basicJavaClient.insertSingle();
//		}
//
//		basicJavaClient.insertStream(10);
//
//		basicJavaClient.pullQuery();
//
//		log.info("Starting pushQuerySync()");
//		basicJavaClient.pushQuerySync();
//		log.info("Done pushQuerySync()");
//
//		log.info("Starting pushQueryAsync()");
//		basicJavaClient.pushQueryAsync();
//		log.info("Done pushQueryAsync()");
	}
}
