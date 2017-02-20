package com.blackjack.client.action;

import com.blackjack.client.action.GameAction.ActionType;
import com.blackjack.client.entities.GameState;
import com.blackjack.client.entities.GameState.TurnState;
import com.blackjack.client.event.Events;
import com.blackjack.client.event.GameEvent;
import com.blackjack.client.sounds.SoundManager;
import com.blackjack.client.sounds.SoundManager.SoundName;
import com.blackjack.client.ui.BlackJackGamePanel;

public class StandAction extends GameAction {

	public StandAction(BlackJackGamePanel panel) {
		super(panel);		
	}

	@Override
	public void processAction(GameEvent event) {
		GameState state = event.getGameState();


		panel.disableAllButtons();
		panel.playerStand();

		//Cause the hand to stand based on state.getTurn() (PLAYER OR DEALER STAND)
		if(state.getTurn() == TurnState.PLAYER_TURN){
			
				SoundManager.play(SoundName.STAND);
				state.setTurn(TurnState.DEALER_TURN);
		
				//implement DealerTurnAction
				DealerTurnAction action = new DealerTurnAction(100, panel);
				action.processAction(event);
		}											
		else if(state.getTurn() == TurnState.DEALER_TURN){
							
				state.setTurn(TurnState.HAND_END);
				
				//create HandEndAction-implement and end action
				HandEndAction action = new HandEndAction(panel);
				action.processAction(event);
		}
		
		//Fire the event so the rest of the UI knows that the action occurred
		event.setActionType(ActionType.STAND);
		Events.eventBus.fireEvent(event);
	}

}
