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
import com.google.gwt.user.client.Timer;

public class DealAction extends GameAction {
	
	int cardNum;
	Card card;

	public DealAction(int delay, BlackJackGamePanel panel) {
		super(panel);
	}

	@Override
	public void processAction(final GameEvent event) {
		//get the game state from the event
		GameState state = event.getGameState();		
		//get bet amount
		int betAmount = state.getBetAmount();		
		//set card number to first card
		cardNum = 0;		
		//reset the hands if there are still hands showing on the table
		panel.resetHands();
		
		//return if state is not awaiting deal
		if(state.getTurn() != TurnState.AWAITING_DEAL)
			return;
		
		//if the bets are in the proper range, start the deal.
		if(betAmount >= state.getRoom().getMinBet() && betAmount <= state.getRoom().getMaxBet()) {
			
			//get the top card from the deck
			card = GameState.getDeck().draw();
			
			//play deal sound
			SoundManager.play(SoundName.PLACE1);
			
			//deal card in the interface
			panel.dealPlayerCard(card);
			
			//update player hand object
			GameState.getPlayerHand().hit(card);
			
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
		//check for push
		if (GameState.getPlayerHand().getHandValue() == 21 && GameState.getDealerHand().getHandValue() == 21) {
			event.setActionType(ActionType.PUSH);
			// TODO add call to dealerACtion?
		} //check for blackjack
		else if (GameState.getPlayerHand().getHandValue() == 21) {
			event.setActionType(ActionType.BLACKJACK);
			// TODO add call to dealerACtion?
		} //enable the appropriate buttons 
		else {
			panel.enableButton(GameButtonType.DEAL, false);
			panel.enableButton(GameButtonType.HIT, true);
			panel.enableButton(GameButtonType.STAND, true);
			panel.chipsEnabled(false);
			panel.enableButton(GameButtonType.SURRENDER, true);
			panel.enableButton(GameButtonType.DOUBLE_DOWN, true);

			//check if insurance is available
			if(GameState.getDealerHand().showingAce())
			{
				panel.displayInstruction("Want Insurance?");
				panel.enableButton(GameButtonType.INSURANCE, true);
			}

			//check if split is available
			if(GameState.getPlayerHand().canSplit())
				panel.enableButton(GameButtonType.SPLIT, true);

		}

		// update the state by setting the proper turn
		GameState.setTurn(TurnState.PLAYER_TURN);
		panel.displayInstruction("Players turn");
		//GameState.setPlayerHand(playerHand);
		//GameState.setDealerHand(dealerHand);

		// Fire the event so the rest of the UI knows that the action occurred
		event.setActionType(ActionType.DEAL);
		Events.eventBus.fireEvent(event);
	}
	
	private void dealCard(int cardNum)
	{
		if(cardNum == 0)
		{
			card = GameState.getDeck().draw();
			SoundManager.play(SoundName.PLACE2);
			panel.dealDealerCard(card);	
			GameState.getDealerHand().hit(card);
		}
		else if(cardNum == 1)
		{
			card = GameState.getDeck().draw();
			SoundManager.play(SoundName.PLACE3);
			panel.dealPlayerCard(card);
			GameState.getPlayerHand().hit(card);
		}
		else if(cardNum == 2)
		{
			card = GameState.getDeck().draw();
			SoundManager.play(SoundName.PLACE4);
			panel.dealDealerCard(card);
			GameState.getDealerHand().hit(card);
		}
	}
}
