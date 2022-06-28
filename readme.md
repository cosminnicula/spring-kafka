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

3.a.Topology changes (see kafka-stream-commodity-topology-3.jpg): split t-commodity-pattern stream into two categories: plastic and non-plastic items; t-commodity-reward -> give reward only for item that is not cheap; t-commodity-order -> kye is base64

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
