package com.blackjack.client.ui;

import com.blackjack.client.entities.Card;
import com.blackjack.client.entities.Card.Set;
import com.blackjack.client.entities.Card.Suit;
import com.blackjack.client.entities.Card.Rank;

/**
 * Static class used to apply styling to cards.
 * @author Dave
 *
 */
public class CardStyleHelper {

	public static String getCardStyle() {
		return "card";
	}

	/**
	 * 
	 * @param set
	 * @return returns the appropriate style name for the set of cards used.
	 */
	public static String getSetStyle(Set set) {
		switch (set) {
		case ONE:
			return "set1";
		default:
			return "set1";
		}
	}

	/**
	 * @param suit
	 * @return the style name for a suit
	 */
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

	/**
	 * 
	 * @param type
	 * @return the style name for the rank
	 */
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

	/**
	 * Automatically applies a style to a card and removes any previous styles
	 * 
	 * @param ui the CardUI to update the style for.
	 */
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
