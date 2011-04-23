package net.agentcorrigalis.twitterMessenger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import twitter4j.IDs;
import twitter4j.ResponseList;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.User;

public class MessageSenderService {

	private Twitter twitSession;
	private List<User> friendMap;

	public MessageSenderService() {
		twitSession = SessionManager.getTwitterSession();
	}

	public void newMessage() throws IllegalStateException, TwitterException,
			IOException {
		populateFriendMap();
		displayFriendMenu();
		BufferedReader optionReader = new BufferedReader(new InputStreamReader(System.in));
		String friendOption = optionReader.readLine();
		executeOption(friendOption.charAt(0));
	}

	private void populateFriendMap() throws IllegalStateException,
			TwitterException {
		friendMap = new ArrayList<User>();
		long cursor = -1;
		IDs friendIds;
		int count = 1;
		do {
			friendIds = twitSession.getFriendsIDs(cursor);
			ResponseList<User> friends = twitSession.lookupUsers(friendIds
					.getIDs());
			for (User friend : friends) {
				friendMap.add(friend);
				count++;
			}
		} while ((cursor = friendIds.getNextCursor()) != 0);
	}

	private void displayFriendMenu() throws IOException {
		System.out.println("Available users to send messages to: ");
		int friendCount = friendMap.size();
		System.out.println(CommonConstants.MENU_SEPARATOR);
		for (int friend = 0; friend < friendCount; friend++) {
			System.out.println(friend + " -- " + friendMap.get(friend).getScreenName());
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
		System.out.println("Write message to send to " + friendMap.get(numericOption).getScreenName());
		BufferedReader messageReader = new BufferedReader(new InputStreamReader(System.in));
		String directMessage = messageReader.readLine();
		twitSession.sendDirectMessage(friendMap.get(numericOption).getId(), directMessage);
		System.out.println("... sent");
	}

}
