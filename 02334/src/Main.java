import database.Category;
import database.Comment;
import database.Connector;
import database.User;

public class Main {

	public static void main(String[] args) {	
		
		Connector connector = null;
		
		try {
			
			connector = new Connector("sql-lab1.cc.dtu.dk", 3306, "s123115", "s123115", "F5iCtVPs4rtHu4oM");
			//connector.reset();

			System.out.println();
			System.out.println("Administrators:");
			System.out.println();
			for (User user : connector.getUsers(User.ADMINISTRATOR)) {
				System.out.println(" " + user.getIdentifier() + ": " + user.getMail() + ", " + user.getName());
			}

			System.out.println();
			System.out.println("Moderators:");
			System.out.println();
			for (User user : connector.getUsers(User.MODERATOR)) {
				System.out.println(" " + user.getIdentifier() + ": " + user.getMail() + ", " + user.getName());
			}

			System.out.println();
			System.out.println("Users:");
			System.out.println();
			for (User user : connector.getUsers(User.USER)) {
				System.out.println(" " + user.getIdentifier() + ": " + user.getMail() + ", " + user.getName());
			}

			System.out.println();
			System.out.println("Blocked:");
			System.out.println();
			for (User user : connector.getUsers(User.BLOCKED)) {
				System.out.println(" " + user.getIdentifier() + ": " + user.getMail() + ", " + user.getName());
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
		
		System.out.println(new String(new char[level]) + "<Category " + category.getIdentifier() + "> " + category.getName());
		
		for (Category c : category.getCategories()) {
			printCategory(level + 3, c);
		}

		for (database.Thread t : category.getThreads()) {
			printThread(level + 3, t);
		}
		
	}
	private static void printThread(int level, database.Thread thread) throws Exception {

		System.out.println(new String(new char[level]) + "<Thread " + thread.getIdentifier() + "> " + thread.getName());

		for (database.Comment c : thread.getComments()) {
			printComment(level + 3, c);
		}
		
	}
	private static void printComment(int level, Comment comment) throws Exception {

		System.out.println(new String(new char[level]) + "<Comment " + comment.getIdentifier() + "> " + comment.getContent());
		
	}
	
}
