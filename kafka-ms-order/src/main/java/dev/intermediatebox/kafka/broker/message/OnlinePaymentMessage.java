package dev.intermediatebox.kafka.broker.message;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OnlinePaymentMessage {
	private String onlineOrderNumber;

	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private LocalDateTime paymentDateTime;

	private String paymentMethod;
	private String paymentNumber;
}
