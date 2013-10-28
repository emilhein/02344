package web;

import database.Tools;
import database.User;

public class Session {
	
	private User user;

	// Properties

	public User getUser() {

		return user;
	}

	// Functions

	public void signin(Application application, String mailOrName, String password) throws Exception {

		signout();

		User user;
		
		Tools.validateUserPassword(password);
		user = application.getConnector().getUser(mailOrName);

		if (!user.checkPassword(password)) {
			throw new Exception("Wrong password.");
		}

		if (user.getType() == User.BLOCKED) {
			throw new Exception("You are currently blocked.");
		}
		
		this.user = user;
		
	}

	public void signout() {

		user = null;

	}

}
