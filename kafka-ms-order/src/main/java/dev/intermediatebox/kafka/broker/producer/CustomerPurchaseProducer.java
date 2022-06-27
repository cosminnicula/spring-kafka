package dev.intermediatebox.kafka.broker.producer;

import dev.intermediatebox.kafka.broker.message.CustomerPurchaseMobileMessage;
import dev.intermediatebox.kafka.broker.message.CustomerPurchaseWebMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class CustomerPurchaseProducer {

	@Autowired
	private KafkaTemplate<String, Object> kafkaTemplate;

	public void publishPurchaseMobile(CustomerPurchaseMobileMessage message) {
		kafkaTemplate.send("t-commodity-customer-purchase-mobile", message.getPurchaseNumber(), message);
	}

	public void publishPurchaseWeb(CustomerPurchaseWebMessage message) {
		kafkaTemplate.send("t-commodity-customer-purchase-web", message.getPurchaseNumber(), message);
	}

}
