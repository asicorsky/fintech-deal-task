package com.elinext.fintech.bus;

import com.elinext.fintech.event.Event;
import com.elinext.fintech.handler.EventsHandler;
import com.google.common.eventbus.EventBus;

/**
 * Facade for event bus. </br>
 * Current implementation works with solution from Guava, but can be changed to any other solution (include custom). </br>
 * For these purposes see {@link com.elinext.fintech.handler.Handler} which currently used only as marker.
 */
public enum Bus {

	INSTANCE;

	private final EventBus eventBus = new EventBus(getClass().getName());

	Bus() {

		eventBus.register(new EventsHandler());
	}

	/**
	 * Trigger the event to specified event bus which should be handler at {@link com.elinext.fintech.handler.Handler}
	 *
	 * @param object the event
	 * @param <T>    the data type inside event
	 */
	public <T> void fire(Event<T> object) {

		eventBus.post(object);
	}
}
