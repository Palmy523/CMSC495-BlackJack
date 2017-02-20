package com.blackjack.client.action;

import com.blackjack.client.action.GameAction.ActionType;
import com.blackjack.client.controls.DealerAI;
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

	private final static String message = "\nPlace the minimum bet and click 'Deal' to start a new hand";
	
	public HandEndAction(BlackJackGamePanel panel) {
		super(panel);		
	}

	@Override
	public void processAction(GameEvent event) {
		int playerHandVal = GameState.getPlayerHand().getHandValue();
		int dealerHandVal = GameState.getDealerHand().getHandValue();
		
		if (event.getActionType() == ActionType.BLACKJACK) {
			panel.displayInstruction("Blackjack!" + message);
			//TODO Award chips 3/2
		} else if (event.getActionType() == ActionType.PUSH || 
				playerHandVal == dealerHandVal) {
			panel.displayInstruction("Push!" + message);
			//Update chips with betted value
		} else if (event.getActionType() == ActionType.PLAYER_BUST) {
			panel.displayInstruction("Dealer wins!" + message);
		} else if (event.getActionType() == ActionType.DEALER_BUST) {
			panel.displayInstruction("You win!" + message);
		} else if (dealerHandVal > playerHandVal) {
			panel.displayInstruction("Dealer wins!" + message);
		} else if (dealerHandVal < playerHandVal) {
			panel.displayInstruction("You win!" + message);
		}
		
		DealerAI.cancelDealerActions();
		event.setActionType(ActionType.HAND_END);
		Events.eventBus.fireEvent(event);
	}
}