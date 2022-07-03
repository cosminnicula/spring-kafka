package dev.intermediatebox.avro.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Branch {
	private long branchId;
	private String city;
	private String address;
	private String country;
}
