package com.blackjack.client.ui;

import com.blackjack.shared.entities.Room;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.SimplePanel;

public class Dashboard extends SimplePanel {

	private Panel[] views;
	private int currentViewIndex = 0;
	private LoginForm loginForm = new LoginForm();
	private ForgotPasswordForm forgotPasswordForm = new ForgotPasswordForm();
	private CreateAccountForm createAccountForm = new CreateAccountForm();
	private RoomSelectionPanel roomSelectionPanel;
	private BlackJackGamePanel gamePanel = new BlackJackGamePanel();
	
	
	public Dashboard() {
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
		
		views = new Panel[] {loginForm, forgotPasswordForm, createAccountForm, roomSelectionPanel, gamePanel};
		
		//Force the dashboard to load the first page.
		retreat();
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
	
	public void loadView(Panel view) {
		this.clear();
		this.add(view);
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
