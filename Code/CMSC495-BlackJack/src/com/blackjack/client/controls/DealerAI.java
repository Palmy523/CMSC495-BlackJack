package com.blackjack.client.controls;

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
		// TODO set to actual hand value from Jeffs update
		int playerHandValue = 0;
		
	}

}