package com.blackjack.client.ui;

import com.blackjack.client.entities.Card;
import com.blackjack.client.entities.Hand.HandType;
import com.google.gwt.user.client.ui.FlowPanel;

/**
 * The HandUI represents a hand of blackjack cards for a person in 
 * the game of blackjack, either the Player or Dealer and allows the 
 * Hand to be split into two separate hands
 * 
 * @author Dave
 *
 */
public class HandUI extends FlowPanel {
	
	/**
	 * UI for the primary hand
	 */
	private HandPanel primaryHandPanel;
	
	/**
	 * UI for the split hand
	 */
	private HandPanel splitHandPanel;
	
	/**
	 * Construct with type to determine if it is a Player or Dealer hand,
	 * init UI and styles, hand always starts empty.
	 * @param type the type of Hand
	 */
	public HandUI(HandType type) {
		primaryHandPanel = new HandPanel(type);
		splitHandPanel = new HandPanel(type);
		
		//TODO add styles and position appropriately.
		
		
		this.add(primaryHandPanel);
		this.add(splitHandPanel);
		
		//Hide the splitHandPanel until the hand is split.
		splitHandPanel.setVisible(false);
		
	}
	
	/**
	 * Causes a card to be dealt to the primary hand and updates the UI
	 * @param card the card to be dealt
	 */
	public void hit(Card card) {
		this.primaryHandPanel.hit(card);
	}
	
	/**
	 * Causes a card to be dealt to the split hand and updates the UI
	 * @param card the card to be dealt
	 */
	public void hitSplit(Card card) {
		this.splitHandPanel.hit(card);
	}
	
	/**
	 * Perfoms a split if card number is == 2 and the card 
	 * rank is of the same type.
	 */
	public void splitHand() {
		//TODO update the UI to show two cards splits and 
		//update the card arrays
	}
	
	public void unSplit() {
		//TODO update the UI to show the primary hand go back
		//to the start position and hide the splitHand.
	}
	
	/**
	 * Resets the hand to empty.
	 */
	public void reset() {
		//TODO perform any actions to clear this hand of 
		//its current state and reset with an empty hand.
		
		
	}
	
	public void clearHands() {
		//TODO clear the HandPanels of their cards
	}

}
