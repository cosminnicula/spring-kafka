package dev.intermediatebox.avro.samples;

import dev.intermediatebox.avro.entity.SimpleEntity;
import org.apache.avro.reflect.ReflectData;

public class Avro09App {

	public static void main(String[] args) {
		var schema = ReflectData.get().getSchema(SimpleEntity.class);
		System.out.println(schema.toString(true));
	}
}
