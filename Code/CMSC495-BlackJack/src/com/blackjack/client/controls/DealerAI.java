package com.blackjack.client.controls;

import com.blackjack.client.action.HandEndAction;
import com.blackjack.client.action.HitAction;
import com.blackjack.client.action.StandAction;
import com.blackjack.client.entities.GameState;
import com.blackjack.client.entities.GameState.TurnState;
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
	
	public void processTurn(GameEvent event) {
		//TODO set to actual hand value from Jeffs update
		int playerHandValue = 0;
		//TODO set to actual hand value from Jeffs update
		int dealerHandValue = 0;
		
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
			
		}
		
	}
	
	private boolean shouldHit(int playerHandValue, int dealerHandValue) {
		if (dealerHandValue == 21) {
			return false;
		}
		
		if (dealerHandValue > playerHandValue) {
			return false;
		}
		
		if (dealerHandValue < playerHandValue) {
			return true;
		}
		
		if (dealerHandValue == 16) {
			return true;
		}
		
		return false;
	}

}