package dev.intermediatebox.kafka.broker.schema;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class KafkaConnectSchema {
  public enum Type {
    struct, string, int32
  }

  private String type;
  private boolean optional;
  private String field;
  private List<KafkaConnectSchema> fields;
}
