package dev.intermediatebox.kafka.broker.api.request;

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
public class BasicDataPersonRequest {
	@Getter
	@Setter
	@ToString
	public static class Address {
		private String streetAddress;
		private String country;
		private Location location;
	}

	@Getter
	@Setter
	@ToString
	public static class Location {
		private double latitude;
		private double longitude;
	}

	@Getter
	@Setter
	@ToString
	public static class Passport {
		private String number;
		@JsonFormat(pattern = "yyyy-MM-dd")
		private LocalDate expiryDate;
	}

	private String firstName;
	private String lastName;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate birthDate;

	private Map<String, String> contacts;
	private Passport passport;
	private List<Address> addresses;
}
