package dev.intermediatebox.kafka.broker.schema;

public class KafkaConnectPersonAddressTargetKeySchema {
  private static KafkaConnectSchema instance;

  static {
    instance = new KafkaConnectSchema(KafkaConnectSchema.Type.int32.toString(), false, null, null);
  }

  private KafkaConnectPersonAddressTargetKeySchema() {
  }

  public static KafkaConnectSchema instance() {
    return instance;
  }
}
