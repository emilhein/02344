package database;

public enum Type {

	ADMINISTRATOR (0), MODERATOR (1), USER (2);
	
	private final int value;
	
	// New
	
	private Type(int value) {
		
		this.value = value;
		
	}
	
	// Properties
	
	public int getValue() {
		
		return value;
	}
	
}
