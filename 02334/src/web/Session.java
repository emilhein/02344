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

	public String signin(Application application, String mailOrName, String password) {

		signout();

		User user;
		
		try {
			Tools.validateUserPassword(password);
			user = application.getConnector().getUser(mailOrName);
		} catch (Exception e) {
			return e.getMessage();
		}

		if (!user.checkPassword(password)) {
			return "Wrong password.";
		}

		if (user.getType() == User.BLOCKED) {
			return "You are currently blocked.";
		}
		
		this.user = user;
		return null;
	}

	public void signout() {

		user = null;

	}

}
