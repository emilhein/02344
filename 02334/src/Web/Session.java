package Web;

import database.Tools;
import database.User;

public class Session {
	
	private User user;

	// Properties

	public User getUser() {

		return user;
	}
	
	public boolean isLoggedIn() {

		return user != null;
	}

	// Functions

	public String login(Application application, String mailOrname, String password) {

		logout();

		User user;
		
		try {
			Tools.validateUserPassword(password);
			user = application.getConnector().getUser(mailOrname);
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

	public void logout() {

		user = null;

	}

}
