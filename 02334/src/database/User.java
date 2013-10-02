package database;

import java.security.MessageDigest;
import java.util.Arrays;

public class User {
	
	public final int ADMINISTRATOR = 0;
	public final int MODERATOR = 1;
	public final int USER = 2;

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
	protected User(Connector connector, int identifier, String mail, String name, String password, int type) {
		
		this.connector = connector;
		this.identifier = identifier;
		this.mail = mail;
		this.name = name;
		this.password = hash(password);
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
	protected byte[] getPassword() {
		
		return password;
	}
	public int getType() {
		
		return type;
	}

	public void setMail(String mail) throws Exception {

		if (!mail.matches("^[A-z0-9]+@[A-z0-9]+\\.[A-z0-9]+$")) {
			throw new IllegalArgumentException("Mail is invalid.");
		}

		this.mail = mail;
		
		connector.update(this); // TODO: Gendan hvis det fejler
		
	}
	public void setName(String name) throws Exception {

		if (!name.matches("^[A-z0-9]+$")) {
			throw new IllegalArgumentException("Name is invalid.");
		}

		this.name = name;
		
		connector.update(this); // TODO: Gendan hvis det fejler
		
	}
	public void setPassword(String password) throws Exception {
		
		if (password.length() < 8) {
			throw new IllegalArgumentException("Password is too short.");
		}
		
		this.password = hash(password);

		connector.update(this); // TODO: Gendan hvis det fejler
		
	}
	public void setType(int type) throws Exception {
		
		if (type != ADMINISTRATOR && type != MODERATOR && type != USER) {
			throw new IllegalArgumentException("Type is invalid.");
		}

		this.type = type;

		connector.update(this); // TODO: Gendan hvis det fejler
		
	}
	
	// Functions
	
	public boolean checkPassword(String password) {
		
		return Arrays.equals(this.password, hash(password));
	}
	
	private byte[] hash(String password) {
		
		// TODO: Tilføj salt
		
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
