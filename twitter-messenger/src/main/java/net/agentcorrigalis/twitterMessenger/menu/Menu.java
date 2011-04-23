package net.agentcorrigalis.twitterMessenger.menu;

import static net.agentcorrigalis.twitterMessenger.common.CommonConstants.MENU_SEPARATOR;
import static net.agentcorrigalis.twitterMessenger.common.CommonConstants.NEW_LINE;

import java.io.IOException;

import net.agentcorrigalis.twitterMessenger.MessageSenderService;
import net.agentcorrigalis.twitterMessenger.MessageWriter;

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
	
	public boolean executeOption(char key) throws TwitterException, IllegalStateException, IOException {
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
			case '5': sendNewDirectMessage();
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
		MessageWriter messageWriter = new MessageWriter();
		messageWriter.displayAllReceivedMessages();
	}
	
	private void printLatestReceivedMessage() {
		MessageWriter messageWriter = new MessageWriter();
		messageWriter.printLatestReceivedMessage();
	}

	private void printAllSentMessages() throws TwitterException {
		MessageWriter messageWriter = new MessageWriter();
		messageWriter.displayAllSentMessages();
	}
	
	private void printLatestSentMessage() throws TwitterException {
		MessageWriter messageWriter = new MessageWriter();
		messageWriter.printLatestSentMessage();
	}
	
	private void sendNewDirectMessage() throws IllegalStateException, TwitterException, IOException {
		MessageSenderService messageSenderService = new MessageSenderService();
		messageSenderService.newMessage();
	}
	
	private void terminateApplication() {
		System.out.println("Quiting TwitMsg");
		System.exit(0);
	}
	
}
