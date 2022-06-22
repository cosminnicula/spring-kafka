package dev.intermediatebox;

import dev.intermediatebox.entity.Employee;
import dev.intermediatebox.producer.EmployeeJsonProducer;
import dev.intermediatebox.producer.HelloKafkaProducer;
import dev.intermediatebox.producer.KafkaKeyProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
@EnableScheduling
public class ProducerApplication implements CommandLineRunner {

//  @Autowired
//  private HelloKafkaProducer helloKafkaProducer;

//  @Autowired
//  KafkaKeyProducer kafkaKeyProducer;

//  @Autowired
//  EmployeeJsonProducer employeeJsonProducer;

  public static void main(String[] args) {
    SpringApplication.run(ProducerApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
// 1.
//    helloKafkaProducer.sendHello("something" + ThreadLocalRandom.current().nextInt());

// 4. & 5.
//    for (int i = 0; i < 10_000; i++) {
//      var key = "key-" + (i%4);
//      var value = "value " + i + " with key " + key;
//      kafkaKeyProducer.send(key, value);
//      TimeUnit.SECONDS.sleep(1);
//    }

// 6.
//    for (int i = 0; i < 10; i++) {
//      employeeJsonProducer.sendMessage(new Employee("employee-" + i, "Employee " + i, LocalDate.now()));
//    }
  }
}
