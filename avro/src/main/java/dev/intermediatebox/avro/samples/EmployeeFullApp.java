package dev.intermediatebox.avro.samples;

import java.io.File;
import java.io.IOException;

import dev.intermediatebox.avro.data.EmployeeFullV1;
import dev.intermediatebox.avro.data.EmployeeFullV2;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;

public class EmployeeFullApp {

	public static void main(String[] args) {
		var fileBackward = new File("employeeFullBackward.avro");
		System.out.println("Backward");
		writeUsingV1(fileBackward);
		System.out.println("");
		readUsingV2(fileBackward);

		System.out.println("-----------------------------------------------");
		System.out.println("");

		var fileForward = new File("employeeFullForward.avro");
		System.out.println("Forward");
		writeUsingV2(fileForward);
		System.out.println("");
		readUsingV1(fileForward);
	}

	private static void writeUsingV1(File toFile) {
		var data = EmployeeFullV1.newBuilder().setFirstName("Clark").build();
		var datumWriter = new SpecificDatumWriter<>(EmployeeFullV1.class);

		try (var dataWriter = new DataFileWriter<>(datumWriter)) {
			dataWriter.create(data.getSchema(), toFile);
			dataWriter.append(data);

			System.out.println("Writing (v1)");
			System.out.println(data);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void writeUsingV2(File toFile) {
		var data = EmployeeFullV2.newBuilder().setFirstName("Clark").build();
		var datumWriter = new SpecificDatumWriter<>(EmployeeFullV2.class);

		try (var dataWriter = new DataFileWriter<>(datumWriter)) {
			dataWriter.create(data.getSchema(), toFile);
			dataWriter.append(data);

			System.out.println("Writing (v2)");
			System.out.println(data);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void readUsingV1(File fromFile) {
		var datumReader = new SpecificDatumReader<>(EmployeeFullV1.class);

		System.out.println("Reading (v1)");
		try (var dataReader = new DataFileReader<>(fromFile, datumReader)) {
			dataReader.forEach(System.out::println);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void readUsingV2(File fromFile) {
		var datumReader = new SpecificDatumReader<>(EmployeeFullV2.class);

		System.out.println("Reading (v2)");
		try (var dataReader = new DataFileReader<>(fromFile, datumReader)) {
			dataReader.forEach(System.out::println);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
