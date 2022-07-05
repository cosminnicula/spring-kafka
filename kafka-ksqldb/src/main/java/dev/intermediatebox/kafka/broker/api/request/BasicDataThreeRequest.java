package dev.intermediatebox.kafka.broker.api.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BasicDataThreeRequest {
	@Getter
	@Setter
	@ToString
	public static class Date {
		private int year;
		private int month;
		private int date;
	}

	@Getter
	@Setter
	@ToString
	public static class DateTime {
		private int year;
		private int month;
		private int date;
		private int hour;
		private int minute;
		private int second;
	}

	@Getter
	@Setter
	@ToString
	public static class Time {
		private int hour;
		private int minute;
		private int second;
	}

	private Date myDate;
	private Time myTime;
	private DateTime myDateTime;
}
