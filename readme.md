kafka-topics --list --bootstrap-server=localhost:9092

kafka-console-consumer --topic sampletopic --from-beginning --bootstrap-server=localhost:9092

kafka-topics --delete --topic sampletopic --bootstrap-server=localhost:9092

---
Kafka Core (producer, consumer, producer-consumer)

Producer & Consumer example
1.HelloKafkaConsumer
kafka-topics --create --topic=t-hello --partitions=1 --replication-factor=1 --bootstrap-server=localhost:9092

2.FixedRateConsumer
kafka-topics --create --topic=t-fixedrate --partitions=1 --replication-factor=1 --bootstrap-server=localhost:9092

3.FixedRateConsumer2
kafka-topics --create --topic=t-fixedrate-2 --partitions=1 --replication-factor=1 --bootstrap-server=localhost:9092

4.KafkaKeyProducer
kafka-topics --create --topic=t-multi-partitions --partitions=3 --replication-factor=1 --bootstrap-server=localhost:9092
kafka-topics --describe --topic=t-multi-partitions --bootstrap-server=localhost:9092
kafka-console-consumer --topic t-multi-partitions --offset=earliest --partition=0 --bootstrap-server=localhost:9092
kafka-console-consumer --topic t-multi-partitions --offset=earliest --partition=1 --bootstrap-server=localhost:9092
kafka-console-consumer --topic t-multi-partitions --offset=earliest --partition=2 --bootstrap-server=localhost:9092

5.KafkaKeyConsumer (multiple consumers for each topic)

6.EmployeeJsonProducer & EmployeeJsonConsumer
kafka-topics --create --topic=t-employee --partitions=1 --replication-factor=1 --bootstrap-server=localhost:9092
kafka-console-consumer --topic t-employee --offset=earliest --partition=0 --bootstrap-server=localhost:9092

7.CommodityProducer & CommodityDashboardConsumer & CommodityDashboardNotification & CommodityApi
kafka-topics --create --topic=t-commodity --partitions=1 --replication-factor=1 --bootstrap-server=localhost:9092
kafka-console-consumer --topic t-commodity --offset=earliest --partition=0 --bootstrap-server=localhost:9092
kafka-consumer-groups --group cg-dashboard --describe --bootstrap-server=localhost:9092
kafka-consumer-groups --group cg-dashboard --execute --reset-offsets --to-offset 10 --topic=t-commodity --bootstrap-server=localhost:9092

8.a.RebalanceProducer & RebalanceConsumer
kafka-topics --create --topic=t-rebalance --partitions=1 --replication-factor=1 --bootstrap-server=localhost:9092
kafka-console-consumer --topic t-rebalance --offset=earliest --partition=0 --bootstrap-server=localhost:9092
--while leaving the producer and consumer running, add a second partition to rebalance
kafka-topics --alter --topic=t-rebalance --partitions=2 --bootstrap-server=localhost:9092
kafka-topics --describe --topic=t-rebalance --bootstrap-server=localhost:9092
--after a couple of minutes, the producer will start sending to Partition 1 as well

8.b.Override producer/consumer factory with custom configuration (ProducerCustomConfig / ConsumerCustomConfig) -> after configuring to 3 partitions, the producer/consumers will rebalance according to configured METADATA_MAX_AGE_CONFIG
kafka-topics --alter --topic=t-rebalance --partitions=3 --bootstrap-server=localhost:9092
kafka-topics --describe --topic=t-rebalance --bootstrap-server=localhost:9092  

9.CarLocationConsumer & CarLocationProducer, CarLocationScheduler, CarLocation  (consumer message filtering)
kafka-topics --create --topic=t-location --partitions=1 --replication-factor=1 --bootstrap-server=localhost:9092
kafka-console-consumer --topic t-location --offset=earliest --partition=0 --bootstrap-server=localhost:9092

10.PurchaseRequestProducer & PurchaseRequestConsumer, CaffeineCacheConfig (idempotent consumer with cache)
kafka-topics --create --topic=t-purchase-request --partitions=1 --replication-factor=1 --bootstrap-server=localhost:9092
kafka-console-consumer --topic t-purchase-request --offset=earliest --partition=0 --bootstrap-server=localhost:9092

11.PaymentRequestProducer & PaymentRequestConsumer (custom cache key)
kafka-topics --create --topic=t-payment-request --partitions=1 --replication-factor=1 --bootstrap-server=localhost:9092
kafka-console-consumer --topic t-payment-request --offset=earliest --partition=0 --bootstrap-server=localhost:9092

12.FoodOrderProducer & FoodOrderConsumer, FoodOrderErrorHandler (KafkaListener error handler)
kafka-topics --create --topic=t-food-order --partitions=1 --replication-factor=1 --bootstrap-server=localhost:9092

13.SimpleNumberProducer & SimpleNumberConsumer, ConsumerCustomConfig (Global error handler)
kafka-topics --create --topic=t-simple-number --partitions=1 --replication-factor=1 --bootstrap-server=localhost:9092

14.ImageProducer & ImageConsumer (blocking retrying consumer -> the consumer keeps retrying and further messages are not processed on a particular topic until success or retry failure -> can cause bottleneck -> create a retry policy not too short and not too long)
kafka-topics --create --topic=t-image --partitions=2 --replication-factor=1 --bootstrap-server=localhost:9092
kafka-topics --describe --topic=t-image --bootstrap-server=localhost:9092

15.InvoiceProducer & InvoiceConsumer (dead letter topic -> is like a combination of retry + producer publishing to another topic called "dead letter topic")
kafka-topics --create --topic=t-invoice --partitions=2 --replication-factor=1 --bootstrap-server=localhost:9092
kafka-topics --create --topic=t-invoice-dead-letter --partitions=2 --replication-factor=1 --bootstrap-server=localhost:9092
kafka-topics --describe --topic=t-invoice --bootstrap-server=localhost:9092
kafka-topics --describe --topic=t-invoice-dead-letter --bootstrap-server=localhost:9092
kafka-console-consumer --topic t-invoice --offset=earliest --partition=0 --bootstrap-server=localhost:9092
kafka-console-consumer --topic t-invoice --offset=earliest --partition=1 --bootstrap-server=localhost:9092
kafka-console-consumer --topic t-invoice-dead-letter --offset=earliest --partition=0 --bootstrap-server=localhost:9092
kafka-console-consumer --topic t-invoice-dead-letter --offset=earliest --partition=1 --bootstrap-server=localhost:9092

16.Image2Producer & Image2Consumer (non-blocking retrying consumer -> when consumer encounters error, it proceeds by consuming next message(s), while the previous message is retried in the background / non-blocking)
kafka-topics --create --topic=t-image-2 --partitions=2 --replication-factor=1 --bootstrap-server=localhost:9092

17.GeneralLedgerProducer & GeneralLedgerConsumer, GeneralLedgerScheduler (pause/resume consumers)
kafka-topics --create --topic=t-general-ledger --partitions=1 --replication-factor=1 --bootstrap-server=localhost:9092

---

Kafka microservice (kafka-ms-order, kafka-ms-pattern, kafka-ms-reward, kafka-ms-storage)

kafka-topics --describe --topic=t-commodity-order --bootstrap-server=localhost:9092
