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
		Hand hand;
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
			
			hand = state.getPlayerHand();	
			
			if(hand.getBustStatus()){
				return;
			}
			
			Card drawn = deck.draw();
			panel.hitPlayerHand(drawn);
			hand.hit(drawn);
			state.setPlayerHand(hand);
			score = hand.getHandValue();
			
			event.setActionType(ActionType.HIT);
			Events.eventBus.fireEvent(event);
			
			if(score> 21){
				hand.busts();
				panel.displayInstruction("Busted!");
				BustAction b = new BustAction(100, panel);				
				b.processAction(event);
			}			
		}
		else if(state.getTurn() == TurnState.DEALER_TURN){
			
			hand = state.getDealerHand();
			
			if(hand.getBustStatus()){
				return;
			}
			
			Card drawn = deck.draw();
			panel.hitDealerHand(drawn);
			hand.hit(drawn);
			state.setDealerHand(hand);
			score = hand.getHandValue();	
			
			event.setActionType(ActionType.HIT);
			Events.eventBus.fireEvent(event);
						
			if(score> 21){
				hand.busts();				
				BustAction b = new BustAction(100, panel);				
				b.processAction(event);
			}			
		}
	}

}
