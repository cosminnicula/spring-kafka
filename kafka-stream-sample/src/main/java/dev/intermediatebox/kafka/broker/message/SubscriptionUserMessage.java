package dev.intermediatebox.kafka.broker.message;

public class SubscriptionUserMessage {

	private String duration;

	private String username;

	public String getDuration() {
		return duration;
	}

	public String getUsername() {
		return username;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
