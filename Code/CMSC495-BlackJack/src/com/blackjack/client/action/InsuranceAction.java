package com.blackjack.client.action;

import com.blackjack.client.controls.UserController;
import com.blackjack.client.entities.GameState;
import com.blackjack.client.event.Events;
import com.blackjack.client.event.GameEvent;
import com.blackjack.client.ui.BlackJackGamePanel;
import com.blackjack.client.ui.GameButton.GameButtonType;

public class InsuranceAction extends GameAction {

	public InsuranceAction(BlackJackGamePanel panel) {
		super(panel);
	}

	@Override
	public void processAction(GameEvent event) {
		// TODO Auto-generated method stub

		panel.displayInsurancePrompt(false);
		event.getGameState().setInsurance(true);

		//Create/Display Side Bet
		float insuranceBet = event.getGameState().getBetAmount()/2;
		panel.betInsurance(insuranceBet);
		panel.displayInsuranceBet(true);

		//update user chip count state
		UserController.updateChipCount(-insuranceBet);

		if (GameState.getDealerHand().dealerHasNatural()) {
			panel.showDealerCard();
			panel.getDealerHandPanel().twentyone();
			event.setActionType(ActionType.DEALER_BLACKJACK);
//			if (event.getGameState().isInsurance()) {
//				UserController.updateChipCount(GameState.getInsuranceBetAmt()*2);
//				panel.displayInsuranceBet(false);
//			}
			
			HandEndAction action = new HandEndAction(panel);
			action.processAction(event);
		} else {
			
			panel.displayInstruction("Dealer doesn't have Blackjack");
			panel.enableButton(GameButtonType.DEAL, false);
			panel.enableButton(GameButtonType.HIT, true);
			panel.enableButton(GameButtonType.STAND, true);
			panel.chipsEnabled(false);
			panel.enableButton(GameButtonType.SURRENDER, true);
			panel.enableButton(GameButtonType.DOUBLE_DOWN, true);

			if (GameState.getPlayerHand().canSplit())
				panel.enableButton(GameButtonType.SPLIT, true);
			
			// Need to find a way to display side bet for at least
			// 5 seconds before hiding insurance bet
			panel.displayInsuranceBet(false);

		}
		
		event.setActionType(ActionType.INSURANCE);
		Events.eventBus.fireEvent(event);

	}
}
