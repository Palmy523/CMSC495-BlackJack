package com.blackjack.client;

import com.blackjack.client.controls.GameController;
import com.blackjack.client.controls.UserController;
import com.blackjack.client.event.Events;
import com.blackjack.client.ui.AccountAnchor;
import com.blackjack.client.ui.Dashboard;
import com.blackjack.client.ui.LoginForm;
import com.blackjack.client.ui.RoomSelectionPanel;
import com.blackjack.shared.entities.Room;
import com.blackjack.shared.events.LoginEvent;
import com.blackjack.shared.handlers.LoginHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.user.client.ui.RootPanel;

public class App {

	private Dashboard dashboard;
	private UserController userController;
	private GameController gameController;
	private LoginForm loginForm;
	private RoomSelectionPanel roomSelectionPanel;
	
	public void start() {
		dashboard = new Dashboard();
		userController = new UserController(dashboard);
		gameController = new GameController(dashboard);
		
		loginForm = new LoginForm();
		setupLogin();
		
		Room room1 = Room.createLowStakesRoom();
		Room room2 = Room.createMediumStakesRoom();
		Room room3 = Room.createHighStakesRoom();
		Room[] rooms = new Room[] {room1, room2, room3};
		roomSelectionPanel = new RoomSelectionPanel(rooms);
		setupRoomSelectionPanel();

		AccountAnchor accountAnchor = new AccountAnchor();

		dashboard.setLoginForm(loginForm);
		dashboard.setRoomSelectionPanel(roomSelectionPanel);
		dashboard.setAccountAnchor(accountAnchor);
		
		
		dashboard.displayLoginScreen();
		RootPanel.get().add(dashboard);
	}
	
	private void setupLogin() {
		loginForm.getLoginButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				String userName = loginForm.getUsernameTextBox().getText();
				String password = loginForm.getPasswordTextBox().getText();
				userController.login(userName, password);
			}
		});
		
		loginForm.getUsernameTextBox().addKeyPressHandler(new KeyPressHandler() {

			@Override
			public void onKeyPress(KeyPressEvent event) {
				if ((int)event.getCharCode() == KeyCodes.KEY_ENTER) {
					loginForm.getLoginButton().click();
				}
			}
		});
		
		loginForm.getPasswordTextBox().addKeyPressHandler(new KeyPressHandler() {

			@Override
			public void onKeyPress(KeyPressEvent event) {
				if ((int)event.getCharCode() == KeyCodes.KEY_ENTER) {
					loginForm.getLoginButton().click();
				}
			}
		});
		
		
		LoginHandler handler = new LoginHandler() {

			@Override
			public void OnLogin(LoginEvent event) {
				if (event.isSuccess()) {
					loginForm.getUsernameTextBox().setText("");
					loginForm.getPasswordTextBox().setText("");
				} else {
					if (event.isUsernameInvalid()) {
						loginForm.getUsernameTextBox().setText("");
						loginForm.getPasswordTextBox().setText("");
						loginForm.getUsernameTextBox().setFocus(true);
						return;
					}
					if (event.isPasswordInvalid()) {
						loginForm.getPasswordTextBox().setText("");
						loginForm.getPasswordTextBox().setFocus(true);
					}
				}
			}
		};
		
		Events.eventBus.addHandler(LoginEvent.TYPE, handler);
	}
	
	private void setupRoomSelectionPanel() {
		//TODO
	}
	
	
}
