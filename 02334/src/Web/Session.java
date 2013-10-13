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
	private static List<Category> categories = new ArrayList<Category>();


	public Session() throws Exception {
		connector = new Connector(SERVER, PORT, DATABASE, USERNAME, PASSWORD);
		categories = connector.getCategories();
	}

	public String getMessage() {
		return "Hej fra Session!";
	}
	
	public List<Category> getCategories() {
		
		return categories;
	}
public List<Category> getCategories(String categoryId) {
		
		if (categoryId != null) {
			try {
				List<Category> temp = new ArrayList<Category>();
				temp.add(connector.getCategory((Integer.parseInt(categoryId))));
				
				return temp;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		
		try {
			return connector.getCategories();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
}