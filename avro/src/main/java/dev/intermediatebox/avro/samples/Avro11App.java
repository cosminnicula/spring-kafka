package dev.intermediatebox.avro.samples;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import dev.intermediatebox.avro.data.Avro11Address;
import dev.intermediatebox.avro.data.Avro11BankAccount;
import dev.intermediatebox.avro.data.Avro11Country;
import dev.intermediatebox.avro.data.Avro11Person;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;

public class Avro11App {

	public static void main(String[] args) {
		var file = new File("avro11.avro");

		write(file);
		System.out.println("");
		read(file);
	}

	private static void write(File toFile) {
		var bankAccount1 = new Avro11BankAccount("Bank Central Asia", "Timotius", "89122675092", "0140410", "IDR");
		var bankAccount2 = new Avro11BankAccount("Bank Central Asia", "Pamungkas", "891009287556", "0140915", "USD");
		var bankAccount3 = new Avro11BankAccount("Bank Mandiri", "Timotius Pamungkas", "201748928664", "0080033",
				"IDR");
		var bankAccounts = List.of(bankAccount1, bankAccount2, bankAccount3);

		var address = new Avro11Address("First address line", null, "Tangerang Selatan", "15321",
				new Avro11Country("ID", "Indonesia"));

		var data = Avro11Person.newBuilder().setAddress(address).setBankAccounts(bankAccounts)
				.setBirthDate(LocalDate.of(1990, 5, 18)).setFirstName("Timotius").setLastName("Pamungkas").build();

		var datumWriter = new SpecificDatumWriter<>(Avro11Person.class);

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
		var datumReader = new SpecificDatumReader<>(Avro11Person.class);

		System.out.println("Reading " + fromFile.getName());
		try (var dataReader = new DataFileReader<>(fromFile, datumReader)) {
			dataReader.forEach(System.out::println);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
