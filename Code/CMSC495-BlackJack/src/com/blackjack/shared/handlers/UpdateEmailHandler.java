package com.blackjack.shared.handlers;

import com.blackjack.shared.events.UpdateEmailEvent;
import com.google.gwt.event.shared.EventHandler;

public interface UpdateEmailHandler extends EventHandler {
	
	public void onUpdateEmail(UpdateEmailEvent event);

}
