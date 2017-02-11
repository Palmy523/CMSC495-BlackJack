package com.blackjack.client.ui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.Label;

public class AccountAnchor extends FocusPanel {

	private FlowPanel content;
	private Label chipCountLabel;
	
	public AccountAnchor() {
		this.setStylePrimaryName("account-anchor");
		
		content = new FlowPanel();
		chipCountLabel = new Label("$15,000");
		chipCountLabel.setStyleName("chipcount");
		
		Label icon = new Label();
		icon.setTitle("Account Settings");
		icon.setStylePrimaryName("account-anchor-icon");
		
		content.add(icon);
		content.add(chipCountLabel);
		
		this.add(content);
	}
	
	public void updateChipCount(float amount) {
		String countString = String.valueOf(amount);
		chipCountLabel.setText("$" + countString);
	}
	
}
