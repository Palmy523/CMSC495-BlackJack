package com.blackjack.shared.handlers;

import com.blackjack.shared.events.LoginEvent;
import com.google.gwt.event.shared.EventHandler;

public interface LoginHandler extends EventHandler {

	public void OnLogin(LoginEvent event);
}
