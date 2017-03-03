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

		//Side Bet Created
		float insuranceBet = event.getGameState().getBetAmount()/2;

		//update user chip count state
		UserController.updateChipCount(-insuranceBet);

		if (GameState.getDealerHand().dealerHasNatural()) {
			panel.showDealerCard();
			panel.getDealerHandPanel().twentyone();
			event.setActionType(ActionType.DEALER_BLACKJACK);
			if (event.getGameState().isInsurance()) {
				// Player is awarded 2:1 and player's bet is return to them
				int insuranceAward = GameState.getInsuranceBetAmt()*2;
				UserController.updateChipCount(insuranceAward);
				panel.displayInstruction("Awarded $" + insuranceAward);
			}
			
			HandEndAction action = new HandEndAction(panel);
			action.processAction(event);
		} else {
			
			panel.displayInstruction("Dealer doesn't have Blackjack. Loss $" + insuranceBet + " side bet.");
			panel.enableButton(GameButtonType.DEAL, false);
			panel.enableButton(GameButtonType.HIT, true);
			panel.enableButton(GameButtonType.STAND, true);
			panel.chipsEnabled(false);
			panel.enableButton(GameButtonType.SURRENDER, true);
			panel.enableButton(GameButtonType.DOUBLE_DOWN, true);

			if (GameState.getPlayerHand().canSplit())
				panel.enableButton(GameButtonType.SPLIT, true);
		}
		
		event.setActionType(ActionType.INSURANCE);
		Events.eventBus.fireEvent(event);

	}
}
