package com.blackjack.client.action;

import com.blackjack.client.entities.GameState;
import com.blackjack.client.entities.GameState.TurnState;
import com.blackjack.client.event.Events;
import com.blackjack.client.event.GameEvent;
import com.blackjack.client.sounds.SoundManager;
import com.blackjack.client.sounds.SoundManager.SoundName;
import com.blackjack.client.ui.BlackJackGamePanel;
import com.blackjack.client.ui.GameButton.GameButtonType;
import com.google.gwt.core.client.GWT;

public class BetAction extends GameAction {
	private int betAmount;

	public BetAction(BlackJackGamePanel panel, int betAmount) {
		super(panel);
		this.betAmount = betAmount;
	}

	@Override
	public void processAction(GameEvent event) {

		if (GameState.getTurn() != TurnState.AWAITING_BET &&
				GameState.getTurn() != TurnState.AWAITING_DEAL
				|| GameState.getBetAmount() == GameState.getRoom().getMaxBet()) {
			return;
		}
		GameState.setTurn(TurnState.AWAITING_DEAL);
		int currentBet = GameState.getBetAmount() + betAmount;
		GameState.setBetAmount(currentBet);

		SoundManager.play(SoundName.CHIP_BET);

		if (currentBet >= GameState.getRoom().getMinBet()) {
			panel.enableButton(GameButtonType.DEAL, true);
		}

		if (currentBet >= GameState.getRoom().getMaxBet()) {
			currentBet = GameState.getRoom().getMaxBet();
			GameState.setBetAmount(currentBet);
			panel.chipsEnabled(false);
		}
		panel.bet(currentBet);

		// Fire the event so the rest of the UI knows that the action occurred
		event.setActionType(ActionType.BET);
		Events.eventBus.fireEvent(event);
	}
}
