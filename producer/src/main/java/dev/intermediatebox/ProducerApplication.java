package dev.intermediatebox;

import dev.intermediatebox.entity.*;
import dev.intermediatebox.producer.*;
import dev.intermediatebox.service.ImageService;
import dev.intermediatebox.service.InvoiceService;
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
//  private KafkaKeyProducer kafkaKeyProducer;

//  @Autowired
//  private EmployeeJsonProducer employeeJsonProducer;

//  @Autowired
//  private PurchaseRequestProducer purchaseRequestProducer;

//  @Autowired
//  private PaymentRequestProducer paymentRequestProducer;

//  @Autowired
//  private FoodOrderProducer foodOrderProducer;
//
//  @Autowired
//  private SimpleNumberProducer simpleNumberProducer;

//  @Autowired
//  private ImageService imageService;
//
//  @Autowired
//  private ImageProducer imageProducer;

//  @Autowired
//  InvoiceService invoiceService;
//
//  @Autowired
//  InvoiceProducer invoiceProducer;

  @Autowired
  ImageService imageService;

  @Autowired
  Image2Producer image2Producer;

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

    //10.
//    var pr1 = new PurchaseRequest(5551, "PR-1", 991, "USD");
//    var pr2 = new PurchaseRequest(5552, "PR-2", 992, "USD");
//    var pr3 = new PurchaseRequest(5553, "PR-3", 993, "USD");

//    purchaseRequestProducer.send(pr1);
//    purchaseRequestProducer.send(pr2);
//    purchaseRequestProducer.send(pr3);
//    // send pr1 again to simulate duplicate data
//    purchaseRequestProducer.send(pr1);

//    // 11.
//    var pr1_transaction1 = new PaymentRequest("pr1", 551, "USD", "Notes pr1", "budget reserve");
//    var pr1_transaction2 = new PaymentRequest("pr1", 551, "USD", "Notes pr1", "approval workflow");
//    var pr1_transaction3 = new PaymentRequest("pr1", 551, "USD", "Notes pr1", "push notification");
//
//    var pr2_transaction1 = new PaymentRequest("pr2", 552, "USD", "Notes pr2", "budget reserve");
//    var pr2_transaction2 = new PaymentRequest("pr2", 552, "USD", "Notes pr2", "approval workflow");
//    var pr2_transaction3 = new PaymentRequest("pr2", 552, "USD", "Notes pr2", "push notification");
//
//    paymentRequestProducer.send(pr1_transaction1);
//    paymentRequestProducer.send(pr1_transaction2);
//    paymentRequestProducer.send(pr1_transaction3);
//    paymentRequestProducer.send(pr2_transaction1);
//    paymentRequestProducer.send(pr2_transaction2);
//    paymentRequestProducer.send(pr2_transaction3);
//    // send some duplicate data
//    paymentRequestProducer.send(pr1_transaction2);
//    paymentRequestProducer.send(pr2_transaction3);

//    // 12. & 13.
//    var fo1 = new FoodOrder(2, "bread");
//    var fo2 = new FoodOrder(1, "cheese");
//    var fo3 = new FoodOrder(5, "water");
//
//    foodOrderProducer.send(fo1);
//    foodOrderProducer.send(fo2);
//    foodOrderProducer.send(fo3);
//
//    for (int i = 100; i < 103; i++) {
//      var simpleNumber = new SimpleNumber(i);
//      simpleNumberProducer.send(simpleNumber);
//    }

//    // 14.
//    var image1 = imageService.generateImage("jpg");
//    var image2 = imageService.generateImage("svg");
//    var image3 = imageService.generateImage("png");
//    var image4 = imageService.generateImage("gif");
//    var image5 = imageService.generateImage("bmp");
//    var image6 = imageService.generateImage("tiff");
//
//    imageProducer.send(image1, 0);
//    imageProducer.send(image2, 0);
//    imageProducer.send(image3, 0);
//    imageProducer.send(image4, 1);
//    imageProducer.send(image5, 1);
//    imageProducer.send(image6, 1);

//    // 15.
//    for (int i = 0; i < 10; i++) {
//      var invoice = invoiceService.generateInvoice();
//
//      if (i > 5) {
//        invoice.setAmount(0);
//      }
//
//      invoiceProducer.send(invoice);
//    }

    // 16.
    var image1 = imageService.generateImage("jpg");
    var image2 = imageService.generateImage("svg");
    var image3 = imageService.generateImage("png");
    var image4 = imageService.generateImage("gif");
    var image5 = imageService.generateImage("bmp");
    var image6 = imageService.generateImage("tiff");

    image2Producer.send(image1, 0);
    image2Producer.send(image2, 0);
    image2Producer.send(image3, 0);
    image2Producer.send(image4, 1);
    image2Producer.send(image5, 1);
    image2Producer.send(image6, 1);
  }
}
