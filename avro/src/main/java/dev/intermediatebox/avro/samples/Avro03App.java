package dev.intermediatebox.avro.samples;

import java.io.File;
import java.io.IOException;

import dev.intermediatebox.avro.data.Avro03;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;

public class Avro03App {

	public static void main(String[] args) {
		var file1 = new File("avro03.avro");
		var data1 = Avro03.newBuilder().setMyMandatoryValue("This is the mandatory value")
				// Note that myWeirdButPossibleValue is boolean
				.setMyWeirdButPossibleValue(false).build();

		write(data1, file1);
		System.out.println("");
		read(file1);

		System.out.println("----------------------------------------------------------");

		var file2 = new File("avro03.avro");
		var data2 = Avro03.newBuilder().setMyMandatoryValue("This is the mandatory value")
				.setMyOptionalValue("not null value").setMyOptionalValueWithDefault("overriding default value")
				// Note that myWeirdButPossibleValue is int
				.setMyWeirdButPossibleValue(4982).build();

		write(data2, file2);
		System.out.println("");
		read(file2);
	}

	private static void write(Avro03 data, File toFile) {
		var datumWriter = new SpecificDatumWriter<>(Avro03.class);

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
		var datumReader = new SpecificDatumReader<>(Avro03.class);

		System.out.println("Reading " + fromFile.getName());
		try (var dataReader = new DataFileReader<>(fromFile, datumReader)) {
			dataReader.forEach(System.out::println);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
