package net.agentcorrigalis.twitterMessenger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyManager {

	private static final String PATH_TO_PROPERTY_FILE = "runtime/twitMsg.properties";
	private static final String TWTT_MSG_PROPERTY_NAME = "twitMsg.";
	private static final String ACCESS_TOKEN_KEY = TWTT_MSG_PROPERTY_NAME + "accessToken";
	private static final String ACCESS_TOKEN_SECRET_KEY = TWTT_MSG_PROPERTY_NAME + "accessTokenSecret";
	
	public PropertyManager() {
		File propertyFile = new File(PATH_TO_PROPERTY_FILE);
		if (!propertyFile.exists()) {
			try {
				propertyFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public boolean accessTokenExists() {
		if (loadProperty(ACCESS_TOKEN_KEY) == null
				|| loadProperty(ACCESS_TOKEN_SECRET_KEY) == null) {
			return false;
		} else {
			return true;
		}
	}

	public String getAccessToken() {
		return loadProperty(ACCESS_TOKEN_KEY);
	}

	public String getAccessTokenSecret() {
		return loadProperty(ACCESS_TOKEN_SECRET_KEY);
	}

	public void writeAccessToken(String accessToken, String accessTokenSecret) {
		Properties properties = new Properties();
		properties.setProperty(ACCESS_TOKEN_KEY, accessToken);
		properties.setProperty(ACCESS_TOKEN_SECRET_KEY, accessTokenSecret);
		storeProperties(properties);
	}

	private void storeProperties(Properties properties) {
		File propertyFile = new File(PATH_TO_PROPERTY_FILE);
		try {
			properties.store(new FileOutputStream(propertyFile), "writing new properties");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String loadProperty(String key) {
		File propertyFile = new File(PATH_TO_PROPERTY_FILE);
		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream(propertyFile));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties.getProperty(key);
	}

}
