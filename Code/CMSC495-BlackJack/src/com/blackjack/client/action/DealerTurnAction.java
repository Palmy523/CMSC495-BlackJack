package com.blackjack.client.action;

import com.blackjack.client.action.GameAction.ActionType;
import com.blackjack.client.controls.DealerAI;
import com.blackjack.client.entities.GameState;
import com.blackjack.client.entities.GameState.TurnState;
import com.blackjack.client.event.Events;
import com.blackjack.client.event.GameEvent;
import com.blackjack.client.ui.BlackJackGamePanel;

public class DealerTurnAction extends GameAction {

	public DealerTurnAction(int delay, BlackJackGamePanel panel) {
		super(panel);		
	}

	@Override
	public void processAction(GameEvent event) {
		if (GameState.getTurn() == TurnState.DEALER_TURN) {
			panel.showDealerCard();
			DealerAI ai = new DealerAI(panel);
			ai.startTurn(event);
		}
	}
}