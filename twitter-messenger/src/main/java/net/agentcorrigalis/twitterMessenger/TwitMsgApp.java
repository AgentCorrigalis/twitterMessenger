package net.agentcorrigalis.twitterMessenger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import net.agentcorrigalis.twitterMessenger.menu.Menu;
import net.agentcorrigalis.twitterMessenger.twitter.AuthorisedTwitterSession;

import twitter4j.TwitterException;

public class TwitMsgApp {

	private static BufferedReader menuOptionReader = new BufferedReader(new InputStreamReader(System.in));
	
	public static void main(String[] args) throws TwitterException, IOException, InterruptedException {
		appInitialisation();
		Menu menu = new Menu();
		while (true) {
			System.out.println(menu.getDisplay());
			String menuOption = menuOptionReader.readLine();
			menu.executeOption(menuOption.charAt(0));
		}
	}
	
	private static void appInitialisation() throws TwitterException, IOException, InterruptedException {
		AuthorisedTwitterSession.openAuthorisedSession();
		System.out.println("Twitter Session Open for " + AuthorisedTwitterSession.getTwitterSession().getScreenName());
	}

}

