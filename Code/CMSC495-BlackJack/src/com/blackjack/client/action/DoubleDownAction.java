package com.blackjack.client.action;

import com.blackjack.client.controls.UserController;
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

/**
 * The DoubleDownAction performs actions relating to a DoubleDown
 * in blackjack. Hits the primary and (if necessary) split hand with
 * an extra card and doubles the current bet.
 * 
 * @author Stephanie
 *
 */
public class DoubleDownAction extends GameAction {

	/**
	 * 
	 * @param panel The BlackJackGamePanel to updates
	 */
	public DoubleDownAction(BlackJackGamePanel panel) {
		super(panel);
	}

	/**
	 * Processes the DoubleDown action by doubling the current bet
	 * and hitting one card to the primary hand. It also hits a card to 
	 * the split hand if the hand is split. Initiates busts, and dealer turn
	 * appropriately.
	 */
	@Override
	public void processAction(GameEvent event) {
		
		GameState state = event.getGameState();
		Deck deck = state.getDeck();
		int score;
		Hand hand;
		
		panel.displayInstruction("Doubled Down");
		event.setActionType(ActionType.DOUBLE_DOWN);
		GameState.setDoubledDown(true);
		panel.bet(GameState.getBetAmount()*2);
		UserController.updateChipCount(-GameState.getBetAmount());
		GameState.setBetAmount(GameState.getBetAmount()*2);
		hand = GameState.getPlayerHand();	
		
		if(hand.getBustStatus()){
			return;
		}
		
		GameState.setDoubledDown(true);
		
		Card drawn = deck.draw();
		SoundManager.play(SoundName.PLACE4);
		panel.hitPlayerHand_DoubleDown(drawn);
		hand.hit(drawn);
		GameState.setPlayerHand(hand);
		score = hand.getHandValue();
		
		
		if(GameState.isSplit()){
			
			Hand splitHand = GameState.getPlayerSplitHand();
			
			if(splitHand.getBustStatus()){
				return;
			}
			
			//dealing second card to split hand
			Card drawn2 = deck.draw();
			SoundManager.play(SoundName.PLACE4);
			panel.hitPlayerSplitHand(drawn2);
			splitHand.hit(drawn2);
			
			//dealing double down card to split hand
			Card drawn3 = deck.draw();
			panel.hitPlayerSplitHand_DoubleDown(drawn3);
			splitHand.hit(drawn3);
			GameState.setPlayerSplitHand(splitHand);
		}
		
		panel.disableAllButtons();
		
		if(score > 21){
			hand.busts();
			panel.displayInstruction("Busted!");	//TODO Change to label
			event.setActionType(ActionType.PLAYER_BUST);
			SoundManager.play(SoundName.BUST);
			BustAction b = new BustAction(panel);				
			b.processAction(event);
		}
		else {			
			GameState.setTurn(TurnState.DEALER_TURN);
			DealerTurnAction action = new DealerTurnAction(panel);
			action.processAction(event);
		}
		
		event.setActionType(ActionType.HIT);
		Events.eventBus.fireEvent(event);

	}

}
