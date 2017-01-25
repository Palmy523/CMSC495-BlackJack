package com.blackjack.client.ui;

import com.blackjack.shared.entities.Room;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.FlowPanel;

public class RoomSelectionPanel extends FlowPanel {

	private CheckBox difficultyCheckBox;
	
	public RoomSelectionPanel(Room[] rooms) {
		this.setStyleName("centered");
		this.setWidth("640px");
		
		FlowPanel roomPanelWrapper = new FlowPanel();
		roomPanelWrapper.setStyleName("roomSelectionPanel");
		roomPanelWrapper.setWidth("640px");
		
		FlowPanel optionsPanel = new FlowPanel();
		optionsPanel.setStylePrimaryName("modal");
		optionsPanel.addStyleDependentName("left");
		optionsPanel.setWidth("578px");
		
		difficultyCheckBox = new CheckBox("Extreme Play (Dealer plays odds, earnings x1.5)");
		difficultyCheckBox.setStylePrimaryName("label");
		difficultyCheckBox.addStyleDependentName("white");
		
		for (Room room : rooms) {
			if (room != null) {
				RoomPanel roomPanel = new RoomPanel(room);
				roomPanelWrapper.add(roomPanel);
			}
		}
		
		optionsPanel.add(difficultyCheckBox);
		this.add(roomPanelWrapper);
		this.add(optionsPanel);
		
	}
	
}
