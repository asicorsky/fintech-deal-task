package com.elinext.fintech.resource;

import java.util.stream.Stream;

/**
 * The interface which provide the stream with data from specified implementation<br/>
 *
 * @param <T> the type of the data
 */
public interface Resource<T> {

	Stream<T> stream();
}
