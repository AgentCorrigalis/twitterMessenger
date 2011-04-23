package net.agentcorrigalis.twitterMessenger.menu;

public enum MenuOption {

	PRINT_ALL_RECEIVED_MESSAGES('1', "Print All Received Messages"),
	PRINT_LAST_RECEIVED_MESSAGES('2', "Print Last Received Messages"),
	PRINT_ALL_SENT_MESSAGES('3', "Print All Sent Messages"),
	PRINT_LAST_SENT_MESSAGES('4', "Print Last Sent Messages"),
	SEND_DIRECT_MESSAGE('5', "Send Direct Message"),
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
