package com.blackjack.client.action;

import com.blackjack.client.action.GameAction.ActionType;
import com.blackjack.client.entities.Card;
import com.blackjack.client.entities.Deck;
import com.blackjack.client.entities.GameState;
import com.blackjack.client.entities.Hand;
import com.blackjack.client.entities.GameState.TurnState;
import com.blackjack.client.event.Events;
import com.blackjack.client.event.GameEvent;
import com.blackjack.client.sounds.SoundManager;
import com.blackjack.client.sounds.SoundManager.SoundName;
import com.blackjack.client.ui.BlackJackGamePanel;
import com.google.gwt.core.client.GWT;

public class DoubleDownAction extends GameAction {

	public DoubleDownAction(BlackJackGamePanel panel) {
		super(panel);
	}

	@Override
	public void processAction(GameEvent event) {
		
		GameState state = event.getGameState();
		Deck deck = state.getDeck();
		int score;
		Hand hand;
		
		// TODO Auto-generated method stub
		panel.displayInstruction("Doubled Down");
		event.setActionType(ActionType.DOUBLE_DOWN);
		event.getGameState().setDoubledDown(true);
		GameState.setBetAmount(GameState.getBetAmount()*2);
		hand = state.getPlayerHand();	
		
		if(hand.getBustStatus()){
			return;
		}
		
		Card drawn = deck.draw();
		SoundManager.play(SoundName.PLACE4);
		panel.hitPlayerHand_DoubleDown(drawn);
		hand.hit(drawn);
		state.setPlayerHand(hand);
		score = hand.getHandValue();
		
		event.setActionType(ActionType.HIT);
		Events.eventBus.fireEvent(event);
		
		GWT.log("Hit Player hand with " + drawn.getRank());
		GWT.log("Player hand value: " + hand.getHandValue());
		
		if(score > 21){
			hand.busts();
			panel.displayInstruction("Busted!");	//TODO Change to label
			event.setActionType(ActionType.PLAYER_BUST);
			SoundManager.play(SoundName.BUST);
			BustAction b = new BustAction(100, panel);				
			b.processAction(event);
		}
		else {			
			GameState.setTurn(TurnState.DEALER_TURN);
			DealerTurnAction action = new DealerTurnAction(100, panel);
			action.processAction(event);
		}
	}

}
