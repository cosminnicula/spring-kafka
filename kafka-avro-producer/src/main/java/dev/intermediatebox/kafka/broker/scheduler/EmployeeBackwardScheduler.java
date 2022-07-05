package dev.intermediatebox.kafka.broker.scheduler;

import dev.intermediatebox.avro.data.EmployeeBackward;
import dev.intermediatebox.kafka.broker.producer.EmployeeBackwardProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalTime;

//@Service
public class EmployeeBackwardScheduler {
  @Autowired
  private EmployeeBackwardProducer producer;

  @Scheduled(fixedRate = 1000)
  public void publish() {
    var now = LocalTime.now();
    var data = EmployeeBackward.newBuilder().setFirstName("First name " + now).setLastName("Last name " + now)
        .build();

    producer.send(data);
  }
}
