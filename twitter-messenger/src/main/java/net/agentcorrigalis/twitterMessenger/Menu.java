package net.agentcorrigalis.twitterMessenger;

import static net.agentcorrigalis.twitterMessenger.CommonConstants.MENU_SEPARATOR;
import static net.agentcorrigalis.twitterMessenger.CommonConstants.NEW_LINE;
import twitter4j.TwitterException;

public class Menu {

	public String getDisplay() {
		return buildMenuDisplay();
	}
	
	private String buildMenuDisplay() {
		StringBuilder menuDisplay = new StringBuilder();
		menuDisplay.append(MENU_SEPARATOR);
		for (MenuOption option : MenuOption.values()) {
			menuDisplay.append(NEW_LINE);
			menuDisplay.append(option.getKey());
			menuDisplay.append(" - ");
			menuDisplay.append(option.getText());
		}
		menuDisplay.append(NEW_LINE + MENU_SEPARATOR);
		
		return menuDisplay.toString();
	}
	
	public boolean executeOption(char key) throws TwitterException {
		Boolean optionExecuted = false;
		switch (key) {
			case '1': printAllReceivedMessages();
				optionExecuted = true;
				break;
			case '2': printLatestReceivedMessage();
				optionExecuted = true;
				break;
			case '3': printAllSentMessages();
				optionExecuted = true;
				break;
			case '4': printLatestSentMessage();
				optionExecuted = true;
				break;
			case 'q': terminateApplication();
				optionExecuted = true;
				break;
			default: optionExecuted = false;
				break;
		}
		return optionExecuted;
	}
	
	private void printAllReceivedMessages() {
		ReceivedMessageQueryService receivedMessageQueryService = new ReceivedMessageQueryService();
		receivedMessageQueryService.printAllMessages();
	}
	
	private void printLatestReceivedMessage() {
		ReceivedMessageQueryService receivedMessageQueryService = new ReceivedMessageQueryService();
		receivedMessageQueryService.printLatestMessage();
	}

	private void printAllSentMessages() throws TwitterException {
		SentMessageQueryService sentMessageQueryService = new SentMessageQueryService();
		sentMessageQueryService.printAllMessages();
	}
	
	private void printLatestSentMessage() throws TwitterException {
		SentMessageQueryService sentMessageQueryService = new SentMessageQueryService();
		sentMessageQueryService.printLatestMessage();
	}
	
	private void terminateApplication() {
		System.out.println("Quiting TwitMsg");
		System.exit(0);
	}
	
}
