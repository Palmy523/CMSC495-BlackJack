package com.blackjack.client.ui;

import com.blackjack.client.entities.Card;
import com.blackjack.client.entities.Card.Set;
import com.blackjack.client.entities.Deck;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;

public class BlackJackGamePanel extends SimplePanel {

	private Deck deck = new Deck(Set.ONE, 1, false);
	
	public BlackJackGamePanel() {
		this.setStylePrimaryName("modal");
		this.addStyleName("centered");
		this.setHeight("500px");
		this.setWidth("500px");
		
		FlowPanel panel = new FlowPanel();
		panel.setStyleName("centered");
		
		//TODO Remove temporary for testing drawing cards
		Label label = new Label("Card Drawn: ");
		label.setStylePrimaryName("label");
		label.addStyleDependentName("white");
		
		final Label cardLabel = new Label();
		cardLabel.setStylePrimaryName("label");
		cardLabel.addStyleDependentName("white");
		
		final CardUI cardUI = new CardUI();
		
		Button drawButton = new Button("Draw New Card");
		drawButton.setStylePrimaryName("button");
		drawButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				Card card = deck.draw();
				if (card == null) {
					cardLabel.setText("No more cards");
					cardUI.setFaceUp(false);
					return;
				}
				cardUI.setCard(card);
				cardUI.setFaceUp(true);
				cardLabel.setText(card.getSuitName() + " " + card.getRankName());
			}
		});
		
		Button newDeckButton = new Button("New Deck");
		newDeckButton.setStylePrimaryName("button");
		newDeckButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				deck = new Deck(Set.ONE, 1, false);
			}
			
		});
		
		Button shuffleDeck = new Button("Shuffle Deck");
		shuffleDeck.setStylePrimaryName("button");
		shuffleDeck.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				deck.shuffleDeck();
			}
			
		});
		//END TODO REMOVE
		
		panel.add(label);
		panel.add(new HTML("<br/>"));
		panel.add(cardLabel);
		panel.add(new HTML("<br/>"));
		panel.add(cardUI);
		panel.add(new HTML("<br/>"));
		panel.add(drawButton);
		panel.add(new HTML("<br/>"));
		panel.add(newDeckButton);
		panel.add(new HTML("<br/>"));
		panel.add(shuffleDeck);
		this.add(panel);
	}
}
