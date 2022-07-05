package dev.intermediatebox.kafka.broker.api.server;

import dev.intermediatebox.kafka.broker.api.request.*;
import dev.intermediatebox.kafka.command.service.BasicDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/basic-data")
public class BasicDataApi {

	@Autowired
	private BasicDataService service;

	@PostMapping(value = "/country", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BasicDataCountryRequest> createBasicDataCountry(
			@RequestBody BasicDataCountryRequest request) {
		service.createBasicDataCountry(request);

		return ResponseEntity.ok(request);
	}

	@PostMapping(value = "/five", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BasicDataFiveRequest> createBasicDataFive(@RequestBody BasicDataFiveRequest request) {
		service.createBasicDataFive(request);

		return ResponseEntity.ok(request);
	}

	@PostMapping(value = "/four", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BasicDataFourRequest> createBasicDataFour(@RequestBody BasicDataFourRequest request) {
		service.createBasicDataFour(request);

		return ResponseEntity.ok(request);
	}

	@PostMapping(value = "/one", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BasicDataOneRequest> createBasicDataOne(@RequestBody BasicDataOneRequest request) {
		service.createBasicDataOne(request);

		return ResponseEntity.ok(request);
	}

	@PostMapping(value = "/person", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BasicDataPersonRequest> createBasicDataPerson(@RequestBody BasicDataPersonRequest request) {
		service.createBasicDataPerson(request);

		return ResponseEntity.ok(request);
	}

	@PostMapping(value = "/three", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BasicDataThreeRequest> createBasicDataThree(@RequestBody BasicDataThreeRequest request) {
		service.createBasicDataThree(request);

		return ResponseEntity.ok(request);
	}

	@PostMapping(value = "/two", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BasicDataTwoRequest> createBasicDataTwo(@RequestBody BasicDataTwoRequest request) {
		service.createBasicDataTwo(request);

		return ResponseEntity.ok(request);
	}

	@DeleteMapping(value = "/country/{countryName}", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> deleteBasicDataCountry(@PathVariable(name = "countryName") String countryName) {
		service.deleteBasicDataCountry(countryName);

		return ResponseEntity.ok("Deleted : " + countryName);
	}

}
