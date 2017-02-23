package com.blackjack.client.ui;

import com.blackjack.client.entities.Card;
import com.blackjack.client.entities.Card.Set;
import com.blackjack.client.entities.Card.Suit;
import com.blackjack.client.entities.Card.Rank;

public class CardStyleHelper {

	public static String getCardStyle() {
		return "card";
	}

	public static String getSetStyle(Set set) {
		switch (set) {
		case ONE:
			return "set1";
		default:
			return "set1";
		}
	}

	public static String getSuitStyle(Suit suit) {
		switch (suit) {
		case HEARTS:
			return "hearts";
		case CLUBS:
			return "clubs";
		case SPADES:
			return "spades";
		case DIAMONDS:
			return "diamonds";
		default:
			return "back";
		}
	}

	public static String getRankStyle(Rank type) {
		switch (type) {
		case ACE:
			return "ace";
		case TWO:
			return "two";
		case THREE:
			return "three";
		case FOUR:
			return "four";
		case FIVE:
			return "five";
		case SIX:
			return "six";
		case SEVEN:
			return "seven";
		case EIGHT:
			return "eight";
		case NINE:
			return "nine";
		case TEN:
			return "ten";
		case JACK:
			return "jack";
		case QUEEN:
			return "queen";
		case KING:
			return "king";
		default:
			return "back";
		}
	}

	public static void applyCardStyle(CardUI ui) {
		Card card = ui.getCard();
		String[] styles = ui.getStyleName().split(",");
		for (String style : styles) {
			if (style.equals("")) {
				continue;
			}
			ui.removeStyleName(style);
		}
		ui.setStylePrimaryName("card-" + getSetStyle(card.getSet()));
		ui.addStyleName("card");
		if (ui.isFaceUp()) {
			ui.addStyleDependentName(getSuitStyle(card.getSuit()));
			ui.addStyleDependentName(getRankStyle(card.getRank()));
			ui.addStyleDependentName(getSuitStyle(card.getSuit()) + "-"
					+ getRankStyle(card.getRank()));
		} else {
			ui.addStyleDependentName("back");
		}
		
		if (ui.isDoubleDown()) {
			ui.addStyleName("card-double-down");
		}
	}

}
