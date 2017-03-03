package com.blackjack.client.action;

import com.blackjack.client.controls.DealerAI;
import com.blackjack.client.controls.UserController;
import com.blackjack.client.entities.GameState;
import com.blackjack.client.event.Events;
import com.blackjack.client.event.GameEvent;
import com.blackjack.client.service.UserService;
import com.blackjack.client.service.UserServiceAsync;
import com.blackjack.client.sounds.SoundManager;
import com.blackjack.client.sounds.SoundManager.SoundName;
import com.blackjack.client.ui.BlackJackGamePanel;
import com.google.gwt.core.shared.GWT;

/**
 * -Check the hand values to determine who wins and determine push
 * -Award money and update chip amounts appropriately. Calls the 
 * GameController startGame() method to start a new hand.
 *
 */
public class HandEndAction extends GameAction {

	private final static String message = "\nPlace the minimum bet and click 'Deal' to start a new hand";
	private UserServiceAsync service = GWT.create(UserService.class);
	
	public HandEndAction(BlackJackGamePanel panel) {
		super(panel);		
	}

	@SuppressWarnings("static-access")
	@Override
	public void processAction(GameEvent event) {
		float easyPlayMultiplier = 1f;
		if(GameState.isEasyPlay())
			easyPlayMultiplier = .5f;
		int playerHandVal = GameState.getPlayerHand().getHandValue();
		int dealerHandVal = GameState.getDealerHand().getHandValue();
		
		if (event.getActionType() == ActionType.BLACKJACK) {
			panel.displayInstruction("Blackjack!" + message);
			SoundManager.play(SoundName.WIN);
			float award = GameState.getBetAmount() + (GameState.getBetAmount() * (3/2) * easyPlayMultiplier);
			UserController.updateChipCount(award);
		} else if (event.getActionType() == ActionType.DEALER_BLACKJACK) {
			panel.displayInstruction("Dealer has Blackjack!" + message);
			
			if (event.getGameState().isInsurance()) {
				UserController.updateChipCount(GameState.getBetAmount()
						+ GameState.getInsuranceBetAmt());
				panel.displayInsuranceBet(false);
			}
			panel.displayInsuranceBet(false);
		} else if (event.getActionType() == ActionType.SURRENDER) {
			panel.displayInstruction("You surrendered. You get half your bet back." + message);
			UserController.updateChipCount((float)GameState.getBetAmount() / 2);
		} else if (event.getActionType() == ActionType.PUSH || 
				playerHandVal == dealerHandVal) {
			panel.displayInstruction("Push!" + message);
			UserController.updateChipCount(GameState.getBetAmount());
		} else if (event.getActionType() == ActionType.PLAYER_BUST) {
			panel.displayInstruction("Dealer wins!" + message);
		} else if (event.getActionType() == ActionType.DEALER_BUST) {
			SoundManager.play(SoundName.WIN);
			panel.displayInstruction("You win!" + message);
			float award = GameState.getBetAmount() + GameState.getBetAmount() * easyPlayMultiplier;
			UserController.updateChipCount(award);
		} else if (dealerHandVal > playerHandVal) {
			panel.displayInstruction("Dealer wins!" + message);
		} else if (dealerHandVal < playerHandVal) {
			panel.displayInstruction("You win!" + message);
			UserController.updateChipCount(GameState.getBetAmount() + GameState.getBetAmount() * easyPlayMultiplier);
			SoundManager.play(SoundName.WIN);
		}
		
		GameState.setHittingPrimary(true);
		GameState.setHittingSplit(false);
		GameState.setSplit(false);
				
		DealerAI.cancelDealerActions();
		event.setActionType(ActionType.HAND_END);
		Events.eventBus.fireEvent(event);
	}
}