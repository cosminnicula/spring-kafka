kafka-topics --list --bootstrap-server=localhost:9092

kafka-console-consumer --topic sampletopic --from-beginning --bootstrap-server=localhost:9092

kafka-topics --delete --topic sampletopic --bootstrap-server=localhost:9092

---
Kafka Core (producer, consumer, producer-consumer)

docker-compose up

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

Kafka microservice (kafka-ms-order -> api + kafka producer, kafka-ms-pattern -> kafka consumer, kafka-ms-reward, kafka-ms-storage)

1.
(t-commodity-order is created automatically by KafkaConfig)
kafka-topics --describe --topic=t-commodity-order --bootstrap-server=localhost:9092
kafka-console-consumer --topic t-commodity-order --from-beginning --bootstrap-server=localhost:9092

2.DiscountProducer and PromotionProducer publish to t-commodity-promotion; PromotionConsumer consumes from t-commodity-promotion
kafka-topics --create --topic=t-commodity-promotion --partitions=1 --replication-factor=1 --bootstrap-server=localhost:9092
kafka-console-consumer --topic t-commodity-promotion --from-beginning --bootstrap-server=localhost:9092

3.OrderConsumer (kafka-ms-reward) and OrderConsumer(kafka-ms-pattern) both listen to t-commodity-order
OrderConsumer (kafka-ms-reward) extracts the bonus percentage from the headers if exists
OrderConsumer (kafka-ms-pattern) simply consumes the message, without looking at the headers

4.OrderConsumer (kafka-ms-reward) will act also as a publisher (OrderReplyConsumer), publishing messages to t-commodity-order-reply. OrderProducer will act also as a consumer (OrderReplyConsumer), listening to t-commodity-order-reply
This flow is commonly known as asynchronous request/reply
t-commodity-order-reply is created automatically by KafkaConfig

---

Kafka Streams

1.Run kafka-ms-order, kafka-stream-sample and kafka-stream-storage (and run Postman -> Promotion -> 1000 iterations, 500 interval)
t-commodity-promotion is the source; t-commodity-promotion-uppercase is the sink; PromotionUppercaseStream, KafkaStreamConfig; kafka-stream-storage hash 2 consumers: one for t-commodity-promotion and one for t-commodity-promotion-uppercase
kafka-topics --bootstrap-server localhost:9092 --create --partitions 1 --replication-factor 1 --topic t-commodity-order
kafka-topics --bootstrap-server localhost:9092 --create --partitions 1 --replication-factor 1 --topic t-commodity-order-masked
kafka-topics --bootstrap-server localhost:9092 --create --partitions 1 --replication-factor 1 --topic t-commodity-promotion
kafka-topics --bootstrap-server localhost:9092 --create --partitions 1 --replication-factor 1 --topic t-commodity-promotion-uppercase

note that PromotionUppercaseConsumer (kafka-stream-storage) displays "promotionCode=null" in the output console -> PromotionUppercaseJsonStream fixes the problem of PromotionUppercaseStream

furthermore, Spring provides a default Json Serde -> see PromotionUppercaseSpringJsonStream

lastly, custom JSON serde can be used for custom formats see CustomJsonSerializer, CustomJsonDeserializer, CustomJsonSerde, PromotionSerde, PromotionUppercaseCustomJsonStream


---

Kafka Stream - Commodity

Topology - see kafka-stream-commodity-topology.jpg

1.MaskOrder
See MaskOrderStream (kafka-stream-sample), OrderMessage (kafka-stream-sample), CommodityStreamUtil (kafka-stream-sample)
Run kafka-stream-sample, kafka-ms-order
kafka-console-consumer --topic t-commodity-order-masked --from-beginning --bootstrap-server=localhost:9092
Run Postman -> Commodity Order -> Order 2 Random Items -> see in the kafka-console-consumer that the creditCardNumber is masked

2.Sink processors (see kafka-stream-commodity-topology-sink-processors-high-level.jpg and kafka-stream-commodity-topology-sink-processors-detail.jpg)
See OrderPatternMessage (kafka-stream-sample), OrderRewardMessage (kafka-stream-sample), CommodityOneStream (kafka-stream-sample)

kafka-topics --bootstrap-server localhost:9092 --create --partitions 1 --replication-factor 1 --topic t-commodity-pattern-one
kafka-topics --bootstrap-server localhost:9092 --create --partitions 1 --replication-factor 1 --topic t-commodity-reward-one
kafka-topics --bootstrap-server localhost:9092 --create --partitions 1 --replication-factor 1 --topic t-commodity-storage-one

kafka-console-consumer --topic t-commodity-pattern-one --from-beginning --property print.key=true --bootstrap-server=localhost:9092
kafka-console-consumer --topic t-commodity-reward-one --from-beginning --property print.key=true --bootstrap-server=localhost:9092
kafka-console-consumer --topic t-commodity-storage-one --from-beginning --property print.key=true --bootstrap-server=localhost:9092

Run kafka-ms-order, kafka-stream-sample
Run Postman -> Commodity Order collection (Order 1 Random Item, Order 2 Random Item, Order 3 Random Item) -> 1000 times, 1000 delay

3.a.Topology changes (see kafka-stream-commodity-topology-3.jpg): split t-commodity-pattern stream into two categories: plastic and non-plastic items; t-commodity-reward -> give reward only for item that is not cheap; t-commodity-order -> key is base64

See CommodityTwoStream

kafka-topics --bootstrap-server localhost:9092 --create --partitions 1 --replication-factor 1 --topic t-commodity-pattern-two-plastic
kafka-topics --bootstrap-server localhost:9092 --create --partitions 1 --replication-factor 1 --topic t-commodity-pattern-two-notplastic
kafka-topics --bootstrap-server localhost:9092 --create --partitions 1 --replication-factor 1 --topic t-commodity-reward-two
kafka-topics --bootstrap-server localhost:9092 --create --partitions 1 --replication-factor 1 --topic t-commodity-storage-two

kafka-console-consumer --topic t-commodity-pattern-two-plastic --from-beginning --property print.key=true --bootstrap-server=localhost:9092
kafka-console-consumer --topic t-commodity-pattern-two-notplastic --from-beginning --property print.key=true --bootstrap-server=localhost:9092
kafka-console-consumer --topic t-commodity-reward-two --from-beginning --property print.key=true --bootstrap-server=localhost:9092
kafka-console-consumer --topic t-commodity-storage-two --from-beginning --property print.key=true --bootstrap-server=localhost:9092

Run kafka-ms-order, kafka-stream-sample
Run Postman -> Commodity Order collection (Create Plastic & Non Plastic Order)

3.b.as an alternative to stream branching, see CommodityThreeStream

See CommodityThreeStream

kafka-topics --bootstrap-server localhost:9092 --create --partitions 1 --replication-factor 1 --topic t-commodity-pattern-three-plastic
kafka-topics --bootstrap-server localhost:9092 --create --partitions 1 --replication-factor 1 --topic t-commodity-pattern-three-notplastic
kafka-topics --bootstrap-server localhost:9092 --create --partitions 1 --replication-factor 1 --topic t-commodity-reward-three
kafka-topics --bootstrap-server localhost:9092 --create --partitions 1 --replication-factor 1 --topic t-commodity-storage-three

kafka-console-consumer --topic t-commodity-pattern-three-plastic --from-beginning --property print.key=true --bootstrap-server=localhost:9092
kafka-console-consumer --topic t-commodity-pattern-three-notplastic --from-beginning --property print.key=true --bootstrap-server=localhost:9092
kafka-console-consumer --topic t-commodity-reward-three --from-beginning --property print.key=true --bootstrap-server=localhost:9092
kafka-console-consumer --topic t-commodity-storage-three --from-beginning --property print.key=true --bootstrap-server=localhost:9092

3.c.as an alternative to deprectated "branch" method used in CommodityTwoStream, use CommodityTwoSplitStream

See CommodityTwoSplitStream

4.Topology change: the key of the OrderReward needs to be changed and become the location

See CommodityFourStream (rewardStream uses map instead of mapValues)

kafka-topics --bootstrap-server localhost:9092 --create --partitions 1 --replication-factor 1 --topic t-commodity-pattern-four-plastic
kafka-topics --bootstrap-server localhost:9092 --create --partitions 1 --replication-factor 1 --topic t-commodity-pattern-four-notplastic
kafka-topics --bootstrap-server localhost:9092 --create --partitions 1 --replication-factor 1 --topic t-commodity-reward-four
kafka-topics --bootstrap-server localhost:9092 --create --partitions 1 --replication-factor 1 --topic t-commodity-storage-four

5.Process stream result without passing it to sink stream (simulate fraud notification)

See CommodityFiveStream

Run Postman -> Commodity Order -> run "Order 1 Random items" and modify orderLocation from random to somehting that starts with "C"

kafka-topics --bootstrap-server localhost:9092 --create --partitions 1 --replication-factor 1 --topic t-commodity-pattern-five-plastic
kafka-topics --bootstrap-server localhost:9092 --create --partitions 1 --replication-factor 1 --topic t-commodity-pattern-five-notplastic
kafka-topics --bootstrap-server localhost:9092 --create --partitions 1 --replication-factor 1 --topic t-commodity-reward-five
kafka-topics --bootstrap-server localhost:9092 --create --partitions 1 --replication-factor 1 --topic t-commodity-storage-five
kafka-topics --bootstrap-server localhost:9092 --create --partitions 1 --replication-factor 1 --topic t-commodity-fraud-five

6.Topology change (see kafka-stream-commodity-topology-6.jpg)

See CommoditySixStream

Run Postman -> Commodity Order -> run "Order 1 Random items" and modify orderLocation from random to somehting that starts with "C"

kafka-topics --bootstrap-server localhost:9092 --create --partitions 1 --replication-factor 1 --topic t-commodity-pattern-six-plastic
kafka-topics --bootstrap-server localhost:9092 --create --partitions 1 --replication-factor 1 --topic t-commodity-pattern-six-notplastic
kafka-topics --bootstrap-server localhost:9092 --create --partitions 1 --replication-factor 1 --topic t-commodity-reward-six
kafka-topics --bootstrap-server localhost:9092 --create --partitions 1 --replication-factor 1 --topic t-commodity-storage-six
kafka-topics --bootstrap-server localhost:9092 --create --partitions 1 --replication-factor 1 --topic t-commodity-fraud-six

kafka-console-consumer --topic t-commodity-fraud-six --from-beginning --property print.key=true --property value.deserializer=org.apache.kafka.common.serialization.IntegerDeserializer --bootstrap-server=localhost:9092

---

Kafka Stream - Feedback

1.New topology (see kafka-stream-commodity-topology-feedback.jpg)

See FeedbackApi, FeedbackRequest, FeedbackMessage, FeedbackProducer, FeedbackAction, FeedbackService (kafka-ms-order)
See FeedbackOneStream (kafka-stream-sample)

kafka-topics --bootstrap-server localhost:9092 --create --partitions 1 --replication-factor 1 --topic t-commodity-feedback
kafka-topics --bootstrap-server localhost:9092 --create --partitions 1 --replication-factor 1 --topic t-commodity-feedback-one-good

kafka-console-consumer --topic t-commodity-feedback --from-beginning --property print.key=true --bootstrap-server=localhost:9092
kafka-console-consumer --topic t-commodity-feedback-one-good --from-beginning --property print.key=true --bootstrap-server=localhost:9092

Run Postman -> Feedback -> Create Good Feedback

Note that the key is null in the kafka-console-consumer output

2.Topology change (see kafka-stream-commodity-feedback-2.jpg): set key to branch location

See FeedbackTwoStream (kafka-stream-sample)

kafka-topics --bootstrap-server localhost:9092 --create --partitions 1 --replication-factor 1 --topic t-commodity-feedback-two-good 

kafka-console-consumer --topic t-commodity-feedback-two-good --from-beginning --property print.key=true --bootstrap-server=localhost:9092

Run again Postman -> Feedback -> Create Good Feedback

3.Topology change (see kafka-stream-commodity-feedback-3.jpg): split stream to good feedback and bad feedback

See FeedbackThreeStream (kafka-stream-sample)

kafka-topics --bootstrap-server localhost:9092 --create --partitions 1 --replication-factor 1 --topic t-commodity-feedback-three-good
kafka-topics --bootstrap-server localhost:9092 --create --partitions 1 --replication-factor 1 --topic t-commodity-feedback-three-bad

kafka-console-consumer --topic t-commodity-feedback-three-good --from-beginning --property print.key=true --bootstrap-server=localhost:9092
kafka-console-consumer --topic t-commodity-feedback-three-bad --from-beginning --property print.key=true --bootstrap-server=localhost:9092

Run again Postman -> Feedback -> Create Good Feedback and Create Bad Feedback

4.Topology change (see kafka-stream-commodity-feedback-4.jpg): count good and bad feedback

See FeedbackFourStream (kafka-stream-sample)

kafka-topics --bootstrap-server localhost:9092 --create --partitions 1 --replication-factor 1 --topic t-commodity-feedback-four-good
kafka-topics --bootstrap-server localhost:9092 --create --partitions 1 --replication-factor 1 --topic t-commodity-feedback-four-bad
kafka-topics --bootstrap-server localhost:9092 --create --partitions 1 --replication-factor 1 --topic t-commodity-feedback-four-good-count
kafka-topics --bootstrap-server localhost:9092 --create --partitions 1 --replication-factor 1 --topic t-commodity-feedback-four-bad-count

kafka-console-consumer --topic t-commodity-feedback-four-good --from-beginning --property print.key=true --bootstrap-server=localhost:9092
kafka-console-consumer --topic t-commodity-feedback-four-bad --from-beginning --property print.key=true --bootstrap-server=localhost:9092
kafka-console-consumer --topic t-commodity-feedback-four-good-count --from-beginning --property print.key=true --property value.deserializer=org.apache.kafka.common.serialization.LongDeserializer --bootstrap-server=localhost:9092
kafka-console-consumer --topic t-commodity-feedback-four-bad-count --from-beginning --property print.key=true --property value.deserializer=org.apache.kafka.common.serialization.LongDeserializer --bootstrap-server=localhost:9092

Run again Postman -> Feedback -> Create Random Feedback

Note that t-commodity-feedback-four-good-count and t-commodity-feedback-four-bad-count consumers have --property value.deserializer=org.apache.kafka.common.serialization.LongDeserializer

the default delay that is used to flush data from Ktable to stream is 30 seconds. it can be controlled via commit.interval.ms -> see KafkaStreamConfig COMMIT_INTERVAL_MS_CONFIG

5.Avoid processing the input stream more than once (avoid using .to more than once)

See FeedbackFiveStream (kafka-stream-sample)

kafka-topics --bootstrap-server localhost:9092 --create --partitions 1 --replication-factor 1 --topic t-commodity-feedback-five-good
kafka-topics --bootstrap-server localhost:9092 --create --partitions 1 --replication-factor 1 --topic t-commodity-feedback-five-bad
kafka-topics --bootstrap-server localhost:9092 --create --partitions 1 --replication-factor 1 --topic t-commodity-feedback-five-good-count
kafka-topics --bootstrap-server localhost:9092 --create --partitions 1 --replication-factor 1 --topic t-commodity-feedback-five-bad-count

Note that the t-commodity-feedback-five-good consumer does not output anything, because when using "repartition", the output topic is for internal Kafka use and the name configured in the code is not the actual Kafka topic name

6.Topology change (see kafka-stream-commodity-feedback-6.jpg)

See FeedbackSixStream (kafka-stream-sample)

kafka-topics --bootstrap-server localhost:9092 --create --partitions 1 --replication-factor 1 --topic t-commodity-feedback-six-good
kafka-topics --bootstrap-server localhost:9092 --create --partitions 1 --replication-factor 1 --topic t-commodity-feedback-six-bad
kafka-topics --bootstrap-server localhost:9092 --create --partitions 1 --replication-factor 1 --topic t-commodity-feedback-six-good-count
kafka-topics --bootstrap-server localhost:9092 --create --partitions 1 --replication-factor 1 --topic t-commodity-feedback-six-bad-count
kafka-topics --bootstrap-server localhost:9092 --create --partitions 1 --replication-factor 1 --topic t-commodity-feedback-six-good-count-word
kafka-topics --bootstrap-server localhost:9092 --create --partitions 1 --replication-factor 1 --topic t-commodity-feedback-six-bad-count-word

---

Kafka Stream - Customer

1.New topology for Customer Purchase (see kafka-stream-commodity-customer.jpg)

See CustomerPurchaseMobileRequest, CustomerPurchaseWebRequest, PurchaseResponse, CustomerPurchaseApi, CustomerPurchaseMobileMessage, CustomerPurchaseWebMessage, CustomerPurchaseProducer, CustomerPurchaseAction, CustomerPurchaseService

kafka-topics --bootstrap-server localhost:9092 --create --partitions 1 --replication-factor 1 --topic t-commodity-customer-purchase-web
kafka-topics --bootstrap-server localhost:9092 --create --partitions 1 --replication-factor 1 --topic t-commodity-customer-purchase-mobile
kafka-topics --bootstrap-server localhost:9092 --create --partitions 1 --replication-factor 1 --topic t-commodity-customer-purchase-all

Run Postman -> Customer Purchase collection

kafka-console-consumer --topic t-commodity-customer-purchase-web --from-beginning --property print.key=true --bootstrap-server=localhost:9092
kafka-console-consumer --topic t-commodity-customer-purchase-mobile --from-beginning --property print.key=true --bootstrap-server=localhost:9092
kafka-console-consumer --topic t-commodity-customer-purchase-all --from-beginning --property print.key=true --bootstrap-server=localhost:9092

2.New topology for Customer Preference (see kafka-stream-commodity-customer-preference.jpg and kafka-stream-commodity-customer-preference-timeline.jpg)

See CustomerPreferenceShoppingCartRequest, CustomerPreferenceWishlistRequest, CustomerPreferenceApi, CustomerPreferenceShoppingCartMessage, CustomerPreferenceShoppingCartMessage, CustomerPreferenceWishlistMessage, CustomerPreferenceProducer, CustomerPreferenceAction, CustomerPreferenceService
See CustomerPreferenceShoppingCartAggregator, CustomerPreferenceWishlistAggregator, CustomerPreferenceOneStream

kafka-topics --bootstrap-server localhost:9092 --create --partitions 1 --replication-factor 1 --topic t-commodity-customer-preference-shopping-cart
kafka-topics --bootstrap-server localhost:9092 --create --partitions 1 --replication-factor 1 --topic t-commodity-customer-preference-wishlist
kafka-topics --bootstrap-server localhost:9092 --create --partitions 1 --replication-factor 1 --topic t-commodity-customer-preference-all

kafka-console-consumer --topic t-commodity-customer-preference-shopping-cart --from-beginning --property print.key=true --bootstrap-server=localhost:9092
kafka-console-consumer --topic t-commodity-customer-preference-wishlist --from-beginning --property print.key=true --bootstrap-server=localhost:9092
kafka-console-consumer --topic t-commodity-customer-preference-all --from-beginning --property print.key=true --bootstrap-server=localhost:9092

Run Postman -> Customer Preference collection -> Simulation

---

1.Kafka Stream - flash sale vote (see kafka-stream-flash-sale-1.jpg)

See FlashSaleVoteOneStream

The Kafka message is converted to "key: customerId, value: itemName" so entries could be treated as upsert in the Kafka stream table (groupBy itemName)

kafka-topics --bootstrap-server localhost:9092 --create --partitions 1 --replication-factor 1 --topic t-commodity-flashsale-vote
kafka-topics --bootstrap-server localhost:9092 --create --partitions 1 --replication-factor 1 --topic t-commodity-flashsale-vote-user-item --config "cleanup.policy=compact" --config "delete.retention.ms=2000"  --config "segment.ms=2000" --config "min.cleanable.dirty.ratio=0.01" --config "min.compaction.lag.ms=2000"
kafka-topics --bootstrap-server localhost:9092 --create --partitions 1 --replication-factor 1 --topic t-commodity-flashsale-vote-one-result
kafka-topics --bootstrap-server localhost:9092 --create --partitions 1 --replication-factor 1 --topic t-commodity-flashsale-vote-two-result

kafka-console-consumer --topic t-commodity-flashsale-vote --from-beginning --property print.key=true --bootstrap-server=localhost:9092
kafka-console-consumer --topic t-commodity-flashsale-vote-user-item --from-beginning --property print.key=true --bootstrap-server=localhost:9092
kafka-console-consumer --topic t-commodity-flashsale-vote-one-result --from-beginning --property print.key=true --property value.deserializer=org.apache.kafka.common.serialization.LongDeserializer --bootstrap-server=localhost:9092

Run Postman -> Flash sale -> Simulation

2.Kafka stream state store (stateful operations)

State store is on the same machine with processing node (no network overhead); The state store is not shared between processes or threads in processors
State store is fault-tolerant: it can be quickly recovered in case of failure (uses changelog topic)

ValueTansformer needs to implement either ValueTransformer or ValueTransformerSupplier interface

See FlashSaleVoteTwoStream

Run Postman -> Flash sale -> Create Random Flash Sale Vote

kafka-console-consumer --topic t-commodity-flashsale-vote-two-result --from-beginning --property print.key=true --property value.deserializer=org.apache.kafka.common.serialization.LongDeserializer --bootstrap-server=localhost:9092

Adjust FlashSaleVoteTwoValueTransformer -> voteStart / voteEnd and computer time to see ValueTransformer in action. 

---

1.Kafka stream - feedback rating: calculate average rating, based on sum and count of ratings; the average rating will be stored to Kafka stream state store

See FeedbackRatingOneMessage, FeedbackRatingOneStoreValue, FeedbackRatingOneValueTransformer, FeedbackRatingOneStream

kafka-topics --bootstrap-server localhost:9092 --create --partitions 1 --replication-factor 1 --topic t-commodity-feedback-rating-one

kafka-console-consumer --topic t-commodity-feedback-rating-one --from-beginning --property print.key=true --bootstrap-server=localhost:9092

Run Postman -> Feedback -> Simulation

2.Detailed rating (Map of ratings)

kafka-topics --bootstrap-server localhost:9092 --create --partitions 1 --replication-factor 1 --topic t-commodity-feedback-rating-two

See FeedbackRatingTwoMessage, FeedbackRatingTwoStoreValue, FeedbackRatingTwoValueTransformer, FeedbackRatingTwoStream

kafka-console-consumer --topic t-commodity-feedback-rating-two --from-beginning --property print.key=true --bootstrap-server=localhost:9092

Run Postman -> Feedback -> Create random feedback

---

1.Kafka stream - summing records

See InventoryRequest, InventoryApi, InventoryMessage, InventoryProducer, InventoryAction, InventoryService
See InventoryOneStream

kafka-topics --bootstrap-server localhost:9092 --create --partitions 1 --replication-factor 1 --topic t-commodity-inventory
kafka-topics --bootstrap-server localhost:9092 --create --partitions 1 --replication-factor 1 --topic t-commodity-inventory-total-one

kafka-console-consumer --topic t-commodity-inventory-total-one --from-beginning --property print.key=true --property value.deserializer=org.apache.kafka.common.serialization.LongDeserializer --bootstrap-server=localhost:9092

Run Postman -> Inventory -> Sum record simulation

2.Subtract value

See InventoryTwoStream

kafka-topics --bootstrap-server localhost:9092 --create --partitions 1 --replication-factor 1 --topic t-commodity-inventory-total-two

kafka-console-consumer --topic t-commodity-inventory-total-two --from-beginning --property print.key=true --property value.deserializer=org.apache.kafka.common.serialization.LongDeserializer --bootstrap-server=localhost:9092

Run Postman -> Inventory -> Subtract Record Simulation

3.Reduce

See InventoryThreeStream

kafka-topics --bootstrap-server localhost:9092 --create --partitions 1 --replication-factor 1 --topic t-commodity-inventory-total-three

kafka-console-consumer --topic t-commodity-inventory-total-three --from-beginning --property print.key=true --property value.deserializer=org.apache.kafka.common.serialization.LongDeserializer --bootstrap-server=localhost:9092

Run Postman -> Inventory -> Subtract Record Simulation

4.Timestamp extractor (extract transaction time from payload and use it as record timestamp)

Built-in timestamp extractors: FailedOnInvalidTimestamp, LogAndSkipOnInvalidTimestamp, UsePreviousTimeOnInvalidTimestamp, WallclockTimestampExtractor

See InventoryFourStream

kafka-topics --bootstrap-server localhost:9092 --create --partitions 1 --replication-factor 1 --topic t-commodity-inventory-total-four

kafka-console-consumer --topic t-commodity-inventory-total-four --from-beginning --property print.key=true --property value.deserializer=org.apache.kafka.common.serialization.LongDeserializer --bootstrap-server=localhost:9092

Run Postman -> Inventory -> Subtract Record Simulation (also adjust local Operating System time)

5.Tumbling Time Window (aggregate transactions on hourly basis)

See InventoryFiveStream

kafka-topics --bootstrap-server localhost:9092 --create --partitions 1 --replication-factor 1 --topic t-commodity-inventory-total-five

kafka-console-consumer --topic t-commodity-inventory-total-five --from-beginning --property print.key=true --property value.deserializer=org.apache.kafka.common.serialization.LongDeserializer --bootstrap-server=localhost:9092

Run Postman -> Inventory -> Window Simulation

Notice the format of the log messages in the IDE output: Something@1656449146/1656449146 -> Something is the original key, while the two epoch times represent the window start and window end

6.Hopping Time Window (aggregate transactions on hourly basis, with a hop interval of 20 minutes)

kafka-topics --bootstrap-server localhost:9092 --create --partitions 1 --replication-factor 1 --topic t-commodity-inventory-six

kafka-console-consumer --topic t-commodity-inventory-six --from-beginning --property print.key=true --property value.deserializer=org.apache.kafka.common.serialization.LongDeserializer --bootstrap-server=localhost:9092

Run Postman -> Inventory -> Window Simulation (first change the inventorySimulationItem variable)

---

Kafka stream/stream joining

1.Inner join Stream / Stream (left is online order, right is online payment; the key is online order number)

See OnlineOrder* classes
See OrderPaymentOneStream class

kafka-topics --bootstrap-server localhost:9092 --create --partitions 1 --replication-factor 1 --topic t-commodity-online-order
kafka-topics --bootstrap-server localhost:9092 --create --partitions 1 --replication-factor 1 --topic t-commodity-online-payment
kafka-topics --bootstrap-server localhost:9092 --create --partitions 1 --replication-factor 1 --topic t-commodity-join-order-payment-one

kafka-console-consumer --topic t-commodity-online-order --from-beginning --property print.key=true --bootstrap-server=localhost:9092
kafka-console-consumer --topic t-commodity-online-payment --from-beginning --property print.key=true --bootstrap-server=localhost:9092
kafka-console-consumer --topic t-commodity-join-order-payment-one --from-beginning --property print.key=true --bootstrap-server=localhost:9092

Run Postman -> Online Order / Payment -> Inner Join Simulation

2.Left join Stream / Stream

See OrderPaymentTwoStream class

kafka-topics --bootstrap-server localhost:9092 --create --partitions 1 --replication-factor 1 --topic t-commodity-join-order-payment-two

kafka-console-consumer --topic t-commodity-join-order-payment-two --from-beginning --property print.key=true --bootstrap-server=localhost:9092

Run Postman -> Online Order / Payment -> Left Join Simulation

3.Outer join Stream / Stream

See OrderPaymentThreeStream class

kafka-topics --bootstrap-server localhost:9092 --create --partitions 1 --replication-factor 1 --topic t-commodity-join-order-payment-three

kafka-console-consumer --topic t-commodity-join-order-payment-three --from-beginning --property print.key=true --bootstrap-server=localhost:9092

Run Postman -> Online Order / Payment -> Outer Join Simulation

---

Kafka table/table joining

1.Inner join (left is color vote, right is layout vote; the key is username)

Inner joins the two Tables, resulting a new Table; counts the number of colors and votes in the new Table

See WebColorVote* and WebLayoutVote* classes
See WebDesignVoteOneStream

kafka-topics --bootstrap-server localhost:9092 --create --partitions 1 --replication-factor 1 --topic t-commodity-web-vote-color
kafka-topics --bootstrap-server localhost:9092 --create --partitions 1 --replication-factor 1 --topic t-commodity-web-vote-layout
kafka-topics --bootstrap-server localhost:9092 --create --partitions 1 --replication-factor 1 --topic t-commodity-web-vote-one-username-color --config "cleanup.policy=compact" --config "delete.retention.ms=2000"  --config "segment.ms=2000" --config "min.cleanable.dirty.ratio=0.01" --config "min.compaction.lag.ms=2000"
kafka-topics --bootstrap-server localhost:9092 --create --partitions 1 --replication-factor 1 --topic t-commodity-web-vote-one-username-layout --config "cleanup.policy=compact" --config "delete.retention.ms=2000"  --config "segment.ms=2000" --config "min.cleanable.dirty.ratio=0.01" --config "min.compaction.lag.ms=2000"
kafka-topics --bootstrap-server localhost:9092 --create --partitions 1 --replication-factor 1 --topic t-commodity-web-vote-one-result

kafka-console-consumer --topic t-commodity-web-vote-one-result --from-beginning --property print.key=true --property print.timestamp=true --bootstrap-server=localhost:9092

Run Postman -> Web Design Vote -> Inner Join Simulation

2.Left join

See WebDesignVoteTwoStream

kafka-topics --bootstrap-server localhost:9092 --create --partitions 1 --replication-factor 1 --topic t-commodity-web-vote-two-result

kafka-console-consumer --topic t-commodity-web-vote-two-result --from-beginning --property print.key=true --property print.timestamp=true --bootstrap-server=localhost:9092

Run Postman -> Web Design Vote -> Left Join Simulation

3.Outer join

See WebDesignVoteThreeStream

kafka-topics --bootstrap-server localhost:9092 --create --partitions 1 --replication-factor 1 --topic t-commodity-web-vote-three-result

kafka-console-consumer --topic t-commodity-web-vote-three-result --from-beginning --property print.key=true --property print.timestamp=true --bootstrap-server=localhost:9092

Run Postman -> Web Design Vote -> Outer Join Simulation

4.Outer join -> creates table directly from stream, without intermediary topic

---

Kafka stream/table joining (left is premium purchase, right is premium user; keys are purchase number and username; the join is done in a premium offer table)

1.Inner join

See Premium* classes
See PremiumOfferOneStream

kafka-topics --bootstrap-server localhost:9092 --create --partitions 1 --replication-factor 1 --topic t-commodity-premium-purchase
kafka-topics --bootstrap-server localhost:9092 --create --partitions 1 --replication-factor 1 --topic t-commodity-premium-user --config "cleanup.policy=compact" --config "delete.retention.ms=2000"  --config "segment.ms=2000" --config "min.cleanable.dirty.ratio=0.01" --config "min.compaction.lag.ms=2000"
kafka-topics --bootstrap-server localhost:9092 --create --partitions 1 --replication-factor 1 --topic t-commodity-premium-offer-one

kafka-console-consumer --topic t-commodity-premium-offer-one --from-beginning --property print.key=true --bootstrap-server=localhost:9092

Run Postman -> Premium Purchase & User -> Premium Offer - Inner Join Stream / Table

2.Left join

See PremiumOfferTwoStream

Note the special treatment in the joiner method when the user is null.

kafka-topics --bootstrap-server localhost:9092 --create --partitions 1 --replication-factor 1 --topic t-commodity-premium-offer-two

kafka-console-consumer --topic t-commodity-premium-offer-two --from-beginning --property print.key=true --bootstrap-server=localhost:9092

Run Postman -> Premium Purchase & User -> Premium Offer - Left Join Stream / Table

---

Kafka stream/globaltable joining

1.Inner join

See PremiumOfferThreeStream

kafka-topics --bootstrap-server localhost:9092 --create --partitions 1 --replication-factor 1 --topic t-commodity-premium-user-filtered
kafka-topics --bootstrap-server localhost:9092 --create --partitions 1 --replication-factor 1 --topic t-commodity-premium-offer-three

kafka-console-consumer --topic t-commodity-premium-offer-three --from-beginning --property print.key=true --bootstrap-server=localhost:9092

Run Postman -> Premium Purchase & User -> Premium Offer - Inner Join Stream / Global Table

---

Kafka stream/table co-partition


See Subscription* classes
See SubscriptionOfferOneStream

kafka-topics --bootstrap-server localhost:9092 --create --partitions 5 --replication-factor 1 --topic t-commodity-subscription-purchase
kafka-topics --bootstrap-server localhost:9092 --create --partitions 2 --replication-factor 1 --topic t-commodity-subscription-user --config "cleanup.policy=compact" --config "delete.retention.ms=2000" --config "segment.ms=2000" --config "min.cleanable.dirty.ratio=0.01" --config "min.compaction.lag.ms=2000"
kafka-topics --bootstrap-server localhost:9092 --create --partitions 1 --replication-factor 1 --topic t-commodity-subscription-offer-one

By running SubscriptionOfferOneStream, the spring application will fail because input is not co-partitioned (notice that t-commodity-subscription-purchase has 5 partitions, while t-commodity-subscription-user has 2 partitions)
The fix is included in SubscriptionOfferTwoStream, which uses a GlobalKTable instead of KTable

kafka-topics --bootstrap-server localhost:9092 --create --partitions 1 --replication-factor 1 --topic t-commodity-subscription-offer-two

kafka-console-consumer --topic t-commodity-subscription-offer-two --from-beginning --property print.key=true --bootstrap-server=localhost:9092

Run Postman -> Subscription Offer -> Diana Case

---

Kafka Connect

docker-compose -f docker-compose-connect.yml up
docker-compose -f docker-compose-connect-sample.yml up

Source connector -> read data from non-Kafka and writes to Kafka (read from target and sink to Kafka)
Sink connector -> read data from Kafka and writes to non-Kafka (read from Kafka and sink to targt)
Connectors: confluent.io/hub

After docker-compose is up, run Postman -> Kafka Connect -> Connectors -> List connector plugins

1.Basic connector (file source)

Download https://www.confluent.io/hub/jcustenborder/kafka-connect-spooldir -> unzip to ./data/kafka-connect-data/connectors and restart docker-compose-connect (docker-compose -f docker-compose-connect.yml restart kafka-connect) -> run Postman -> Kafka Connect -> Connectors -> List connector plugins

Run Postman -> Kafka Connect -> Setup source connectors -> Spooldir - CSV

kafka-console-consumer.sh --topic t-spooldir-csv-demo --from-beginning --bootstrap-server=localhost:9092
kafka-consumer-groups.sh --bootstrap-server localhost:9092 --list
kafka-consumer-groups.sh --bootstrap-server localhost:9092 --group console-consumer-71849 --describe

2.Basic connector (database sink)

Download https://www.confluent.io/hub/confluentinc/kafka-connect-jdbc and install (see above)

Change Postman -> local-ip variable to point to the ip of the local machine (run "ip addr show docker0" in terminal https://www.howtogeek.com/devops/how-to-connect-to-localhost-within-a-docker-container)
Run Postman -> Kafka Connect -> Setup sink connectors -> PostgreSQL from CSV

List connectors (Postman -> Connectors -> List connectors (name only)) and get connector status (Postman -> Connectors -> Get specific connector status)

copy .csv to ./data/kafka-connect-data/inputs

Check Postgresql -> kafka_employees table -> all records were imported

3.Basic connector (sftp sink)

Download https://www.confluent.io/hub/confluentinc/kafka-connect-sftp and install

Run Postman -> Kafka Connect -> Setup sink connectors -> SFTP (as JSON output)

Check Filezilla (connect to "ip addr show docker0")

---

Kafka Connect - CDC

1.CDC Postgresql source connector

See ./data/postgresql/postgresql.conf for CDC specific settings
See ./data/postgresql/docker-entrypoint-initdb.d/01-postgresql-publication.sql and 02-postgresql-schema.sql for initialization files

Download and install https://www.confluent.io/hub/debezium/debezium-connector-postgresql

Topics are created automatically by the CDC connector

Run Postman -> Kafka Connect -> Setup source connectors -> PostgreSQL CDC - Finance
Run Postman -> Kafka Connect -> Setup source connectors -> PostgreSQL CDC - Marketing (note that it has tombstone.on.delete=false)

insert sample data to postgresql (see spring-kafka-scripts/kafka-connect-samples/cdc-legacy-modernization)

kafka-topics.sh --bootstrap-server localhost:9092 --list
kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic t-cdc-finance.public.fin_invoices --from-beginning
kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic t-cdc-marketing.public.mkt_promotions --from-beginning
kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic t-cdc-marketing.public.mkt_sales --from-beginning

See the json in the console-consumer.
Update some data in the database; see again the json in the console-consumer -> the "before" field is null
Delete some data in the database; see again the json in the console-consumer -> the "before" field only shows primary key column (remaining columns are null)
To have the "before" filed behave correctly, run the following commands in postgresql:
  ALTER TABLE public.fin_invoices REPLICA IDENTITY FULL;
  ALTER TABLE public.mkt_promotions REPLICA IDENTITY FULL;
  ALTER TABLE public.mkt_sales REPLICA IDENTITY FULL;

2.CDC Postgresql sink connector

DROP TABLE IF EXISTS kafka_fin_invoices;

CREATE TABLE IF NOT EXISTS kafka_fin_invoices (
    invoice_id INT PRIMARY KEY,
    invoice_amount INT,
    invoice_currency VARCHAR(3),
    invoice_number VARCHAR(50),
    invoice_date DATE
);

Run Postman -> Kafka Connect -> Setup sink connectors -> PostgreSQL from finance (invoices) (note that auto.create and auto.evolve is set to false)

Add, update and delete some data in fin_invoices, and see that the changes are propagated to kafka_fin_invoices


3.CDC Postgresql sink connector

See CdcMessage, CdcPayloadMessage, CdcSourceMessage, MarketingPromotionMessage, MarketingSalesMessage
See CdcMarketingListener

Start kafka-connect spring project and see console output

Insert / Update / Delete some data in the mkt_sales and mkt_promotions

See spring project console output

4.CDC Postgresql source connector

Run SQL scripts in ./spring-kafka-scripts/kafka-connect-samples/data-engineering

Run Postman -> Kafka Connect -> Setup source connectors -> PostgreSQL - Person Address

kafka-console-consumer.sh --bootstrap-server localhost:9092 --from-beginning --property print.key=true --topic t-person-address-postgresql

Run Postman -> Kafka Connect -> Setup sink connectors -> PostgreSQL person address from target topic

5.CDC HTTP source connector

Download an install https://www.confluent.io/hub/castorm/kafka-connect-http

Create mockaroo API endpoint based on Spring Kafka - Person Address (JSON).schema.json file

Run Postman -> Kafka Connect -> Setup source connectors -> HTTP - Person Address

kafka-console-consumer.sh --bootstrap-server localhost:9092 --from-beginning --topic t-person-address-http

6.Custom source

See AddressMessage, PersonMessage, PersonProducer, PersonAddressScheduler

kafka-topics.sh --bootstrap-server localhost:9092 --create --partitions 1 --replication-factor 1 --topic t-person-address-custom

kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic t-person-address-custom

Run kafka-connect spring application

6.Elasticsearch sink

Create Elasticsearch deployment on https://www.elastic.co/cloud/

Download and install https://www.confluent.io/hub/confluentinc/kafka-connect-elasticsearch

Change Postman elasticsearch* variables

Run Postman -> Kafka Connect -> Setup sink connectors -> Elasticsearch 01 - setup privileges, Elasticsearch 02 - setup roles, Elasticsearch 03 - create sink

Run Postman -> Kafka Connect -> Elasticsearch

---

Kafka Connect & Kafka Stream

Flow: Kafka Connect sources (postgresql, http, custom) publishes to kafka topics (t-person-address-postgresql, t-person-address-t-http, t-person-address-t-custom); Kafka Stream converts to a single format and publishes to t-person-address-target; Kafka Connect sinks to Elasticsearch

1.Postgresql to target

See KafkaStreamConfig, KafkaConnectMessage, KafkaConnectSchema, PersonAddressFromPostgresqlStream

To test postgresql to target topic:
kafka-console-consumer.sh --bootstrap-server localhost:9092 --from-beginning --topic t-person-address-postgresql --property print.key=true
kafka-console-consumer.sh --bootstrap-server localhost:9092 --from-beginning --topic t-person-address-target --property print.key=true

Run kafka-connect spring application

Before proceeding to next step, pause the source connector for postgresql (pause the name of the Postman -> Kafka Connect -> Setup source connectors -> PostgreSQL - Person Address)

2.HTTP to target

See KafkaConnectPersonTargetMessage, KafkaConnectPersonAddressTargetKeySchema, KafkaConnectPersonAddressTargetValueSchema, KafkaConnectPersonAddressFromHttpMessage, KafkaConnectPersonMessageSnakeCase, KafkaConnectAddressMessageSnakeCase, PersonAddressFromHttpStream

To test postgresql to target topic:
kafka-console-consumer.sh --bootstrap-server localhost:9092 --from-beginning --topic t-person-address-http --property print.key=true
kafka-console-consumer.sh --bootstrap-server localhost:9092 --from-beginning --topic t-person-address-target --property print.key=true

Run kafka-connect spring application

Note: the http messages inside t-person-address-target don't have the same exact structure as the postgresql meessages (e.g. for http, the key type is int32 and optional=false, whereas for postgresql the type is string and optional=true)

Run kafka-connect spring application

Before proceeding to next step, pause the source connector for http (pause the name of the Postman -> Kafka Connect -> Setup source connectors -> HTTP - Person Address

3.Custom to target

See PersonAddressFromCustomStream

To test postgresql to target topic:
kafka-console-consumer.sh --bootstrap-server localhost:9092 --from-beginning --topic t-person-address-custom --property print.key=true
kafka-console-consumer.sh --bootstrap-server localhost:9092 --from-beginning --topic t-person-address-target --property print.key=true

Run kafka-connect spring application

4.Extra

To change the default converter (org.apache.kafka.connect.json.JsonConverter, as defined in docker-compose-connect.yml), change Postman -> Kafka Connect -> Setup source connectors -> HTTP - Person Address and add key.converter=org.apache.kafka.connect.storage.StringConverter (do the same for value.converter).
kafka-console-consumer.sh --bootstrap-server localhost:9092 --from-beginning --topic t-person-address-http --property print.key=true
The messages are in plain string, and not in json format

---

Binary data

docker-compose -f docker-compose-full.yml up
docker-compose -f docker-compose-full-sample.yml up

kafka-console-consumer.sh --bootstrap-server localhost:9092 --from-beginning --topic binary-topic

Run kafka-binary-data spring application

---

Avro

1.Simple Avro

See Hello.avsc

Run Maven -> Lifecycle -> Compile (it will generate src/main/java/dev.intermediatebox.avro.data.Hello)

Run HelloAvro java class -> it will produce the helloAvro.avro binary file
Run HelloAvroReader java class to convert the binary file to java object

2.Generic Avro

With generic Avro, we set everything manually (not recommended)

Run HelloAvroGeneric java class -> it will produce the helloAvroGeneric.avro binary file
Run HelloAvroGenericReader java class to convert the binary file to java object 

3.Specific Avro - basic

Don't mix specific Avro with generate Avro classes. 

See Avro01
Run Avro01App

4.Specific Avro - logical type

See Avro02
Run Avro02App

Note that myDate, myTimeMillis, myTimestampMillis in Avro02 have java.time.* types
Note that avro maven plugin uses java.nio.ByteBuffer instead of java.math.BigDecimal for the myDecimal field, which causes compiler error (https://stackoverflow.com/questions/56444213/generate-classes-with-decimal-datatype-with-avro-maven-plugin-1-9-0)

5.Specific Avro - optional field

See Avro03
Run Avro03App

Note that myWeirdButPossibleValue can be either boolean or int

5.Specific Avro - enum

See Avro04 and Avro05
Run Avro04App and Avro05App

Note that Avro enum is converted to Java enum

6.Specific Avro - array

See Avro06
Run Avro06App

Note that the "quotes" field is optional

7.Specific Avro - map

See Avro07
Run Avro07App

8.Specific Avro - fixed data type

See Avro08
Run Avro08App

"Fixed" means a fixed number of bytes (bytes[])

9.Avro reflection (generate Avro schema from existing java classes)

See SimpleEntity
Run Avro09App -> copy output to Avro09.avsc -> Run Maven Compile -> It will produce a new class LocalDate, which clashes with Java's LocalDate -> generated Avro09 needs to be refactored in order to solve the compiler errors

See Company, Branch
Run Avro10App -> copy output to Avro10.avsc -> change the namespace in Avro10.avsc -> Run Maven Compile -> It will produce new generated classes (Company and Branch)

10.Generate Avro schema from JSON (see online generators)

11.Nested Avro record

See Avro11Person.avsc

Run Avro11App

12.Convert JSON string to Avro java object, and Avro java object to JSON string

See BookAvro.avsc and BookJson

Run BookJsonAvroConversion:
*converts JSON string to Avro java object, based on existing .avsc file; serialize the Avro java object as .avro file; deserialize the .avro file as JSON string
*converts Java POJO to JSON string via Jackson; converts JSON string to Avro java object, based on existing .avsc file; serialize the Avro java object as .avro file; deserialize the .avro file as JSON string; deserialize the JSON string to Java POJO via Jackson

13.Avro Tools

https://dlcdn.apache.org/avro/

---

Avro Schema Evolution

1.Backward compatible

EmployeeBackwardV2.avsc is backward compatible with EmployeeBackwardV1.avsc
Run EmployeeBackwardApp: it writes .avro file using V1 schema, and reads the same .avro file using V2 schema

EmployeeNotBackwardV1.avsc and EmployeeNotBackwardV2.avsc are not backward compatible
Run EmployeeNotBackwardApp: it will throw exception

2.Forward compatible

EmployeeForwardV2.avsc is forward compatible with EmployeeForwardV1.avsc
Run EmployeeForwardApp: it writes .avro file using V2 schema, and reads the same .avro file using V1 schema

EmployeeNotForwardV1.avsc and EmployeeNotForwardV2.avsc are not backward compatible
Run EmployeeNotForwardApp: it will throw exception

3.Full compatible

EmployeeFullV1.avsc is full compatible with EmployeeFullV2.avsc

Run EmployeeFullApp

---

Confluent Schema Registry

See Kafka Schema Registry Postman collection
See https://www.conduktor.io

Exercise:
create topic with single partition
Run Postman -> Kafka Schema Registry -> Subject -> Create new subject with schema of Avro01.avsc
Run Postman -> Kafka Schema Registry -> Compatibility -> Update schema compatibility with "FULL"

---

Avro and Spring

1.Avro Kafka Producer and Consumer (automatically generated Avro schema)

See Avro01Producer
Run Maven compile to generate Avro01 java class
Run kafka-avro-producer spring application
kafka-console-consumer.sh --bootstrap-server localhost:9092 --from-beginning --topic sc-avro01 --property print.key=true

Run Postman -> Kafka Schema Registry -> Schemas -> List schemas -> it will display sc-avro01-value schema; the schema is automatically created when sending first message, if not already created
The producer will publish the message with sc-avro01-value schema

See Avro01Consumer
Run kafka-avro-consumer

2.Avro Kafka Producer and Consumer (manually generated Avro schema)

Create schema: Run Postman -> Subject -> Create new subject (the schema needs to be on a single line https://w3percentagecalculator.com/json-to-one-line-converter/, and the double quotes escaped - find and replace " with \") -> use Avro02.avsc, subject is sc-avro02-value

See Avro02Producer
Run kafka-avro-producer spring application
kafka-console-consumer.sh --bootstrap-server localhost:9092 --from-beginning --topic sc-avro02 --property print.key=true

3.Kafka Avro and Kafka Stream

kafka-topics.sh --bootstrap-server localhost:9092 --create --partitions 1 --replication-factor 1 --topic sc-hello
Create schema for Hello.avsc

See KafkaStreamConfig, HelloProducer, HelloPositiveUppercase, HelloStream
kafka-console-consumer.sh --bootstrap-server localhost:9092 --from-beginning --topic sc-hello --property print.key=true
Run kafka-avro-producer and kafka-avro-consumer spring applications

4.Backward compatibility (occurs when consumer is updated first - e.g. v2, but the producer is not updated - e.g. v1)

In real life there is not V1, or V2 of the .avsc file (e.g. instead of EmployeeBackwardV1.avsc and EmployeeBackwardV2.avsc, there's only one EmployeeBackward.avsc)

kafka-topics.sh --bootstrap-server localhost:9092 --create --partitions 1 --replication-factor 1 --topic sc-employee-backward
Create schema for EmployeeBackward.avsc (Postman)
Check compatibility in Conduktor UI (set Compatibility to "Backward")
See EmployeeBackwardProducer, EmployeeBackwardScheduler, EmployeeBackwardConsumer
Run kafka-avro-producer and kafka-avro-consumer spring applications

Now, in the kafka-avro-consumer project, copy the content of EmployeeBackwardV2.avsc into EmployeeBackward.avsc
Note: the consumer will automatically increment the version of the schema in the Registry (or you can manually update the version of the schema via Conduktor UI)
Run Maven compile to regenerate EmployeeBackward java class and run kafka-avro-producer and kafka-avro-consumer spring applications

5.Forward compatibility (occurs when producer is updated first - e.g. v2, but the consumer is not updated - e.g. v1)

kafka-topics.sh --bootstrap-server localhost:9092 --create --partitions 1 --replication-factor 1 --topic sc-employee-forward
Create schema for EmployeeForward.avsc (Postman)
Check compatibility in Conduktor UI (set Compatibility to "Forward")
See EmployeeForwardProducer, EmployeeForwardScheduler, EmployeeForwardConsumer
Run kafka-avro-producer and kafka-avro-consumer spring applications

Now, in the kafka-avro-producer project, copy the content of EmployeeForwardV2.avsc into EmployeeForward.avsc
Note: the consumer will automatically increment the version of the schema in the Registry (or you can manually update the version of the schema via Conduktor UI)
Run Maven compile to regenerate EmployeeForward java class
Modify EmployeeForwardScheduler to send dummy email data (the consumer will ignore this field)
Run kafka-avro-producer and kafka-avro-consumer spring applications

6.Full compatibility (neither producer, nor consumer breaks when schema evolves)

---

Avro and Kafka Connect

Note that docker-compose-full.yml has CONNECT_VALUE_CONVERTER and CONNECT_VALUE_CONVERTER_SCHEMA_REGISTRY_URL for kafka-connect service (the schema will not be embedded in the message, as with JSON messages)
Note: you can override each connector configuration and set the default converter
Note: source connector automatically generates schema, which is saved on schema registry; the sink connector will read the schema from the Registry


1.Postgresql source connector + Kafka consumer 

See PersonAddressPostgresql.avsc
Create source connector by running Postman -> Kafka Schema Registry -> Kafka Connect -> Source PostgreSQL - Person Address
Check Conduktor -> Schema Registry -> note that a new schema "sc-person-address-postgresql-value" was automatically created
Copy schema content from Conduktor to kafka-avro-consumer/src/main/avro/PersonAddressPostgresql.avsc

See PersonAddressPostgresqlConsumer

2.Kafka producer

See PersonAddressPostgresqlProducer and PersonAddressScheduler

3.Postgresql sink connector

Create postresql table kafka_employee_forward (firstName varchar(200), lastName varchar(200), email varchar(200))
Enable EmployeeForwardScheduler
Create sink connector by running Postman -> Kafka Schema Registry -> Kafka Connect -> Sink PostgreSQL - Employee Forward

---

Kafka Confluent REST Proxy

1.Various operations via REST Proxy
List cluster ids: Postman -> Kafka Rest Proxy -> v3 -> Cluster -> List clusters
Create topic: Postman -> Kafka Rest Proxy -> v3 -> Topic -> Create topic (my-topic-from-api-binary, my-topic-from-api-avro, my-topic-from-api-json)

2.Produce and consume binary data via REST Proxy
The following step needs to be executed in order to produce binary data:
Produce binary data: Postman -> Kafka Rest Proxy -> v2 -> Producer -> Produce binary (the value is base64 encoded)
kafka-console-consumer.sh --bootstrap-server localhost:9092 --from-beginning --topic my-topic-from-api-binary --property print.key=true

The following steps need to be executed in order to consume binary data:
Consume binary data: Postman -> Kafka Rest Proxy -> v2 -> Consumer -> Consume binary -> Create consumer
Subscribe to topic: Postman -> Kafka Rest Proxy -> v2 -> Consumer -> Consume binary -> Subscribe to topic
Consume from topic: Postman -> Kafka Rest Proxy -> v2 -> Consumer -> Consume binary -> Consume from topic

3.Produce and consume JSON data via REST Proxy

Same with binary, only the content-type header is changed

The following step needs to be executed in order to produce json data:
Postman -> Kafka Rest Proxy -> v2 -> Producer -> Produce json

The following steps need to be executed in order to consume json data:
Consume json data: Postman -> Kafka Rest Proxy -> v2 -> Consumer -> Consume json -> Create consumer
Subscribe to topic: Postman -> Kafka Rest Proxy -> v2 -> Consumer -> Consume json -> Subscribe to topic
Consume from topic: Postman -> Kafka Rest Proxy -> v2 -> Consumer -> Consume json -> Consume from topic

4.Produce and consume Avro data via REST Proxy

Similar with json

The following step needs to be executed in order to produce avro data:
Postman -> Kafka Rest Proxy -> v2 -> Producer -> Produce avro 1 (has both schema and records; the schema will be automatically created)
or
Postman -> Kafka Rest Proxy -> v2 -> Producer -> Produce avro 1 (references previously created schema id)

The following steps need to be executed in order to consume avro data:
Consume avro data: Postman -> Kafka Rest Proxy -> v2 -> Consumer -> Consume avro -> Create consumer
Subscribe to topic: Postman -> Kafka Rest Proxy -> v2 -> Consumer -> Consume avro -> Subscribe to topic
Consume from topic: Postman -> Kafka Rest Proxy -> v2 -> Consumer -> Consume avro -> Consume from topic

---

ksqlDB

docker exec -it kafka-ksqldb ksql
Run kafka-ms-order spring application

1.Hello ksqlDB stream

kafka-topics --create --topic=t-commodity-promotion --partitions=1 --replication-factor=1 --bootstrap-server=localhost:9092
Run Postman -> Microservices & Kafka Stream -> Promotion -> Create Promotion

Console consumer:
Run in kafka-ksqldb: print 't-commodity-promotion'; (it will start listening for new data)
or
Run in kafka-ksqldb: SET 'auto.offset.reset'='earliest'; print 't-commodity-promotion';
or
Run in kafka-ksqldb: print 't-commodity-promotion' from beginning;

Create stream:
CREATE STREAM `s-commodity-promotion` (
promotionCode VARCHAR
) WITH (
KAFKA_TOPIC = 't-commodity-promotion',
VALUE_FORMAT = 'JSON'
);

Read from stream:
SELECT *
FROM `s-commodity-promotion`
EMIT CHANGES;

Read from stream with transformation (see https://docs.ksqldb.io/en/latest/developer-guide/ksqldb-reference/functions/):
SELECT UCASE(promotionCode) AS uppercasePromotionCode
FROM `s-commodity-promotion`
EMIT CHANGES;

Create stream with transformation (it will create a new topic 's-commodity-promotion-uppercase'; by running SHOW TOPICS or kafka-topics.sh --list --bootstrap-server=localhost:9092, it will list the 's-commodity-promotion-uppercase' topic, which is just a normal topic)
CREATE STREAM `s-commodity-promotion-uppercase`
WITH (
kafka_topic = 't-ksql-commodity-promotion-uppercase'
)
AS
SELECT UCASE(promotionCode) AS uppercasePromotionCode
FROM `s-commodity-promotion`
EMIT CHANGES;

SHOW STREAMS;

Select from stream:
SELECT *
FROM `s-commodity-promotion-uppercase`
EMIT CHANGES;

2.Basic ksqlDB stream commands (see https://docs.ksqldb.io/en/latest/)

PRINT `t-commodity-promotion`;
SELECT * FROM `s-commodity-promotion` EMIT CHANGES;
CREATE STREAM IF NOT EXISTS `s-commodity-promotion2`;
CREATE OR REPLACE STREAM IF NOT EXISTS `s-commodity-promotion2`;
DROP STREAM `s-commodity-promotion2`;
DROP STREAM IF EXISTS `s-commodity-promotion2`;

3.Primitive data types

See BasicDataOne*
Run Postman -> Kafka ksqlDB -> Basic Data -> Basic Data 1 multiple times

Show data in topic:
PRINT `t-ksql-basic-data-one`
FROM BEGINNING;

Create stream:
CREATE STREAM `s-basic-data-one` (
`myString` STRING,
`myFloat` DOUBLE,
`myBoolean` BOOLEAN,
`myInteger` INT,
`myDouble` DOUBLE,
`myBigDecimal` DECIMAL(30,18),
`myLong` BIGINT,
`myAnotherString` VARCHAR
)
WITH (
KAFKA_TOPIC = 't-ksql-basic-data-one',
VALUE_FORMAT = 'JSON'
);

Try to update stream with different column order (it will fail):
CREATE OR REPLACE STREAM `s-basic-data-one` (
`myBoolean` BOOLEAN,
`myFloat` DOUBLE,
`myDouble` DOUBLE,
`myInteger` INT,
`myLong` BIGINT,
`myString` STRING,
`myAnotherString` VARCHAR,
`myBigDecimal` DECIMAL(30,18)
)
WITH (
KAFKA_TOPIC = 't-ksql-basic-data-one',
VALUE_FORMAT = 'JSON'
);
The workaround is to delete and recreate the stream (DROP STREAM IF EXISTS `s-basic-data-one`;)

Show data with limit:
SELECT *
FROM `s-basic-data-one`
EMIT CHANGES
LIMIT 15;

3.Date and time data types 

See BasicDataTwo*
Run Postman -> Kafka ksqlDB -> Basic Data -> Basic Data 2 multiple times (see Postman Pre-request Script)

Show data in topic:
PRINT `t-ksql-basic-data-two`
FROM BEGINNING;

Create stream:
CREATE OR REPLACE STREAM `s-basic-data-two` (
`myEpochDay` DATE,
`myMillisOfDay` TIME,
`myEpochMillis` TIMESTAMP
)
WITH (
KAFKA_TOPIC = 't-ksql-basic-data-two',
VALUE_FORMAT = 'JSON'
);

Read data from stream:
SELECT *
FROM `s-basic-data-two`
EMIT CHANGES;

Date/time ksqlDB functions:
SELECT `myEpochDay`,
DATEADD(DAYS, 7, `myEpochDay`) AS `aWeekAfterMyEpochDay`,
`myMillisOfDay`,
TIMESUB(HOURS, 2, `myMillisOfDay`) AS `twoHoursBeforeMyMillisOfDay`,
`myEpochMillis`,
FORMAT_TIMESTAMP(`myEpochMillis`, 'dd-MMM-yyyy, HH:mm:ss Z', 'Asia/Jakarta') as `epochMillisAtJakartaTimezone`
FROM `s-basic-data-two`
EMIT CHANGES;

4.Date and time data types (ISO 8601 format)

See BasicDataThree*
Run Postman -> Kafka ksqlDB -> Basic Data -> Basic Data 3 multiple times

Show data in topic:
PRINT `t-ksql-basic-data-three`;

Create stream:
CREATE OR REPLACE STREAM `s-basic-data-three` (
`myLocalDate` VARCHAR,
`myLocalDateCustomFormat` VARCHAR,
`myLocalTime` VARCHAR,
`myLocalTimeCustomFormat` VARCHAR,
`myLocalDateTime` VARCHAR,
`myLocalDateTimeCustomFormat` VARCHAR
)
WITH (
KAFKA_TOPIC = 't-ksql-basic-data-three',
VALUE_FORMAT = 'JSON'
);

Read data from stream:
SELECT *
FROM `s-basic-data-three`
EMIT CHANGES;

Read data (LocalDate)
SELECT `myLocalDate`,
DATEADD(DAYS, 7, `myLocalDate`) AS `aWeekAfterMyLocalDate`,
CONCAT('Prefix string- ', `myLocalDate`, ' -suffix String') AS `myLocalDateConcatString`,
`myLocalDateCustomFormat`,
DATEADD(DAYS, 7, `myLocalDateCustomFormat`) AS `aWeekAfterMyLocalDateCustomFormat`,
CONCAT('Prefix string- ', `myLocalDateCustomFormat`, ' -suffix String') AS `myLocalDateCustomFormatConcatString`
FROM `s-basic-data-three`
EMIT CHANGES;

Read data (LocalTime)
SELECT `myLocalTime`,
TIMEADD(HOURS, 3, `myLocalTime`) AS `3HoursAfterMyLocalTime`,
CONCAT('Prefix string- ', `myLocalTime`, ' -suffix String') AS `myLocalTimeConcatString`,
`myLocalTimeCustomFormat`,
TIMEADD(HOURS, 3, `myLocalTimeCustomFormat`) AS `3HoursAfterMyLocalDateCustomFormat`,
CONCAT('Prefix string- ', `myLocalTimeCustomFormat`, ' -suffix String') AS `myLocalTimeCustomFormatConcatString`
FROM `s-basic-data-three`
EMIT CHANGES;

Read data (LocalDateTime)
SELECT `myLocalDateTime`,
DATEADD(DAYS, 2, `myLocalDateTime`) AS `2DaysAfterMyLocalDateTime`,
CONCAT('Prefix string- ', `myLocalDateTime`, ' -suffix String') AS `myLocalDateTimeConcatString`,
`myLocalDateTimeCustomFormat`,
DATEADD(DAYS, 2, `myLocalDateTimeCustomFormat`) AS `2DaysAfterMyLocalDateTimeCustomFormat`,
CONCAT('Prefix string- ', `myLocalDateTimeCustomFormat`, ' -suffix String') AS `myLocalDateTimeCustomFormatConcatString`
FROM `s-basic-data-three`
EMIT CHANGES;

Parse date/time from string:
SELECT PARSE_DATE(`myLocalDate`, 'yyyy-MM-dd') AS `parsedLocalDate`,
PARSE_DATE(`myLocalDateCustomFormat`, 'dd MMM yyyy') AS `parsedLocalDateCustomFormat`,
PARSE_TIME(`myLocalTime`, 'HH:mm:ss') AS `parsedLocalTime`,
PARSE_TIME(`myLocalTimeCustomFormat`, 'hh:mm:ss a') AS `parsedLocalTimeCustomFormat`,
PARSE_TIMESTAMP(`myLocalDateTime`, 'yyyy-MM-dd''T''HH:mm:ss') AS `parsedLocalDateTime`,
PARSE_TIMESTAMP(`myLocalDateTimeCustomFormat`, 'dd-MMM-yyyy hh:mm:ss a') AS `parsedLocalDateTimeCustomFormat`
FROM `s-basic-data-three`
EMIT CHANGES;

Create stream with parsed date/time from string:
CREATE STREAM `s-basic-data-three-parsed`
AS
SELECT PARSE_DATE(`myLocalDate`, 'yyyy-MM-dd') AS `parsedLocalDate`,
PARSE_DATE(`myLocalDateCustomFormat`, 'dd MMM yyyy') AS `parsedLocalDateCustomFormat`,
PARSE_TIME(`myLocalTime`, 'HH:mm:ss') AS `parsedLocalTime`,
PARSE_TIME(`myLocalTimeCustomFormat`, 'hh:mm:ss a') AS `parsedLocalTimeCustomFormat`,
PARSE_TIMESTAMP(`myLocalDateTime`, 'yyyy-MM-dd''T''HH:mm:ss') AS `parsedLocalDateTime`,
PARSE_TIMESTAMP(`myLocalDateTimeCustomFormat`, 'dd-MMM-yyyy hh:mm:ss a') AS `parsedLocalDateTimeCustomFormat`
FROM `s-basic-data-three`
EMIT CHANGES;

Describe stream:
DESCRIBE `s-basic-data-three`;
DESCRIBE `s-basic-data-three-parsed`;
DESCRIBE `s-basic-data-three` EXTENDED;
DESCRIBE `s-basic-data-three-parsed` EXTENDED;

Correctly read data for LocalDate from parsedLocalDate column:
SELECT `parsedLocalDate`,
DATEADD(DAYS, 7, `parsedLocalDate`) AS `aWeekAfterParsedLocalDate`,
`parsedLocalDateCustomFormat`,
DATEADD(DAYS, 7, `parsedLocalDateCustomFormat`) AS `aWeekAfterParsedLocalDateCustomFormat`
FROM `s-basic-data-three-parsed`
EMIT CHANGES;

Correctly read data for LocalTime from parsedLocalDate column:
SELECT `parsedLocalTime`,
TIMEADD(HOURS, 3, `parsedLocalTime`) AS `3HoursAfterParsedLocalTime`,
`parsedLocalTimeCustomFormat`,
TIMEADD(HOURS, 3, `parsedLocalTimeCustomFormat`) AS `3HoursAfterParsedLocalDateCustomFormat`
FROM `s-basic-data-three-parsed`
EMIT CHANGES;

Correctly read data for LocalDateTime from parsedLocalDate column:
SELECT `parsedLocalDateTime`,
TIMESTAMPADD(DAYS, 2, `parsedLocalDateTime`) AS `2DaysAfterParsedLocalDateTime`,
`parsedLocalDateTimeCustomFormat`,
TIMESTAMPADD(DAYS, 2, `parsedLocalDateTimeCustomFormat`) AS `2DaysAfterParsedLocalDateTimeCustomFormat`
FROM `s-basic-data-three-parsed`
EMIT CHANGES;

5.Array, List and Set data types

See BasicDataFour*
Run Postman -> Kafka ksqlDB -> Basic Data -> Basic Data 4 multiple times

Show data in topic:
PRINT `t-ksql-basic-data-four`;

Create stream:
CREATE STREAM `s-basic-data-four` (
`myStringArray` ARRAY<VARCHAR>,
`myIntegerList` ARRAY<INT>,
`myDoubleSet` ARRAY<DOUBLE>
) WITH (
KAFKA_TOPIC = 't-ksql-basic-data-four',
VALUE_FORMAT = 'JSON'
);

Read data from stream:
SELECT *
FROM `s-basic-data-four`
EMIT CHANGES;

Describe stream
DESCRIBE `s-basic-data-four`;

Array functions:
SELECT ARRAY_LENGTH(`myStringArray`) as `lengthMyStringArray`,
ARRAY_CONCAT(`myIntegerList`, ARRAY[999, 998, 997]) as `concatMyIntegerList`,
ARRAY_SORT(`myDoubleSet`, 'DESC') as `sortedDescMyDoubleSet`
FROM `s-basic-data-four`
EMIT CHANGES;

5.Map data type

See BasicDataFive*
Run Postman -> Kafka ksqlDB -> Basic Data -> Basic Data 5 multiple times

Show data in topic:
PRINT `t-ksql-basic-data-five`;

Create stream:
CREATE STREAM `s-basic-data-five` (
`myMapAlpha` MAP<VARCHAR, VARCHAR>,
`myMapBeta` MAP<VARCHAR, VARCHAR>
) WITH (
KAFKA_TOPIC = 't-ksql-basic-data-five',
VALUE_FORMAT = 'JSON'
);

Read data from stream:
SELECT *
FROM `s-basic-data-five`
EMIT CHANGES;

Describe stream:
DESCRIBE `s-basic-data-five`;

Map functions:
SELECT MAP_VALUES(`myMapAlpha`) as `valuesAtMyMapAlpha`,
MAP_KEYS(`myMapBeta`) as `keysAtMyMapBeta`
FROM `s-basic-data-five`
EMIT CHANGES;

6.Complex data types

See BasicDataPersonRequest, BasicDataPersonMessage, BasicDataPassportMessage, BasicDataAddressMessage, BasicDataLocationMessage
Run Postman -> Kafka ksqlDB -> Basic Data -> Basic Data Person multiple times

Show data in topic:
PRINT `t-ksql-basic-data-person` FROM BEGINNING;

Create stream:
CREATE STREAM `s-basic-data-person` (
`firstName` VARCHAR,
`lastName` VARCHAR,
`birthDate` VARCHAR,
`contacts` MAP<VARCHAR, VARCHAR>,
`passport` STRUCT<
`number` VARCHAR,
`expiryDate` VARCHAR
>,
`addresses` ARRAY<
STRUCT<
`streetAddress` VARCHAR,
`country` VARCHAR,
`location` STRUCT<
`latitude` DOUBLE,
`longitude` DOUBLE
>
>
>
) WITH (
KAFKA_TOPIC = 't-ksql-basic-data-person',
VALUE_FORMAT = 'JSON'
);

Read data from stream:
SELECT *
FROM `s-basic-data-person`
EMIT CHANGES;

Access map data:
SELECT `contacts`['email'] AS `emailFromContactsMap`,
`contacts`['phoneHome'] AS `phoneHomeFromContactsMap`,
`contacts`['phoneWork'] AS `phoneWorkFromContactsMap`
FROM `s-basic-data-person`
EMIT CHANGES;

Access struct data:
SELECT `passport`->`number` AS `passportNumber`,
`passport`->`expiryDate` AS `passportExpiryDate`
FROM `s-basic-data-person`
EMIT CHANGES;

Convert each address in the list into one record (explode function):
SELECT `firstName`, `lastName`,
EXPLODE(`addresses`) as `addressSingle`
FROM `s-basic-data-person`
EMIT CHANGES;

Convert each address in the list into one record, then access each field in the address:
SELECT `firstName`, `lastName`,
EXPLODE(`addresses`)->`streetAddress`,
EXPLODE(`addresses`)->`country`,
EXPLODE(`addresses`)->`location`
FROM `s-basic-data-person`
EMIT CHANGES;

Convert each address in the list into one record, then access each field in the address, including fields from structs within structs:
SELECT `firstName`, `lastName`,
EXPLODE(`addresses`)->`streetAddress`,
EXPLODE(`addresses`)->`country`,
EXPLODE(`addresses`)->`location`->`latitude` AS `latitude`,
EXPLODE(`addresses`)->`location`->`longitude` AS `longitude`
FROM `s-basic-data-person`
EMIT CHANGES;


Convert each address in the list into one record, then access each field in the address, including fields from structs within structs, and date conversion functions:
CREATE STREAM `s-basic-data-person-complete`
AS
SELECT `firstName`,
`lastName`,
PARSE_DATE(`birthDate`, 'yyyy-MM-dd') AS `birthDate`,
`contacts`,
`passport`->`number` AS `passportNumber`,
PARSE_DATE(`passport`->`expiryDate`,'yyyy-MM-dd') AS `passportExpiryDate`,		
EXPLODE(`addresses`)->`streetAddress`,
EXPLODE(`addresses`)->`country`,
EXPLODE(`addresses`)->`location`->`latitude` AS `latitude`,
EXPLODE(`addresses`)->`location`->`longitude` AS `longitude`
FROM `s-basic-data-person`
EMIT CHANGES;

Describe stream:
DESCRIBE `s-basic-data-person-complete`;

Get data from stream:
SELECT *
FROM `s-basic-data-person-complete`
EMIT CHANGES;

---

ksqlDB Stream and Table key (KSQL Stream and Table are similar with Kafka Stream and Table)

See BasicDataCountryMessage
Run Postman -> Kafka ksqlDB -> Stream & Table Key -> Basic Data Country multiple times

Show data in topic:
PRINT `t-ksql-basic-data-country` FROM BEGINNING;

Create stream:
CREATE STREAM `s-basic-data-country` (
`countryName` VARCHAR,
`currencyCode` VARCHAR,
`population` INT
) WITH (
KAFKA_TOPIC = 't-ksql-basic-data-country',
VALUE_FORMAT = 'JSON'
);

Describe stream:
DESCRIBE `s-basic-data-country`;

Get stream data:
SELECT *
FROM `s-basic-data-country`
EMIT CHANGES;

Re-key by country name (from existing s-basic-data-country stream):
DROP STREAM IF EXISTS `s-basic-data-country-rekeyed`;

CREATE STREAM `s-basic-data-country-rekeyed`
AS
SELECT `countryName`, `currencyCode`, `population`
FROM `s-basic-data-country`
PARTITION BY `countryName`
EMIT CHANGES;

DESCRIBE `s-basic-data-country-rekeyed`;

Include key in the stream:
DROP STREAM IF EXISTS `s-basic-data-country-rekeyed`;

CREATE STREAM `s-basic-data-country-rekeyed`
AS
SELECT `countryName` AS `rowkey`, AS_VALUE(`countryName`) AS `countryName`, `currencyCode`, `population`
FROM `s-basic-data-country`
PARTITION BY `countryName`
EMIT CHANGES;

Get data from stream:
SET 'auto.offset.reset'='earliest';

SELECT *
FROM `s-basic-data-country-rekeyed`
EMIT CHANGES;

Re-key by country name and currency code:
DROP STREAM IF EXISTS `s-basic-data-country-rekeyed-json`;

CREATE STREAM `s-basic-data-country-rekeyed-json`
WITH (
KEY_FORMAT = 'JSON'
)
AS
SELECT STRUCT(`countryName` := `countryName`, `currencyCode` := `currencyCode`) AS `jsonKey`,
AS_VALUE(`countryName`) AS `countryName`,
AS_VALUE(`currencyCode`) AS `currencyCode`,
`population`
FROM `s-basic-data-country`
PARTITION BY STRUCT(`countryName` := `countryName`, `currencyCode` := `currencyCode`)
EMIT CHANGES;

Create table with key = country name and sum(population) (Table is an aggregation of stream with group by)
DROP TABLE IF EXISTS `tbl-basic-data-country`;

CREATE TABLE `tbl-basic-data-country`
AS
SELECT `countryName`, SUM(`population`) AS `totalPopulation`
FROM `s-basic-data-country`
GROUP BY `countryName`
EMIT CHANGES;

Run Postman -> Kafka ksqlDB -> Stream & Table Key -> Table Simulation requests
Note: 07 - Null key (no country name) is still publishing to the underlying topic, however, it's not published to table
Note: 09 - Japan Delete sends a tombstone record (nothing happened on the topic, while in the Table, a tombstone value was created); re-running "SELECT * FROM `tbl-basic-data-country`" will only display "Indonesia" records

Get data from table:
SET 'auto.offset.reset'='earliest';

SELECT *
FROM `tbl-basic-data-country`
EMIT CHANGES;

---

ksqlDB Commodity

1.Commodity stream + KSQL rowkey
Create stream:
CREATE STREAM `s-commodity-order` (
`rowkey` VARCHAR KEY,
`creditCardNumber` VARCHAR,
`itemName` VARCHAR,
`orderDateTime` VARCHAR,
`orderLocation` VARCHAR,
`orderNumber` VARCHAR,
`price` INT,
`quantity` INT
) WITH (
KAFKA_TOPIC = 't-commodity-order',
VALUE_FORMAT = 'JSON'
);

Describe stream
DESCRIBE `s-commodity-order`;

Run Postman -> Microservices & Kafka Stream -> Commodity Order -> Order 1 Random Item 

Get data from topic:
PRINT `t-commodity-order` FROM BEGINNING;
Note: the PRINT statement is not reliable (e.g. "Key format: KAFKA_BIGINT or KAFKA_DOUBLE or KAFKA_STRING")

Mask credit card number
CREATE STREAM `s-commodity-order-masked`
AS
SELECT `rowkey`, MASK_LEFT(`creditCardNumber`, 12, '*', '*', '*', '*') AS `maskedCreditCardNumber`,
`itemName`, `orderDateTime`, `orderLocation`, `orderNumber`, `price`, `quantity`
FROM `s-commodity-order`
EMIT CHANGES;

Get data from stream:
SELECT *
FROM `s-commodity-order-masked`
EMIT CHANGES;

Calculate total item amount to pattern output
CREATE STREAM `s-commodity-pattern-one`
AS
SELECT `rowkey`, `itemName`, `orderDateTime`, `orderLocation`, `orderNumber`,
(`price` * `quantity`) as `totalItemAmount`
FROM `s-commodity-order-masked`
EMIT CHANGES;

Get data from stream:
SELECT *
FROM `s-commodity-pattern-one`
EMIT CHANGES;

Filter stream based on quantity:
CREATE STREAM `s-commodity-reward-one`
AS
SELECT `rowkey`, `itemName`, `orderDateTime`, `orderLocation`,
`orderNumber`, `price`, `quantity`
FROM `s-commodity-order-masked`
WHERE `quantity` > 200
EMIT CHANGES;

Storage sink:
CREATE STREAM `s-commodity-storage-one`
AS
SELECT *
FROM `s-commodity-order-masked`
EMIT CHANGES;

Select from stream:
SELECT *
FROM `s-commodity-reward-one`
EMIT CHANGES;

2.Custom rowkey from value

CREATE STREAM `s-commodity-order-key-from-value` (
`creditCardNumber` VARCHAR,
`itemName` VARCHAR,
`orderDateTime` VARCHAR,
`orderLocation` VARCHAR,
`orderNumber` VARCHAR KEY,
`price` INT,
`quantity` INT
) WITH (
KAFKA_TOPIC = 't-commodity-order',
VALUE_FORMAT = 'JSON'
);

Describe stream:
DESCRIBE `s-commodity-order-key-from-value`;

3.Commodity plastic/non-plastic (see Kafka Stream - Commodity -> 3.a.)

Create stream for plastic items
CREATE OR REPLACE STREAM `s-commodity-pattern-two-plastic`
AS
SELECT `rowkey`, `itemName`, `orderDateTime`, `orderLocation`,
`orderNumber`, (`price` * `quantity`) as `totalItemAmount`
FROM `s-commodity-order-masked`
WHERE LCASE(`itemName`) LIKE 'plastic%'
EMIT CHANGES;

SELECT *
FROM `s-commodity-pattern-two-plastic`
EMIT CHANGES;

CREATE OR REPLACE STREAM `s-commodity-pattern-two-notplastic`
AS
SELECT `rowkey`, `itemName`, `orderDateTime`, `orderLocation`,
`orderNumber`, (`price` * `quantity`) as `totalItemAmount`
FROM `s-commodity-order-masked`
WHERE LCASE(`itemName`) NOT LIKE 'plastic%'
EMIT CHANGES;

SELECT *
FROM `s-commodity-pattern-two-notplastic`
EMIT CHANGES;

4.Commodity reward (see Kafka Stream - Commodity -> t-commodity-reward-two)

Create stream for large & not cheap items
CREATE OR REPLACE STREAM `s-commodity-reward-two`
AS
SELECT `rowkey`, `itemName`, `orderDateTime`, `orderLocation`,
`orderNumber`, `price`, `quantity`
FROM `s-commodity-order-masked`
WHERE `quantity` > 200
AND `price` > 100
EMIT CHANGES;

5.Commodity reward (see Kafka Stream - Commodity -> t-commodity-storage-two)

Replace key for storage
CREATE OR REPLACE STREAM `s-commodity-storage-two`
AS
SELECT FROM_BYTES(
TO_BYTES(`orderNumber`, 'utf8'), 'base64'
) AS `base64Rowkey`,
`itemName`, `orderDateTime`, `orderLocation`,
`orderNumber`, `price`, `quantity`
FROM `s-commodity-order-masked`
PARTITION BY FROM_BYTES(
TO_BYTES(`orderNumber`, 'utf8'), 'base64'
)
EMIT CHANGES;

Describe stream  
DESCRIBE `s-commodity-storage-two`;
Note that base64Rowkey column is marked as key
or
Use console consumer to see the key
kafka-console-consumer --topic t-commodity-storage-two --from-beginning --property print.key=true --bootstrap-server=localhost:9092

6.Commodity reward for each location (see Kafka Stream - Commodity -> 4. -> the key of the OrderReward needs to be changed and become the location)

CREATE OR REPLACE STREAM `s-commodity-reward-four`
AS
SELECT `itemName`, `orderDateTime`, `orderLocation`,
`orderNumber`, `price`, `quantity`
FROM `s-commodity-order-masked`
PARTITION BY `orderLocation`
EMIT CHANGES;

7.KSQL scripts

copy spring-kafka-scripts/ksqldb-samples/scripts/commodity-sample.ksql to ./data/kafka-ksqldb-data/scripts

execute script by running: RUN SCRIPT /data/scripts/commodity-sample.ksql;

SHOW STREAMS;

Credits to Udemy/Java Spring & Apache Kafka Bootcamp - Basic to Complete