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
/**
 * The HitAction is used to process a hit on a hand in the game of blackjack.
 * This deals a card to the appropriate hand and performs any subsequent actions
 * based on the hand value. 
 * 
 * @author Abby
 */
public class HitAction extends GameAction {

	/**
	 * 
	 * @param panel The BlackJackGamePanel to update
	 */
	public HitAction(BlackJackGamePanel panel) {
		super(panel);
	}

	/**
	 * Processes a hit by dealing a card to the hand for the player or 
	 * dealer based on the turn state. Checks value after hit and determines
	 * if hit causes a bust, ends the hand or procs the dealer turn if necessary.
	 */
	@Override
	public void processAction(GameEvent event) {
		Deck deck = GameState.getDeck();
		int score;
		Hand hand;
		Card drawn;
		
				
		if(GameState.getTurn() == TurnState.PLAYER_TURN){
			
			panel.enableButton(GameButtonType.SPLIT, false);
			
			if(!GameState.isSplit() || GameState.isHittingPrimary()==true)
			{
				
				hand = GameState.getPlayerHand();	
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
				GameState.setPlayerHand(hand);
				score=hand.getHandValue();
				
			}
			else
			{
				panel.enableButton(GameButtonType.DOUBLE_DOWN, false);
				hand = GameState.getPlayerSplitHand();	
				
				if(hand.getBustStatus()){
					return;
				}
				
				drawn = deck.draw();
				SoundManager.play(SoundName.PLACE4);
				panel.hitPlayerSplitHand(drawn);
				hand.hit(drawn);
				GameState.setPlayerSplitHand(hand);
				score=hand.getHandValue();
				
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
					
					if(!GameState.isSplit()|| GameState.isHittingPrimary() == true){
						panel.twentyone();
						
						if(!GameState.isSplit()){
							panel.disableAllButtons();
							GameState.setTurn(TurnState.DEALER_TURN);
							DealerTurnAction action = new DealerTurnAction(panel);
							action.processAction(event);
						}
						
						GameState.setHittingPrimary(false);
						GameState.setHittingSplit(true);
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
		else if(GameState.getTurn() == TurnState.DEALER_TURN){
			
			hand = GameState.getDealerHand();
			
			if(hand.getBustStatus()){
				return;
			}
			
			drawn = deck.draw();
			SoundManager.play(SoundName.PLACE4);
			panel.hitDealerHand(drawn);
			hand.hit(drawn);
			GameState.setDealerHand(hand);
			score = hand.getHandValue();	

					
			
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
			
			event.setActionType(ActionType.HIT);
			Events.eventBus.fireEvent(event);
		}
	}

}
