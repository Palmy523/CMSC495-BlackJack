package com.blackjack.client.action;

import com.blackjack.client.entities.GameState;
import com.blackjack.client.event.Events;
import com.blackjack.client.event.GameEvent;
import com.blackjack.client.ui.BlackJackGamePanel;
import com.blackjack.client.ui.GameButton.GameButtonType;

public class DeclineInsuranceAction extends GameAction {

	public DeclineInsuranceAction(BlackJackGamePanel panel) {
		super(panel);
	}

	@Override
	public void processAction(GameEvent event) {
		panel.displayInsurancePrompt(false);
		if (GameState.getDealerHand().getHandValue() == 21) {
			panel.showDealerCard();
			panel.getDealerHandPanel().twentyone();
			event.setActionType(ActionType.DEALER_BLACKJACK);
			HandEndAction action = new HandEndAction(panel);
			action.processAction(event);
		} else {
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
