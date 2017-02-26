package com.blackjack.client.action;

import com.blackjack.client.entities.Card;
import com.blackjack.client.entities.GameState;
import com.blackjack.client.entities.GameState.TurnState;
import com.blackjack.client.entities.Hand;
import com.blackjack.client.entities.Hand.HandType;
import com.blackjack.client.event.Events;
import com.blackjack.client.event.GameEvent;
import com.blackjack.client.ui.BlackJackGamePanel;

public class SplitAction extends GameAction {

	public SplitAction(BlackJackGamePanel panel) {
		super(panel);
	}

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

			event.setActionType(ActionType.SPLIT);
			Events.eventBus.fireEvent(event);
		}
	}

}
