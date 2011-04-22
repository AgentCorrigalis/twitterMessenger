package net.agentcorrigalis.twitterMessenger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import twitter4j.DirectMessage;
import twitter4j.Paging;
import twitter4j.Twitter;
import twitter4j.TwitterException;

public class SentMessageQueryService {

	private Twitter twitSession;
	
	public SentMessageQueryService() {
		twitSession = SessionManager.getTwitterSession();
	}
	
	public void printAllCompactDirectMessages() throws TwitterException {
		List<CompactDirectMessage> CompactDirectMessages = getAllSentMessages();
		for (CompactDirectMessage CompactDirectMessage : CompactDirectMessages) {
			System.out.println("From " + CompactDirectMessage.getSender() + "--" + CompactDirectMessage.getId() + "--" + CompactDirectMessage.getContent());
		}
	}
	
	public void printLastCompactDirectMessage() throws TwitterException {
		List<CompactDirectMessage> CompactDirectMessages = getAllSentMessages();
		CompactDirectMessage lastReceived = CompactDirectMessages.get(0);
		System.out.println("From " + lastReceived.getSender() + "--" + lastReceived.getId() + "--" + lastReceived.getContent());
	}
	
	private List<CompactDirectMessage> getAllSentMessages() throws TwitterException {
		List<CompactDirectMessage> sentMessages = new ArrayList<CompactDirectMessage>();
		Paging paging = new Paging(1);
		List<DirectMessage> messages;
		do {
			messages = twitSession.getSentDirectMessages(paging);
			for (DirectMessage message : messages) {
				CompactDirectMessage compactDirectMessage = new CompactDirectMessage();
				compactDirectMessage.setId(message.getId());
				compactDirectMessage.setDate(message.getCreatedAt());
				compactDirectMessage.setRecipient(message.getRecipientScreenName());
				compactDirectMessage.setContent(message.getText());
				sentMessages.add(compactDirectMessage);
			}
			paging.setPage(paging.getPage() + 1);
		} while (messages.size() > 0);
		CompactDirectMessageComparator compactDirectMessageComparator = new CompactDirectMessageComparator();
		Collections.sort(sentMessages, compactDirectMessageComparator);
		return sentMessages;
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
