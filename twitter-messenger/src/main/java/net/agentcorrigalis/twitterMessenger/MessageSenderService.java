package net.agentcorrigalis.twitterMessenger;

import twitter4j.IDs;
import twitter4j.ResponseList;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.User;

public class MessageSenderService {

	private Twitter twitSession;
	
	public MessageSenderService() {
		twitSession = SessionManager.getTwitterSession();
	}
	
	public void newMessage() throws IllegalStateException, TwitterException {
		displayRecipients();
	}
	
	private void displayRecipients() throws IllegalStateException, TwitterException {
		IDs followers = twitSession.getFollowersIDs(twitSession.getId());
		ResponseList<User> users = twitSession.lookupUsers(followers.getIDs());
		for (User user : users) {
			System.out.println(user.getScreenName());
		}
	}
	
}
