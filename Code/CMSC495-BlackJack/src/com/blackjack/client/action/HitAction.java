package com.blackjack.client.action;

import com.blackjack.client.entities.Card;
import com.blackjack.client.entities.Deck;
import com.blackjack.client.entities.GameState;
import com.blackjack.client.entities.GameState.TurnState;
import com.blackjack.client.entities.Hand;
import com.blackjack.client.event.Events;
import com.blackjack.client.event.GameEvent;
import com.blackjack.client.ui.BlackJackGamePanel;

public class HitAction extends GameAction {

	public HitAction(BlackJackGamePanel panel) {
		super(panel);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void processAction(GameEvent event) {
		GameState state = event.getGameState();
		Deck deck = state.getDeck();
		int score;
		//Update panel based on state, see accessors below 
		//for potentially required state objects
		//state.getBetAmount()
		//state.getDealerHand()
		//state.getPlayerHand()
		//state.getDeck()
		//state.getTurn()
		
		//TODO Play sounds using the SoundManager.play(SoundName) static method!!!!! See SoundManager to create
		//the sounds you need. Just follow the same setup that FAN1 uses, add an enum name
		//then add a new sound that follows the creation in the loadResources method that matches 
		//FAN1 but reference the sound from the war/sounds directory that you want.
			
		if(state.getTurn() == TurnState.PLAYER_TURN){
			Hand hand = state.getPlayerHand();	
			Card drawn = deck.draw();
			panel.hitPlayerHand(drawn);
			hand.hit(drawn);
			state.setPlayerHand(hand);
			score = hand.getHandValue();
			if(score> 21){
				hand.busts();
				state.setTurn(TurnState.DEALER_TURN);
				event.setActionType(ActionType.BUST);
				Events.eventBus.fireEvent(event);
			}			
		}
		else if(state.getTurn() == TurnState.DEALER_TURN){
			Hand hand = state.getDealerHand();					
			Card drawn = deck.draw();
			panel.hitDealerHand(drawn);
			hand.hit(drawn);
			state.setDealerHand(hand);
			score = hand.getHandValue();
			if(score> 21){
				hand.busts();
				state.setTurn(TurnState.HAND_END);
				event.setActionType(ActionType.BUST);
				Events.eventBus.fireEvent(event);
			}			
		}
		
		//TODO Check if the new hand value causes a bust, if so, call a new BustAction and pass in
		//the event
		
		//Fire the event so the rest of the UI knows that the action occurred
		event.setActionType(ActionType.HIT);
		Events.eventBus.fireEvent(event);
	}

}
