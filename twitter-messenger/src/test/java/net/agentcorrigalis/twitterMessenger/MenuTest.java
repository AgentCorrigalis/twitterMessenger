package net.agentcorrigalis.twitterMessenger;

import static org.junit.Assert.*;
import static net.agentcorrigalis.twitterMessenger.common.CommonConstants.*;

import net.agentcorrigalis.twitterMessenger.menu.Menu;

import org.junit.Test;

public class MenuTest {

	private Menu menu = new Menu();
	
	private String expectedMenuDisplay =
		MENU_SEPARATOR
		+ NEW_LINE
		+ "1 - Print Received Messages"
		+ NEW_LINE 
		+ "q - Quit" 
		+ NEW_LINE 
		+ MENU_SEPARATOR;
	
	@Test
	public void menuDisplay() {
		String menuDisplay = menu.getDisplay();
		assertEquals(expectedMenuDisplay, menuDisplay);
	}
	
	@Test
	public void menuExecuteUnlistedOption() {
		assertFalse(menu.executeOption('0'));
	}
	
	@Test
	public void executePrintReceivedMessagesOption() {
		assertTrue(menu.executeOption('1'));
	}
	
}
