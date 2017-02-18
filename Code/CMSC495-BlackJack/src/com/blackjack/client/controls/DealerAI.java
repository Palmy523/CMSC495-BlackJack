package com.blackjack.client.controls;

import com.blackjack.client.action.BustAction;
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

	private BlackJackGamePanel gamePanel;
	private Timer timer;

	public DealerAI(BlackJackGamePanel panel) {
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

		int dealerHandValue = GameState.getDealerHand().getHandValue();

		boolean hit = shouldHit(dealerHandValue);

		// dealer hit or stand actions
		//No hand action or bust actions here, those 
		//should get called by the Stand and Hit actions
		if (hit) {
			HitAction action = new HitAction(gamePanel);
			event.setActionType(ActionType.HIT);
			action.processAction(event);
		} else {
			timer.cancel();
			StandAction action = new StandAction(gamePanel);
			event.setActionType(ActionType.STAND);
			action.processAction(event);
		}

		Events.eventBus.fireEvent(event);
	}

	/**
	 * HIT until dealer's hand value = 17 regardless of a possible push, win, or
	 * loss
	 * 
	 * @param dealerHandValue
	 * @return boolean hit or no hit
	 */
	private boolean shouldHit(int dealerHandValue) {
		return (dealerHandValue < 17);
	}

}