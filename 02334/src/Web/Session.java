package Web;

import java.util.ArrayList;
import java.util.List;

import database.Connector;
import database.Tools;
import database.User;

public class Session {
	private static Object lock = new Object();
	private static Connector connector = null;
	private User user;

	public Session() throws Exception {
		synchronized (lock) {
			if (connector == null)
				;
			connector = new Connector("sql-lab1.cc.dtu.dk", 3306, "s123115",
					"s123115", "F5iCtVPs4rtHu4oM");
		}

	}

	public static Connector getConnector() {
		return connector;

	}

	public String login(String name, String password) {

		logout();

		try {
			Tools.validateUserName(name);
			Tools.validateUserPassword(password);
		} catch (Exception e) {
			return e.getMessage();
		}

		if (password == null || !password.matches("^.{5,8}$")) {
			return "You must enter a valid password.";
		}

		// Check

		User user;
		try {
			user = connector.getUser(Integer.parseInt(id));
		} catch (Exception e) {
			System.err.println("Wrong user id or password (" + e.getMessage()
					+ ").");
			return "Wrong user id or password.";
		}

		if (!user.checkPassword(password)) {
			return "Wrong user id or password.";
		}

		if (user.getType() == User.BLOCKED) {
			return "You are Blocked.";
		}
		this.user = user;
		return null;
	}

	public void logout() {

		user = null;

	}

	public boolean isLoggedIn() {

		return user != null;
	}

	public User getUser() {

		return user;
	}

}
