package com.blackjack.client.event;

import com.google.gwt.event.shared.SimpleEventBus;

public class Events {

	/**
	 * Event bus used for firing events with all registered handlers to 
	 * an event.
	 */
	public static SimpleEventBus eventBus = new SimpleEventBus();
}
