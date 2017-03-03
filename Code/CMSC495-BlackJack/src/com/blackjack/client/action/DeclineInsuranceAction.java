package com.blackjack.client.action;

import com.blackjack.client.entities.GameState;
import com.blackjack.client.event.Events;
import com.blackjack.client.event.GameEvent;
import com.blackjack.client.ui.BlackJackGamePanel;
import com.blackjack.client.ui.GameButton.GameButtonType;

/**
 * The DeclineInsuranceAction performs the needed steps when 
 * a player declines insurance. Checks the natural state of 
 * the dealer hand and procs end turn if necessary.
 * 
 * @author Lea
 *
 */
public class DeclineInsuranceAction extends GameAction {

	/**
	 * 
	 * @param panel the BlackJackGamePanel to update.
	 */
	public DeclineInsuranceAction(BlackJackGamePanel panel) {
		super(panel);
	}
	

	/**
	 * Processes the decline insurance action by ending the turn if the 
	 * Dealer has a natural blackjack or resumes play as normal if not.
	 */
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
			panel.displayInstruction("Dealer does not have Blackjack.");
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
