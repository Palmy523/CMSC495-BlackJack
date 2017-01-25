package com.blackjack.client;

import com.blackjack.client.ui.BlackJackGamePanel;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class CMSC495_BlackJack implements EntryPoint {

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		BlackJackGamePanel panel = new BlackJackGamePanel();
		
		RootPanel.get().add(panel);
		RootPanel.get().addStyleName("body-background");
	}
}
