package com.blackjack.client.action;

import com.blackjack.client.entities.GameState;
import com.blackjack.client.event.GameEvent;
import com.blackjack.client.ui.BlackJackGamePanel;
import com.google.gwt.core.shared.GWT;
import com.blackjack.client.action.HandEndAction;

/**
 * Actions used to cause a surrender
 * 
 * @author Stephanie
 *
 */
public class SurrenderAction extends GameAction {

	public SurrenderAction(BlackJackGamePanel panel) {
		super(panel);
	}

	/**
	 * Ends hand in a surrender.
	 */
	@Override
	public void processAction(GameEvent event) {
		panel.displayInstruction("Surrendered");
		event.setActionType(ActionType.SURRENDER);
		GameState.setSurrender(true);
		HandEndAction handEndAction = new HandEndAction(panel);
		handEndAction.processAction(event);

	}

}
