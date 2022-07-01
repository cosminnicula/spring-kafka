package dev.intermediatebox.kafka.broker.schema;

import java.util.ArrayList;

import dev.intermediatebox.kafka.broker.schema.KafkaConnectSchema.Type;

public class KafkaConnectPersonAddressTargetValueSchema {
  private static KafkaConnectSchema instance;

  static {
    instance = new KafkaConnectSchema();
    var fields = new ArrayList<KafkaConnectSchema>();

    fields.add(new KafkaConnectSchema(Type.int32.toString(), false, "person_id", null));
    fields.add(new KafkaConnectSchema(Type.string.toString(), true, "id_card_number", null));
    fields.add(new KafkaConnectSchema(Type.string.toString(), true, "full_name", null));
    fields.add(new KafkaConnectSchema(Type.string.toString(), true, "email", null));
    fields.add(new KafkaConnectSchema(Type.int32.toString(), false, "address_id", null));
    fields.add(new KafkaConnectSchema(Type.string.toString(), true, "address", null));
    fields.add(new KafkaConnectSchema(Type.string.toString(), true, "city", null));
    fields.add(new KafkaConnectSchema(Type.string.toString(), true, "postal_code", null));

    instance.setType(Type.struct.toString());
    instance.setOptional(false);
    instance.setFields(fields);
  }

  private KafkaConnectPersonAddressTargetValueSchema() {
  }

  public static KafkaConnectSchema instance() {
    return instance;
  }
}
