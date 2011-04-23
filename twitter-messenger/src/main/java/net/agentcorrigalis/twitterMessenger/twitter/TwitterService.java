package net.agentcorrigalis.twitterMessenger.twitter;

import java.util.List;

import twitter4j.DirectMessage;
import twitter4j.TwitterException;
import twitter4j.User;

public interface TwitterService {

	public String getScreenName() throws IllegalStateException, TwitterException;
	public List<DirectMessage> getSentMessagesSorted();
	public List<DirectMessage> getReceivedMessagesSorted();
	public DirectMessage getLatestSentMessage();
	public DirectMessage getLatestReceivedMessage();
	public List<User> getFriends();
	public void sendDirectMessage(String friendScreenName, String message);
	
}
