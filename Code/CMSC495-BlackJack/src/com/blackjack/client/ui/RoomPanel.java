package com.blackjack.client.ui;

import com.blackjack.shared.entities.Room;
import com.blackjack.shared.entities.Stakes;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.blackjack.shared.entities.Stakes;

public class RoomPanel extends SimplePanel {

	private Label difficultyLabel;
	private Label requiredChipsLabel;
	private Label requiredChipsLabelValue;
	private Label minBetLabel;
	private Label minBetLabelValue;
	private Label maxBetLabel;
	private Label maxBetLabelValue;
	
	private Room room;
	
	public RoomPanel(Room room) {
		FlowPanel panel = new FlowPanel();
		this.room = room;
		this.setStyleName("modal");
		this.addStyleDependentName("left");
		this.addStyleName("roomPanel");
		
		difficultyLabel = new Label(room.getStakes().getStakesString());
		difficultyLabel.setStylePrimaryName("label");
		difficultyLabel.addStyleDependentName("roomHeader");
		
		switch(room.getStakes().getStakesLevel()) {
			case LOW : 
				difficultyLabel.addStyleDependentName("green");
				break;
			case MEDIUM : 
				difficultyLabel.addStyleDependentName("blue");
				break;
			case HIGH : 
				difficultyLabel.addStyleDependentName("red");
				break;
		}
		
		requiredChipsLabel = new Label("Required Chip Amount");
		requiredChipsLabel.setStylePrimaryName("label");
		requiredChipsLabel.addStyleDependentName("white");
		requiredChipsLabel.addStyleDependentName("padded");
		requiredChipsLabel.addStyleDependentName("centered");
		requiredChipsLabel.addStyleDependentName("underline");
		
		requiredChipsLabelValue = new Label("$" + String.valueOf(room.getChipLimit()));
		requiredChipsLabelValue.setStylePrimaryName("label");
		requiredChipsLabelValue.addStyleDependentName("white");
		requiredChipsLabelValue.addStyleDependentName("padded");
		requiredChipsLabelValue.addStyleDependentName("centered");
		requiredChipsLabelValue.addStyleDependentName("goldFont");
		
		minBetLabel = new Label("Minimum Bet");
		minBetLabel.setStylePrimaryName("label");
		minBetLabel.addStyleDependentName("white");
		minBetLabel.addStyleDependentName("padded");
		minBetLabel.addStyleDependentName("centered");
		minBetLabel.addStyleDependentName("underline");
		
		minBetLabelValue = new Label("$" + String.valueOf(room.getMinBet()));
		minBetLabelValue.setStylePrimaryName("label");
		minBetLabelValue.addStyleDependentName("white");
		minBetLabelValue.addStyleDependentName("padded");
		minBetLabelValue.addStyleDependentName("centered");
		minBetLabelValue.addStyleDependentName("goldFont");
		
		maxBetLabel = new Label("Maximum Bet");
		maxBetLabel.setStylePrimaryName("label");
		maxBetLabel.addStyleDependentName("white");
		maxBetLabel.addStyleDependentName("padded");
		maxBetLabel.addStyleDependentName("centered");
		maxBetLabel.addStyleDependentName("underline");
		
		maxBetLabelValue = new Label("$" + String.valueOf(room.getMaxBet()));
		maxBetLabelValue.setStylePrimaryName("label");
		maxBetLabelValue.addStyleDependentName("white");
		maxBetLabelValue.addStyleDependentName("padded");
		maxBetLabelValue.addStyleDependentName("centered");
		maxBetLabelValue.addStyleDependentName("goldFont");
		
		panel.add(difficultyLabel);
		panel.add(new HTML("<br/>"));
		panel.add(requiredChipsLabel);
		panel.add(new HTML("<br/>"));
		panel.add(requiredChipsLabelValue);
		panel.add(new HTML("<br/>"));
		panel.add(minBetLabel);
		panel.add(new HTML("<br/>"));
		panel.add(minBetLabelValue);
		panel.add(new HTML("<br/>"));
		panel.add(maxBetLabel);
		panel.add(new HTML("<br/>"));
		panel.add(maxBetLabelValue);
		
		this.add(panel);
	}
	
	public void enable(boolean enable) {
		String enabled = (enable) ? "true" : "false";
		this.getElement().setAttribute("disabled", enabled);
		this.setTitle("Requirements not met");
	}
}
