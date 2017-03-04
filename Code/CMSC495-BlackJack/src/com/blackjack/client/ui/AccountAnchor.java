package com.blackjack.client.ui;

import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.Label;

/**
 * UI Piece that is a link for account management and display of 
 * chips for a user.
 * 
 * @author Dave
 *
 */
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
	
	/**
	 * Updates the account anchor with the specified amount
	 * @param amount the amount of money to display in the account anchor.
	 */
	public void updateChipCount(float amount) {
		String value = NumberFormat.getDecimalFormat().format(amount);
		if (value.contains(".")) {
			if (value.substring(value.indexOf('.'), value.length()).length() < 3) {
				value += "0";
			}
		}
		chipCountLabel.setText("$" + value);
	}
	
}
