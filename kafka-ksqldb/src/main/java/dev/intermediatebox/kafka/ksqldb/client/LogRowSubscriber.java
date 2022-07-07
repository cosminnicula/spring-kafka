package dev.intermediatebox.kafka.ksqldb.client;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.confluent.ksql.api.client.Row;

public class LogRowSubscriber implements Subscriber<Row> {

	private Subscription subscription;
	private static final Logger LOG = LoggerFactory.getLogger(LogRowSubscriber.class);
	
	@Override
	public void onSubscribe(Subscription s) {
		LOG.info("onSubscribe(), starting subscription");
		
		this.subscription = s;
		
		// request first row
		subscription.request(1l);
	}

	@Override
	public void onNext(Row t) {
		LOG.info("onNext() receive row : {}", t.values());
		
		// request next row
		subscription.request(1l);
	}

	@Override
	public void onError(Throwable t) {
		LOG.error("onError{} receive error : {}", t.getMessage());
	}

	@Override
	public void onComplete() {
		LOG.info("onComplete()");
	}

}
