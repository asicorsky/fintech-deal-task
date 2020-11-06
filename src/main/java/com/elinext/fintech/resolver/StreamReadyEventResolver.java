package com.elinext.fintech.resolver;

import com.elinext.fintech.bus.Bus;
import com.elinext.fintech.event.Event;
import com.elinext.fintech.event.EventType;
import com.elinext.fintech.event.bid.BidEvent;
import com.elinext.fintech.event.resource.ResourceReadyEvent;

import java.util.concurrent.CompletableFuture;

public class StreamReadyEventResolver implements EventResolver<ResourceReadyEvent> {

	@Override
	public void resolve(ResourceReadyEvent data) {

		Bus instance = Bus.INSTANCE;
		var stream = data.getStream();
		stream.forEach(dto -> CompletableFuture.runAsync(() -> instance.fire(new Event<>(EventType.BID, new BidEvent(dto)))));
	}
}
