package dev.intermediatebox.kafka;

import dev.intermediatebox.avro.data.Avro02;
import dev.intermediatebox.kafka.broker.producer.Avro02Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.nio.ByteBuffer;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@SpringBootApplication
@EnableScheduling
public class KafkaAvroProducerApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(KafkaAvroProducerApplication.class, args);
	}

//	@Autowired
//	private Avro01Producer avro01Producer;

//	@Autowired
//	private Avro02Producer avro02Producer;

	@Override
	public void run(String... args) throws Exception {
//		var data = Avro01.newBuilder().setActive(false)
//				.setFullName("Full name " + ThreadLocalRandom.current().nextInt()).setMaritalStatus("SINGLE").build();
//
//		avro01Producer.send(data);

//		var data = Avro02.newBuilder().setMyDecimal(ByteBuffer.wrap(new byte[10]))
//				.setMyUUID(UUID.randomUUID().toString()).setMyDate(LocalDate.of(2022, 7, 2))
//				.setMyTimeMillis(LocalTime.now()).setMyTimestampMillis(Instant.now()).build();
//
//		avro02Producer.send(data);
	}
}
