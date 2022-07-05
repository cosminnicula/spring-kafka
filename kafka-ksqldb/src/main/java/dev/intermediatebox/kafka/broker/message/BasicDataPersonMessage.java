package dev.intermediatebox.kafka.broker.message;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BasicDataPersonMessage {
	private String firstName;
	private String lastName;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate birthDate;

	private Map<String, String> contacts;
	private BasicDataPassportMessage passport;
	private List<BasicDataAddressMessage> addresses;
}
