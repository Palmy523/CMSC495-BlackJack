package com.blackjack.shared.handlers;

import com.blackjack.shared.events.CreateAccountEvent;
import com.google.gwt.event.shared.EventHandler;


public interface CreateAccountHandler extends EventHandler {

	public void onCreateAccount(CreateAccountEvent event);
	
}
