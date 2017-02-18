package com.blackjack.client.ui;

import com.blackjack.client.entities.Card;
import com.blackjack.client.entities.Hand;
import com.blackjack.client.entities.Hand.HandType;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;

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
	 * Determines the Offset to position each successive card in the hand
	 */
	private final static int CARD_OFFSET = 20;
	
	/**
	 * The hand is a representation of all the cards in the hand
	 */
	private Hand hand = new Hand();
	
	/**
	 * An array of CardUI objects in this HandUI
	 */
	private CardUI[] cardUIs = new CardUI[Hand.MAX_CARDS];
	
	/**
	 * The number of CardUIs in this HandUI
	 */
	private int numCardUIs = 0;
	
	/**
	 * Determines if the Hand is a Dealer's or Player's hand
	 */
	private HandType type;
	
	/**
	 * The label to display on stand
	 */
	private Label standLabel;
	
	/**
	 * The label to display on a bust
	 */
	private Label bustLabel;
	
	/**
	 * Construct with type to determine if it is a Player or Dealer hand,
	 * init UI and styles, hand always starts empty.
	 * @param type the type of Hand
	 */
	public HandUI(HandType type) {
		hand = new Hand();	
		
		this.type = type;
		
		this.setStylePrimaryName("hand-panel");
		this.addStyleName("centeredHorizontal");
		
		Label label = new Label();
		label.setStylePrimaryName("label");
		label.addStyleDependentName("white");
		label.addStyleDependentName("centered");
		
		standLabel = new Label("Stand");
		standLabel.setStylePrimaryName("gameLabel");
		standLabel.addStyleDependentName("stand");
		standLabel.setVisible(false);
		
		bustLabel = new Label("Bust");
		bustLabel.setStylePrimaryName("gameLabel");
		bustLabel.addStyleDependentName("bust");
		bustLabel.setVisible(false);
		
		switch(type) {
			case DEALER :
				label.setText("Dealer Hand");
				this.addStyleDependentName("dealer");
				break;
			case PLAYER :
				label.setText("Player Hand");
				this.addStyleDependentName("player");
		}
		
		this.add(label);
		this.add(standLabel);
	}
	
	/**
	 * Causes a card to be dealt to the primary hand and updates the UI
	 * @param card the card to be dealt
	 */
	public void hit(Card card) {
		CardUI cardUI = new CardUI(card);
		hit(cardUI);
	}
	
	/**
	 * Applies the face down attribute to the card so it displays facedown and
	 * displays it in this hand.
	 * 
	 * @param card
	 */
	public void hitFaceDown(Card card) {
		CardUI cardUI = new CardUI(card);
		cardUI.setFaceUp(false);
		hit(cardUI);
	}
	
	/**
	 * Hits this hand with the specified CardUI and adds the card 
	 * to the Hand object
	 * 
	 * @param cardUI the card UI to display in this hand
	 */
	private void hit(CardUI cardUI) {
		cardUIs[numCardUIs] = cardUI;
		
		//update positioning and styles
		int left = (numCardUIs) * CARD_OFFSET; 
		cardUI.getElement().setAttribute("style", "left: " + left + "px;");
		
		this.add(cardUI);
		hand.hit(cardUI.getCard());
		numCardUIs++;
	}
	
	/**
	 * Performs a split and returns a HandUI object to display as 	 * the split hand in the HandPanel
	 * rank is of the same type.
	 */
	public HandUI splitHand() {
		Card card = hand.split();
		HandUI handUI = null;
		if (card != null) {
			handUI = new HandUI(type);
			handUI.hit(card);
		}
		return handUI;
	}
	
	public void stand() {
		standLabel.setVisible(true);
	}
	
	public void bust() {
		bustLabel.setVisible(true);
	}
	
	/**
	 * Resets the hand to empt by clearing the hand and UI
	 */
	public void reset() {
		for (CardUI cardUI : cardUIs) {
			if (cardUI != null) {
				this.remove(cardUI);
			}
		}
		cardUIs = new CardUI[Hand.MAX_CARDS];
		numCardUIs = 0;
		hand.clear();
		standLabel.setVisible(false);
		bustLabel.setVisible(false);
		//TODO if this HandUI is split, reset it to it's original position
	}
	
	public void showDealerCard() {
		if (cardUIs[1] != null) {
			cardUIs[1].setFaceUp(true);
		}
	}

	public int getNumCardUIs() {
		return numCardUIs;
	}

	public void setNumCardUIs(int numCardUIs) {
		this.numCardUIs = numCardUIs;
	}

	public HandType getType() {
		return type;
	}

	public void setType(HandType type) {
		this.type = type;
	}
}
