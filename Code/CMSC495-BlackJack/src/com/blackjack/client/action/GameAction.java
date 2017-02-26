package com.blackjack.client.action;

import java.util.concurrent.Delayed;

import com.blackjack.client.event.GameEvent;
import com.blackjack.client.ui.BlackJackGamePanel;
import com.google.gwt.event.shared.EventHandler;

public abstract class GameAction implements EventHandler{

	public static enum ActionType {
		DEAL, 
		BET, 
		HIT, 
		STAND, 
		PLAYER_BUST, 
		DEALER_BUST, 
		PUSH, 
		BLACKJACK, 
		HAND_END, 
		DEALER_BLACKJACK,
		DOUBLE_DOWN,
		SPLIT,
		INSURANCE,
		SURRENDER
	}
	protected BlackJackGamePanel panel;
	protected ActionType type;
	
	public GameAction(BlackJackGamePanel panel) {
		this.panel = panel;
	}
	
	public abstract void processAction(GameEvent event);
	
}
