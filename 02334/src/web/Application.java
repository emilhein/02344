package web;

import java.util.ArrayList;
import java.util.List;
import database.Connector;
import database.User;

public class Application {
	
	private Connector connector = null;
	private List<Page> pages = new ArrayList<Page>();
	
	// New
	
	public Application() throws Exception {
		
		connector = new Connector("sql-lab1.cc.dtu.dk", 3306, "s123115", "s123115", "F5iCtVPs4rtHu4oM");

		pages.add(new Page("Home", "Home"));
		pages.add(new Page("Create user", "CreateUser"));
		pages.add(new Page("Administrate users", "AdministrateUsers", User.MODERATOR));
		
	}
	
	// Properties
	
	public Connector getConnector() {
		
		return connector;
	}
	
	// Functions
	
	public Page getPage(String name, User user) {
		
		if (name != null) {
			for (Page page : pages) {
				if (name.equalsIgnoreCase(page.getFilename())) {
					if (page.checkAccess(user)) {
						return page;
					}
					break;
				}
			}
		}
		
		return pages.get(0);
	}

}
