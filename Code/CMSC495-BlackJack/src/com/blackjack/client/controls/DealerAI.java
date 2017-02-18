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
		// //TODO set to actual hand value from Jeffs update
		// int playerHandValue = state.getPlayerHand().getHandValue();
		// TODO set to actual hand value from Jeffs update
		int dealerHandValue = state.getDealerHand().getHandValue();

		boolean hit = shouldHit(dealerHandValue);

		// dealer hit or stand actions
		if (hit) {
			HitAction action = new HitAction(gamePanel);
			action.processAction(event);
			event.setActionType(ActionType.HIT);
		} else {
			timer.cancel();
			StandAction action = new StandAction(gamePanel);
			action.processAction(event);
			event.setActionType(ActionType.STAND);

			// dealerHandValue should be > 0
			// if dealer did not bust
			if (dealerHandValue < 22) {
				HandEndAction endHand = new HandEndAction(gamePanel);
				endHand.processAction(event);
			}
		}

		// dealer busts after hit
		if (dealerHandValue > 21) {
			timer.cancel();
			// TODO UI indicate dealer busted
			state.setTurn(TurnState.HAND_END);

			BustAction action = new BustAction(100, gamePanel);
			action.processAction(event);

			// Fire the event so the rest of the UI knows that the action
			// occurred
			event.setActionType(ActionType.BUST);
		}

		// Fire the event so the rest of the UI knows that the action occurred
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
		if (dealerHandValue < 17) {
			// TODO under review, adding medium stakes dealer always hits on a
			// soft 17
			return true;
		}
		return false;
	}

}