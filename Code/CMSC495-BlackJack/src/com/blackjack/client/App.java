package com.blackjack.client;

import com.blackjack.client.controls.GameController;
import com.blackjack.client.controls.UserController;
import com.blackjack.client.event.Events;
import com.blackjack.client.ui.AccountAnchor;
import com.blackjack.client.ui.AccountManagementForm;
import com.blackjack.client.ui.CreateAccountForm;
import com.blackjack.client.ui.Dashboard;
import com.blackjack.client.ui.ForgotPasswordForm;
import com.blackjack.client.ui.LoginForm;
import com.blackjack.client.ui.RoomSelectionPanel;
import com.blackjack.shared.entities.Room;
import com.blackjack.shared.entities.User;
import com.blackjack.shared.events.CreateAccountEvent;
import com.blackjack.shared.events.LoginEvent;
import com.blackjack.shared.events.UpdateChipEvent;
import com.blackjack.shared.handlers.CreateAccountHandler;
import com.blackjack.shared.handlers.LoginHandler;
import com.blackjack.shared.handlers.UpdateChipHandler;
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
	private CreateAccountForm createAccountForm;
	private ForgotPasswordForm forgotPasswordForm;
	private RoomSelectionPanel roomSelectionPanel;
	private AccountAnchor accountAnchor;
	private AccountManagementForm accountManagementForm;
	
	/**
	 * Call start in CMSC495_BlackJack to load the app
	 */
	public void start() {
		dashboard = new Dashboard();
		userController = new UserController(dashboard);
		gameController = new GameController(dashboard);
		
		loginForm = new LoginForm();
		setupLogin();
		
		createAccountForm = new CreateAccountForm();
		setupCreateAccount();
		
		forgotPasswordForm = new ForgotPasswordForm();
		setupForgotPasswordForm();
		
		Room room1 = Room.createLowStakesRoom();
		Room room2 = Room.createMediumStakesRoom();
		Room room3 = Room.createHighStakesRoom();
		Room[] rooms = new Room[] {room1, room2, room3};
		roomSelectionPanel = new RoomSelectionPanel(rooms);
		setupRoomSelectionPanel();

		accountAnchor = new AccountAnchor();
		setupAccountAnchor();
		
		accountManagementForm = new AccountManagementForm();
		setupAccountManagementForm();
		
		dashboard.setLoginForm(loginForm);
		dashboard.setCreateAccountForm(createAccountForm);
		dashboard.setForgotPasswordForm(forgotPasswordForm);
		dashboard.setRoomSelectionPanel(roomSelectionPanel);
		dashboard.setAccountAnchor(accountAnchor);
		dashboard.setAccountManagementForm(accountManagementForm);
		
		dashboard.displayLoginScreen();
		RootPanel.get().add(dashboard);
	}
	
	/**
	 * Sets up the login screen handlers
	 */
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
		
		//Action for opening the CreateAccount screen
		loginForm.getCreateAccountButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				dashboard.displayCreateAccountScreen();
			}
			
		});
		
		//Add a Login Handler to clear form values depending on success of the event
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
	
	/**
	 * Sets up the create account handlers
	 */
	private void setupCreateAccount() {
		createAccountForm.getCreateAccountButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				String username = createAccountForm.getUserNameTextBox().getText();
				String email = createAccountForm.getEmailTextBox().getText();
				String password = createAccountForm.getPasswordTextBox().getText();
				String confirmPassword = createAccountForm.getConfirmPasswordTextBox().getText();
				userController.createAccount(username, password, confirmPassword, email);
			}
			
		});
		
		createAccountForm.getCancelButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				dashboard.displayLoginScreen();
			}
		});
		
		CreateAccountHandler handler = new CreateAccountHandler() {

			@Override
			public void onCreateAccount(CreateAccountEvent event) {
				if (event.isSuccess()) {
					createAccountForm.clearValues();
				}
			}
			
		};
		Events.eventBus.addHandler(CreateAccountEvent.TYPE, handler);
	}
	
	/**
	 * Sets up the Forgot Passowrd Form handlers
	 */
	private void setupForgotPasswordForm() {
		forgotPasswordForm.getResetPasswordButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				String email = forgotPasswordForm.getEmailTextBox().getText();
				userController.resetPassword(email);
			}
		});
	}
	
	/**
	 * Sets up the Account Anchor handlers
	 */
	private void setupAccountAnchor() {
		LoginHandler loginHandler = new LoginHandler() {

			@Override
			public void OnLogin(LoginEvent event) {
				if (event.isSuccess()) {
					User user = event.getUser();
					accountAnchor.updateChipCount(user.getBankAmount());
				}
			}
			
		};
		
		Events.eventBus.addHandler(LoginEvent.TYPE, loginHandler);
		
		UpdateChipHandler chipUpdateHandler = new UpdateChipHandler() {

			@Override
			public void processUpdateChipEvent(UpdateChipEvent event) {
				if(event.isSuccess()) {
					accountAnchor.updateChipCount(event.getNewAmount());
				}
			}
			
		};
		
		Events.eventBus.addHandler(UpdateChipEvent.TYPE, chipUpdateHandler);
		
		accountAnchor.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				dashboard.displayAccountManagementScreen();
			}
			
		});
	}
	
	/**
	 * Sets up the account management form handlers
	 */
	private void setupAccountManagementForm() {
		//TODO
	}
	
	/**
	 * Sets up the Room Selection Panel handlers
	 */
	private void setupRoomSelectionPanel() {
		//TODO
	}
	
	
}
