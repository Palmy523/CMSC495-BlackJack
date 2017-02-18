package com.blackjack.client.controls;

import com.blackjack.client.action.GameAction;
import com.blackjack.client.action.HandEndAction;
import com.blackjack.client.action.HitAction;
import com.blackjack.client.action.StandAction;
import com.blackjack.client.action.GameAction.ActionType;
import com.blackjack.client.entities.GameState;
import com.blackjack.client.entities.GameState.TurnState;
import com.blackjack.client.event.Events;
import com.blackjack.client.event.GameEvent;
import com.blackjack.client.ui.BlackJackGamePanel;
import com.google.gwt.user.client.Timer;

public class DealerAI {

	private GameController controller;
	private BlackJackGamePanel gamePanel;
	private Timer timer;

	public DealerAI(GameController controller, BlackJackGamePanel panel) {
		this.controller = controller;
		this.gamePanel = panel;
	}
	
	public void startTurn(final GameEvent event) {
		
		timer = new Timer() {

			@Override
			public void run() {
				while (GameState.getTurn() == TurnState.DEALER_TURN) {
					processTurn(event);
				}
			}
		};
		timer.scheduleRepeating(1000);
	}
	
	@SuppressWarnings("static-access")
	public void processTurn(GameEvent event) {
		
		GameState state = event.getGameState();
		//TODO set to actual hand value from Jeffs update
		int playerHandValue = state.getPlayerHand().getHandValue();
		//TODO set to actual hand value from Jeffs update
		int dealerHandValue = state.getDealerHand().getHandValue();
		
		boolean hit = shouldHit(playerHandValue, dealerHandValue);
		
		if (hit) {
			HitAction action = new HitAction(gamePanel);
			action.processAction(event);
		} else {
			StandAction action = new StandAction(gamePanel);
			action.processAction(event);
		}
		
		if (dealerHandValue == 21) {
			timer.cancel();
			HandEndAction action = new HandEndAction(gamePanel);
			action.processAction(event);
		}
		
		if (dealerHandValue == 17 && playerHandValue <= 17) {
			timer.cancel();
			//TODO UI indicate player's hand is pushed
			
			
			
			//Fire the event so the rest of the UI knows that the action occurred
			event.setActionType(ActionType.BUST);
			Events.eventBus.fireEvent(event);
		}
		
	}
	
	private boolean shouldHit(int playerHandValue, int dealerHandValue) {
		if (/*dealerHandValue == 21 ||*/ dealerHandValue > playerHandValue) {
			//STAND = return false
			return false;
		}
		
		//HIT until dealer's hand value = 17
		if (dealerHandValue < 17 && dealerHandValue < playerHandValue) {
			//TODO under review, adding medium stakes dealer always hits on a soft 17
			return true;
		}
		
		if (dealerHandValue == 16) {
			return true;
		}
		
		return false;
	}

}