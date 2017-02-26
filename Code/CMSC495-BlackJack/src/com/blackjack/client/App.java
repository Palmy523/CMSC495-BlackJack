package com.blackjack.client;

import com.blackjack.client.controls.GameController;
import com.blackjack.client.controls.UserController;
import com.blackjack.client.entities.Card;
import com.blackjack.client.entities.Card.Rank;
import com.blackjack.client.entities.Card.Set;
import com.blackjack.client.entities.Card.Suit;
import com.blackjack.client.entities.Deck;
import com.blackjack.client.entities.GameState;
import com.blackjack.client.entities.GameState.TurnState;
import com.blackjack.client.entities.Hand;
import com.blackjack.client.event.Events;
import com.blackjack.client.ui.AccountAnchor;
import com.blackjack.client.ui.AccountManagementForm;
import com.blackjack.client.ui.BackAnchor;
import com.blackjack.client.ui.BlackJackGamePanel;
import com.blackjack.client.ui.ConfirmEmailForm;
import com.blackjack.client.ui.CreateAccountForm;
import com.blackjack.client.ui.Dashboard;
import com.blackjack.client.ui.ForgotPasswordForm;
import com.blackjack.client.ui.LoginForm;
import com.blackjack.client.ui.RoomPanel;
import com.blackjack.client.ui.RoomSelectionPanel;
import com.blackjack.shared.entities.Room;
import com.blackjack.shared.entities.User;
import com.blackjack.shared.events.ConfirmEmailEvent;
import com.blackjack.shared.events.CreateAccountEvent;
import com.blackjack.shared.events.LoginEvent;
import com.blackjack.shared.events.ResetPasswordEvent;
import com.blackjack.shared.events.UpdateChipEvent;
import com.blackjack.shared.events.UpdateEmailEvent;
import com.blackjack.shared.events.UpdatePasswordEvent;
import com.blackjack.shared.handlers.ConfirmEmailHandler;
import com.blackjack.shared.handlers.CreateAccountHandler;
import com.blackjack.shared.handlers.LoginHandler;
import com.blackjack.shared.handlers.ResetPasswordHandler;
import com.blackjack.shared.handlers.UpdateChipHandler;
import com.blackjack.shared.handlers.UpdateEmailHandler;
import com.blackjack.shared.handlers.UpdatePasswordHandler;
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
	private BackAnchor backAnchor;
	private AccountManagementForm accountManagementForm;
	private ConfirmEmailForm confirmEmailForm;
	private BlackJackGamePanel gamePanel;

	/**
	 * Call start in CMSC495_BlackJack to load the app
	 */
	public void start() {
		dashboard = new Dashboard();
		UserController.setDashboard(dashboard);
		
		loginForm = new LoginForm();
		setupLogin();

		createAccountForm = new CreateAccountForm();
		setupCreateAccount();

		forgotPasswordForm = new ForgotPasswordForm();
		setupForgotPasswordForm();

		Room room1 = Room.createLowStakesRoom();
		Room room2 = Room.createMediumStakesRoom();
		Room room3 = Room.createHighStakesRoom();
		Room[] rooms = new Room[] { room1, room2, room3 };
		roomSelectionPanel = new RoomSelectionPanel(rooms);
		setupRoomSelectionPanel();

		accountAnchor = new AccountAnchor();
		setupAccountAnchor();

		backAnchor = new BackAnchor();
		setupBackAnchor();

		accountManagementForm = new AccountManagementForm();
		setupAccountManagementForm();

		confirmEmailForm = new ConfirmEmailForm();
		setupConfirmEmailForm();

		gamePanel = new BlackJackGamePanel();
		setupGamePanel();

		dashboard.setLoginForm(loginForm);
		dashboard.setCreateAccountForm(createAccountForm);
		dashboard.setForgotPasswordForm(forgotPasswordForm);
		dashboard.setRoomSelectionPanel(roomSelectionPanel);
		dashboard.setAccountAnchor(accountAnchor);
		dashboard.setBackAnchor(backAnchor);
		dashboard.setAccountManagementForm(accountManagementForm);
		dashboard.setConfirmEmailForm(confirmEmailForm);
		dashboard.setGamePanel(gamePanel);

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
				UserController.login(userName, password);
			}
		});

		loginForm.getUsernameTextBox().addKeyPressHandler(new KeyPressHandler() {

			@Override
			public void onKeyPress(KeyPressEvent event) {
				if ((int) event.getCharCode() == KeyCodes.KEY_ENTER) {
					loginForm.getLoginButton().click();
				}
			}
		});

		loginForm.getPasswordTextBox().addKeyPressHandler(new KeyPressHandler() {

			@Override
			public void onKeyPress(KeyPressEvent event) {
				if ((int) event.getCharCode() == KeyCodes.KEY_ENTER) {
					loginForm.getLoginButton().click();
				}
			}
		});

		// Action for opening the CreateAccount screen
		loginForm.getCreateAccountButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				dashboard.displayCreateAccountScreen();
			}

		});

		loginForm.getForgotPasswordButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				dashboard.displayForgotPasswordForm();
			}

		});

		// Add a Login Handler to clear form values depending on success of the
		// event
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
				UserController.createAccount(username, password, confirmPassword, email);
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
				UserController.resetPassword(email);
			}
		});

		ResetPasswordHandler handler = new ResetPasswordHandler() {

			@Override
			public void onResetPassword(ResetPasswordEvent event) {
				if (event.isSuccess()) {
					forgotPasswordForm.getEmailTextBox().setText("");
				}
			}

		};
		Events.eventBus.addHandler(ResetPasswordEvent.TYPE, handler);
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
				if (event.isSuccess()) {
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
	 * Sets up the back anchor form handlers
	 */
	private void setupBackAnchor() {
		backAnchor.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				dashboard.loadPreviousScreen();
			}

		});
	}

	/**
	 * Sets up the account management form handlers
	 */
	private void setupAccountManagementForm() {
		accountManagementForm.getUpdatePasswordButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				String currentPassword = accountManagementForm.getCurrentPasswordTextBox().getText();
				String newPassword = accountManagementForm.getNewPasswordTextBox().getText();
				String confirmPassword = accountManagementForm.getConfirmPasswordTextBox().getText();
				UserController.updatePassword(currentPassword, newPassword, confirmPassword);
			}
		});

		accountManagementForm.getUpdateEmailButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				String newEmail = accountManagementForm.getNewEmailTextBox().getText();
				UserController.updatEmail(newEmail);
			}

		});

		UpdatePasswordHandler passwordHandler = new UpdatePasswordHandler() {

			@Override
			public void onUpdatePassword(UpdatePasswordEvent event) {
				if (event.isSuccess()) {
					accountManagementForm.clearPasswords();
				}
			}

		};
		Events.eventBus.addHandler(UpdatePasswordEvent.TYPE, passwordHandler);

		UpdateEmailHandler emailHandler = new UpdateEmailHandler() {

			@Override
			public void onUpdateEmail(UpdateEmailEvent event) {
				if (event.isSuccess()) {
					accountManagementForm.clearEmail();
				}
			}

		};
		Events.eventBus.addHandler(UpdateEmailEvent.TYPE, emailHandler);

	}

	private void setupConfirmEmailForm() {
		confirmEmailForm.getSubmitButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				String key = confirmEmailForm.getConfirmKeyTextBox().getText();
				UserController.confirmEmail(key);
			}
		});

		ConfirmEmailHandler handler = new ConfirmEmailHandler() {

			@Override
			public void onConfirmEmail(ConfirmEmailEvent event) {
				if (event.isSuccess()) {
					confirmEmailForm.getConfirmKeyTextBox().setText("");
				}
			}
		};
		Events.eventBus.addHandler(ConfirmEmailEvent.TYPE, handler);

		confirmEmailForm.getCancelButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				confirmEmailForm.getConfirmKeyTextBox().setText("");
				dashboard.displayAccountManagementScreen();
			}
		});
	}

	/**
	 * Sets up the Room Selection Panel handlers
	 */
	private void setupRoomSelectionPanel() {
		for (RoomPanel roomPanel : roomSelectionPanel.getRoomPanels()) {
			final Room room = roomPanel.getRoom();
			roomPanel.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					if(room.isEnabled()){						
						boolean isEasyPlay = roomSelectionPanel.getDifficultyCheckBox().getValue();
						GameState state = new GameState();
						GameState.setEasyPlay(isEasyPlay);
						GameState.setBetAmount(0);
						GameState.setPlayerHand(new Hand());
						GameState.setDealerHand(new Hand());
						GameState.setDeck(new Deck(Set.ONE, 1, true));
						GameState.setTurn(TurnState.PLAYER_TURN);
						GameState.setRoom(room);
						GameState.setUser(UserController.getUser());
						gameController = new GameController(dashboard, state);
						gameController.startGame();
					}
				}
			});
		}
		
		LoginHandler handler = new LoginHandler() {

			@Override
			public void OnLogin(LoginEvent event) {
				if (event.isSuccess()) {
					roomSelectionPanel.updateRoomEnabledness();
				}
			}
		};
		
		Events.eventBus.addHandler(LoginEvent.TYPE, handler);
	}

	private void setupGamePanel() {
		gamePanel.displayInstruction("Place bet");
		
		gamePanel.getHitButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				gameController.playerHit();
			}
		});

		gamePanel.getStandButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				gameController.playerStand();
			}
		});
		
		gamePanel.getDealButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				gameController.deal();
			}
		});
		
		gamePanel.getChip1().getPlusButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				gameController.betPlus(1);
			}
			
		});
		
		gamePanel.getChip1().getMinusButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				gameController.betPlus(-1);
			}
			
		});
		
		gamePanel.getChip5().getPlusButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				gameController.betPlus(5);
			}
			
		});
		
		gamePanel.getChip5().getMinusButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				gameController.betPlus(-5);
			}
			
		});
		
		gamePanel.getChip25().getPlusButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				gameController.betPlus(25);
			}
			
		});
		
		gamePanel.getChip25().getMinusButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				gameController.betPlus(-25);
			}
			
		});
		
		gamePanel.getChip50().getPlusButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				gameController.betPlus(50);
			}
			
		});
		
		gamePanel.getChip50().getMinusButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				gameController.betPlus(-50);
			}
			
		});
		
		gamePanel.getChip100().getPlusButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				gameController.betPlus(100);
			}
			
		});
		
		gamePanel.getChip100().getMinusButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				gameController.betPlus(-100);
			}
			
		});
		
		gamePanel.getSplitButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				gameController.split();
			}
			
		});
		
		gamePanel.getDoubleDownButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				gameController.doubleDown();
			}
			
		});
		
		gamePanel.getInsuranceButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				gameController.insurance();
			}
		});
		
		gamePanel.getSurrenderButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				gameController.surrender();
			}
		});
		
		gamePanel.getInsurancePrompt().getQuitButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				gameController.noInsurance();
			}
			
		});
		
		gamePanel.getInsurancePrompt().getOkButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				gameController.insurance();
			}
			
		});
		
	}

}
