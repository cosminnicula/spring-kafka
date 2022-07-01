package dev.intermediatebox.avro.samples;

import java.io.File;
import java.io.IOException;
import java.time.LocalTime;
import java.util.concurrent.ThreadLocalRandom;

import dev.intermediatebox.avro.data.Hello;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.specific.SpecificDatumWriter;

public class HelloAvro {

	public static void main(String[] args) {
//		var data = new Hello();
//		data.setMyIntField(ThreadLocalRandom.current().nextInt());
//		data.setMyStringField("The time is " + LocalTime.now());

		var data = Hello.newBuilder().setMyIntField(ThreadLocalRandom.current().nextInt())
				.setMyStringField("The time is " + LocalTime.now()).build();

		// Avro binary representation
		var datumWriter = new SpecificDatumWriter<>(Hello.class);

		try (var dataWriter = new DataFileWriter<>(datumWriter)) {
			var file = new File("helloAvro.avro");
			dataWriter.create(data.getSchema(), file);
			dataWriter.append(data);

			System.out.println("Written : " + data);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
