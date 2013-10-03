package database;

import java.util.Arrays;

public class User {
	
	public static final int ADMINISTRATOR = 0;
	public static final int MODERATOR = 1;
	public static final int USER = 2;

	private Connector connector;
	private int identifier;
	private String mail;
	private String name;
	private byte[] password;
	private int type;
	
	// New
	
	protected User(Connector connector, int identifier, String mail, String name, byte[] password, int type) {
		
		this.connector = connector;
		this.identifier = identifier;
		this.mail = mail;
		this.name = name;
		this.password = password;
		this.type = type;
		
	}
	
	// Properties
	
	public int getIdentifier() {
		
		return identifier;
	}
	public String getMail() {
		
		return mail;
	}
	public String getName() {
		
		return name;
	}
	public int getType() {
		
		return type;
	}
	
	public void setMail(String mail) throws Exception {
		
		Tools.validateUserMail(mail);
		
		connector.updateUser(identifier, mail, name, password, type);
		
		this.mail = mail;
		
	}
	public void setName(String name) throws Exception {
		
		Tools.validateUserName(name);
		
		connector.updateUser(identifier, mail, name, password, type);
		
		this.name = name;
		
	}
	public void setPassword(String password) throws Exception {
		
		Tools.validateUserPassword(password);
		
		byte[] hash = Tools.hash(password);
		
		connector.updateUser(identifier, mail, name, hash, type);
		
		this.password = hash;
		
	}
	public void setType(int type) throws Exception {
		
		Tools.validateUserType(type);
		
		connector.updateUser(identifier, mail, name, password, type);
		
		this.type = type;
		
	}
	
	protected byte[] getPassword() {
		
		return password;
	}
	
	// Functions
	
	public boolean checkPassword(String password) {
		
		return Arrays.equals(this.password, Tools.hash(password));
	}
	
}
