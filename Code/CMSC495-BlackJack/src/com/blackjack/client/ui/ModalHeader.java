package com.blackjack.client.ui;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;

public class ModalHeader extends SimplePanel {

	
	public ModalHeader(String text) {
		this.setStylePrimaryName("modal");
		this.addStyleDependentName("header");
		Label headerText = new Label(text);
		headerText.setStylePrimaryName("label");
		headerText.addStyleDependentName("header");
		this.add(headerText);
	}
}
