package com.elinext.fintech.event.bid;

import com.elinext.fintech.dto.BidDto;

public class BidEvent {

	private final BidDto bid;

	public BidEvent(BidDto bid) {

		this.bid = bid;
	}

	public BidDto getBid() {

		return bid;
	}
}
