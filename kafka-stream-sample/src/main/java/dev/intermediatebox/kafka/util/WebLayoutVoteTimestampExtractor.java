package dev.intermediatebox.kafka.util;

import dev.intermediatebox.kafka.broker.message.WebLayoutVoteMessage;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.streams.processor.TimestampExtractor;

public class WebLayoutVoteTimestampExtractor implements TimestampExtractor {
	@Override
	public long extract(ConsumerRecord<Object, Object> record, long partitionTime) {
		var message = (WebLayoutVoteMessage) record.value();

		return message != null ? LocalDateTimeUtil.toEpochTimestamp(message.getVoteDateTime()) : record.timestamp();
	}
}
