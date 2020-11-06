package com.elinext.fintech.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BidDto {

	private final long id;
	private final long timestamp;
	private final BidType type;
	private final String text;

	@JsonCreator
	public BidDto(@JsonProperty("id") long id, @JsonProperty("ts") long timestamp, @JsonProperty("ty") BidType type, @JsonProperty("pl") String text) {

		this.id = id;
		this.timestamp = timestamp;
		this.type = type;
		this.text = text;
	}

	public long getId() {

		return id;
	}

	public long getTimestamp() {

		return timestamp;
	}

	public BidType getType() {

		return type;
	}

	public String getText() {

		return text;
	}

	@Override
	public String toString() {

		return "BidDto{" + "id='" + id + '\'' + ", timestamp='" + timestamp + '\'' + ", type=" + type + ", text='" + text + '\'' + '}';
	}
}
