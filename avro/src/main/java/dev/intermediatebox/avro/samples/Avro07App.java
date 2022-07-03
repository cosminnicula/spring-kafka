package dev.intermediatebox.avro.samples;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dev.intermediatebox.avro.data.Avro07;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;

public class Avro07App {

	public static void main(String[] args) {
		var file = new File("avro07.avro");

		write(file);
		System.out.println("");
		read(file);
	}

	private static void write(File toFile) {
		var bestHeroVotes = Map.ofEntries(Map.entry("Voter01", "Superman"), Map.entry("Voter02", "Aquaman"),
				Map.entry("Voter03", "Wonder Woman"), Map.entry("Voter04", "Wonder Woman"),
				Map.entry("Voter05", "Superman"));

		var lotteryNumbers = new HashMap<String, List<Integer>>();
		lotteryNumbers.put("Anna", List.of(298, 461));
		lotteryNumbers.put("Betty", List.of(209, 519, 201, 468));
		lotteryNumbers.put("Charlie", List.of(194));
		lotteryNumbers.put("Donna", List.of(891, 300, 239));

		var data = Avro07.newBuilder().setBestHeroVotes(bestHeroVotes).setLotteryNumbers(lotteryNumbers).build();

		var datumWriter = new SpecificDatumWriter<>(Avro07.class);

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
		var datumReader = new SpecificDatumReader<>(Avro07.class);

		System.out.println("Reading " + fromFile.getName());
		try (var dataReader = new DataFileReader<>(fromFile, datumReader)) {
			dataReader.forEach(System.out::println);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
