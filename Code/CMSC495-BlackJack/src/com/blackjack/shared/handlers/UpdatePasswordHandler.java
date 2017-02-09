package com.blackjack.shared.handlers;

import com.blackjack.shared.events.UpdatePasswordEvent;
import com.google.gwt.event.shared.EventHandler;

public interface UpdatePasswordHandler extends EventHandler {

	public void onUpdatePassword(UpdatePasswordEvent event);
	
}
