package com.elinext.fintech.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BidWrapperDto {

	private final BidDto bid;

	@JsonCreator
	public BidWrapperDto(@JsonProperty("bid") BidDto bid) {

		this.bid = bid;
	}

	public BidDto getBid() {

		return bid;
	}
}
