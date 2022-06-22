kafka-topics --list --bootstrap-server=localhost:9092

kafka-console-consumer --topic sampletopic --from-beginning --bootstrap-server=localhost:9092

---

Producer & Consumer example
1.HelloKafkaConsumer
kafka-topics --create --topic=t-hello --partitions=1 --replication-factor=1 --bootstrap-server=localhost:9092

2.FixedRateConsumer
kafka-topics --create --topic=t-fixedrate --partitions=1 --replication-factor=1 --bootstrap-server=localhost:9092

3. FixedRateConsumer2
kafka-topics --create --topic=t-fixedrate-2 --partitions=1 --replication-factor=1 --bootstrap-server=localhost:9092

4. KafkaKeyProducer
kafka-topics --create --topic=t-multi-partitions --partitions=3 --replication-factor=1 --bootstrap-server=localhost:9092
kafka-topics --describe --topic=t-multi-partitions --bootstrap-server=localhost:9092
kafka-console-consumer --topic t-multi-partitions --offset=earliest --partition=0 --bootstrap-server=localhost:9092
kafka-console-consumer --topic t-multi-partitions --offset=earliest --partition=1 --bootstrap-server=localhost:9092
kafka-console-consumer --topic t-multi-partitions --offset=earliest --partition=2 --bootstrap-server=localhost:9092

5. KafkaKeyConsumer (multiple consumers for each topic)

6. EmployeeJsonProducer & EmployeeJsonConsumer
kafka-topics --create --topic=t-employee --partitions=1 --replication-factor=1 --bootstrap-server=localhost:9092
kafka-console-consumer --topic t-employee --offset=earliest --partition=0 --bootstrap-server=localhost:9092

7.CommodityProducer & CommodityDashboardConsumer & CommodityDashboardNotification & CommodityApi
kafka-topics --create --topic=t-commodity --partitions=1 --replication-factor=1 --bootstrap-server=localhost:9092
kafka-console-consumer --topic t-commodity --offset=earliest --partition=0 --bootstrap-server=localhost:9092
kafka-consumer-groups --group cg-dashboard --describe --bootstrap-server=localhost:9092
kafka-consumer-groups --group cg-dashboard --execute --reset-offsets --to-offset 10 --topic=t-commodity --bootstrap-server=localhost:9092

8.RebalanceProducer & RebalanceConsumer
kafka-topics --create --topic=t-rebalance --partitions=1 --replication-factor=1 --bootstrap-server=localhost:9092
kafka-console-consumer --topic t-rebalance --offset=earliest --partition=0 --bootstrap-server=localhost:9092
--while leaving the producer and consumer running, add a second partition to rebalance
kafka-topics --alter --topic=t-rebalance --partitions=2 --bootstrap-server=localhost:9092
kafka-topics --describe --topic=t-rebalance --bootstrap-server=localhost:9092
--after a couple of minutes, the producer will start sending to Partition 1 as well