package net.agentcorrigalis.twitterMessenger.twitter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


import twitter4j.DirectMessage;
import twitter4j.IDs;
import twitter4j.Paging;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.User;

public class TwitterServiceImpl implements TwitterService {

	private Twitter twitterSession;
	
	public TwitterServiceImpl() {
		twitterSession = AuthorisedTwitterSession.getTwitterSession();
	}
	
	@Override
	public String getScreenName() {
		try {
			return twitterSession.getScreenName();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (TwitterException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<DirectMessage> getSentMessagesSorted() {
		List<DirectMessage> sentMessages = new ArrayList<DirectMessage>();
        Paging paging = new Paging(1);
        try {
        	do {
				sentMessages.addAll(twitterSession.getSentDirectMessages(paging));
				paging.setPage(paging.getPage() + 1);
        	} while (twitterSession.getSentDirectMessages(paging).size() > 0);
        } catch (TwitterException e) {
        	e.printStackTrace();
        }
        DirectMessageComparator directMessageComparator = new DirectMessageComparator();
        Collections.sort(sentMessages, directMessageComparator);
		return sentMessages;
	}

	@Override
	public List<DirectMessage> getReceivedMessagesSorted() {
		List<DirectMessage> receivedMessages = new ArrayList<DirectMessage>();
        Paging paging = new Paging(1);
        try {
        	do {
        		receivedMessages.addAll(twitterSession.getDirectMessages(paging));
				paging.setPage(paging.getPage() + 1);
        	} while (twitterSession.getDirectMessages(paging).size() > 0);
        } catch (TwitterException e) {
        	e.printStackTrace();
        }
        DirectMessageComparator directMessageComparator = new DirectMessageComparator();
        Collections.sort(receivedMessages, directMessageComparator);
		return receivedMessages;
	}

	@Override
	public DirectMessage getLatestSentMessage() {
		return getSentMessagesSorted().get(0);
	}

	@Override
	public DirectMessage getLatestReceivedMessage() {
		return getReceivedMessagesSorted().get(0);
	}

	@Override
	public List<User> getFriends() {
		long cursor = -1;
		IDs friendIds;
		List<User> friends = null;
		try {
			do {
				friendIds = twitterSession.getFriendsIDs(cursor);
				friends = twitterSession.lookupUsers(friendIds.getIDs());
			} while ((cursor = friendIds.getNextCursor()) != 0);
		} catch (TwitterException e) {
			e.printStackTrace();
		}
		return friends;
	}
	
	@Override
	public void sendDirectMessage(
			String friendScreenName, 
			String message) {
		
		try {
			twitterSession.sendDirectMessage(friendScreenName, message);
		} catch (TwitterException e) {
			e.printStackTrace();
		}
		
	}
	
	private class DirectMessageComparator implements Comparator<DirectMessage> {

		@Override
		public int compare(DirectMessage m1, DirectMessage m2) {
            // sort descending order
			if (m1.getId() < m2.getId()) {
                return 1;
            } else if (m1.getId() > m2.getId()) {
                return -1;
            } else {
                return 0;
            }
		}

	}

}
