package dev.intermediatebox.kafka.broker.stream;

import java.util.ArrayList;

import dev.intermediatebox.kafka.broker.message.AddressMessage;
import dev.intermediatebox.kafka.broker.message.KafkaConnectMessage;
import dev.intermediatebox.kafka.broker.message.KafkaConnectPersonTargetMessage;
import dev.intermediatebox.kafka.broker.message.PersonMessage;
import dev.intermediatebox.kafka.broker.schema.KafkaConnectPersonAddressTargetKeySchema;
import dev.intermediatebox.kafka.broker.schema.KafkaConnectPersonAddressTargetValueSchema;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KeyValueMapper;
import org.apache.kafka.streams.kstream.Produced;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class PersonAddressFromCustomStream {
  private static final Logger log = LoggerFactory.getLogger(PersonAddressFromCustomStream.class);

  @Autowired
  private ObjectMapper objectMapper;

  @Bean
  public KStream<String, String> kstreamPersonAddressFromCustom(StreamsBuilder builder) {
    var sourceStream = builder.stream("t-person-address-custom", Consumed.with(Serdes.String(), Serdes.String()));
    var targetStream = sourceStream.flatMap(expandPersonAddress());

    targetStream.to("t-person-address-target", Produced.with(Serdes.String(), Serdes.String()));

    return sourceStream;
  }

  private KeyValueMapper<String, String, Iterable<KeyValue<String, String>>> expandPersonAddress() {
    return (key, value) -> {
      var expanded = new ArrayList<KeyValue<String, String>>();

      try {
        var original = objectMapper.readValue(value, PersonMessage.class);

        for (var address : original.getAddresses()) {
          var targetKey = generateTargetKey(address.getAddressId());
          var targetValue = generateTargetValue(original, address);

          expanded.add(KeyValue.pair(targetKey, targetValue));
        }
      } catch (Exception e) {
        log.error("Invalid data : {} throws : {}", value, e.getMessage());
      }

      return expanded;
    };
  }

  private String generateTargetKey(int addressId) throws JsonProcessingException {
    var targetKey = new KafkaConnectMessage<Integer>();
    targetKey.setSchema(KafkaConnectPersonAddressTargetKeySchema.instance());
    targetKey.setPayload(addressId);

    return objectMapper.writeValueAsString(targetKey);
  }

  private String generateTargetValue(PersonMessage person, AddressMessage address) throws JsonProcessingException {
    var targetPayload = new KafkaConnectPersonTargetMessage();

    targetPayload.setEmail(person.getEmail());
    targetPayload.setPersonId(person.getPersonId());
    targetPayload.setFullName(person.getFullName());
    targetPayload.setAddress(address.getAddress());
    targetPayload.setCity(address.getCity());
    targetPayload.setPostalCode(address.getPostalCode());
    targetPayload.setAddressId(address.getAddressId());

    var targetValue = new KafkaConnectMessage<KafkaConnectPersonTargetMessage>();

    targetValue.setSchema(KafkaConnectPersonAddressTargetValueSchema.instance());
    targetValue.setPayload(targetPayload);

    return objectMapper.writeValueAsString(targetValue);
  }
}