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
	 * Displays the stand label over the split hand.
	 */
	public void stand_Split() {
		splitHandUI.stand();
	}
	
	/**
	 * Displays the bust label for the primary hand
	 */
	public void bust() {
		primaryHandUI.bust();
	}
	
	/**
	 * Displays the bust label over the split hand.
	 */
	public void bust_Split() {
		splitHandUI.bust();
	}
	
	/**
	 * Displays the 21! label on the primary hand
	 */
	public void twentyone(){
		primaryHandUI.twentyone();
	}
	
	/**
	 * Display the 21! label on the split hand
	 */
	public void twentyone_Split() {
		splitHandUI.twentyone();
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
		if (splitHandUI != null) {
			this.remove(splitHandUI);
		}
		primaryHandUI.removeStyleDependentName("left");
	}
	
	/**
	 * Shows the dealer down card.
	 */
	public void showDealerCard() {
		primaryHandUI.showDealerCard();
	}
	
	/**
	 * Resets this HandPanel to default.
	 */
	public void reset() {
		if (primaryHandUI != null) {
			primaryHandUI.reset();
		}
		this.join();
	}
	
	/**
	 * 
	 * @return the number of cards in the primary hand.
	 */
	public int getNumberPrimaryCards() {
		return primaryHandUI.getNumCardUIs();
	}
	
	/**
	 * Gets the number of cards in the split hand.
	 * @return
	 */
	public int getNumberSplitCards() {
		return splitHandUI.getNumCardUIs();
	}

	/**
	 * Hits the primary hand with a card in double down display
	 * @param card
	 */
	public void hit_DoubleDown(Card card) {
		primaryHandUI.hit_DoubleDown(card);
	}
	
	/**
	 * Hits this split hand with a card in double down display
	 * @param card
	 */
	public void hitSplit_DoubleDown(Card card) {
		if (splitHandUI != null) {
			splitHandUI.hit_DoubleDown(card);
		}
	}
}
