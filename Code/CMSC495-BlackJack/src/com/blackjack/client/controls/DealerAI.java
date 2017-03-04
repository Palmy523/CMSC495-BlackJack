package com.blackjack.client.controls;

import com.blackjack.client.action.GameAction.ActionType;
import com.blackjack.client.action.HandEndAction;
import com.blackjack.client.action.HitAction;
import com.blackjack.client.action.StandAction;
import com.blackjack.client.entities.GameState;
import com.blackjack.client.entities.GameState.TurnState;
import com.blackjack.client.event.Events;
import com.blackjack.client.event.GameEvent;
import com.blackjack.client.ui.BlackJackGamePanel;
import com.google.gwt.user.client.Timer;

/**
 * Class used to issue a series of commands by the dealer based on the 
 * state of the hand. 
 * 
 * @author Dave
 *
 */
public class DealerAI {

	private BlackJackGamePanel gamePanel;
	private static Timer timer;

	/**
	 * 
	 * @param panel The BlackJackGamePanel to update
	 */
	public DealerAI(BlackJackGamePanel panel) {
		this.gamePanel = panel;
	}

	/**
	 * Starts the dealers turn with a timer at 1 second intervals
	 * @param event
	 */
	public void startTurn(final GameEvent event) {


		
		timer = new Timer() {

			@Override
			public void run() {
				if (GameState.getTurn() == TurnState.DEALER_TURN) {
					processTurn(event);
				}
			}
		};
		timer.scheduleRepeating(1000);
	}

	/**
	 * Performs an appropriate action by the dealer based on the state
	 * of the cards. 
	 * 
	 * @param event
	 */
	public void processTurn(GameEvent event) {

		int dealerHandValue = GameState.getDealerHand().getHandValue();
		int playerHandVal = GameState.getPlayerHand().getHandValue();
		if (GameState.isSplit()) {
			int splitValue = GameState.getPlayerSplitHand().getHandValue();
			if (splitValue <= 21) {
				if (playerHandVal > 21) {
					playerHandVal = splitValue;
				} else {
					playerHandVal = (playerHandVal > splitValue) ? playerHandVal : splitValue;
				}
			}
		}
		
		if (playerHandVal > 21) {
			HandEndAction handEnd = new HandEndAction(gamePanel);
			event.setActionType(ActionType.PLAYER_BUST);
			handEnd.processAction(event);
		}
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
	
	/**
	 * Stops the dealer from issuing any more actions.
	 */
	public static void cancelDealerActions() {
		if (timer != null) {
			timer.cancel();
		}
	}

}