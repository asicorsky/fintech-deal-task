package com.elinext.fintech.processor;

import com.elinext.fintech.dto.BidType;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * The holder for currently active processes
 */
public enum Processors {

	INSTANCE;

	private final Map<BidType, Processor> processors = new ConcurrentHashMap<>();

	public Map<BidType, Processor> processors() {

		return processors;
	}
}
