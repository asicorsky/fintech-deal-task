package com.elinext.fintech.event.resource;

import com.elinext.fintech.dto.BidDto;

import java.util.stream.Stream;

public class ResourceReadyEvent {

	private final Stream<BidDto> stream;

	public ResourceReadyEvent(Stream<BidDto> stream) {

		this.stream = stream;
	}

	public Stream<BidDto> getStream() {

		return stream;
	}
}
