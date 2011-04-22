package net.agentcorrigalis.twitterMessenger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import twitter4j.DirectMessage;
import twitter4j.Paging;
import twitter4j.Twitter;
import twitter4j.TwitterException;

public class ReceivedMessageQueryService {

	private Twitter twitSession;
	
	public ReceivedMessageQueryService() {
		twitSession = SessionManager.getTwitterSession();
	}
	
	public void printAllMessages() {
		List<CompactDirectMessage> CompactDirectMessages = getAllDirectMessages();
		for (CompactDirectMessage CompactDirectMessage : CompactDirectMessages) {
			System.out.println("From " + CompactDirectMessage.getSender() + "--" + CompactDirectMessage.getId() + "--" + CompactDirectMessage.getContent());
		}
	}
	
	public void printLatestMessage() {
		List<CompactDirectMessage> CompactDirectMessages = getAllDirectMessages();
		CompactDirectMessage lastReceived = CompactDirectMessages.get(0);
		System.out.println("From " + lastReceived.getSender() + "--" + lastReceived.getId() + "--" + lastReceived.getContent());
	}
	
	private List<CompactDirectMessage> getAllDirectMessages() {
		List<CompactDirectMessage> CompactDirectMessages = new ArrayList<CompactDirectMessage>();
		try {
            Paging paging = new Paging(1);
            List<DirectMessage> messages;
            do {
                messages = twitSession.getDirectMessages(paging);
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
