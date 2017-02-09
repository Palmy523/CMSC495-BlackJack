package com.blackjack.client;

import com.blackjack.client.service.UserService;
import com.blackjack.client.service.UserServiceAsync;
import com.blackjack.client.sounds.SoundManager;
import com.blackjack.client.sounds.SoundManager.SoundName;
import com.blackjack.client.ui.Dashboard;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
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
		
		UserServiceAsync service = GWT.create(UserService.class);
		AsyncCallback<Void> callback = new AsyncCallback<Void>() {

			@Override
			public void onFailure(Throwable caught) {
				GWT.log("Init db communication failure");
				GWT.log(caught.getMessage());
			}

			@Override
			public void onSuccess(Void result) {
				GWT.log("Init db communication success");
			}
			
		};
		
		service.initDB(callback);
		
		final Dashboard dashboard = new Dashboard();
		
		FlowPanel buttonPanel = new FlowPanel();
		buttonPanel.setStylePrimaryName("fixed");
		buttonPanel.addStyleDependentName("bottom");
		buttonPanel.addStyleName("centeredHorizontal");
		
		Button advance = new Button("Next Page");
		advance.setStylePrimaryName("button");
		advance.addStyleDependentName("green");
		advance.addStyleDependentName("middle");
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
		
		Button playSound = new Button("Play a Sound");
		playSound.setStylePrimaryName("button");
		playSound.addStyleDependentName("red");
		playSound.addStyleDependentName("right");
		playSound.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				SoundManager.play(SoundName.FAN1);
			}
			
		});
		
		
		buttonPanel.add(retreat);
		buttonPanel.add(advance);
		buttonPanel.add(playSound);
		
		RootPanel.get().add(dashboard);
		RootPanel.get().add(buttonPanel);
		RootPanel.get().addStyleName("body-background");
	}
}
