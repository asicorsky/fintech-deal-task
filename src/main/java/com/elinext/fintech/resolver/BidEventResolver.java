package com.elinext.fintech.resolver;

import com.elinext.fintech.dto.BidDto;
import com.elinext.fintech.dto.BidType;
import com.elinext.fintech.event.bid.BidEvent;
import com.elinext.fintech.processor.Processor;
import com.elinext.fintech.processor.Processors;

public class BidEventResolver implements EventResolver<BidEvent> {

	@Override
	public void resolve(BidEvent data) {

		BidDto bid = data.getBid();
		BidType type = bid.getType();
		var processors = Processors.INSTANCE.processors();
		processors.computeIfAbsent(type, Processor::new).propose(bid);
	}
}
