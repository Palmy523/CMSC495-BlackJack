package com.blackjack.client.action;

import com.blackjack.client.action.GameAction.ActionType;
import com.blackjack.client.entities.GameState;
import com.blackjack.client.event.Events;
import com.blackjack.client.event.GameEvent;
import com.blackjack.client.ui.BlackJackGamePanel;

public class DealAction extends GameAction {

	public DealAction(int delay, BlackJackGamePanel panel) {
		super(panel);
	}

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
		
		//TODO DEAL A SINGLE CARD to a player hand or dealer hand from the deck
		//based on the getTurn() state. This will be called multiple times from the controller
		//to properly deal all the required cards.
		
		//TODO Play sounds using the SoundManager.play(SoundName) static method!!!!! See SoundManager to create
		//the sounds you need. Just follow the same setup that FAN1 uses, add an enum name
		//then add a new sound that follows the creation in the loadResources method that matches 
		//FAN1 but reference the sound from the war/sounds directory that you want.
		
		//THIS should do nothing if the GameState is in a state that does not allow a deal
		
		//TODO update the GameState by setting the proper turn, or other data
		
		//Fire the event so the rest of the UI knows that the action occurred
		event.setActionType(ActionType.DEAL);
		Events.eventBus.fireEvent(event);
	}

}
