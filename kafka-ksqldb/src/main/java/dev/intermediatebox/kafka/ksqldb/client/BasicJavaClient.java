package dev.intermediatebox.kafka.ksqldb.client;

import java.time.LocalTime;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadLocalRandom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import io.confluent.ksql.api.client.Client;
import io.confluent.ksql.api.client.ClientOptions;
import io.confluent.ksql.api.client.InsertsPublisher;
import io.confluent.ksql.api.client.KsqlObject;

@Service
public class BasicJavaClient {

	private static final Logger log = LoggerFactory.getLogger(BasicJavaClient.class);

	private static final ClientOptions CLIENT_OPTIONS = ClientOptions.create().setHost("localhost").setPort(8088);

	public void createStream() throws InterruptedException, ExecutionException {
		var client = Client.create(CLIENT_OPTIONS);

		var statement = "DROP STREAM IF EXISTS `s-java-client` DELETE TOPIC;";

		// client.executeStatement is asynchronous; call get() to make it synchronous
		var result = client.executeStatement(statement).get();

		log.info(result.toString());

		statement = """
				CREATE OR REPLACE STREAM `s-java-client` (
				  `fieldOne` VARCHAR,
				  `fieldTwo` INT,
				  `fieldThree` BOOLEAN
				) WITH (
				  KAFKA_TOPIC = 't-java-client',
				  PARTITIONS = 2,
				  VALUE_FORMAT = 'JSON'
				);
				""";

		result = client.executeStatement(statement).get();

		log.info(result.toString());
	}

	public void describeStream() throws InterruptedException, ExecutionException {
		var client = Client.create(CLIENT_OPTIONS);

		var description = client.describeSource("`s-java-client`").get();

		log.info("{} {} has the following fields : {}", description.type(), description.name(), description.fields());

		client.close();
	}

	public void listObjects() throws InterruptedException, ExecutionException {
		var client = Client.create(CLIENT_OPTIONS);

		log.info("Kafka topics");
		client.listTopics().get().forEach(t -> log.info("topic {} has {} partitions", t.getName(), t.getPartitions()));

		log.info("\n\n");
		log.info("Kafka streams");
		client.listStreams().get()
				.forEach(s -> log.info("stream {} is coming from topic {}", s.getName(), s.getTopic()));

		log.info("\n\n");
		log.info("Kafka tables");
		client.listTables().get()
				.forEach(tbl -> log.info("table {} is coming from topic {}", tbl.getName(), tbl.getTopic()));

		client.close();
	}

	private KsqlObject generateNewRow() {
		return new KsqlObject().put("`fieldOne`", "Now is " + LocalTime.now())
				.put("`fieldTwo`", ThreadLocalRandom.current().nextInt())
				.put("`fieldThree`", ThreadLocalRandom.current().nextBoolean());
	}

	public void insertSingle() throws InterruptedException, ExecutionException {
		var client = Client.create(CLIENT_OPTIONS);

		var newRow = generateNewRow();

		client.insertInto("`s-java-client`", newRow).get();

		client.close();
	}

	public void insertStream(int rows) throws InterruptedException, ExecutionException {
		var client = Client.create(CLIENT_OPTIONS);

		var insertPublisher = new InsertsPublisher();
		var acksPublisher = client.streamInserts("`s-java-client`", insertPublisher).get();

		for (int i = 0; i < rows; i++) {
			var newRow = generateNewRow();
			insertPublisher.accept(newRow);
		}

		insertPublisher.complete();

		client.close();
	}

	public void pullQuery() throws InterruptedException, ExecutionException {
		var client = Client.create(CLIENT_OPTIONS);

		var pullQueryResult = client.executeQuery("SELECT * FROM `s-java-client` LIMIT 10;").get();

		pullQueryResult.forEach(row -> log.info(row.toString()));

		client.close();
	}

	public void pushQuerySync() throws InterruptedException, ExecutionException {
		var client = Client.create(CLIENT_OPTIONS);

		var pushQueryResult = client.streamQuery("SELECT * FROM `s-java-client` EMIT CHANGES;").get();

		while (true) {
			var row = pushQueryResult.poll();
			log.info("Received row : {}", row);
		}
	}

	public void pushQueryAsync() throws InterruptedException, ExecutionException {
		var client = Client.create(CLIENT_OPTIONS);

		client.streamQuery("SELECT * FROM `s-java-client` EMIT CHANGES;").thenAccept(pushQueryResult -> {
			var subscriber = new LogRowSubscriber();
			pushQueryResult.subscribe(subscriber);
		}).exceptionally(e -> {
			log.error("Request error : {}", e.getMessage());
			return null;
		});
	}

}
