package dev.intermediatebox.avro.samples;

import java.io.File;
import java.io.IOException;

import dev.intermediatebox.avro.data.BookAvro;
import dev.intermediatebox.avro.entity.BookJson;
import org.apache.avro.Schema;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.io.DecoderFactory;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;

import com.fasterxml.jackson.databind.ObjectMapper;

public class BookJsonAvroConversion {

	public static void main(String[] args) throws IOException {
		var bookJsonString = "{\r\n" + "   \"title\":\"Kafka is Awesome!\",\n"
				+ "   \"author\":\"Timotius Pamungkas\",\n" + "   \"price\":138\n" + "}";
		var fileString = new File("fromJsonStringToAvro.avro");

		var bookAsAvro1 = convertJsonToAvro(bookJsonString);

		write(bookAsAvro1, fileString);
		var jsonString = readAvroToJsonString(fileString);
		System.out.println(jsonString);

		// --------------------------------------------------

		System.out.println("\n");

		var bookJsonObject = new BookJson();
		bookJsonObject.setAuthor("Paulo Coelho");
		bookJsonObject.setTitle("The Alchemist");
		bookJsonObject.setPrice(271);

		var fileObject = new File("fromJsonObjectToAvro.avro");

		var objectMapper = new ObjectMapper();
		var bookAsAvro2 = convertJsonToAvro(objectMapper.writeValueAsString(bookJsonObject));

		write(bookAsAvro2, fileObject);
		System.out.println("From JSON string to JSON object via jackson");
		var bookJsonObjectFromAvro = objectMapper.readValue(readAvroToJsonString(fileString), BookJson.class);
		System.out.println(bookJsonObjectFromAvro);
	}

	private static BookAvro convertJsonToAvro(String bookJsonString) throws IOException {
		var avroSchemaFile = BookJsonAvroConversion.class.getResourceAsStream("/BookAvro.avsc");
		var avroSchema = new Schema.Parser().parse(avroSchemaFile);
		var avroBookdecoder = DecoderFactory.get().jsonDecoder(avroSchema, bookJsonString);
		var avroReader = new SpecificDatumReader<BookAvro>(avroSchema);

		return avroReader.read(null, avroBookdecoder);
	}

	private static void write(BookAvro data, File toFile) throws IOException {
		var datumWriter = new SpecificDatumWriter<>(BookAvro.class);

		try (var dataWriter = new DataFileWriter<>(datumWriter)) {
			dataWriter.create(data.getSchema(), toFile);
			dataWriter.append(data);

			System.out.println("Writing");
			System.out.println(data);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static String readAvroToJsonString(File fromFile) {
		var datumReader = new SpecificDatumReader<>(BookAvro.class);
		var sb = new StringBuilder();

		System.out.println("Reading to JSON string");
		try (var dataReader = new DataFileReader<>(fromFile, datumReader)) {
			dataReader.forEach(sb::append);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return sb.toString();
	}

}
