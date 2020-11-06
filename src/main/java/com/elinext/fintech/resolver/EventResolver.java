package com.elinext.fintech.resolver;

/**
 * Resolve and process the data for specified {@link com.elinext.fintech.handler.Handler} and {@link com.elinext.fintech.event.Event}
 */
public interface EventResolver<T> {

	void resolve(T data);
}
