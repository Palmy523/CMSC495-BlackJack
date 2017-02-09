package com.blackjack.shared.handlers;

import com.blackjack.shared.events.ConfirmEmailEvent;
import com.google.gwt.event.shared.EventHandler;

public interface ConfirmEmailHandler extends EventHandler {

	public void onConfirmEmail(ConfirmEmailEvent event);
	
}
