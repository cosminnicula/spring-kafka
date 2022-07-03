package dev.intermediatebox.avro.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BookJson {
	private String title;
	private String author;
	private int price;
}
