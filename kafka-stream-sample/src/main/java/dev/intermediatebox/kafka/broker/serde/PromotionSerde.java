package dev.intermediatebox.kafka.broker.serde;

import dev.intermediatebox.kafka.broker.message.PromotionMessage;

public class PromotionSerde extends CustomJsonSerde<PromotionMessage>{
  public PromotionSerde() {
    super(new CustomJsonSerializer<PromotionMessage>(),
        new CustomJsonDeserializer<PromotionMessage>(PromotionMessage.class));
  }
}
