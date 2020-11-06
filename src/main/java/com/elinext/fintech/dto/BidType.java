package com.elinext.fintech.dto;

import com.fasterxml.jackson.annotation.JsonValue;

public enum BidType implements HasText {

	ZU("ZU"),
	AQ("AQ");

	private final String text;

	BidType(String text) {

		this.text = text;
	}

	@Override
	@JsonValue
	public String text() {

		return text;
	}
}
