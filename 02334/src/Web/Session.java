package Web;

import database.Category;
import database.Connector;
import database.User;

import java.util.ArrayList;
import java.util.List;

public class Session {
	public static final String SERVER = "sql-lab1.cc.dtu.dk";
	public static final int PORT = 3306;
	public static final String DATABASE = "s123115";
	public static final String USERNAME = "s123115";
	public static final String PASSWORD = "F5iCtVPs4rtHu4oM";
	private static Connector connector;

	public Session() throws Exception {
		connector = new Connector(SERVER, PORT, DATABASE, USERNAME, PASSWORD);

	}

	public String getMessage() {
		return "Hej fra Session!";
	}

	public Connector getConnector() {
		Connector connector = null;
		try {
			connector = new Connector("sql-lab1.cc.dtu.dk", 3306, "s123115", "s123115", "F5iCtVPs4rtHu4oM");
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	
		return connector;

	}
}
