package dev.intermediatebox.avro.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Company {
	private long companyId;
	private String code;
	private String name;
	private List<Branch> branches;
}
