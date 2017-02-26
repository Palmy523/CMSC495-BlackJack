package com.blackjack.client.ui;

import com.blackjack.client.entities.Card;
import com.google.gwt.user.client.ui.SimplePanel;

public class CardUI extends SimplePanel{

	private Card card;
	private boolean isFaceUp = true;
	private boolean isDoubleDown = false;
	
	public CardUI(){}
	public CardUI(Card card) {
		setCard(card);
	}

	/**
	 * @return the card
	 */
	public Card getCard() {
		return card;
	}
	
	/**
	 * @return the isFaceUp
	 */
	public boolean isFaceUp() {
		return isFaceUp;
	}
	/**
	 * Sets the card to display face up or face down
	 * @param isFaceUp whether the card should be displayed face up
	 * or face down
	 */
	public void setFaceUp(boolean isFaceUp) {
		this.isFaceUp = isFaceUp;
		if (card != null) {
			CardStyleHelper.applyCardStyle(this);
		}
	}
	/**
	 * Sets the card as the card to this UI and applies 
	 * the style for the card.
	 * 
	 * @param card the card to display in this UI
	 */
	public void setCard(Card card) {
		this.card = card;
		CardStyleHelper.applyCardStyle(this);
	}
	public boolean isDoubleDown() {
		return isDoubleDown;
	}
	public void setDoubleDown(boolean isDoubleDown) {
		this.isDoubleDown = isDoubleDown;
		CardStyleHelper.applyCardStyle(this);
	}
	
	
	
}
