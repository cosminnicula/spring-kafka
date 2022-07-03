package dev.intermediatebox.avro.samples;

import java.io.File;
import java.io.IOException;

import dev.intermediatebox.avro.data.Avro01;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;

public class Avro01App {

	public static void main(String[] args) {
		System.out.println("With default");
		var fileDefault = new File("avro01Default.avro");
		writeDefault(fileDefault);
		System.out.println("");
		read(fileDefault);

		System.out.println("");
		System.out.println("Without default");
		var file = new File("avro01.avro");
		write(file);
		System.out.println("");
		read(file);
	}

	private static void writeDefault(File toFile) {
		var data = Avro01.newBuilder().setFullName("This is data with default").build();
		var datumWriter = new SpecificDatumWriter<>(Avro01.class);

		try (var dataWriter = new DataFileWriter<>(datumWriter)) {
			dataWriter.create(data.getSchema(), toFile);
			dataWriter.append(data);

			System.out.println("Writing");
			System.out.println(data);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void write(File toFile) {
		var data = Avro01.newBuilder().setFullName("This is data no default").setActive(true).setMaritalStatus("SINGLE")
				.build();
		var datumWriter = new SpecificDatumWriter<>(Avro01.class);

		try (var dataWriter = new DataFileWriter<>(datumWriter)) {
			dataWriter.create(data.getSchema(), toFile);
			dataWriter.append(data);

			System.out.println("Writing");
			System.out.println(data);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void read(File fromFile) {
		var datumReader = new SpecificDatumReader<>(Avro01.class);

		System.out.println("Reading");
		try (var dataReader = new DataFileReader<>(fromFile, datumReader)) {
			dataReader.forEach(System.out::println);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
