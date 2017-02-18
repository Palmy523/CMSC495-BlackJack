package com.blackjack.client.action;

import com.blackjack.client.action.GameAction.ActionType;
import com.blackjack.client.entities.GameState;
import com.blackjack.client.entities.GameState.TurnState;
import com.blackjack.client.event.Events;
import com.blackjack.client.event.GameEvent;
import com.blackjack.client.ui.BlackJackGamePanel;

/**
 * -Check the hand values to determine who wins and determine push
 * -Award money and update chip amounts appropriately. Calls the 
 * GameController startGame() method to start a new hand.
 *
 */
public class HandEndAction extends GameAction {

	public HandEndAction(BlackJackGamePanel panel) {
		super(panel);		
	}

	@Override
	public void processAction(GameEvent event) {
		// TODO Auto-generated method stub	
		
		GameState state = event.getGameState();
		
		int playerHandVal = state.getPlayerHand().getHandValue();
		int dealerHandVal = state.getDealerHand().getHandValue();
		
		if (dealerHandVal > playerHandVal) {
			//TODO UI displays dealer wins
			//play chip sounds when player loses
				// balance should already be updated when the betting started, no update
				// to balance
		} if (dealerHandVal < playerHandVal) {
			//TODO UI displays player wins
			
			// play chip winning sounds
			// update players balance 1:1 for normal wins
			// ...
		} else {
			//TODO UI displays a push is made
			
		}
		
		
	}
}