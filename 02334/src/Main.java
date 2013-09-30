import java.util.List;

import database.Connector;
import database.User;

public class Main {

	public static void main(String[] args) {	
		
		Connector connector = null;
		
		try {
			
			connector = new Connector("sql-lab1.cc.dtu.dk", 3306, "s123115", "s123115", "F5iCtVPs4rtHu4oM");
			connector.reset();
			
			List<User> users = connector.getUsers();
			
			System.out.println("Users:");
			System.out.println();
			for (User user : users) {
				System.out.println(" " + user.getIdentifier() + "   " + user.getMail() + "   " + user.getName() + "   " + user.getType());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connector.close();
		}
		
	}

}
