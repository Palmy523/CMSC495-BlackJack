package com.blackjack.client.ui;

import com.blackjack.client.entities.Card;
import com.blackjack.client.entities.Hand;
import com.blackjack.client.entities.Hand.HandType;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;

public class HandPanel extends FlowPanel {

	private HandUI primaryHandUI;
	private HandUI splitHandUI;
	private HandType type;
	
	public HandPanel(HandType type) {
		this.type = type;
		primaryHandUI = new HandUI(type);
		splitHandUI = new HandUI(type);
		
		splitHandUI.setVisible(false);
		
		this.add(primaryHandUI);
		this.add(splitHandUI);
	}
	
	/**
	 * Causes a card to be dealt to the primary hand and updates the UI
	 * @param card the card to be dealt
	 */
	public void hit(Card card) {
		primaryHandUI.hit(card);
	}
	
	public void hitFaceDown(Card card) {
		primaryHandUI.hitFaceDown(card);
	}
	
	/**
	 * Creates a new card UI and calls the hit function of the 
	 * split hand.
	 * 
	 * @param card the card to display in the split hand
	 */
	public void hitSplit(Card card) {
		splitHandUI.hit(card);
	}
	
	public void stand(){
		primaryHandUI.stand();
	}
	
	public void bust() {
		primaryHandUI.bust();
	}
	
	/**
	 * Displays the split hand by taking the duplicate card from the 
	 * primary and displaying it in the splitHand. Additionally, updates
	 * the position of the primary and split hands appropriately
	 */
	public void split() {
		//TODO see comments above
	}
	
	/**
	 * This should join a split hand to appear back as one hand
	 */
	public void join() {
		
	}
	
	public void showDealerCard() {
		primaryHandUI.showDealerCard();
	}
	
	public void reset() {
		primaryHandUI.reset();
		splitHandUI.reset();
	}
	
	public int getNumberPrimaryCards() {
		return primaryHandUI.getNumCardUIs();
	}
	
	public int getNumberSplitCards() {
		return splitHandUI.getNumCardUIs();
	}
}
