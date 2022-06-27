package dev.intermediatebox.kafka.util;

import dev.intermediatebox.kafka.broker.message.OrderMessage;
import dev.intermediatebox.kafka.broker.message.OrderPatternMessage;
import dev.intermediatebox.kafka.broker.message.OrderRewardMessage;
import org.apache.commons.lang3.StringUtils;
import org.apache.kafka.streams.kstream.Predicate;

public class CommodityStreamUtil {
  public static OrderMessage maskCreditCard(OrderMessage original) {
    var converted = original.copy();
    var maskedCreditCardNumber = original.getCreditCardNumber().replaceFirst("\\d{12}",
        StringUtils.repeat('*', 12));

    converted.setCreditCardNumber(maskedCreditCardNumber);

    return converted;
  }

  public static OrderPatternMessage mapToOrderPattern(OrderMessage original) {
    var result = new OrderPatternMessage();

    result.setItemName(original.getItemName());
    result.setOrderDateTime(original.getOrderDateTime());
    result.setOrderLocation(original.getOrderLocation());
    result.setOrderNumber(original.getOrderNumber());
    result.setTotalItemAmount(original.getPrice() * original.getQuantity());

    return result;
  }

  public static OrderRewardMessage mapToOrderReward(OrderMessage original) {
    var result = new OrderRewardMessage();

    result.setItemName(original.getItemName());
    result.setOrderDateTime(original.getOrderDateTime());
    result.setOrderLocation(original.getOrderLocation());
    result.setOrderNumber(original.getOrderNumber());
    result.setPrice(original.getPrice());
    result.setQuantity(original.getQuantity());

    return result;
  }

  public static Predicate<String, OrderMessage> isLargeQuantity() {
    return (key, value) -> value.getQuantity() > 200;
  }
}
