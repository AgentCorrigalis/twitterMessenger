package net.agentcorrigalis.twitterMessenger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import net.agentcorrigalis.twitterMessenger.common.CommonConstants;
import net.agentcorrigalis.twitterMessenger.twitter.TwitterServiceImpl;

import twitter4j.TwitterException;
import twitter4j.User;

public class MessageSenderService {

	private TwitterServiceImpl twitterService;
	private List<User> friends;

	public void newMessage() throws IllegalStateException, TwitterException,
			IOException {
		populateFriendMap();
		displayFriendMenu();
		BufferedReader optionReader = new BufferedReader(new InputStreamReader(System.in));
		String friendOption = optionReader.readLine();
		executeOption(friendOption.charAt(0));
	}

	private void populateFriendMap() throws IllegalStateException, TwitterException {
		friends = twitterService.getFriends();
	}

	private void displayFriendMenu() throws IOException {
		System.out.println("Available users to send messages to: ");
		int friendCount = twitterService.getFriends().size();
		System.out.println(CommonConstants.MENU_SEPARATOR);
		for (int friend = 0; friend < friendCount; friend++) {
			System.out.println(friend + " -- " + friends.get(friend).getScreenName());
		}
		System.out.println(CommonConstants.MENU_SEPARATOR);
		System.out.print("Pick one to message or 'q' to return to main menu: ");
	}

	private boolean executeOption(char option) throws IOException, TwitterException {
		Boolean optionExecuted = false;
		switch (option) {
		case 'q':
			optionExecuted = true;
			break;
		default:
			sendMessage(option);
			optionExecuted = false;
			break;
		}
		return optionExecuted;
	}
	
	private void sendMessage(char option) throws IOException, TwitterException {
		Integer numericOption = null;
		try {
			numericOption = Integer.parseInt(String.valueOf(option));
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Write message to send to " + friends.get(numericOption).getScreenName());
		BufferedReader messageReader = new BufferedReader(new InputStreamReader(System.in));
		String directMessage = messageReader.readLine();
		twitterService.sendDirectMessage(friends.get(numericOption).getScreenName(), directMessage);
		System.out.println("... sent");
	}

}
