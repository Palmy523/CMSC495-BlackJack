package com.blackjack.client.controls;

import com.blackjack.client.entities.GameState;
import com.blackjack.client.entities.GameState.TurnState;

public class DealerAI {

	private GameController controller;
	
	public DealerAI(GameController controller) {
		
	}
	
	public void startTurn() {
		//TODO proc off a series of actions on at a time
		while (controller.getGameState().getTurn() == TurnState.DEALER_TURN) {
			//TODO do some stuff with the dealer by creating GameActions (HIT, STAND) 
			//until the dealer chooses to stand or busts, when that happens, update the GameState
			//to PlayerTurn
		}
	}
	
}
