package Web;

import database.Connector;
import database.User;

public class Session {
	public static final String SERVER = "sql-lab1.cc.dtu.dk";
	
	public static final int PORT = 3306;
	
	public static final String DATABASE = "s123115";
	
	public static final String USERNAME = "s123115";
	
	public static final String PASSWORD = "F5iCtVPs4rtHu4oM";
	
	private static Connector connector;
	
	private User user = null;


	public Session() throws Exception {
		connector = new Connector(SERVER, PORT, DATABASE, USERNAME, PASSWORD);
	}
	
	public String getMessage() {
		return "Hello World!";
	}
	
	
}
