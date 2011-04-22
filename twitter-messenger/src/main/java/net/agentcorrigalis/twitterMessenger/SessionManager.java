package net.agentcorrigalis.twitterMessenger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

public class SessionManager {

	private PropertyManager propertyManager = new PropertyManager();
	private static Twitter twitSession;
	
	public void openAuthorisedSession() throws TwitterException, IOException {
		if (propertyManager.accessTokenExists()) {
			Twitter twitterSession = new TwitterFactory().getInstance();
			AccessToken accessToken = new AccessToken(propertyManager.getAccessToken(), propertyManager.getAccessTokenSecret());
			twitterSession.setOAuthAccessToken(accessToken);
			SessionManager.twitSession = twitterSession;
		} else {
			Twitter twitterSession = new TwitterFactory().getInstance();
			RequestToken requestToken = twitterSession.getOAuthRequestToken();
			AccessToken accessToken = null;
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Visit here to get pin and enter below --> " + requestToken.getAuthenticationURL());
			String pin = br.readLine();
			accessToken = twitterSession.getOAuthAccessToken(requestToken, pin);
			propertyManager.writeAccessToken(accessToken.getToken(), accessToken.getTokenSecret());
			SessionManager.twitSession = twitterSession;
		}
	}
	
	public static Twitter getTwitterSession() {
		return SessionManager.twitSession;
	}
	
}
