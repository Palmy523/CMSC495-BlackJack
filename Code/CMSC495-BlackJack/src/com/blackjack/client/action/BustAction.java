package com.blackjack.client.action;

import com.blackjack.client.entities.GameState;
import com.blackjack.client.event.Events;
import com.blackjack.client.event.GameEvent;
import com.blackjack.client.ui.BlackJackGamePanel;
import com.blackjack.client.entities.GameState.TurnState;

/**
 * The BustAction is processed when the hand value for a hand 
 * goes over 21. This handles the ending of turns, hands, and updates
 * the UI with a visual display of a Bust.
 * 
 * @author Lea
 *
 */
public class BustAction extends GameAction {

	/**
	 * Constructor.
	 * @param panel The BlackJackGamePanel to update
	 */
	public BustAction(BlackJackGamePanel panel) {
		super(panel);
		// TODO Auto-generated constructor stub
	}

	/**
	 * determines if the current turn busted (dealer or player)
	 * 
	 * if player busted, ends player's turn and invokes DealerTurnAction
	 * processAction(event) if dealer busted, ends dealer's turn and invokes
	 * HandEndAction processAction(event)
	 * 
	 */
	@SuppressWarnings("static-access")
	@Override
	public void processAction(GameEvent event) {
		//TODO Cause the panel bust action

		TurnState turn = GameState.getTurn();
		
		if (turn == TurnState.PLAYER_TURN) {
			//Logic for normal gameplay, no split
			if (!GameState.isSplit() || (GameState.isSplit() && GameState.isHittingSplit())) {
				if (GameState.isHittingSplit()) {
					panel.playerBust_Split();
				} else {
					panel.playerBust();
				}
				panel.disableAllButtons();
				GameState.setTurn(TurnState.DEALER_TURN); //TODO needs a corresponding label case
				DealerTurnAction dAction = new DealerTurnAction(panel);
				dAction.processAction(event);
				event.setActionType(ActionType.PLAYER_BUST);
			} else if (GameState.isSplit() && GameState.isHittingPrimary()) {
				panel.playerBust();
				GameState.setHittingPrimary(false);
				GameState.setHittingSplit(true);
				if(!GameState.isDoubledDown()){
					HitAction h = new HitAction(panel);
					h.processAction(event);
				}else{
					GameState.setTurn(TurnState.DEALER_TURN);
					DealerTurnAction dAction = new DealerTurnAction(panel);
					dAction.processAction(event);
				}
			}
		} else if (turn == TurnState.DEALER_TURN) {
			panel.dealerBust();
			GameState.setTurn(TurnState.HAND_END);
			HandEndAction endHand = new HandEndAction(panel);
			event.setActionType(ActionType.DEALER_BUST);
			endHand.processAction(event);
		}
		
		//Fire the event so the rest of the UI knows that the action occurred
		Events.eventBus.fireEvent(event);
	}

}
