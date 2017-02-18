package com.blackjack.client.action;

import com.blackjack.client.entities.GameState;
import com.blackjack.client.event.Events;
import com.blackjack.client.event.GameEvent;
import com.blackjack.client.ui.BlackJackGamePanel;
import com.blackjack.client.entities.GameState.TurnState;

/**
 * 
 * @author Lea
 *
 */
public class BustAction extends GameAction {

	public BustAction(int delay, BlackJackGamePanel panel) {
		super(panel);
		// TODO Auto-generated constructor stub
	}

	/**
	 * determines if the current turn busted (dealer or player)
	 * 
	 * if player busted, ends player's turn and invokes DealerTurnAction 
	 * 		processAction(event)
	 * if dealer busted, ends dealer's turn and invokes HandEndAction
	 * 		processAction(event)
	 * 
	 *  //TODO identify possible exceptions
	 */
	@SuppressWarnings("static-access")
	@Override
	public void processAction(GameEvent event) {
		GameState state = event.getGameState();
		//Update panel based on state, see accessors below 
		//for potentially required state objects
		//state.getBetAmount()
		//state.getDealerHand()
		//state.getPlayerHand()
		//state.getDeck()
		//state.getTurn()

		//TODO Cause the panel bust action
		panel.disableAllButtons();

		TurnState turn = state.getTurn();
		
		//TODO fix static access to turn states
		if (turn == TurnState.PLAYER_TURN) {
			//TODO UI displays Player Busts
			
			//player busts set player turn to dealer
			state.setTurn(TurnState.DEALER_TURN); //TODO needs a corresponding label case
			DealerTurnAction dAction = new DealerTurnAction(100, panel);
			dAction.processAction(event);
		} else if (turn == TurnState.DEALER_TURN) {
			//TODO UI displays Dealer Busts
			
			//dealer busts set state to end dealer's turn
			state.setTurn(TurnState.HAND_END); //TODO needs a corresponding label case
			HandEndAction endHand = new HandEndAction(100, panel);
			endHand.processAction(event);
		}
		
		// Project Plan 2.4.10 Player Busts, the player does not lose their bet until the
		// dealer ends their turn. Therefore no sound needed here.

		//TODO if dealer busts check gamestate to see who wins and ??? ask me later ???

		//Fire the event so the rest of the UI knows that the action occurred
		event.setActionType(ActionType.BUST);
		Events.eventBus.fireEvent(event);
	}

}
