import database.Category;
import database.Connector;
import database.User;

public class Main {

	public static void main(String[] args) {	
		
		Connector connector = null;
		
		try {
			
			connector = new Connector("sql-lab1.cc.dtu.dk", 3306, "s123115", "s123115", "F5iCtVPs4rtHu4oM");
			//connector.reset();

			System.out.println();
			System.out.println("Users (Identifier, Mail, Name, Type):");
			System.out.println();
			for (User user : connector.getUsers()) {
				System.out.println(" " + user.getIdentifier() + ": " + user.getMail() + ", " + user.getName() + ", " + user.getType());
			}

			System.out.println();
			System.out.println("Categories (Identifier, Name, Parrent):");
			System.out.println();
			for (Category category : connector.getCategories()) {
				System.out.println(" " + category.getIdentifier() + ": " + category.getName() + ", " + (category.getParent() == null ? "(ingen)" : category.getParent().getName()));
			}
			
			System.out.println();
			System.out.println("Threads (Identifier, User, Category, Name, Sticky, Closed):");
			System.out.println();
			for (database.Thread thread : connector.getThreads()) {
				System.out.println(" " + thread.getIdentifier() + ": " + thread.getUser().getName() + ", " + thread.getCategory().getName() + ", " + thread.getName() + ", " + thread.getSticky() + ", " + thread.getClosed());
			}

			System.out.println();
			System.out.println();
			System.out.println();
			print(connector);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connector.close();
		}
		
	}

	private static void print(Connector connector) throws Exception {
		
		for (Category category : connector.getCategories(null)) {
			printCategory(1, category);
		}
		
	}
	private static void printCategory(int level, Category category) throws Exception {
		
		System.out.println(new String(new char[level]) + "<C" + category.getIdentifier() + "> " + category.getName());
		
		for (Category c : category.getCategories()) {
			printCategory(level + 3, c);
		}

		for (database.Thread t : category.getThreads()) {
			printThread(level + 3, t);
		}
		
	}
	private static void printThread(int level, database.Thread thread) throws Exception {

		System.out.println(new String(new char[level]) + "<T" + thread.getIdentifier() + "> " + thread.getName());
		
	}

}
