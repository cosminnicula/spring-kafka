package dev.intermediatebox.kafka.command.action;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import dev.intermediatebox.kafka.broker.api.request.*;
import dev.intermediatebox.kafka.broker.message.*;
import dev.intermediatebox.kafka.broker.producer.BasicDataProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BasicDataAction {

	@Autowired
	private BasicDataProducer producer;

	public void deleteBasicDataCountry(String countryName) {
		producer.sendBasicDataCountryWithNullValue(countryName);
	}

	public void publishBasicDataCountry(BasicDataCountryMessage message) {
		producer.sendBasicDataCountry(message);
	}

	public void publishBasicDataFive(BasicDataFiveMessage message) {
		producer.sendBasicDataFive(message);
	}

	public void publishBasicDataFour(BasicDataFourMessage message) {
		producer.sendBasicDataFour(message);
	}

	public void publishBasicDataOne(BasicDataOneMessage message) {
		producer.sendBasicDataOne(message);
	}

	public void publishBasicDataPerson(BasicDataPersonMessage message) {
		producer.sendBasicDataPerson(message);
	}

	public void publishBasicDataThree(BasicDataThreeMessage message) {
		producer.sendBasicDataThree(message);
	}

	public void publishBasicDataTwo(BasicDataTwoMessage message) {
		producer.sendBasicDataTwo(message);
	}

	private long toEpochDay(BasicDataTwoRequest.Date myDate) {
		var dateString = String.format("%d-%2d-%2d", myDate.getYear(), myDate.getMonth(), myDate.getDate()).replace(' ',
				'0');
		return LocalDate.parse(dateString).toEpochDay();
	}

	private long toEpochMillis(BasicDataTwoRequest.Timestamp myTimestamp) {
		var dateString = String.format("%d-%2d-%2dT%2d:%2d:%2d", myTimestamp.getYear(), myTimestamp.getMonth(),
				myTimestamp.getDate(), myTimestamp.getHour(), myTimestamp.getMinute(), myTimestamp.getSecond())
				.replace(' ', '0');
		return LocalDateTime.parse(dateString).toInstant(ZoneOffset.UTC).toEpochMilli();
	}

	private BasicDataAddressMessage toKafkaMessage(BasicDataPersonRequest.Address original) {
		var result = new BasicDataAddressMessage();

		result.setStreetAddress(original.getStreetAddress());
		result.setCountry(original.getCountry());
		result.setLocation(toKafkaMessage(original.getLocation()));

		return result;
	}

	public BasicDataCountryMessage toKafkaMessage(BasicDataCountryRequest original) {
		var result = new BasicDataCountryMessage();

		result.setCountryName(original.getCountryName());
		result.setCurrencyCode(original.getCurrencyCode());
		result.setPopulation(original.getPopulation());

		return result;
	}

	public BasicDataFiveMessage toKafkaMessage(BasicDataFiveRequest request) {
		var result = new BasicDataFiveMessage();

		var mapAlpha = new TreeMap<Integer, String>();
		IntStream.range(0, request.getAlphaElementsCount()).forEach(i -> mapAlpha.put(i, "Map value alpha " + i));

		var mapBeta = new HashMap<UUID, String>();
		IntStream.range(0, request.getBetaElementsCount())
				.forEach(i -> mapBeta.put(UUID.randomUUID(), "Map value beta " + i));

		result.setMyMapAlpha(mapAlpha);
		result.setMyMapBeta(mapBeta);

		return result;
	}

	public BasicDataFourMessage toKafkaMessage(BasicDataFourRequest request) {
		var result = new BasicDataFourMessage();

		var array = new String[request.getArrayElementsCount()];
		IntStream.range(0, request.getArrayElementsCount()).forEach(i -> array[i] = "Array element " + i);

		var list = new ArrayList<Integer>();
		IntStream.range(0, request.getListElementsCount()).forEach(list::add);

		var set = new TreeSet<Double>();
		IntStream.range(0, request.getSetElementsCount())
				.forEach(i -> set.add(ThreadLocalRandom.current().nextDouble(1000)));

		result.setMyStringArray(array);
		result.setMyIntegerList(list);
		result.setMyDoubleSet(set);

		return result;
	}

	public BasicDataOneMessage toKafkaMessage(BasicDataOneRequest request) {
		var result = new BasicDataOneMessage();

		result.setMyAnotherString(request.getMyAnotherString());
		result.setMyBoolean(request.isMyBoolean());
		result.setMyDouble(request.getMyDouble());
		result.setMyFloat(request.getMyFloat());
		result.setMyInteger(request.getMyInteger());
		result.setMyLong(request.getMyLong());
		result.setMyString(request.getMyString());
		result.setMyBigDecimal(request.getMyBigDecimal());

		return result;
	}

	public BasicDataPersonMessage toKafkaMessage(BasicDataPersonRequest request) {
		var result = new BasicDataPersonMessage();

		result.setFirstName(request.getFirstName());
		result.setLastName(request.getLastName());
		result.setBirthDate(request.getBirthDate());
		result.setContacts(request.getContacts());

		var passport = new BasicDataPassportMessage();
		passport.setNumber(request.getPassport().getNumber());
		passport.setExpiryDate(request.getPassport().getExpiryDate());
		result.setPassport(passport);

		var addresses = request.getAddresses().stream().map(this::toKafkaMessage).collect(Collectors.toList());
		result.setAddresses(addresses);

		return result;
	}

	public BasicDataThreeMessage toKafkaMessage(BasicDataThreeRequest request) {
		var result = new BasicDataThreeMessage();

		var localDate = toLocalDate(request.getMyDate());
		result.setMyLocalDate(localDate);
		result.setMyLocalDateCustomFormat(localDate);

		var localTime = toLocalTime(request.getMyTime());
		result.setMyLocalTime(localTime);
		result.setMyLocalTimeCustomFormat(localTime);

		var localDatetime = toLocalDateTime(request.getMyDateTime());
		result.setMyLocalDateTime(localDatetime);
		result.setMyLocalDateTimeCustomFormat(localDatetime);

		return result;
	}

	public BasicDataTwoMessage toKafkaMessage(BasicDataTwoRequest request) {
		var result = new BasicDataTwoMessage();

		var epochDay = toEpochDay(request.getMyDate());
		result.setMyEpochDay(epochDay);

		var millisOfDay = toMillisOfDay(request.getMyTime());
		result.setMyMillisOfDay(millisOfDay);

		var epochMillis = toEpochMillis(request.getMyTimestamp());
		result.setMyEpochMillis(epochMillis);

		return result;
	}

	private BasicDataLocationMessage toKafkaMessage(BasicDataPersonRequest.Location original) {
		var result = new BasicDataLocationMessage();

		result.setLatitude(original.getLatitude());
		result.setLongitude(original.getLongitude());

		return result;
	}

	private LocalDate toLocalDate(BasicDataThreeRequest.Date myDate) {
		return LocalDate.of(myDate.getYear(), myDate.getMonth(), myDate.getDate());
	}

	private LocalDateTime toLocalDateTime(BasicDataThreeRequest.DateTime myDateTime) {
		return LocalDateTime.of(myDateTime.getYear(), myDateTime.getMonth(), myDateTime.getDate(), myDateTime.getHour(),
				myDateTime.getMinute(), myDateTime.getSecond());
	}

	private LocalTime toLocalTime(BasicDataThreeRequest.Time myTime) {
		return LocalTime.of(myTime.getHour(), myTime.getMinute(), myTime.getSecond());
	}

	private long toMillisOfDay(BasicDataTwoRequest.Time myTime) {
		return LocalTime.of(myTime.getHour(), myTime.getMinute(), myTime.getSecond()).toSecondOfDay() * 1000;
	}

}
