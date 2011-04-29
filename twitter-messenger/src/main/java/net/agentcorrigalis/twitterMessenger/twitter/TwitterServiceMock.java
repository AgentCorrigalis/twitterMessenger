package net.agentcorrigalis.twitterMessenger.twitter;

import java.util.List;

import twitter4j.DirectMessage;
import twitter4j.TwitterException;
import twitter4j.User;

public class TwitterServiceMock implements TwitterService {

	@Override
	public String getScreenName() throws IllegalStateException,
			TwitterException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DirectMessage> getSentMessagesSorted() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DirectMessage> getReceivedMessagesSorted() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DirectMessage getLatestSentMessage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DirectMessage getLatestReceivedMessage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getFriends() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void sendDirectMessage(String friendScreenName, String message) {
		// TODO Auto-generated method stub
		
	}

}
