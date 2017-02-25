package com.blackjack.client.action;

import com.blackjack.client.entities.GameState;
import com.blackjack.client.event.GameEvent;
import com.blackjack.client.ui.BlackJackGamePanel;
import com.google.gwt.core.shared.GWT;
import com.blackjack.client.action.HandEndAction;

public class SurrenderAction extends GameAction {

	public SurrenderAction(BlackJackGamePanel panel) {
		super(panel);
	}

	@Override
	public void processAction(GameEvent event) {
		panel.displayInstruction("Surrendered");
		event.setActionType(ActionType.SURRENDER);
		event.getGameState().setSurrender(true);
		GameState.getUser().setBankAmount(GameState.getUser().getBankAmount()+(float)GameState.getBetAmount()/2);
		HandEndAction handEndAction = new HandEndAction(panel);
		handEndAction.processAction(event);

	}

}
