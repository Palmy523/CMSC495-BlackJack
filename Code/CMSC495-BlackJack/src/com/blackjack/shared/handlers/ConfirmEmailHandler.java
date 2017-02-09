package com.blackjack.shared.handlers;

import java.io.Serializable;

import com.blackjack.shared.events.ConfirmEmailEvent;
import com.google.gwt.event.shared.EventHandler;

public interface ConfirmEmailHandler extends EventHandler, Serializable{

	public void onConfirmEmail(ConfirmEmailEvent event);
	
}
