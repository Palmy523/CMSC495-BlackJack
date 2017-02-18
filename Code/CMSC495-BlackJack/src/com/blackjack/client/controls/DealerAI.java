package com.blackjack.client.controls;

import com.blackjack.client.action.GameAction;
import com.blackjack.client.entities.GameState;
import com.blackjack.client.entities.GameState.TurnState;

/**
 * 
 * @author Lea
 *
 */
public class DealerAI {

	private GameController controller;
	private double proc = Math.random();

	public DealerAI(GameController controller) {
		this.controller = controller;
	}

	@SuppressWarnings("static-access")
	public void startTurn() {

		//TODO proc off a series of actions on at a time
		while (controller.getGameState().getTurn() == TurnState.DEALER_TURN) {
			//TODO do some stuff with the dealer by creating GameActions (HIT, STAND) 
			//until the dealer chooses to stand or busts, when that happens, update the GameState
			//to PlayerTurn
			
			// start timer that processes a method that is one turn for the dealer
			
			
			// on each proc of the timer the method will be processed until the Dealer decides to
			// Stand or Busts
			
			
			
			
			// A Stand or Bust Event listener needs to be created, so the timer can be stopped
			// and HandEndAction can be called when the dealer busts or stands.
			
			controller.dealerHit();
			
//			if (lowstakes) {
//
//				//70%
//				if (controller.canDealerHit() && ()proc < 0.7) {
////					GameAction.ActionType.HIT;
////					GameAction.ActionType.STAND;
//					controller.dealerHit();
//					controller.canDealerStand();
//				}
//
//			} else if (medium_stakes) {
//
//				//50%
//				if (proc < 0.5) {
////					GameAction.ActionType.HIT;
////					GameAction.ActionType.STAND;
//					controller.dealerHit();
//					controller.canDealerStand();
//				}
//
//			} else if (high_stakes) {
//
//				//20%
//				if (proc < 0.2) {
////					GameAction.ActionType.HIT;
////					GameAction.ActionType.STAND;
//					controller.dealerHit();
//					controller.canDealerStand();
//				}
//
//				
//			}
			
			//when the dealer chooses to stand or bust HandEndAction 
			//processAction is called
			controller.getGameState().setTurn(TurnState.HAND_END);
		}
		
		

	}
}
