package com.elinext.fintech.handler;

import com.elinext.fintech.event.Event;
import com.elinext.fintech.event.EventType;
import com.elinext.fintech.resolver.BidEventResolver;
import com.elinext.fintech.resolver.EventResolver;
import com.elinext.fintech.resolver.StreamReadyEventResolver;
import com.google.common.eventbus.Subscribe;

public class EventsHandler<T> implements Handler<Event<T>> {

	@SuppressWarnings("unchecked")
	@Subscribe
	@Override
	public void handle(Event<T> event) {

		EventType type = event.getType();
		var resolver = (EventResolver<T>) resolver(type);
		resolver.resolve(event.getValue());
	}

	private EventResolver<?> resolver(EventType type) {

		if (EventType.BID.equals(type)) {
			return new BidEventResolver();
		} else if (EventType.STREAM_READY.equals(type)) {
			return new StreamReadyEventResolver();
		}

		throw new UnsupportedOperationException(type + " is not implemented");
	}
}
