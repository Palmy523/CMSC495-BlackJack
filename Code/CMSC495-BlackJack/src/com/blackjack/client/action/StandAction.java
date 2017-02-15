package com.blackjack.client.action;

import com.blackjack.client.action.GameAction.ActionType;
import com.blackjack.client.entities.GameState;
import com.blackjack.client.event.Events;
import com.blackjack.client.event.GameEvent;
import com.blackjack.client.ui.BlackJackGamePanel;

public class StandAction extends GameAction {

	public StandAction(int delay, BlackJackGamePanel panel) {
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
		
		//TODO Cause the hand to stand based on state.getTurn() (PLAYER OR DEALER STAND)
		
		//TODO Play sounds using the SoundManager.play(SoundName) static method!!!!! See SoundManager to create
		//the sounds you need. Just follow the same setup that FAN1 uses, add an enum name
		//then add a new sound that follows the creation in the loadResources method that matches 
		//FAN1 but reference the sound from the war/sounds directory that you want.
		
		//TODO update the GameState by setting the proper turn, or other data
		
		//Fire the event so the rest of the UI knows that the action occurred
		event.setActionType(ActionType.STAND);
		Events.eventBus.fireEvent(event);
	}

}
