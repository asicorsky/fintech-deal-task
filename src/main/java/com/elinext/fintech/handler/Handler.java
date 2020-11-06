package com.elinext.fintech.handler;

import com.elinext.fintech.event.Event;

/**
 * The marker for all event handlers. Not used currently, but may help with custom implementation of event queue
 */
public interface Handler<T extends Event<?>> {

	void handle(T handler);
}
