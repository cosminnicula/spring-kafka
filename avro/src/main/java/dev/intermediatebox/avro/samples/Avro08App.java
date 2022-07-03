package dev.intermediatebox.avro.samples;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import dev.intermediatebox.avro.data.Avro08;
import dev.intermediatebox.avro.data.Sha256;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;

public class Avro08App {

	public static void main(String[] args) throws NoSuchAlgorithmException {
		var file = new File("avro08.avro");

		write(file);
		System.out.println("");
		read(file);
	}

	private static void write(File toFile) throws NoSuchAlgorithmException {
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		byte[] sha256Bytes = digest.digest("A dummy string for hash".getBytes(StandardCharsets.UTF_8));
		Sha256 sha256Value = new Sha256(sha256Bytes);
		var data = Avro08.newBuilder().setHash(sha256Value).build();

		var datumWriter = new SpecificDatumWriter<>(Avro08.class);

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
		var datumReader = new SpecificDatumReader<>(Avro08.class);

		System.out.println("Reading " + fromFile.getName());
		try (var dataReader = new DataFileReader<>(fromFile, datumReader)) {
			dataReader.forEach(data -> {
				var bytesFromAvro = data.getHash().bytes();
				var base64 = Base64.getEncoder().encodeToString(bytesFromAvro);
				System.out.println("Hash value on base64 : " + base64);
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
