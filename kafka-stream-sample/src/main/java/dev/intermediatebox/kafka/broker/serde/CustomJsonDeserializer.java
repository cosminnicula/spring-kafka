package dev.intermediatebox.kafka.broker.serde;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.IOException;

public class CustomJsonDeserializer<T> implements Deserializer<T> {

  private ObjectMapper objectMapper = new ObjectMapper();

  private Class<T> deserializedClass;

  public CustomJsonDeserializer(Class<T> deserializedClass) {
    this.deserializedClass = deserializedClass;
  }

  @Override
  public T deserialize(String topic, byte[] data) {
    try {
      return objectMapper.readValue(data, deserializedClass);
    } catch (IOException e) {
      throw new SerializationException(e.getMessage());
    }
  }
}
