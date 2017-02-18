package com.blackjack.client.action;

import com.blackjack.client.entities.GameState;
import com.blackjack.client.entities.GameState.TurnState;
import com.blackjack.client.event.Events;
import com.blackjack.client.event.GameEvent;
import com.blackjack.client.sounds.SoundManager;
import com.blackjack.client.sounds.SoundManager.SoundName;
import com.blackjack.client.ui.BlackJackGamePanel;
import com.blackjack.client.ui.GameButton.GameButtonType;

public class BetAction extends GameAction {
	private int betAmount;

	public BetAction(BlackJackGamePanel panel, int betAmount) {
		super(panel);
		this.betAmount = betAmount;
	}

	@Override
	public void processAction(GameEvent event) {
		GameState state = event.getGameState();
		if (GameState.getTurn() != TurnState.AWAITING_BET || GameState.getTurn() != TurnState.AWAITING_DEAL)
			return;
		int currentBet = GameState.getBetAmount() + betAmount;
		GameState.setBetAmount(currentBet);
		panel.bet(currentBet);

		SoundManager.play(SoundName.CHIP_BET);

		if (currentBet >= state.getRoom().getMinBet()) {
			panel.enableButton(GameButtonType.DEAL, true);
		}

		if (currentBet > state.getRoom().getMaxBet()) {
			currentBet = state.getRoom().getMaxBet();
			panel.chipsEnabled(false);
		}

		// Fire the event so the rest of the UI knows that the action occurred
		event.setActionType(ActionType.BET);
		Events.eventBus.fireEvent(event);
	}
}
