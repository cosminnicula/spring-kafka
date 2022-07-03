package dev.intermediatebox.avro.samples;

import java.io.File;
import java.io.IOException;

import org.apache.avro.file.DataFileReader;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericRecord;

public class HelloAvroGenericReader {

	public static void main(String[] args) {
		var file = new File("helloAvroGeneric.avro");
		var datumReader = new GenericDatumReader<GenericRecord>();

		try (var dataReader = new DataFileReader<>(file, datumReader)) {
			dataReader.forEach(data -> {
				System.out.println(data);
				// manual casting
				var parsedInt = (int) data.get("myIntField");
				System.out.println(parsedInt % 10);
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
