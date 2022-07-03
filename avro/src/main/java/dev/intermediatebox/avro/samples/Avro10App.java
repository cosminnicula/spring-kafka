package dev.intermediatebox.avro.samples;

import dev.intermediatebox.avro.entity.Company;
import org.apache.avro.reflect.ReflectData;

public class Avro10App {

	public static void main(String[] args) {
		var schema = ReflectData.get().getSchema(Company.class);
		System.out.println(schema.toString(true));
	}
}
