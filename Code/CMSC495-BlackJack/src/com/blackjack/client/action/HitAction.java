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
import com.blackjack.client.ui.GameButton.GameButtonType;
import com.google.gwt.core.client.GWT;

public class HitAction extends GameAction {

	public HitAction(BlackJackGamePanel panel) {
		super(panel);
	}

	@Override
	public void processAction(GameEvent event) {
		GameState state = event.getGameState();
		Deck deck = state.getDeck();
		int score;
		Hand hand;
		Card drawn;
		
				
		if(state.getTurn() == TurnState.PLAYER_TURN){
			
			panel.enableButton(GameButtonType.SPLIT, false);
			
			if(!state.isSplit() || state.isHittingPrimary()==true)
			{
				
				hand = state.getPlayerHand();	
				if(hand.getNumCards()>=2){
					panel.enableButton(GameButtonType.DOUBLE_DOWN, false);
				}
				
				if(hand.getBustStatus()){
					return;
				}
				
				drawn = deck.draw();
				SoundManager.play(SoundName.PLACE4);
				panel.hitPlayerHand(drawn);
				hand.hit(drawn);
				state.setPlayerHand(hand);
				score=hand.getHandValue();
				
				GWT.log("Hit Player hand with " + drawn.getRank());
				GWT.log("Player hand value: " + hand.getHandValue());
			}
			else
			{
				panel.enableButton(GameButtonType.DOUBLE_DOWN, false);
				hand = state.getPlayerSplitHand();	
				
				if(hand.getBustStatus()){
					return;
				}
				
				drawn = deck.draw();
				SoundManager.play(SoundName.PLACE4);
				panel.hitPlayerSplitHand(drawn);
				hand.hit(drawn);
				state.setPlayerSplitHand(hand);
				score=hand.getHandValue();
				
				GWT.log("Hit Player Split hand with " + drawn.getRank());
				GWT.log("Player hand value: " + hand.getHandValue());
			}	
				
				event.setActionType(ActionType.HIT);
				Events.eventBus.fireEvent(event);
								
				if(score > 21){
					hand.busts();
					panel.displayInstruction("Busted!");
					SoundManager.play(SoundName.BUST);
					BustAction b = new BustAction(panel);				
					b.processAction(event);
				}
				else if(score == 21){
					
					panel.displayInstruction("You got 21!");
					
					if(!state.isSplit()|| state.isHittingPrimary() == true){
						panel.twentyone();
						
						if(!state.isSplit()){
							panel.disableAllButtons();
							GameState.setTurn(TurnState.DEALER_TURN);
							DealerTurnAction action = new DealerTurnAction(panel);
							action.processAction(event);
						}
						
						state.setHittingPrimary(false);
						state.setHittingSplit(true);
					}
					else{					
						panel.twentyone_Split();
						panel.disableAllButtons();
						GameState.setTurn(TurnState.DEALER_TURN);
						DealerTurnAction action = new DealerTurnAction(panel);
						action.processAction(event);						
					}
				}			
		}
		else if(state.getTurn() == TurnState.DEALER_TURN){
			
			hand = state.getDealerHand();
			
			if(hand.getBustStatus()){
				return;
			}
			
			drawn = deck.draw();
			SoundManager.play(SoundName.PLACE4);
			panel.hitDealerHand(drawn);
			hand.hit(drawn);
			state.setDealerHand(hand);
			score = hand.getHandValue();	
			
			event.setActionType(ActionType.HIT);
			Events.eventBus.fireEvent(event);
					
			GWT.log("Hit Dealer hand with " + drawn.getRank());
			GWT.log("Dealer hand value: " + hand.getHandValue());
			
			if(score> 21){
				hand.busts();				
				BustAction b = new BustAction(panel);				
				b.processAction(event);
			}
			else if(score == 21){
				panel.dealerTwentyone();
				HandEndAction h = new HandEndAction(panel);
				h.processAction(event);
			}
		}
	}

}
