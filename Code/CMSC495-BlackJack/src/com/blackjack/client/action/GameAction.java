package com.blackjack.client.action;

import com.blackjack.client.event.GameEvent;
import com.google.gwt.event.shared.EventHandler;

public abstract class GameAction implements EventHandler{

	private int delay;
	
	public GameAction() {
		this(0);
	}
	
	public GameAction(int delay) {
		this.delay = delay;
	}
	
	public abstract void processAction(GameEvent event);
	
}
