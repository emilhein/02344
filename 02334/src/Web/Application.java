package Web;

import database.Connector;

public class Application {
	
	private Connector connector = null;
	
	// New
	
	public Application() throws Exception {
		
		connector = new Connector("sql-lab1.cc.dtu.dk", 3306, "s123115", "s123115", "F5iCtVPs4rtHu4oM");
		
	}
	
	// Properties
	
	public Connector getConnector() {
		
		return connector;
	}

}
