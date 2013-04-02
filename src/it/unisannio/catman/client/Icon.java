package it.unisannio.catman.client;

public enum Icon {
	INBOX('w'), OUTBOX('v'), CIRCLES('L'), TAG('C'), BADGE('.'), BOLT('Q'), CALENDAR('P'),
	PEOPLE(','), CABINET('t'), GEAR('@');
	
	private final char character;
	Icon(char c) {
		this.character = c;
	}
	
	public char getCharacter() {
		return character;
	}
}