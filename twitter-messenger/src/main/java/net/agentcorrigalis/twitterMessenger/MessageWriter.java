package net.agentcorrigalis.twitterMessenger;

import java.util.List;

import net.agentcorrigalis.twitterMessenger.common.CommonConstants;
import net.agentcorrigalis.twitterMessenger.twitter.TwitterServiceImpl;
import twitter4j.DirectMessage;


public class MessageWriter {
	
	private TwitterServiceImpl twitterService = new TwitterServiceImpl();
	
	public void displayAllReceivedMessages() {
		List<DirectMessage> messages = twitterService.getReceivedMessagesSorted();
		for (DirectMessage message : messages) {
			messageWriter(message);
		}
	}
	
	public void displayAllSentMessages() {
		for (DirectMessage message : twitterService.getSentMessagesSorted()) {
			messageWriter(message);
		}
	}
	
	public void printLatestReceivedMessage() {
		messageWriter(twitterService.getLatestReceivedMessage());
	}
	
	public void printLatestSentMessage() {
		messageWriter(twitterService.getLatestSentMessage());
	}
	
	private void messageWriter(DirectMessage message) {
		System.out.println(CommonConstants.MESSAGE_SEPARATOR);
		System.out.println("Message ID: " + message.getId());
		System.out.println("To: " + message.getSender());
		System.out.println("From: " + message.getRecipient());
		System.out.println("From: " + message.getCreatedAt());
		System.out.println(message.getText());
		System.out.println(CommonConstants.MESSAGE_SEPARATOR);
	}

}
