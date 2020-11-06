package com.elinext.fintech.event;

/**
 * Event wrapper
 * @param <T> the type of event
 */
public class Event<T> {

	private final EventType type;
	private final T value;

	public Event(EventType type, T value) {

		this.type = type;
		this.value = value;
	}

	public EventType getType() {

		return type;
	}

	public T getValue() {

		return value;
	}
}
