package dev.intermediatebox.scheduler;

import com.fasterxml.jackson.core.JsonProcessingException;
import dev.intermediatebox.entity.CarLocation;
import dev.intermediatebox.producer.CarLocationProducer;
import dev.intermediatebox.producer.FixedRateProducer2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

//@Service
public class CarLocationScheduler {
  private static final Logger log = LoggerFactory.getLogger(CarLocationScheduler.class);

  private CarLocation car1;
  private CarLocation car2;
  private CarLocation car3;

  @Autowired
  CarLocationProducer carLocationProducer;

  public CarLocationScheduler() {
    var now = System.currentTimeMillis();

    car1 = new CarLocation("car1", now, 0);
    car2 = new CarLocation("car2", now, 110);
    car3 = new CarLocation("car3", now, 95);
  }

  @Scheduled(fixedRate = 1000)
  public void generateCarLocation() throws JsonProcessingException {
    var now = System.currentTimeMillis();

    car1.setDistance(car1.getDistance() + 1);
    car2.setDistance(car2.getDistance() - 1);
    car3.setDistance(car3.getDistance() + 1);

    carLocationProducer.send(car1);
    carLocationProducer.send(car2);
    carLocationProducer.send(car3);

    log.info("Sent {}", car1);
    log.info("Sent {}", car2);
    log.info("Sent {}", car3);
  }
}
