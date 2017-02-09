package com.blackjack.shared.handlers;

import com.blackjack.shared.events.ResetPasswordEvent;
import com.google.gwt.event.shared.EventHandler;

public interface ResetPasswordHandler extends EventHandler {

	public void onResetPassword(ResetPasswordEvent event);
	
}
