package dev.intermediatebox.kafka.broker.message;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BasicDataPassportMessage {
	private String number;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate expiryDate;
}
