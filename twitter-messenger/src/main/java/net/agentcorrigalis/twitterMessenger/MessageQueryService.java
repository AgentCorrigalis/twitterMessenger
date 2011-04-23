package net.agentcorrigalis.twitterMessenger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import twitter4j.DirectMessage;
import twitter4j.Paging;
import twitter4j.Twitter;
import twitter4j.TwitterException;


public class MessageQueryService {
	
	public enum MessageDirection {
		SENT, RECEIVED;
	}

	private Twitter twitSession;
	
	public MessageQueryService() {
		twitSession = SessionManager.getTwitterSession();
	}
	
	public void printAllMessages(MessageDirection direction) {
		List<CompactDirectMessage> CompactDirectMessages = getAllDirectMessages(direction);
		for (CompactDirectMessage CompactDirectMessage : CompactDirectMessages) {
			System.out.println("From " + CompactDirectMessage.getSender() + "--" + CompactDirectMessage.getId() + "--" + CompactDirectMessage.getContent());
		}
	}
	
	public void printLatestMessage(MessageDirection direction) {
		List<CompactDirectMessage> CompactDirectMessages = getAllDirectMessages(direction);
		CompactDirectMessage lastReceived = CompactDirectMessages.get(0);
		System.out.println("From " + lastReceived.getSender() + "--" + lastReceived.getId() + "--" + lastReceived.getContent());
	}
	
	private List<CompactDirectMessage> getAllDirectMessages(MessageDirection direction) {
		List<CompactDirectMessage> CompactDirectMessages = new ArrayList<CompactDirectMessage>();
		try {
            Paging paging = new Paging(1);
            List<DirectMessage> messages = null;
            do {
            	if (direction.equals(MessageDirection.RECEIVED)) {
            		messages = twitSession.getDirectMessages(paging);
            	} else if (direction.equals(MessageDirection.SENT)) {
            		messages = twitSession.getSentDirectMessages(paging);
            	}
                for (DirectMessage message : messages) {
                    CompactDirectMessage CompactDirectMessage = new CompactDirectMessage();
                    CompactDirectMessage.setId(message.getId());
                    CompactDirectMessage.setDate(message.getCreatedAt());
                    CompactDirectMessage.setSender(message.getSenderScreenName());
                    CompactDirectMessage.setContent(message.getText());
                    CompactDirectMessages.add(CompactDirectMessage);
                }
                paging.setPage(paging.getPage() + 1);
            } while (messages.size() > 0);
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to get messages: " + te.getMessage());
            System.exit(-1);
        }
        CompactDirectMessageComparator CompactDirectMessageComparator = new CompactDirectMessageComparator();
        Collections.sort(CompactDirectMessages, CompactDirectMessageComparator);
        return CompactDirectMessages;
	}
	
	
	private class CompactDirectMessageComparator implements Comparator<CompactDirectMessage> {

		@Override
		public int compare(CompactDirectMessage m1, CompactDirectMessage m2) {
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
