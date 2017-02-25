package com.blackjack.client.ui;

import com.blackjack.client.entities.Card;
import com.blackjack.client.entities.Hand.HandType;
import com.blackjack.client.ui.GameButton.GameButtonType;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;

/**
 * The BlackJackGamePanel is the primary UI piece for the main 
 * game of BlackJack.
 * 
 * @author Dave
 *
 */
public class BlackJackGamePanel extends SimplePanel {

	private int betAmount;
	private FlowPanel content;
	private GameButton insuranceButton;
	private GameButton doubleDownButton;
	private GameButton hitButton;
	private GameButton standButton;
	private GameButton splitButton;
	private GameButton surrenderButton;
	private ChipButton chip1;
	private ChipButton chip5;
	private ChipButton chip25;
	private ChipButton chip50;
	private ChipButton chip100;
	private HandPanel playerHandPanel;
	private HandPanel dealerHandPanel;
	private Button dealButton;
	private Label betLabelValue;
	private Label sideBetLabelValue;
	private Label sideBetLabel;
	private Label instructionLabel;
	private UserMessageBox insurancePrompt;
	/**
	 * Default constructor adds all children to the panel and 
	 * sets display styles for all components.
	 */
	public BlackJackGamePanel() {
		this.setStylePrimaryName("modal");
		this.addStyleName("centered");
		this.addStyleDependentName("game");
		this.setHeight("100%");
		this.setWidth("100%");
		
		content = new FlowPanel();
		FlowPanel buttonPanel = new FlowPanel();
		buttonPanel.setStylePrimaryName("gameButtonPanel");
		
		insuranceButton = new GameButton(GameButton.GameButtonType.INSURANCE);
		insuranceButton.setVisible(false);
		doubleDownButton = new GameButton(GameButton.GameButtonType.DOUBLE_DOWN);
		hitButton = new GameButton(GameButton.GameButtonType.HIT);
		standButton = new GameButton(GameButton.GameButtonType.STAND);
		splitButton = new GameButton(GameButton.GameButtonType.SPLIT);
		surrenderButton = new GameButton(GameButton.GameButtonType.SURRENDER);
		dealButton = new GameButton(GameButtonType.DEAL);
		
		buttonPanel.add(surrenderButton);
		buttonPanel.add(insuranceButton);
		buttonPanel.add(doubleDownButton);
		buttonPanel.add(splitButton);
		buttonPanel.add(hitButton);
		buttonPanel.add(standButton);
		buttonPanel.add(dealButton);
		
		playerHandPanel = new HandPanel(HandType.PLAYER);
		dealerHandPanel = new HandPanel(HandType.DEALER);
		
		FlowPanel chipPanel = new FlowPanel();
		chipPanel.setStylePrimaryName("chipPanel");
		
		FlowPanel betPanel = new FlowPanel();
		Label betLabel = new Label("Current Bet: ");
		betLabel.setStylePrimaryName("label");
		betLabel.addStyleDependentName("goldFont");
		betLabel.addStyleDependentName("underline");
		betLabel.getElement().setAttribute("style", "margin-right: 20px;");
		
		betLabelValue = new Label("$0");
		betLabelValue.setStylePrimaryName("label");
		betLabelValue.addStyleDependentName("goldFont");
		
		betPanel.add(betLabel);
		betPanel.add(betLabelValue);
		
		FlowPanel sideBetPanel = new FlowPanel();
		sideBetLabel = new Label("Side Bet: ");
		sideBetLabel.setStylePrimaryName("label");
		sideBetLabel.addStyleDependentName("goldFont");
		sideBetLabel.addStyleDependentName("underline");
		sideBetLabel.getElement().setAttribute("style", "margin-right: 38px;");
		sideBetLabel.setVisible(false);
		
		sideBetLabelValue = new Label("$0");
		sideBetLabelValue.setStylePrimaryName("label");
		sideBetLabelValue.addStyleDependentName("goldFont");
		sideBetLabelValue.setVisible(false);
		
		sideBetPanel.add(sideBetLabel);
		sideBetPanel.add(sideBetLabelValue);
		
		instructionLabel = new Label();
		instructionLabel.setStylePrimaryName("label");
		instructionLabel.addStyleDependentName("goldFont");
		instructionLabel.addStyleDependentName("instruction");
		instructionLabel.addStyleName("centered");
		
		insurancePrompt = new UserMessageBox();
		insurancePrompt.setMessage("Do you want insurnace?");
		insurancePrompt.getOkButton().setText("Yes");
		insurancePrompt.getQuitButton().setText("No");
		insurancePrompt.getQuitButton().setVisible(true);
		insurancePrompt.setVisible(false);
		
		chip1 = new ChipButton(ChipButton.ChipValue.ONE);
		chip5 = new ChipButton(ChipButton.ChipValue.FIVE);
		chip25 = new ChipButton(ChipButton.ChipValue.TWENTY_FIVE);
		chip50 = new ChipButton(ChipButton.ChipValue.FIFTY);
		chip100 = new ChipButton(ChipButton.ChipValue.ONE_HUNDERED);
		
		chipPanel.add(betPanel);
		chipPanel.add(sideBetPanel);
		HTML br = new HTML("<br/>");
		br.setHeight("0px");
		chipPanel.add(br);
		br.setHeight("30px");
		chipPanel.add(br);
		chipPanel.add(chip1);
		chipPanel.add(chip5);
		chipPanel.add(chip25);
		chipPanel.add(chip50);
		chipPanel.add(chip100);
		content.add(buttonPanel);
		content.add(playerHandPanel);
		content.add(dealerHandPanel);
		content.add(chipPanel);
		content.add(instructionLabel);
		content.add(insurancePrompt);
		
		this.add(content);
	}
	
	/**
	 * Hits the player's primary hand
	 * 
	 * @param card the card to display in the primary hand
	 */
	public void hitPlayerHand(Card card) {
		playerHandPanel.hit(card);
	}
	
	/**
	 * Hits the player's split hand
	 * 
	 * @param card the card to display in the split hand
	 */
	public void hitPlayerSplitHand(Card card) {
		playerHandPanel.hitSplit(card);
	}
	
	/**
	 * Hits the player primary hand and displays the card at 
	 * 90 degrees to infer a double down.
	 * 
	 * @param card the card to display at 90deg in the primary hand
	 */
	public void hitPlayerHand_DoubleDown(Card card) {
		playerHandPanel.hit_DoubleDown(card);
	}
	
	/**
	 * Hits the player split hand and displays the card at 
	 * 90 degrees to infer a double down.
	 * 
	 * @param card
	 */
	public void hitPlayerSplitHand_DoubleDown(Card card) {
		playerHandPanel.hitSplit_DoubleDown(card);
	}
	
	/**
	 * Hits the dealer hand
	 * 
	 * @param card the card to hit the dealer hand with
	 */
	public void hitDealerHand(Card card) {
		dealerHandPanel.hit(card);
	}
	
	/**
	 * Causes the UI to display a stand label over the player hand
	 */
	public void playerStand() {
		playerHandPanel.stand();
	}
	
	/**
	 * Causes the UI to display a stand label over the dealer hand
	 */
	public void dealerStand() {
		dealerHandPanel.stand();
	}
	
	/**
	 * Causes the UI to display a bust label over the player hand
	 */
	public void playerBust() {
		playerHandPanel.bust();
	}
	
	/**
	 * Causes the UI to display a bust label over the dealer hand
	 */
	public void dealerBust() {
		dealerHandPanel.bust();
	}
	
	//causes UI to display 21 label over player hand
	public void twentyone() {
		playerHandPanel.twentyone();
	}
	/**
	 * Deals a card to the player, will not allow more than 
	 * two cards 
	 * 
	 * @param card the card to deal to the player
	 */
	public void dealPlayerCard(Card card) {
		if (playerHandPanel.getNumberPrimaryCards() < 2) {
			this.hitPlayerHand(card);
		}
	}
	
	/**
	 * Deal a card to the dealer, will not allow more than two cards,
	 * if the card is the second card, will set the UI to display the 
	 * card face down.
	 * 
	 * @param card the card to deal to the dealer
	 */
	public void dealDealerCard(Card card, boolean isEasyPlay) {
		int numCards = dealerHandPanel.getNumberPrimaryCards();
		if (numCards < 2) {
			if (numCards == 0 && !isEasyPlay) {
				dealerHandPanel.hitFaceDown(card);
			} else {
				dealerHandPanel.hit(card);
			}
		}
	}
	
	/**
	 * Updates the UI with the appropriate bet amount, this is not
	 * the amount to set, but the amount to decrease or increase the current
	 * bet by. Use negative values for bet decreasing.
	 *  
	 * @param amount the amount to increase or decrease the current bet amount by
	 */
	public void bet(float amount) {
		String value = NumberFormat.getDecimalFormat().format(amount);
		if (value.contains(".")) {
			if (value.substring(value.indexOf('.'), value.length()).length() < 3) {
				value += "0";
			}
		}
		betLabelValue.setText("$" + value);
	}
	
	
	public void betInsurance(float amount) {
		String value = NumberFormat.getDecimalFormat().format(amount);
		if (value.contains(".")) {
			if (value.substring(value.indexOf('.'), value.length()).length() < 3) {
				value += "0";
			}
		}
		sideBetLabelValue.setText("$" + value);
	}
	
	/**
	 * Enables or disabled a button specified by the button type.
	 * @param button the button to disable/enable
	 * @param enabled if false will disable the button, if true will enable it
	 */
	public void enableButton(GameButton.GameButtonType button, boolean enabled){
		switch(button){
			case INSURANCE:
				insuranceButton.setEnabled(enabled);
				break;
			case DOUBLE_DOWN:
				doubleDownButton.setEnabled(enabled);
				break;
			case HIT:				
				hitButton.setEnabled(enabled);
				break;
			case STAND:				
				standButton.setEnabled(enabled);
				break;
			case SPLIT:
				splitButton.setEnabled(enabled);
				break;
			case SURRENDER:
				surrenderButton.setEnabled(enabled);
				break;
			case DEAL:
				dealButton.setEnabled(enabled);
				break;			
		}
	}
	
	/**
	 * Enables or disables the chip buttons
	 * 
	 * @param enabled true will enable the chips, false will disable
	 */
	public void chipsEnabled(boolean enabled){
		chip1.setEnabled(enabled);
		chip5.setEnabled(enabled);		
		chip25.setEnabled(enabled);
		chip50.setEnabled(enabled);
		chip100.setEnabled(enabled);	
	}
	
	
	/**
	 * Disables all the game buttons for the user
	 */
	public void disableAllButtons(){
		insuranceButton.setEnabled(false);
		doubleDownButton.setEnabled(false);
		hitButton.setEnabled(false);
		standButton.setEnabled(false);
		splitButton.setEnabled(false);
		surrenderButton.setEnabled(false);
		dealButton.setEnabled(false);
		chip1.setEnabled(false);
		chip5.setEnabled(false);		
		chip25.setEnabled(false);
		chip50.setEnabled(false);
		chip100.setEnabled(false);		
	}
	
	/**
	 * Turns over the dealers down card
	 */
	public void showDealerCard() {
		dealerHandPanel.showDealerCard();
	}
	
	/**
	 * Sets the panel back to the starting hand state
	 */
	public void reset() {
		disableAllButtons();
		chipsEnabled(true);
		bet(0);
	}
	
	/**
	 * Resets the dealer and player hands to their original state
	 */
	public void resetHands() {
		playerHandPanel.reset();
		dealerHandPanel.reset();
	}
	
	/**
	 * Displays a message in the center of the game panel to the player
	 * 
	 * @param message the message to display
	 */
	public void displayInstruction(String message) {
		this.instructionLabel.setText(message);
	}
	
	public void displayInsurancePrompt(boolean visible) {
		insurancePrompt.setVisible(visible);
	}
	
	public void displayInsuranceBet(boolean visible) {
		sideBetLabel.setVisible(visible);
		sideBetLabelValue.setVisible(visible);
	}
	
	/**
	 * Updates the UI to display a split in the player hand
	 */
	public void splitPlayerHand() {
		playerHandPanel.split();
	}
	
	public int getBetAmount() {
		return betAmount;
	}

	public void setBetAmount(int betAmount) {
		this.betAmount = betAmount;
	}

	public FlowPanel getContent() {
		return content;
	}

	public void setContent(FlowPanel content) {
		this.content = content;
	}

	public GameButton getInsuranceButton() {
		return insuranceButton;
	}

	public void setInsuranceButton(GameButton insuranceButton) {
		this.insuranceButton = insuranceButton;
	}

	public GameButton getDoubleDownButton() {
		return doubleDownButton;
	}

	public void setDoubleDownButton(GameButton doubleDownButton) {
		this.doubleDownButton = doubleDownButton;
	}

	public GameButton getHitButton() {
		return hitButton;
	}

	public void setHitButton(GameButton hitButton) {
		this.hitButton = hitButton;
	}

	public GameButton getStandButton() {
		return standButton;
	}

	public void setStandButton(GameButton standButton) {
		this.standButton = standButton;
	}

	public GameButton getSplitButton() {
		return splitButton;
	}

	public void setSplitButton(GameButton splitButton) {
		this.splitButton = splitButton;
	}

	public GameButton getSurrenderButton() {
		return surrenderButton;
	}

	public void setSurrenderButton(GameButton surrenderButton) {
		this.surrenderButton = surrenderButton;
	}

	public ChipButton getChip1() {
		return chip1;
	}

	public void setChip1(ChipButton chip1) {
		this.chip1 = chip1;
	}

	public ChipButton getChip5() {
		return chip5;
	}

	public void setChip5(ChipButton chip5) {
		this.chip5 = chip5;
	}

	public ChipButton getChip25() {
		return chip25;
	}

	public void setChip25(ChipButton chip25) {
		this.chip25 = chip25;
	}

	public ChipButton getChip50() {
		return chip50;
	}

	public void setChip50(ChipButton chip50) {
		this.chip50 = chip50;
	}

	public ChipButton getChip100() {
		return chip100;
	}

	public void setChip100(ChipButton chip100) {
		this.chip100 = chip100;
	}

	public HandPanel getPlayerHandPanel() {
		return playerHandPanel;
	}

	public void setPlayerHandPanel(HandPanel playerHandPanel) {
		this.playerHandPanel = playerHandPanel;
	}

	public HandPanel getDealerHandPanel() {
		return dealerHandPanel;
	}

	public void setDealerHandPanel(HandPanel dealerHandPanel) {
		this.dealerHandPanel = dealerHandPanel;
	}

	public Button getDealButton() {
		return dealButton;
	}

	public void setDealButton(Button dealButton) {
		this.dealButton = dealButton;
	}

	public UserMessageBox getInsurancePrompt() {
		return insurancePrompt;
	}

	public void setInsurancePrompt(UserMessageBox insurancePrompt) {
		this.insurancePrompt = insurancePrompt;
	}
	
}
