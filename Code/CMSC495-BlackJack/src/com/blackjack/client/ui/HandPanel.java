package com.blackjack.client.ui;

import com.blackjack.client.entities.Card;
import com.blackjack.client.entities.Hand;
import com.blackjack.client.entities.Hand.HandType;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;

public class HandPanel extends FlowPanel {

	private Hand hand;
	
	public HandPanel(HandType type) {
		this.hand = new Hand();
		this.setStylePrimaryName("hand-panel");
		this.addStyleName("centeredHorizontal");

		Label label = new Label();
		label.setStylePrimaryName("label");
		label.addStyleDependentName("white");
		label.addStyleDependentName("centered");
		
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
	}
	
	/**
	 * Causes a card to be dealt to the primary hand and updates the UI
	 * @param card the card to be dealt
	 */
	public void hit(Card card) {
		//TODO Add a Card to this Hand and update the UI
	}
	
}
