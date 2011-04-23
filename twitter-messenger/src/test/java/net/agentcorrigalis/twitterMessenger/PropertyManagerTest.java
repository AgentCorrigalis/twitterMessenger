package net.agentcorrigalis.twitterMessenger;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.junit.Before;
import org.junit.Test;

public class PropertyManagerTest {

	private PropertyService propertyManager = new PropertyService();
	private Properties properties;
	
	@Before
	public void testSetUp() {
		properties = new Properties();
	}
	
	@Test
	public void accessTokenOnlyExistsInPropertyFile() throws FileNotFoundException, IOException {
		startWithFileExists();
		properties.setProperty("twitMsg.accessToken", "accessToken");
		TestUtils.writePropertiesToFile(properties);
		assertFalse(propertyManager.accessTokenExists());
	}
	
	@Test
	public void accessTokenSecretOnlyExistsInPropertyFile() throws FileNotFoundException, IOException {
		startWithFileExists();
		properties.setProperty("twitMsg.accessTokenSecret", "accessTokenSecret");
		TestUtils.writePropertiesToFile(properties);
		assertFalse(propertyManager.accessTokenExists());
	}
	
	@Test
	public void accessTokenAndSecretExistInPropertyFile() throws FileNotFoundException, IOException {
		startWithFileExists();
		properties.setProperty("twitMsg.accessToken", "accessToken");
		properties.setProperty("twitMsg.accessTokenSecret", "accessTokenSecret");
		TestUtils.writePropertiesToFile(properties);
		assertTrue(propertyManager.accessTokenExists());
	}
	
	@Test
	public void accessTokenAndSecretReturned() throws FileNotFoundException, IOException {
		startWithFileExists();
		properties.setProperty("twitMsg.accessToken", "accessToken");
		properties.setProperty("twitMsg.accessTokenSecret", "accessTokenSecret");
		TestUtils.writePropertiesToFile(properties);
		assertEquals("accessToken", propertyManager.getAccessToken());
		assertEquals("accessTokenSecret", propertyManager.getAccessTokenSecret());
	}
	
	@Test
	public void writeNewAccessTokenProperties() {
		startWithFileExists();
		propertyManager.writeAccessToken("accessToken", "accessTokenSecret");
		assertEquals("accessToken", propertyManager.getAccessToken());
		assertEquals("accessTokenSecret", propertyManager.getAccessTokenSecret());
	}
	
	private void startWithFileExists() {
		TestUtils.createNewEmptyPropertyFile();
	}
	
}
