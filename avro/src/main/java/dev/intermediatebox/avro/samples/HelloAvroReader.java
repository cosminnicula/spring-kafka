package dev.intermediatebox.avro.samples;

import java.io.File;
import java.io.IOException;

import dev.intermediatebox.avro.data.Hello;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.specific.SpecificDatumReader;

public class HelloAvroReader {

	public static void main(String[] args) {
		var file = new File("helloAvro.avro");
		var datumReader = new SpecificDatumReader<>(Hello.class);

		try (var dataReader = new DataFileReader<>(file, datumReader)) {
			dataReader.forEach(data -> {
				System.out.println(data);
				System.out.println(data.getMyIntField() % 10);
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
