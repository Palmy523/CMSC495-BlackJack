package com.blackjack.client.ui;

import com.blackjack.client.controls.GameController;
import com.blackjack.client.controls.UserController;
import com.blackjack.client.ui.UserMessageBox.MessageType;
import com.blackjack.shared.entities.Room;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
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
	
	private GameController gameController;
	
	private UserController userController;
	
	/**
	 * A View for the login.
	 */
	private LoginForm loginForm = new LoginForm();
	
	/**
	 * A view for the Forgot Password form.
	 */
	private ForgotPasswordForm forgotPasswordForm = new ForgotPasswordForm();
	
	/**
	 * A view for the Create Account Form
	 */
	private CreateAccountForm createAccountForm = new CreateAccountForm();
	
	/**
	 * The view for the Room Selection Screen
	 */
	private RoomSelectionPanel roomSelectionPanel;
	
	/**
	 * A view for the BlackJack Game Screen.
	 */
	private BlackJackGamePanel gamePanel = new BlackJackGamePanel();
	
	/**
	 * Widget used to display Chip Count and quick access to account
	 * management.
	 */
	private AccountAnchor accountAnchor = new AccountAnchor();
	
	/**
	 * Widget user for navigation.
	 */
	private BackAnchor backAnchor = new BackAnchor();
	
	/**
	 * Form used for account actions.
	 */
	private AccountManagementForm accountManagementForm = new AccountManagementForm();
	
	/**
	 * Default constructor
	 */
	public Dashboard() {
		//Create the views
		loginForm = new LoginForm();
		forgotPasswordForm = new ForgotPasswordForm();
		createAccountForm = new CreateAccountForm();
		
		Room lowStakes = Room.createLowStakesRoom();
		Room mediumStakes = Room.createMediumStakesRoom();
		Room highStakes = Room.createHighStakesRoom();
		
		roomSelectionPanel = new RoomSelectionPanel(
				new Room[] {
							lowStakes, 
							mediumStakes, 
							highStakes
							});
		
		gamePanel = new BlackJackGamePanel();
		
		//Add the views to the array
		views = new Panel[] {loginForm, forgotPasswordForm, createAccountForm, roomSelectionPanel, gamePanel};
		
		//Force the dashboard to load the first page.
		retreat();
		
		backAnchor.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				retreat();
			}
		});
		
		accountAnchor.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				loadView(accountManagementForm);
			}
			
		});
	}
	
	//TODO Remove test
	public void advance() {
		currentViewIndex = (currentViewIndex >= views.length - 1) ? currentViewIndex : currentViewIndex + 1;
		Panel view = views[currentViewIndex];
		loadView(view);
	}
	
	public void retreat() {
		currentViewIndex = (currentViewIndex == 0) ? currentViewIndex : currentViewIndex - 1;
		Panel view = views[currentViewIndex];
		loadView(view);
	}
	//TODO end remove test
	
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
	
	public void loadRoomSelectionScreen() {
		this.loadView(roomSelectionPanel);
	}
	
	/**
	 * Displays a message to the user of the application
	 * @param type the type of message to display
	 * @param message the message to display
	 */
	public void displayMessage(MessageType type, String message) {
		
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
	
	
	
	
}
