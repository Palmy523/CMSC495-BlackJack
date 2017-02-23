package com.blackjack.client.ui;

import com.blackjack.client.entities.Card;
import com.blackjack.client.entities.Hand.HandType;
import com.google.gwt.user.client.ui.FlowPanel;

/**
 * THe Hand Panel is the primary UI the represents a Hand 
 * or set of cards for a round in BlackJack. The HandPanel includes
 * a primary HandUI and an additional UI for a split hand and 
 * methods to deal with display.
 * 
 * @author Dave
 *
 */
public class HandPanel extends FlowPanel {

	private HandUI primaryHandUI;
	private HandUI splitHandUI;
	private HandType type;
	
	/**
	 * Construct with a hand type to determine whether the hand
	 * belongs to the player or dealer.
	 * 
	 * @param type DEALER or PLAYER hand from the Hand entity class
	 */
	public HandPanel(HandType type) {
		this.type = type;
		primaryHandUI = new HandUI(type);
		
		this.add(primaryHandUI);
	}
	
	/**
	 * Causes a card to be dealt to the primary hand and updates the UI
	 * 
	 * @param card the card to be dealt
	 */
	public void hit(Card card) {
		primaryHandUI.hit(card);
	}
	
	/**
	 * Causes a card to be dealt face down in the primary hand.
	 * 
	 * @param card the card to be dealt face down
	 */
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
	
	/**
	 * Displays the stand label for the primary hand
	 */
	public void stand(){
		primaryHandUI.stand();
	}
	
	/**
	 * Displays the bust label for the primary hand
	 */
	public void bust() {
		primaryHandUI.bust();
	}
	
	/**
	 * Displays the split hand by taking the duplicate card from the 
	 * primary and displaying it in the splitHand. Additionally, updates
	 * the position of the primary and split hands appropriately
	 */
	public void split() {
		if (primaryHandUI.getNumCardUIs() != 2) {
			return;
		}
		
		CardUI[] cardUIs = primaryHandUI.getCardUIs();
		Card cardOne = cardUIs[0].getCard();
		Card cardTwo = cardUIs[1].getCard();
		
		if (cardOne.getRank() != cardTwo.getRank()) {
			return;
		}

		if (splitHandUI != null && this.getWidgetIndex(splitHandUI) != -1) {
			this.remove(splitHandUI);
		}
		
		if (primaryHandUI != null && this.getWidgetIndex(primaryHandUI) != -1) {
			this.remove(primaryHandUI);
		}
		
		primaryHandUI = new HandUI(type);
		primaryHandUI.hit(cardOne);
		
		splitHandUI = new HandUI(type);
		splitHandUI.hit(cardTwo);
		
		splitHandUI.addStyleDependentName("right");
		
		primaryHandUI.addStyleDependentName("left");
		
		this.add(primaryHandUI);
		this.add(splitHandUI);
	}
	
	/**
	 * This should join a split hand to appear back as one hand
	 */
	public void join() {
		this.remove(splitHandUI);
		
		primaryHandUI.removeStyleDependentName("split");
		primaryHandUI.removeStyleDependentName("left");
	}
	
	public void showDealerCard() {
		primaryHandUI.showDealerCard();
	}
	
	public void reset() {
		if (primaryHandUI != null) {
			primaryHandUI.reset();
		}
		if (splitHandUI != null) {
			splitHandUI.reset();
		}
	}
	
	public int getNumberPrimaryCards() {
		return primaryHandUI.getNumCardUIs();
	}
	
	public int getNumberSplitCards() {
		return splitHandUI.getNumCardUIs();
	}

	public void hit_DoubleDown(Card card) {
		// TODO Auto-generated method stub
		
	}
}
