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

public class DealAction extends GameAction {

	public DealAction(int delay, BlackJackGamePanel panel) {
		super(panel);
	}

	@Override
	public void processAction(GameEvent event) {
		GameState state = event.getGameState();
		int betAmount = state.getBetAmount();
		Deck deck = state.getDeck();
		Hand playerHand = new Hand();
		Hand dealerHand = new Hand();
		//Update panel based on state, see accessors below 
		//for potentially required state objects
		//state.getBetAmount()
		//state.getDealerHand()
		//state.getPlayerHand()
		//state.getDeck()
		//state.getTurn()
		
		if(state.getTurn() == TurnState.AWAITING_DEAL && betAmount > state.getRoom().getMinBet() && betAmount < state.getRoom().getMaxBet()) {
			
			//TODO GWT Timer can be used to deal cards at certain intervals until all cards
			//are dealt, see http://www.gwtproject.org/javadoc/latest/com/google/gwt/user/client/Timer.html
			
			Card card = deck.draw();			
			SoundManager.play(SoundName.PLACE1);
			panel.dealPlayerCard(card);
			playerHand.hit(card);
			
			card = deck.draw();
			SoundManager.play(SoundName.PLACE2);
			panel.dealDealerCard(deck.draw());			
			card = deck.draw();
			dealerHand.hit(card);
			
			card = deck.draw();
			SoundManager.play(SoundName.PLACE3);
			panel.dealPlayerCard(deck.draw());
			playerHand.hit(card);
			
			card = deck.draw();
			SoundManager.play(SoundName.PLACE4);
			panel.dealDealerCard(deck.draw());
			dealerHand.hit(card);
			
		}
		else
			return;
		
		if(playerHand.getHandValue() == 21 && dealerHand.getHandValue() == 21)
		{
			event.setActionType(ActionType.PUSH);
			//TODO add call to dealerACtion?
		}			
		else if(playerHand.getHandValue() == 21)
		{
			event.setActionType(ActionType.BLACKJACK);
			//TODO add call to dealerACtion?
		}			
		else 
		else
		{
			panel.enabled(GameButtonType.DEAL, false);
			panel.enabled(GameButtonType.HIT, true);
			panel.enabled(GameButtonType.STAND, true);
			panel.chipsEnabled(false);
			panel.enabled(GameButtonType.SURRENDER, true);			
			panel.enabled(GameButtonType.DOUBLE_DOWN, true);
			
			if(dealerHand.showingAce())
				panel.enabled(GameButtonType.INSURANCE, true);
			
			if(playerHand.canSplit())
				panel.enabled(GameButtonType.SPLIT, true);
			
		}
			//TODO call DealerAction.processAction(gameEvent)
			
			//update the state by setting the proper turn
			state.setTurn(TurnState.PLAYER_TURN);
			state.setPlayerHand(playerHand);
			state.setDealerHand(dealerHand);
		
		//Fire the event so the rest of the UI knows that the action occurred
		event.setActionType(ActionType.DEAL);
		Events.eventBus.fireEvent(event);
	}

}
