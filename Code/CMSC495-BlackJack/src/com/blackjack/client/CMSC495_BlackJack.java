package com.blackjack.client;

import com.blackjack.client.ui.Dashboard;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class CMSC495_BlackJack implements EntryPoint {

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		
		final Dashboard dashboard = new Dashboard();
		
		FlowPanel buttonPanel = new FlowPanel();
		buttonPanel.setStylePrimaryName("fixed");
		buttonPanel.addStyleDependentName("bottom");
		buttonPanel.addStyleName("centeredHorizontal");
		
		Button advance = new Button("Next Page");
		advance.setStylePrimaryName("button");
		advance.addStyleDependentName("green");
		advance.addStyleDependentName("right");
		advance.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				dashboard.advance();
			}
			
		});
		
		Button retreat = new Button("Previous Page");
		retreat.setStylePrimaryName("button");
		retreat.addStyleDependentName("red");
		retreat.addStyleDependentName("left");
		retreat.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				dashboard.retreat();
			}
			
		});
		
		buttonPanel.add(retreat);
		buttonPanel.add(advance);
		
		dashboard.getRoomSelectionPanel().enableRoom(2, false);
		
		RootPanel.get().add(dashboard);
		RootPanel.get().add(buttonPanel);
		RootPanel.get().addStyleName("body-background");
	}
}
