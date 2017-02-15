package com.blackjack.client.action;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

import com.blackjack.client.entities.GameState;
import com.blackjack.client.event.Events;
import com.blackjack.client.event.GameEvent;
import com.blackjack.client.ui.BlackJackGamePanel;

public class BetAction extends GameAction {

	private int betAmount;
	
	public BetAction(BlackJackGamePanel panel, int betAmount) {
		super(panel);
		this.betAmount = betAmount;
	}

	@Override
	public void processAction(GameEvent event) {
		GameState state = event.getGameState();
		//Update panel based on state and bet amount, see accessors below 
		//for potentially required state objects
		//state.getBetAmount()
		//state.getDealerHand()
		//state.getPlayerHand()
		//state.getDeck()
		//state.getTurn()
		
		//TODO Cause a bet increase on the GameState by the specified amount in the constructor
		//NOTE: NEGATIVE integer values will be passed in for betMinus, and Positive for betPlus
		
		//TODO Play sounds using the SoundManager.play(SoundName) static method!!!!! See SoundManager to create
		//the sounds you need. Just follow the same setup that FAN1 uses, add an enum name
		//then add a new sound that follows the creation in the loadResources method that matches 
		//FAN1 but reference the sound from the war/sounds directory that you want.
		
		//TODO update the GameState by setting the proper turn, or other data
		
		//Fire the event so the rest of the UI knows that the action occurred
		event.setActionType(ActionType.BET);
		Events.eventBus.fireEvent(event);
	}
}
