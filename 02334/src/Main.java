import database.Category;
import database.Connector;
import database.User;

public class Main {

	public static void main(String[] args) {	
		
		Connector connector = null;
		
		try {
			
			connector = new Connector("sql-lab1.cc.dtu.dk", 3306, "s123115", "s123115", "F5iCtVPs4rtHu4oM");
			connector.reset();

			System.out.println();
			System.out.println("Users:");
			System.out.println();
			for (User user : connector.getUsers()) {
				System.out.println(" " + user.getIdentifier() + "   " + user.getMail() + "   " + user.getName() + "   " + user.getType());
			}

			System.out.println();
			System.out.println("Categories:");
			System.out.println();
			for (Category category : connector.getCategories()) {
				System.out.println(" " + category.getIdentifier() + "   " + category.getName() + "   " + (category.getParent() == null ? "" : category.getParent().getName()));
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connector.close();
		}
		
	}

}
