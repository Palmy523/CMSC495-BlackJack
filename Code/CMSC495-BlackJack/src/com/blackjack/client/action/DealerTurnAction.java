package com.blackjack.client.action;

import com.blackjack.client.controls.DealerAI;
import com.blackjack.client.entities.GameState;
import com.blackjack.client.entities.GameState.TurnState;
import com.blackjack.client.event.GameEvent;
import com.blackjack.client.ui.BlackJackGamePanel;

/**
 * The DealerTurnAction is used to start the dealer's turn by 
 * calling the DealerAI.
 * 
 * @author Dave
 *
 */
public class DealerTurnAction extends GameAction {

	/**
	 * 
	 * @param panel the BlackJackGamePanel UI to update.
	 */
	public DealerTurnAction(BlackJackGamePanel panel) {
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