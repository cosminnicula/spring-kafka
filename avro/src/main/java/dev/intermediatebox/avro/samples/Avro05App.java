package dev.intermediatebox.avro.samples;

import java.io.File;
import java.io.IOException;

import dev.intermediatebox.avro.data.Avro05;
import dev.intermediatebox.avro.data.Avro05Enum;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;

public class Avro05App {

	public static void main(String[] args) {
		var file = new File("avro05.avro");

		write(file);
		System.out.println("");
		read(file);
	}

	private static void write(File toFile) {
		var data = Avro05.newBuilder().setMaritalStatus(Avro05Enum.MARRIED).build();

		var datumWriter = new SpecificDatumWriter<>(Avro05.class);

		try (var dataWriter = new DataFileWriter<>(datumWriter)) {
			dataWriter.create(data.getSchema(), toFile);
			dataWriter.append(data);

			System.out.println("Writing " + toFile.getName());
			System.out.println(data);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void read(File fromFile) {
		var datumReader = new SpecificDatumReader<>(Avro05.class);

		System.out.println("Reading " + fromFile.getName());
		try (var dataReader = new DataFileReader<>(fromFile, datumReader)) {
			dataReader.forEach(System.out::println);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
