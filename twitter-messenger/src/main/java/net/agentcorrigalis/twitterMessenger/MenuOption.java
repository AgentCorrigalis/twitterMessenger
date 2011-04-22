package net.agentcorrigalis.twitterMessenger;

public enum MenuOption {

	PRINT_ALL_RECEIVED_MESSAGES('1', "Print All Received Messages"),
	PRINT_LAST_RECEIVED_MESSAGES('2', "Print Last Received Messages"),
	QUIT('q', "Quit");
	
	private char key;
	private String text;
	
	private MenuOption(char key, String text) {
		this.key = key;
		this.text = text;
	}
	
	public char getKey() {
		return key;
	}
	
	public String getText() {
		return text;
	}
	
}
