package com.blackjack.client.action;

import java.util.concurrent.Delayed;

import com.blackjack.client.event.GameEvent;
import com.blackjack.client.ui.BlackJackGamePanel;
import com.google.gwt.event.shared.EventHandler;

/**
 * Abstract class used to implement game actions.
 * @author Dave
 *
 */
public abstract class GameAction implements EventHandler{

	/**
	 * A list of Action types to track the series of 
	 * actions processed in gameplay.
	 * 
	 * @author Dave
	 *
	 */
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

	/**
	 * The BlackJackGamePanel needed by the actions to update the UI.
	 */
	protected BlackJackGamePanel panel;
	
	/**
	 * The action type of the action
	 */
	protected ActionType type;
	
	public GameAction(BlackJackGamePanel panel) {
		this.panel = panel;
	}
	
	/**
	 * Abstract to allow implementation of actions.
	 * 
	 * @param event
	 */
	public abstract void processAction(GameEvent event);
	
}
