package com.elinext.fintech.processor;

import com.elinext.fintech.dto.BidDto;
import com.elinext.fintech.dto.BidType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.util.Base64;
import java.util.Objects;
import java.util.Queue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class Processor {

	private final static Logger LOGGER = LoggerFactory.getLogger(Processor.class);
	private final Queue<BidDto> queue = new LinkedBlockingQueue<>();
	private final AtomicBoolean active = new AtomicBoolean(false);
	private final BidType type;
	private static final long MAX_AWAIT_TIME = 600;

	public Processor(BidType type) {

		this.type = type;

		LOGGER.trace("Processor initiated for {} type (queue name)", type);
		activate();
	}

	/**
	 * Add a new bid to processing queue
	 */
	public void propose(BidDto bid) {

		queue.offer(bid);
		if (!active.get()) {
			activate();
		}
	}

	/**
	 * Stop the execution of current thread which initiated by {@link java.util.concurrent.ForkJoinPool} and {@link CompletableFuture}
	 */
	public void salvation() {

		active.set(false);
	}

	// Method which activate execution and create CompletableFuture for the bid type
	private void activate() {

		var future = CompletableFuture.runAsync(() -> {
			active.set(true);
			long lastHandledAt = LocalDateTime.now().get(ChronoField.SECOND_OF_DAY);
			while (active.get()) {
				BidDto bid = queue.poll();
				if (Objects.nonNull(bid)) {
					lastHandledAt = LocalDateTime.now().get(ChronoField.SECOND_OF_DAY);
					// Thanks, Java, now we can use default Base64 API from core which works
					String payload = new String(Base64.getDecoder().decode(bid.getText()));
					// in case with multiple lines log it may be a problem to read with concurrent executions
					LOGGER.info("Bid was handled: id = {}, timestamp = {}, queue name = {}, payload = {}", bid.getId(), bid.getTimestamp(), bid.getType(), payload);
				} else {
					long now = LocalDateTime.now().get(ChronoField.SECOND_OF_DAY);
					if (now - lastHandledAt > MAX_AWAIT_TIME) {
						// free CPU resources because of execution processed at common ForkJoinPool due to reduce problems with slow CPU
						salvation();
					} else {
						try {
							TimeUnit.MILLISECONDS.sleep(50);
						} catch (InterruptedException e) {
							throw new RuntimeException(e);
						}
					}

				}
			}
		});
		future.thenAccept(__ -> LOGGER.trace("Free resources due to {} values has not come for a long time [{} seconds]", type, MAX_AWAIT_TIME));
	}
}
