package com.blackjack.client.ui;

import com.blackjack.client.ui.UserMessageBox.MessageType;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;

/**
 * The Dashboard is a panel used to control UI navigation between 
 * the sections of the application.
 * 
 * @author Dave
 *
 */
public class Dashboard extends SimplePanel {

	/**
	 * A list of views this Dashboard can attach (only 1 can be attached at a time).
	 */
	private Panel[] views;
	
	/**
	 * The current view index of the Dashboard. Corresponds with the index of the
	 * view displayed.
	 */
	private int currentViewIndex = 0;
	
	/**
	 * A View for the login.
	 */
	private LoginForm loginForm;
	
	/**
	 * A view for the Forgot Password form.
	 */
	private ForgotPasswordForm forgotPasswordForm;
	
	/**
	 * A view for the Create Account Form
	 */
	private CreateAccountForm createAccountForm;
	
	/**
	 * The view for the Room Selection Screen
	 */
	private RoomSelectionPanel roomSelectionPanel;
	
	/**
	 * A view for the BlackJack Game Screen.
	 */
	private BlackJackGamePanel gamePanel;
	
	/**
	 * Widget used to display Chip Count and quick access to account
	 * management.
	 */
	private AccountAnchor accountAnchor = new AccountAnchor();
	
	/**
	 * Widget user for navigation.
	 */
	private BackAnchor backAnchor;
	
	/**
	 * Form used for account actions.
	 */
	private AccountManagementForm accountManagementForm;
	
	private ConfirmEmailForm confirmEmailForm;
	
	private UserMessageBox messageBox = new UserMessageBox();
	
	private String[] place = new String[2];
	/**
	 * Default constructor
	 */
	public Dashboard() {
		messageBox.setVisible(false);
		RootPanel.get().add(messageBox);
		
		History.addValueChangeHandler(new ValueChangeHandler<String>() {

			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				loadHistoryItem(event.getValue());
			}
		});
	}
	
	/**
	 * Loads a view as the current view to this dashboard.
	 * @param view
	 */
	public void loadView(Panel view) {
		boolean displayAccountAnchor = false;
		boolean displayBackAnchor = false;
		if (view.getClass() == RoomSelectionPanel.class 
				|| view.getClass() == BlackJackGamePanel.class){
			displayAccountAnchor = true;
		} 
		if (view.getClass() == BlackJackGamePanel.class 
				|| view.getClass() == AccountManagementForm.class) {
			displayBackAnchor = true;
		}
		this.displayAccountAnchor(displayAccountAnchor);
		this.displayBackAnchor(displayBackAnchor);
		this.clear();
		this.add(view);
		

	}
	
	private void loadHistoryItem(String name) {
		if (!place[0].equals(name)) {
			String last = place[0];
			place[0] = name;
			place[1] = last;
		}
		
		switch (name) {
		case "login":
			displayLoginScreen();
			break;
		case "rooms":
			displayRoomSelectionScreen();
			break;
		case "createAccount":
			displayCreateAccountScreen();
			break;
		case "accountManagement":
			displayAccountManagementScreen();
			break;
		case "forgotPassword" :
			displayForgotPasswordForm();
			break;
		case "game" :
			displayGamePanel();
			break;
		}
	}
	
	/**
	 * Loads the previous screen, useful for the back button.
	 * Abby's edits: Each screen can only go back to one other screen
	 * (eg game room only goes back to room selection screen,
	 * room selection only goes back to login, etc). Except for account management
	 * (accessible from game room and room selection screen, tracked by 
	 * loadHistoryItem) 
	 */
	public void loadPreviousScreen() {		
		switch (place[0]) {
		case "rooms":
			displayLoginScreen();
			break;
		case "game":
			displayMessage(MessageType.WARN, 
					"Are you sure you want to quit? All chips currently bet will be lost!");
			break;
		case "createAccount":
			displayLoginScreen();
			break;
		case "accountManagement":
			loadHistoryItem(place[1]);
			break;
		case "forgotPassword" :
			displayLoginScreen();
			break;	
		}
		
		
	}
	
	/**
	 * Displays or hides the account anchor.
	 * @param display if true, the account anchor will be displayed. if false, the 
	 * account anchor will be removed from the screen.
	 */
	public void displayAccountAnchor(boolean display) {
		boolean anchorAttached = RootPanel.get().getWidgetIndex(accountAnchor) > -1;
		if (display) {
			if (!anchorAttached) {
				RootPanel.get().add(accountAnchor);
			}
		} else {
			if (anchorAttached) {
				RootPanel.get().remove(accountAnchor);
			}
		}
	}
	
	/**
	 * Displays or hides the back anchor.
	 * @param display if true, the back anchor will be displayed. if false, the 
	 * back anchor will be removed from the screen.
	 */
	public void displayBackAnchor(boolean display) {
		boolean anchorAttached = RootPanel.get().getWidgetIndex(backAnchor) > -1;
		if (display) {
			if (!anchorAttached) {
				RootPanel.get().add(backAnchor);
			}
		} else {
			if (anchorAttached) {
				RootPanel.get().remove(backAnchor);
			}
		}
	}
	
	/**
	 * Display the room selection screen as the current screen for the user.
	 */
	public void displayRoomSelectionScreen() {
		this.loadView(roomSelectionPanel);
		History.newItem("rooms");
	}
	
	/**
	 * Display the login screen as the current screen for the user.
	 */
	public void displayLoginScreen() {
		this.loadView(loginForm);
		History.newItem("login");
	}
	
	public void displayForgotPasswordForm() {
		this.loadView(forgotPasswordForm);
		History.newItem("forgotPassword");
	}
	
	public void displayEmailConfirmationPanel(boolean display) {
		//TODO display the email confirmation panel
	}
	
	/**
	 * Display the create account screen
	 */
	public void displayCreateAccountScreen() {
		this.loadView(createAccountForm);
		History.newItem("createAccount");
	}
	
	
	public void displayAccountManagementScreen() {
		this.loadView(accountManagementForm);
		History.newItem("accountManagement");
	}
	
	public void displayConfirmEmailForm() {
		this.loadView(confirmEmailForm);
	}
	
	public void displayGamePanel() {
		this.loadView(gamePanel);		
		History.newItem("game");
	}
	
	/**
	 * Displays a message to the user of the application
	 * @param type the type of message to display
	 * @param message the message to display
	 */
	public void displayMessage(MessageType type, String message) {
		messageBox.setType(type);
		messageBox.setMessage(message);
		messageBox.setVisible(true);
		messageBox.setFocus(true);
	}
	
	/**
	 * @return the views
	 */
	public Panel[] getViews() {
		return views;
	}

	/**
	 * @param views the views to set
	 */
	public void setViews(Panel[] views) {
		this.views = views;
	}

	/**
	 * @return the currentViewIndex
	 */
	public int getCurrentViewIndex() {
		return currentViewIndex;
	}

	/**
	 * @param currentViewIndex the currentViewIndex to set
	 */
	public void setCurrentViewIndex(int currentViewIndex) {
		this.currentViewIndex = currentViewIndex;
	}

	/**
	 * @return the loginForm
	 */
	public LoginForm getLoginForm() {
		return loginForm;
	}

	/**
	 * @param loginForm the loginForm to set
	 */
	public void setLoginForm(LoginForm loginForm) {
		this.loginForm = loginForm;
	}

	/**
	 * @return the forgotPasswordForm
	 */
	public ForgotPasswordForm getForgotPasswordForm() {
		return forgotPasswordForm;
	}

	/**
	 * @param forgotPasswordForm the forgotPasswordForm to set
	 */
	public void setForgotPasswordForm(ForgotPasswordForm forgotPasswordForm) {
		this.forgotPasswordForm = forgotPasswordForm;
	}

	/**
	 * @return the createAccountForm
	 */
	public CreateAccountForm getCreateAccountForm() {
		return createAccountForm;
	}

	/**
	 * @param createAccountForm the createAccountForm to set
	 */
	public void setCreateAccountForm(CreateAccountForm createAccountForm) {
		this.createAccountForm = createAccountForm;
	}

	/**
	 * @return the roomSelectionPanel
	 */
	public RoomSelectionPanel getRoomSelectionPanel() {
		return roomSelectionPanel;
	}

	/**
	 * @param roomSelectionPanel the roomSelectionPanel to set
	 */
	public void setRoomSelectionPanel(RoomSelectionPanel roomSelectionPanel) {
		this.roomSelectionPanel = roomSelectionPanel;
	}

	/**
	 * @return the gamePanel
	 */
	public BlackJackGamePanel getGamePanel() {
		return gamePanel;
	}

	/**
	 * @param gamePanel the gamePanel to set
	 */
	public void setGamePanel(BlackJackGamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}

	public AccountAnchor getAccountAnchor() {
		return accountAnchor;
	}

	public void setAccountAnchor(AccountAnchor accountAnchor) {
		this.accountAnchor = accountAnchor;
	}

	public BackAnchor getBackAnchor() {
		return backAnchor;
	}

	public void setBackAnchor(BackAnchor backAnchor) {
		this.backAnchor = backAnchor;
	}

	public AccountManagementForm getAccountManagementForm() {
		return accountManagementForm;
	}

	public void setAccountManagementForm(AccountManagementForm accountManagementForm) {
		this.accountManagementForm = accountManagementForm;
	}

	public UserMessageBox getMessageBox() {
		return messageBox;
	}

	public void setMessageBox(UserMessageBox messageBox) {
		this.messageBox = messageBox;
	}

	public ConfirmEmailForm getConfirmEmailForm() {
		return confirmEmailForm;
	}

	public void setConfirmEmailForm(ConfirmEmailForm confirmEmailForm) {
		this.confirmEmailForm = confirmEmailForm;
	}
	
	
	
	
	
	
}
