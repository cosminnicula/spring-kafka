package dev.intermediatebox.kafka.broker.message;

public class SubscriptionPurchaseMessage {

	private String subscriptionNumber;

	private String username;

	public String getSubscriptionNumber() {
		return subscriptionNumber;
	}

	public String getUsername() {
		return username;
	}

	public void setSubscriptionNumber(String subscriptionNumber) {
		this.subscriptionNumber = subscriptionNumber;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
