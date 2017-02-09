package com.blackjack.client.ui;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.Label;

public class BackAnchor extends FocusPanel {

	public BackAnchor() {
		this.setStylePrimaryName("back-anchor");
		
		FlowPanel panel = new FlowPanel();
		
		Label icon = new Label();
		icon.setTitle("Go Back");
		icon.setStylePrimaryName("back-anchor-icon");
		
		panel.add(icon);
		
		this.add(panel);
	}
	
}
