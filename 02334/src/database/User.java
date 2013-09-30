package database;

import java.security.MessageDigest;
import java.util.Arrays;

public class User {

	private Connector connector;
	private int identifier;
	private String name;
	private byte[] password;
	private Type type;
	
	// New
	
	protected User(Connector connector, int identifier, String name, String password, Type type) {
		
		this.connector = connector;
		this.identifier = identifier;
		this.name = name;
		this.password = hash(password);
		this.type = type;
		
	}
	
	// Properties
	
	public int getIdentifier() {
		
		return identifier;
	}
	public String getName() {
		
		return name;
	}
	protected byte[] getPassword() {
		
		return password;
	}
	public Type getType() {
		
		return type;
	}
	
	public void setName(String name) throws Exception {
		
		// TODO: Validate
		
		this.name = name;
		
		connector.update(this);
		
	}
	public void setPassword(String password) throws Exception {
		
		// TODO: Validate
		
		this.password = hash(password);

		connector.update(this);
		
	}
	public void setType(Type type) throws Exception {
		
		// TODO: Validate
		
		this.type = type;

		connector.update(this);
		
	}
	
	// Functions
	
	public boolean checkPassword(String password) {
		
		return Arrays.equals(this.password, hash(password));
	}
	
	private byte[] hash(String password) {
		
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
			messageDigest.update(password.getBytes("UTF-8"));
			return messageDigest.digest();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
}
