package com.blackjack.client.action;

import com.blackjack.client.action.GameAction.ActionType;
import com.blackjack.client.entities.GameState;
import com.blackjack.client.event.Events;
import com.blackjack.client.event.GameEvent;
import com.blackjack.client.ui.BlackJackGamePanel;

public class BustAction extends GameAction {

	public BustAction(int delay, BlackJackGamePanel panel) {
		super(panel);
		// TODO Auto-generated constructor stub
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
		
		//TODO Cause the panel bust action
		
		//TODO Play sounds using the SoundManager.play(SoundName) static method!!!!! See SoundManager to create
		//the sounds you need. Just follow the same setup that FAN1 uses, add an enum name
		//then add a new sound that follows the creation in the loadResources method that matches 
		//FAN1 but reference the sound from the war/sounds directory that you want.
		//A good one for this would be wheel of fortune type (WAH WAH WAH [kidding])
		
		//TODO update the GameState by setting the proper turn, or other data (If player busts set player turn to dealer)
		// if dealer busts check gamestate to see who wins and ??? ask me later ???
		
		//Fire the event so the rest of the UI knows that the action occurred
		event.setActionType(ActionType.BUST);
		Events.eventBus.fireEvent(event);
	}

}
