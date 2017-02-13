package com.blackjack.client.action;

import com.blackjack.client.event.GameEvent;
import com.blackjack.client.ui.BlackJackGamePanel;
import com.google.gwt.event.shared.EventHandler;

public abstract class GameAction implements EventHandler{

	public static enum ActionType {DEAL, BET, HIT, STAND, BUST}
	private int delay;
	protected BlackJackGamePanel panel;
	protected ActionType type;
	
	public GameAction(BlackJackGamePanel panel) {
		this(0, panel);
	}
	
	public GameAction(int delay, BlackJackGamePanel panel) {
		this.delay = delay;
		this.panel = panel;
	}
	
	public abstract void processAction(GameEvent event);
	
}
