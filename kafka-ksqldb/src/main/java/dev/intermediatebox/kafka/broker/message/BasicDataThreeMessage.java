package dev.intermediatebox.kafka.broker.message;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BasicDataThreeMessage {
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate myLocalDate;

	@JsonFormat(pattern = "dd MMM yyyy")
	private LocalDate myLocalDateCustomFormat;

	@JsonFormat(pattern = "HH:mm:ss")
	private LocalTime myLocalTime;

	@JsonFormat(pattern = "hh:mm:ss a")
	private LocalTime myLocalTimeCustomFormat;

	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private LocalDateTime myLocalDateTime;

	@JsonFormat(pattern = "dd-MMM-yyyy hh:mm:ss a")
	private LocalDateTime myLocalDateTimeCustomFormat;
}
