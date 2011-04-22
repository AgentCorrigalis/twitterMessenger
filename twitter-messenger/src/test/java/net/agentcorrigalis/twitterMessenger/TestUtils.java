package net.agentcorrigalis.twitterMessenger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class TestUtils {

	private static final File PROPERTY_FILE = new File("runtime/twitMsg.properties");
	
	public static void createPropertyFileWithAccessToken() {
		createNewEmptyPropertyFile();
	}
	
	public static void createNewEmptyPropertyFile() {
		if (PROPERTY_FILE.exists()) PROPERTY_FILE.delete();
		try {
			PROPERTY_FILE.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void writePropertiesToFile(Properties properties) throws FileNotFoundException, IOException {
		properties.store(new FileOutputStream(PROPERTY_FILE), "set properties");
	}
	
}
