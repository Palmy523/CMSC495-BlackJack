package com.blackjack.client.ui;

import com.blackjack.client.entities.Card;
import com.google.gwt.user.client.ui.SimplePanel;

public class CardUI extends SimplePanel{

	private Card card;
	private boolean isFaceUp = true;
	
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
	 * @param isFaceUp the isFaceUp to set
	 */
	public void setFaceUp(boolean isFaceUp) {
		this.isFaceUp = isFaceUp;
		if (card != null) {
			CardStyleHelper.applyCardStyle(this);
		}
	}
	/**
	 * @param card the card to set
	 */
	public void setCard(Card card) {
		this.card = card;
		CardStyleHelper.applyCardStyle(this);
	}
	
	
	
}
