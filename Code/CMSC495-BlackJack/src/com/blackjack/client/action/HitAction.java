package com.blackjack.client.action;

import com.blackjack.client.entities.Card;
import com.blackjack.client.entities.Deck;
import com.blackjack.client.entities.GameState;
import com.blackjack.client.entities.GameState.TurnState;
import com.blackjack.client.entities.Hand;
import com.blackjack.client.event.Events;
import com.blackjack.client.event.GameEvent;
import com.blackjack.client.sounds.SoundManager;
import com.blackjack.client.sounds.SoundManager.SoundName;
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
		
				
		if(state.getTurn() == TurnState.PLAYER_TURN){
			
			hand = state.getPlayerHand();	
			
			if(hand.getBustStatus()){
				return;
			}
			
			Card drawn = deck.draw();
			SoundManager.play(SoundName.PLACE4);
			panel.hitPlayerHand(drawn);
			hand.hit(drawn);
			state.setPlayerHand(hand);
			score = hand.getHandValue();
			
			event.setActionType(ActionType.HIT);
			Events.eventBus.fireEvent(event);
			
			if(score> 21){
				hand.busts();
				panel.displayInstruction("Busted!");	//TODO Change to label
				SoundManager.play(SoundName.BUST);
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
			SoundManager.play(SoundName.PLACE4);
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
