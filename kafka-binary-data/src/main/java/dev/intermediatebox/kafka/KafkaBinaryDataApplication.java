package dev.intermediatebox.kafka;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.core.KafkaTemplate;

import java.io.File;
import java.util.Base64;

@SpringBootApplication
public class KafkaBinaryDataApplication implements CommandLineRunner {

  public static void main(String[] args) {
    SpringApplication.run(KafkaBinaryDataApplication.class, args);
  }

  @Autowired
  private KafkaTemplate<String, String> kafkaTemplate;

  @Override
  public void run(String... args) throws Exception {
    var file = new File("something.jpg");

    var fileBytes = FileUtils.readFileToByteArray(file);
    var fileBase64 = Base64.getEncoder().encodeToString(fileBytes);

    kafkaTemplate.send("binary-topic", fileBase64);
  }
}
