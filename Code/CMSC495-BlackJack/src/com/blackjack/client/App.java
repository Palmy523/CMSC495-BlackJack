package com.blackjack.client;

import com.blackjack.client.controls.GameController;
import com.blackjack.client.controls.UserController;
import com.blackjack.client.ui.AccountAnchor;
import com.blackjack.client.ui.Dashboard;
import com.blackjack.client.ui.LoginForm;
import com.blackjack.client.ui.RoomSelectionPanel;
import com.blackjack.shared.entities.Room;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
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
	}
	
	private void setupRoomSelectionPanel() {
		//TODO
	}
	
	
}
