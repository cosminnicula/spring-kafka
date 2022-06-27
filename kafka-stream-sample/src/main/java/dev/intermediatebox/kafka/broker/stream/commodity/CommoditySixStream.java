package dev.intermediatebox.kafka.broker.stream.commodity;

import dev.intermediatebox.kafka.broker.message.OrderMessage;
import dev.intermediatebox.kafka.broker.message.OrderPatternMessage;
import dev.intermediatebox.kafka.broker.message.OrderRewardMessage;
import dev.intermediatebox.kafka.util.CommodityStreamUtil;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.KafkaStreamBrancher;
import org.springframework.kafka.support.serializer.JsonSerde;

//@Configuration
public class CommoditySixStream {
  private static final Logger log = LoggerFactory.getLogger(CommoditySixStream.class);

  @Bean
  public KStream<String, OrderMessage> kstreamCommodityTrading(StreamsBuilder builder) {
    var stringSerde = Serdes.String();
    var orderSerde = new JsonSerde<>(OrderMessage.class);
    var orderPatternSerde = new JsonSerde<>(OrderPatternMessage.class);
    var orderRewardSerde = new JsonSerde<>(OrderRewardMessage.class);

    var maskedCreditCardStream = builder.stream("t-commodity-order", Consumed.with(stringSerde, orderSerde))
        .mapValues(CommodityStreamUtil::maskCreditCard);

    final var branchProducer = Produced.with(stringSerde, orderPatternSerde);

    new KafkaStreamBrancher<String, OrderPatternMessage>()
        .branch(CommodityStreamUtil.isPlastic(),
            kstream -> kstream.to("t-commodity-pattern-six-plastic", branchProducer))
        .defaultBranch(kstream -> kstream.to("t-commodity-pattern-six-notplastic", branchProducer))
        .onTopOf(maskedCreditCardStream.mapValues(CommodityStreamUtil::mapToOrderPattern));

    var rewardStream = maskedCreditCardStream.filter(CommodityStreamUtil.isLargeQuantity())
        .filterNot(CommodityStreamUtil.isCheap()).map(CommodityStreamUtil.mapToOrderRewardChangeKey());
    rewardStream.to("t-commodity-reward-six", Produced.with(stringSerde, orderRewardSerde));

    var storageStream = maskedCreditCardStream.selectKey(CommodityStreamUtil.generateStorageKey());
    storageStream.to("t-commodity-storage-six", Produced.with(stringSerde, orderSerde));

    // fraud processing
    var fraudStream = maskedCreditCardStream.filter((k, v) -> v.getOrderLocation().toUpperCase().startsWith("C"))
        .peek((k, v) -> this.reportFraud(v))
        .map((k, v) -> KeyValue.pair(v.getOrderLocation().toUpperCase().charAt(0) + "***", v.getPrice() * v.getQuantity()));
    fraudStream.to("t-commodity-fraud-six", Produced.with(Serdes.String(), Serdes.Integer()));

    return maskedCreditCardStream;
  }

  private void reportFraud(OrderMessage v) {
    log.info("fraud detected  {}", v);
  }
}
