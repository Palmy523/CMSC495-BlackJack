package com.blackjack.client.action;

import com.blackjack.client.entities.Card;
import com.blackjack.client.entities.GameState;
import com.blackjack.client.entities.GameState.TurnState;
import com.blackjack.client.entities.Hand;
import com.blackjack.client.entities.Hand.HandType;
import com.blackjack.client.event.Events;
import com.blackjack.client.event.GameEvent;
import com.blackjack.client.ui.BlackJackGamePanel;

/**
 * Performs a split of the primary hand into two separate hands.
 * 
 * @author Lea
 *
 */
public class SplitAction extends GameAction {

	/**
	 * 
	 * @param panel the BlackJackGamePanel to update.
	 */
	public SplitAction(BlackJackGamePanel panel) {
		super(panel);
	}

	/**
	 * Updates the UI by taking one card from the primary hand and putting
	 * it into a separate split hand. Updates the game state appropriately so other
	 * actions can perform appropriately.
	 */
	@Override
	public void processAction(GameEvent event) {
		if (GameState.getTurn() == TurnState.PLAYER_TURN && GameState.getPlayerHand().canSplit()) {
			// TODO Auto-generated method stub
			panel.getSplitButton().setEnabled(false);

			// Splits players hand into two separate hands
			panel.splitPlayerHand();
			GameState.setSplit(true);

			Card card = GameState.getPlayerHand().split();
			Hand hand = new Hand();
			hand.hit(card);
			GameState.setPlayerSplitHand(hand);
			
			GameState.setHittingPrimary(true);
			
			HitAction h = new HitAction(panel);
			h.processAction(event);
			
			event.setActionType(ActionType.SPLIT);
			Events.eventBus.fireEvent(event);
		}
	}

}
