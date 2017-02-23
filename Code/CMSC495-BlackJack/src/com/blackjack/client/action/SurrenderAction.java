package com.blackjack.client.action;

import com.blackjack.client.entities.GameState;
import com.blackjack.client.event.GameEvent;
import com.blackjack.client.ui.BlackJackGamePanel;
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
		GameState.getUser().setBankAmount(GameState.getUser().getBankAmount()+GameState.getBetAmount()/2);
		//TODO Update bank amount display
		HandEndAction handEndAction = new HandEndAction(panel);
		handEndAction.processAction(event);

	}

}
