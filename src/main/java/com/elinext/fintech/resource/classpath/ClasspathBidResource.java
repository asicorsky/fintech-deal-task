package com.elinext.fintech.resource.classpath;

import com.elinext.fintech.dto.BidDto;
import com.elinext.fintech.dto.BidWrapperDto;
import com.elinext.fintech.resource.Resource;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Stream;

/**
 * Resource implementation for current application which get bids from json file from classpath
 */
public class ClasspathBidResource implements Resource<BidDto> {

	private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
	private static final String FILE_NAME = "bids.json";

	@Override
	public Stream<BidDto> stream() {

		InputStream inputStream = this.getClass().getResourceAsStream(FILE_NAME);
		try (inputStream) {
			var wrappers = OBJECT_MAPPER.readValue(inputStream, bidWrapperTypeRef());
			return wrappers.stream().map(BidWrapperDto::getBid);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private static TypeReference<List<BidWrapperDto>> bidWrapperTypeRef() {

		return new TypeReference<>() {

		};
	}
}
