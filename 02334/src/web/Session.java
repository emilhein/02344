package web;

import database.Connector;
import database.Tools;
import database.User;

public class Session {
	
	private Connector connector;
	private int user = -1;
	
	// Properties

	public User getUser() throws Exception {

		if (user == -1) {
			return null;
		}
		
		return connector.getUser(user);
	}

	// Functions

	public void setup(Connector connector) {
		
		this.connector = connector;
		
	}
	
	public void signin(String mailOrName, String password) throws Exception {

		signout();

		User user;
		
		Tools.validateUserPassword(password);
		user = connector.getUser(mailOrName);

		if (!user.checkPassword(password)) {
			throw new Exception("Wrong password.");
		}

		if (user.getType() == User.BLOCKED) {
			throw new Exception("You are currently blocked.");
		}
		
		this.user = user.getIdentifier();
		
	}
	
	public void signout() {

		user = -1;

	}
	
}
