package com.blackjack.client.action;

import java.util.Random;

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
import com.google.gwt.user.client.Timer;

public class DealAction extends GameAction {
	
	Deck deck;
	Hand playerHand;
	Hand dealerHand;
	int cardNum;
	Card card;

	public DealAction(int delay, BlackJackGamePanel panel) {
		super(panel);
	}

	@Override
	public void processAction(final GameEvent event) {
		GameState state = event.getGameState();
		int betAmount = state.getBetAmount();
		cardNum = 0;
		deck = state.getDeck();
		playerHand = new Hand();
		dealerHand = new Hand();
		panel.resetHands();
		//Update panel based on state, see accessors below 
		//for potentially required state objects
		//state.getBetAmount()
		//state.getDealerHand()
		//state.getPlayerHand()
		//state.getDeck()
		//state.getTurn()
		
		if(state.getTurn() != TurnState.AWAITING_DEAL)
			return;
		
		if(betAmount >= state.getRoom().getMinBet() && betAmount <= state.getRoom().getMaxBet()) {
			
			card = deck.draw();			
			//playRandomDealSound();
			SoundManager.play(SoundName.PLACE1);
			panel.dealPlayerCard(card);
			playerHand.hit(card);
			GWT.log("Hit player hand with card " + card.getRank());

			Timer timer = new Timer() {
				public void run() {
					dealCard(cardNum);
					
					cardNum++;
					if (cardNum == 3) {
						cancel();
					}
				}
				
				public void cancel() {
					super.cancel();
					endDeal(event);
				}
			};

			timer.scheduleRepeating(300);
		}
		else
			return;
		

	}
	
	private void endDeal(GameEvent event) {
		int temp = 1;
		if (playerHand.getHandValue() == 21 && dealerHand.getHandValue() == 21) {
			panel.showDealerCard();
			event.setActionType(ActionType.PUSH);
			HandEndAction handEndAction = new HandEndAction(panel);
			handEndAction.processAction(event);
		} else if (playerHand.getHandValue() == 21) {
			event.setActionType(ActionType.BLACKJACK);
			HandEndAction handEndAction = new HandEndAction(panel);
			handEndAction.processAction(event);
		} else if (dealerHand.getHandValue() == 21) {
			panel.showDealerCard();
			event.setActionType(ActionType.DEALER_BLACKJACK);
			HandEndAction handEndAction = new HandEndAction(panel);
			handEndAction.processAction(event);
		} else {
			panel.enableButton(GameButtonType.DEAL, false);
			panel.enableButton(GameButtonType.HIT, true);
			panel.enableButton(GameButtonType.STAND, true);
			panel.chipsEnabled(false);
			panel.enableButton(GameButtonType.SURRENDER, true);
			panel.enableButton(GameButtonType.DOUBLE_DOWN, true);

			if (dealerHand.showingAce()) {
				panel.displayInstruction("Want Insurance?");
				panel.enableButton(GameButtonType.INSURANCE, true);
			}

			if (playerHand.canSplit())
				panel.enableButton(GameButtonType.SPLIT, true);

			// update the state by setting the proper turn
			GameState.setTurn(TurnState.PLAYER_TURN);
			panel.displayInstruction("Players turn");
			GameState.setPlayerHand(playerHand);
			GWT.log("Player hand value: " + GameState.getPlayerHand().getHandValue());
			GameState.setDealerHand(dealerHand);
			GWT.log("Dealer hand value: " + GameState.getDealerHand().getHandValue());
		}

		// Fire the event so the rest of the UI knows that the action occurred
		event.setActionType(ActionType.DEAL);
		Events.eventBus.fireEvent(event);
	}
	
	private void dealCard(int cardNum)
	{
		if(cardNum == 0)
		{
			card = deck.draw();
			//playRandomDealSound();
			SoundManager.play(SoundName.PLACE2);
			panel.dealDealerCard(card, GameState.isEasyPlay());	
			dealerHand.hit(card);
			GWT.log("Hit dealer hand with card " + card.getRank());
		}
		else if(cardNum == 1)
		{
			card = deck.draw();
			//playRandomDealSound();
			SoundManager.play(SoundName.PLACE3);
			panel.dealPlayerCard(card);
			playerHand.hit(card);
			GWT.log("Hit player hand with card " + card.getRank());
		}
		else if(cardNum == 2)
		{
			card = deck.draw();
			//playRandomDealSound();
			SoundManager.play(SoundName.PLACE4);
			panel.dealDealerCard(card, GameState.isEasyPlay());
			dealerHand.hit(card);
			GWT.log("Hit dealer hand with card " + card.getRank());
		}
	}
}