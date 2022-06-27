package dev.intermediatebox.kafka.broker.stream.commodity;

import dev.intermediatebox.kafka.broker.message.OrderMessage;
import dev.intermediatebox.kafka.broker.message.OrderPatternMessage;
import dev.intermediatebox.kafka.broker.message.OrderRewardMessage;
import dev.intermediatebox.kafka.util.CommodityStreamUtil;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.KafkaStreamBrancher;
import org.springframework.kafka.support.serializer.JsonSerde;

//@Configuration
public class CommodityFourStream {
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
            kstream -> kstream.to("t-commodity-pattern-four-plastic", branchProducer))
        .defaultBranch(kstream -> kstream.to("t-commodity-pattern-four-notplastic", branchProducer))
        .onTopOf(maskedCreditCardStream.mapValues(CommodityStreamUtil::mapToOrderPattern));

    var rewardStream = maskedCreditCardStream.filter(CommodityStreamUtil.isLargeQuantity())
        .filterNot(CommodityStreamUtil.isCheap()).map(CommodityStreamUtil.mapToOrderRewardChangeKey());
    rewardStream.to("t-commodity-reward-four", Produced.with(stringSerde, orderRewardSerde));

    var storageStream = maskedCreditCardStream.selectKey(CommodityStreamUtil.generateStorageKey());
    storageStream.to("t-commodity-storage-four", Produced.with(stringSerde, orderSerde));

    return maskedCreditCardStream;
  }
}
