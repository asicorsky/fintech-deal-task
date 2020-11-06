package com.elinext.fintech.runner;

import com.elinext.fintech.bus.Bus;
import com.elinext.fintech.event.Event;
import com.elinext.fintech.event.EventType;
import com.elinext.fintech.event.resource.ResourceReadyEvent;
import com.elinext.fintech.resource.classpath.ClasspathBidResource;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Runner which trigger all the functions of application
 */
public class Runner {

	public void run(String... args) {

		var resource = new ClasspathBidResource();
		ScheduledExecutorService scheduledExecutor = Executors.newSingleThreadScheduledExecutor();
		scheduledExecutor.scheduleAtFixedRate(() -> {
			var stream = resource.stream();
			Bus.INSTANCE.fire(new Event<>(EventType.STREAM_READY, new ResourceReadyEvent(stream)));
		}, 0, 15, TimeUnit.SECONDS);
	}
}
