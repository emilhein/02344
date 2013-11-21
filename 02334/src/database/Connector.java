package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Connector {
	
	private Connection connection;
	
	// New
	
	public Connector(String server, int port, String database, String username, String password) throws Exception {
		
		try {
			connection = DriverManager.getConnection("jdbc:mysql://" + server + ":" + port + "/" + database, username, password);
		} catch (Exception ex) {
			close();
			throw ex;
		}
		
	}
	
	// Close
	
	public void close() {

		try {
			connection.close();
		} catch (Exception ex) {
		}
		
	}
	
	// Properties
	
	public List<User> getUsers(int type) throws Exception {

		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		try {

			statement = connection.prepareStatement("SELECT * FROM users WHERE type = ?;");
			statement.setInt(1, type);
			resultSet = statement.executeQuery();
			List<User> list = new ArrayList<User>();

			while (resultSet.next()) {
				list.add(new User(this, resultSet.getInt("identifier"), resultSet.getString("mail"), resultSet.getString("name"), resultSet.getBytes("password"), resultSet.getInt("type")));
			}
			
			return list;
			
		} finally {
			try {
				resultSet.close();
			} catch (Exception ex) {
			}
			try {
				statement.close();
			} catch (Exception ex) {
			}
		}
		
	}
	public List<Category> getCategories(Category parent) throws Exception {

		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		try {

			statement = connection.prepareStatement("SELECT * FROM categories WHERE parent = ?;");
			statement.setInt(1, parent == null ? 0 : parent.getIdentifier());
			resultSet = statement.executeQuery();
			List<Category> list = new ArrayList<Category>();

			while (resultSet.next()) {
				list.add(new Category(this, resultSet.getInt("identifier"), resultSet.getString("name"), resultSet.getInt("parent")));
			}
			
			return list;
			
		} finally {
			try {
				resultSet.close();
			} catch (Exception ex) {
			}
			try {
				statement.close();
			} catch (Exception ex) {
			}
		}
		
	}
	public List<Thread> getThreads(Category category) throws Exception {
		
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		try {

			statement = connection.prepareStatement("SELECT * FROM threads WHERE category = ?;"); // TODO: Sort by changed.
			statement.setInt(1, category == null ? 0 : category.getIdentifier());
			resultSet = statement.executeQuery();
			List<Thread> list = new ArrayList<Thread>();

			while (resultSet.next()) {
				list.add(new Thread(this, resultSet.getInt("identifier"), resultSet.getInt("user"), resultSet.getInt("category"), resultSet.getString("name"), resultSet.getBoolean("sticky"), resultSet.getBoolean("closed"), resultSet.getTimestamp("created")));
			}
			
			return list;
			
		} finally {
			try {
				resultSet.close();
			} catch (Exception ex) {
			}
			try {
				statement.close();
			} catch (Exception ex) {
			}
		}
		
	}
	public List<Comment> getComments(Thread thread) throws Exception {
		
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		try {

			statement = connection.prepareStatement("SELECT * FROM comments WHERE thread = ?;"); // TODO: Sort by identifier.
			statement.setInt(1, thread.getIdentifier());
			resultSet = statement.executeQuery();
			List<Comment> list = new ArrayList<Comment>();

			while (resultSet.next()) {
				list.add(new Comment(this, resultSet.getInt("identifier"), resultSet.getInt("user"), resultSet.getInt("thread"), resultSet.getString("content"), resultSet.getTimestamp("changed")));
			}
			
			return list;
			
		} finally {
			try {
				resultSet.close();
			} catch (Exception ex) {
			}
			try {
				statement.close();
			} catch (Exception ex) {
			}
		}
		
	}
	
	public User getUser(int identifier) throws Exception {

		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		try {

			statement = connection.prepareStatement("SELECT * FROM users WHERE identifier = ?;");
			statement.setInt(1, identifier);
			resultSet = statement.executeQuery();

			if (!resultSet.next()) {
				throw new Exception("Cannot find a user where identifier is '" + identifier + "'.");
			}
			
			return new User(this, resultSet.getInt("identifier"), resultSet.getString("mail"), resultSet.getString("name"), resultSet.getBytes("password"), resultSet.getInt("type"));
			
		} finally {
			try {
				resultSet.close();
			} catch (Exception ex) {
			}
			try {
				statement.close();
			} catch (Exception ex) {
			}
		}
		
	}
	public User getUser(String mailOrName) throws Exception {

		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		try {

			statement = connection.prepareStatement("SELECT * FROM users WHERE mail = ? OR name = ?;");
			statement.setString(1, mailOrName);
			statement.setString(2, mailOrName);
			resultSet = statement.executeQuery();

			if (!resultSet.next()) {
				throw new Exception("Cannot find a user where mail or name is '" + mailOrName + "'.");
			}
			
			return new User(this, resultSet.getInt("identifier"), resultSet.getString("mail"), resultSet.getString("name"), resultSet.getBytes("password"), resultSet.getInt("type"));
			
		} finally {
			try {
				resultSet.close();
			} catch (Exception ex) {
			}
			try {
				statement.close();
			} catch (Exception ex) {
			}
		}
		
	}
	public Category getCategory(int identifier) throws Exception {

		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		try {

			statement = connection.prepareStatement("SELECT * FROM categories WHERE identifier = ?;");
			statement.setInt(1, identifier);
			resultSet = statement.executeQuery();

			if (!resultSet.next()) {
				throw new Exception("Cannot find a category where identifier is '" + identifier + "'.");
			}
			
			return new Category(this, resultSet.getInt("identifier"), resultSet.getString("name"), resultSet.getInt("parent"));
			
		} finally {
			try {
				resultSet.close();
			} catch (Exception ex) {
			}
			try {
				statement.close();
			} catch (Exception ex) {
			}
		}
		
	}
	public Category getCategory(Category parent, String name) throws Exception {

		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		try {

			statement = connection.prepareStatement("SELECT * FROM categories WHERE parent = ? AND name = ?;");
			statement.setInt(1, parent == null ? 0 : parent.getIdentifier());
			statement.setString(2, name);
			resultSet = statement.executeQuery();

			if (!resultSet.next()) {
				throw new Exception("Cannot find a category where parent is '" + parent.getIdentifier() + "' and name is '" + name + "'.");
			}
			
			return new Category(this, resultSet.getInt("identifier"), resultSet.getString("name"), resultSet.getInt("parent"));
			
		} finally {
			try {
				resultSet.close();
			} catch (Exception ex) {
			}
			try {
				statement.close();
			} catch (Exception ex) {
			}
		}
		
	}
	public Thread getThread(int identifier) throws Exception {

		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		try {

			statement = connection.prepareStatement("SELECT * FROM threads WHERE identifier = ?;");
			statement.setInt(1, identifier);
			resultSet = statement.executeQuery();

			if (!resultSet.next()) {
				throw new Exception("Cannot find a thread where identifier is '" + identifier + "'.");
			}
			
			return new Thread(this, resultSet.getInt("identifier"), resultSet.getInt("user"), resultSet.getInt("category"), resultSet.getString("name"), resultSet.getBoolean("sticky"), resultSet.getBoolean("closed"), resultSet.getTimestamp("created"));
			
		} finally {
			try {
				resultSet.close();
			} catch (Exception ex) {
			}
			try {
				statement.close();
			} catch (Exception ex) {
			}
		}
		
	}
	public Thread getThread(Category category, String name) throws Exception {

		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		try {

			statement = connection.prepareStatement("SELECT * FROM threads WHERE category = ? AND name = ?;");
			statement.setInt(1, category == null ? 0 : category.getIdentifier());
			statement.setString(2, name);
			resultSet = statement.executeQuery();

			if (!resultSet.next()) {
				throw new Exception("Cannot find a thread where category is '" + category.getIdentifier() + "' and name is '" + name + "'.");
			}
			
			return new Thread(this, resultSet.getInt("identifier"), resultSet.getInt("user"), resultSet.getInt("category"), resultSet.getString("name"), resultSet.getBoolean("sticky"), resultSet.getBoolean("closed"), resultSet.getTimestamp("created"));
			
		} finally {
			try {
				resultSet.close();
			} catch (Exception ex) {
			}
			try {
				statement.close();
			} catch (Exception ex) {
			}
		}
		
	}
	public Comment getComment(int identifier) throws Exception {

		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		try {

			statement = connection.prepareStatement("SELECT * FROM comments WHERE identifier = ?;");
			statement.setInt(1, identifier);
			resultSet = statement.executeQuery();

			if (!resultSet.next()) {
				throw new Exception("Cannot find a comment where identifier is '" + identifier + "'.");
			}
			
			return new Comment(this, resultSet.getInt("identifier"), resultSet.getInt("user"), resultSet.getInt("thread"), resultSet.getString("content"), resultSet.getTimestamp("changed"));
			
		} finally {
			try {
				resultSet.close();
			} catch (Exception ex) {
			}
			try {
				statement.close();
			} catch (Exception ex) {
			}
		}
		
	}

	public int getUserCount() throws Exception {

		Statement statement = null;
		ResultSet resultSet = null;
		
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT COUNT(*) FROM users;");
			resultSet.next();
			return resultSet.getInt(1);
		} finally {
			try {
				resultSet.close();
			} catch (Exception ex) {
			}
			try {
				statement.close();
			} catch (Exception ex) {
			}
		}
		
	}
	public int getCategoryCount() throws Exception {

		Statement statement = null;
		ResultSet resultSet = null;
		
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT COUNT(*) FROM categories;");
			resultSet.next();
			return resultSet.getInt(1);
		} finally {
			try {
				resultSet.close();
			} catch (Exception ex) {
			}
			try {
				statement.close();
			} catch (Exception ex) {
			}
		}
		
	}
	public int getThreadCount() throws Exception {

		Statement statement = null;
		ResultSet resultSet = null;
		
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT COUNT(*) FROM threads;");
			resultSet.next();
			return resultSet.getInt(1);
		} finally {
			try {
				resultSet.close();
			} catch (Exception ex) {
			}
			try {
				statement.close();
			} catch (Exception ex) {
			}
		}
		
	}
	public int getCommentCount() throws Exception {

		Statement statement = null;
		ResultSet resultSet = null;
		
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT COUNT(*) FROM comments;");
			resultSet.next();
			return resultSet.getInt(1);
		} finally {
			try {
				resultSet.close();
			} catch (Exception ex) {
			}
			try {
				statement.close();
			} catch (Exception ex) {
			}
		}
		
	}
	
	public int getCategoryCount(Category category) throws Exception {

		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		try {
			statement = connection.prepareStatement("SELECT COUNT(*) FROM categories WHERE parent = ?;");
			statement.setInt(1, category.getIdentifier());
			resultSet = statement.executeQuery();
			resultSet.next();
			return resultSet.getInt(1);
		} finally {
			try {
				resultSet.close();
			} catch (Exception ex) {
			}
			try {
				statement.close();
			} catch (Exception ex) {
			}
		}
		
	}
	public int getThreadCount(Category category) throws Exception {

		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		try {
			statement = connection.prepareStatement("SELECT COUNT(*) FROM threads WHERE category = ?;");
			statement.setInt(1, category.getIdentifier());
			resultSet = statement.executeQuery();
			resultSet.next();
			return resultSet.getInt(1);
		} finally {
			try {
				resultSet.close();
			} catch (Exception ex) {
			}
			try {
				statement.close();
			} catch (Exception ex) {
			}
		}
		
	}
	public int getCommentCount(Thread thread) throws Exception {
		
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		try {
			statement = connection.prepareStatement("SELECT COUNT(*) FROM comments WHERE thread = ?;");
			statement.setInt(1, thread.getIdentifier());
			resultSet = statement.executeQuery();
			resultSet.next();
			return resultSet.getInt(1);
		} finally {
			try {
				resultSet.close();
			} catch (Exception ex) {
			}
			try {
				statement.close();
			} catch (Exception ex) {
			}
		}
		
	}
	
	public int getAverageCommentLength() throws Exception {

		Statement statement = null;
		ResultSet resultSet = null;
		
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT AVG(LENGTH(content)) FROM comments;");
			resultSet.next();
			return resultSet.getInt(1);			
		} finally {
			try {
				resultSet.close();
			} catch (Exception ex) {
			}
			try {
				statement.close();
			} catch (Exception ex) {
			}
		}
		
	}

	
	public List<CommentsAndUser> getUserActivity() throws Exception {

		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		try {

			statement = connection.prepareStatement("select users.identifier, users.mail, users.name, users.password, users.type, count(users.identifier) from users,comments where users.identifier = comments.user group by users.identifier order by count(users.identifier) desc limit 5;");
			resultSet = statement.executeQuery();
			List<CommentsAndUser> list = new ArrayList<CommentsAndUser>();

			while (resultSet.next()) {
				list.add(new CommentsAndUser(new User(this, resultSet.getInt("identifier"), resultSet.getString("mail"), resultSet.getString("name"), resultSet.getBytes("password"), resultSet.getInt("type")), resultSet.getInt(6)));

			}
			
			return list;
			
		} finally {
			try {
				resultSet.close();
			} catch (Exception ex) {
			}
			try {
				statement.close();
			} catch (Exception ex) {
			}
		}
		
	}
	
	// Functions
	
	public void reset() throws Exception {

		Statement statement = null;
		
		try {

			statement = connection.createStatement();
		
			// Drop

			try {
				statement.executeUpdate("DROP TABLE comments;");
			} catch (Exception ex) {
			}
			try {
				statement.executeUpdate("DROP TABLE threads;");
			} catch (Exception ex) {
			}
			try {
				statement.executeUpdate("DROP TABLE categories;");
			} catch (Exception ex) {
			}
			try {
				statement.executeUpdate("DROP TABLE users;");
			} catch (Exception ex) {
			}

			// Create
			
			statement.executeUpdate("CREATE TABLE users (identifier INTEGER NOT NULL AUTO_INCREMENT, " +
														"mail VARCHAR(50) NOT NULL UNIQUE, " +
														"name VARCHAR(50) NOT NULL UNIQUE, " +
														"password VARBINARY(32) NOT NULL, " +
														"type INTEGER NOT NULL, " +
														"PRIMARY KEY (identifier), " +
													    "UNIQUE (mail, name));");
			
			statement.executeUpdate("CREATE TABLE categories (identifier INTEGER NOT NULL AUTO_INCREMENT, " +
															 "name VARCHAR(100) NOT NULL, " +
															 "parent INTEGER, " +
															 "PRIMARY KEY (identifier), " +
															 "UNIQUE (name, parent), " +
															 "FOREIGN KEY (parent) REFERENCES categories(identifier));");
			
			statement.executeUpdate("CREATE TABLE threads (identifier INTEGER NOT NULL AUTO_INCREMENT, " +
														  "user INTEGER NOT NULL, " +
														  "category INTEGER NOT NULL, " + 
														  "name VARCHAR(100) NOT NULL, " +
														  "sticky BOOLEAN NOT NULL, " +
														  "closed BOOLEAN NOT NULL, " +
														  "created TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, " +
														  "PRIMARY KEY (identifier), " +
														  "FOREIGN KEY (user) REFERENCES users(identifier), " +
														  "FOREIGN KEY (category) REFERENCES categories(identifier));");
			
			statement.executeUpdate("CREATE TABLE comments (identifier INTEGER NOT NULL AUTO_INCREMENT, " +
														   "user INTEGER NOT NULL, " +
														   "thread INTEGER NOT NULL, " +
														   "content VARCHAR(1000) NOT NULL, " +
														   "changed TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, " +
														   "PRIMARY KEY (identifier), " +
														   "FOREIGN KEY (user) REFERENCES users(identifier), " +
														   "FOREIGN KEY (thread) REFERENCES threads(identifier));");
			
			
			// Insert
			
			createUser("administrator@test.com", "Administrator", "administrator123", User.ADMINISTRATOR);
			createUser("administrator2@test.com", "Administrator2", "administrator123", User.ADMINISTRATOR);
			createUser("administrator3@test.com", "Administrator3", "administrator123", User.ADMINISTRATOR);
			
			createUser("moderator@test.com", "Moderator", "moderator123", User.MODERATOR);
			createUser("moderator2@test.com", "Moderator2", "moderator123", User.MODERATOR);
			createUser("moderator3@test.com", "Moderator3", "moderator123", User.MODERATOR);
			
			createUser("bruger@test.com", "Bruger", "bruger123", User.USER);
			createUser("bruger2@test.com", "Bruger2", "bruger123", User.USER);
			createUser("bruger3@test.com", "Bruger3", "bruger123", User.USER);
			
			createUser("blokeret@test.com", "Blokeret", "blokeret123", User.BLOCKED);
			createUser("blokeret2@test.com", "Blokeret2", "blokeret123", User.BLOCKED);
			createUser("blokeret3@test.com", "Blokeret3", "blokeret123", User.BLOCKED);
			
			createCategory("Hovedkategori", null);
			createCategory("Tom hovedkategori", null);
			createCategory("Underkategori", getCategory(1));
			createCategory("Tom underkategori", getCategory(1));
			createCategory("Underunderkategori", getCategory(3));
			createCategory("Tom underunderkategori", getCategory(3));

			createThread(getUser("Administrator"), getCategory(1), "Fremhævet og lukket tråd", true, true);
			createThread(getUser("Moderator"), getCategory(3), "Fremhævet tråd", true, false);
			createThread(getUser("Bruger"), getCategory(5), "Lukket tråd", false, true);
			createThread(getUser("Blokeret"), getCategory(5), "Tråd", false, false);
			
			createComment(getUser("Administrator"), getThread(1), "Tekst i fremhævet og lukket tråd.");
			createComment(getUser("Moderator"), getThread(2), "Tekst i fremhævet tråd.");
			createComment(getUser("Bruger"), getThread(3), "Tekst i lukket tråd.");
			createComment(getUser("Blokeret"), getThread(4), "Tekst i tråd.");
			
			statement.executeUpdate("CREATE TRIGGER blockuser AFTER INSERT ON comments " +
					"FOR EACH ROW UPDATE users SET type = 3 WHERE identifier = NEW.user AND EXISTS " +
					"(SELECT * FROM (SELECT TIMEDIFF(MAX(changed), MIN(changed)) AS timedifference FROM " +
					"(SELECT * FROM comments WHERE user = NEW.user ORDER BY changed DESC LIMIT 10) AS newest HAVING COUNT(*) >= 10) AS difference WHERE timedifference < '00:01:00');");

			
		} finally {
			try {
				statement.close();
			} catch (Exception ex) {
			}
		}
		
	}
	
	public void createUser(String mail, String name, String password, int type) throws Exception {

		Tools.validateUserMail(mail);
		Tools.validateUserName(name);
		Tools.validateUserPassword(password);
		Tools.validateUserType(type);
		
		PreparedStatement statement = null;
		
		try {
			statement = connection.prepareStatement("INSERT INTO users (mail, name, password, type) VALUES (?, ?, ?, ?);");
			statement.setString(1, mail);
			statement.setString(2, name);
			statement.setBytes(3, Tools.hash(password));
			statement.setInt(4, type);
			statement.executeUpdate();
		} finally {
			try {
				statement.close();
			} catch (Exception ex) {
			}
		}
		
	}
	public void createCategory(String name, Category parent) throws Exception {

		Tools.validateCategoryName(name);
		
		PreparedStatement statement = null;
		
		try {
			statement = connection.prepareStatement("INSERT INTO categories (name, parent) VALUES (?, ?);");
			statement.setString(1, name);
			statement.setInt(2, parent == null ? 0 : parent.getIdentifier());
			statement.executeUpdate();
		} finally {
			try {
				statement.close();
			} catch (Exception ex) {
			}
		}
		
	}
	public void createThread(User user, Category category, String name, boolean sticky, boolean closed) throws Exception {

		Tools.validateThreadName(name);
		
		PreparedStatement statement = null;
		
		try {
			statement = connection.prepareStatement("INSERT INTO threads (user, category, name, sticky, closed) VALUES (?, ?, ?, ?, ?);");
			statement.setInt(1, user.getIdentifier());
			statement.setInt(2, category.getIdentifier());
			statement.setString(3, name);
			statement.setBoolean(4, sticky);
			statement.setBoolean(5, closed);
			statement.executeUpdate();
		} finally {
			try {
				statement.close();
			} catch (Exception ex) {
			}
		}
		
	}
	public void createComment(User user, Thread thread, String content) throws Exception {

		Tools.validateCommentContent(content);
		
		PreparedStatement statement = null;
		
		try {
			statement = connection.prepareStatement("INSERT INTO comments (user, thread, content) VALUES (?, ?, ?);");
			statement.setInt(1, user.getIdentifier());
			statement.setInt(2, thread.getIdentifier());
			statement.setString(3, content);
			statement.executeUpdate();
		} finally {
			try {
				statement.close();
			} catch (Exception ex) {
			}
		}
		
	}
	
	protected void updateUser(int identifier, String mail, String name, byte[] password, int type) throws Exception {
		
		PreparedStatement statement = null;
		
		try {
			statement = connection.prepareStatement("UPDATE users SET mail = ?, name = ?, password = ?, type = ? WHERE identifier = ?;");
			statement.setString(1, mail);
			statement.setString(2, name);
			statement.setBytes(3, password);
			statement.setInt(4, type);
			statement.setInt(5, identifier);
			statement.executeUpdate();
		} finally {
			try {
				statement.close();
			} catch (Exception ex) {
			}
		}
		
	}
	protected void updateCategory(int identifier, String name, int parent) throws Exception {
		
		PreparedStatement statement = null;
		
		try {
			statement = connection.prepareStatement("UPDATE categories SET name = ?, parent = ? WHERE identifier = ?;");
			statement.setString(1, name);
			statement.setInt(2, parent);
			statement.setInt(3, identifier);
			statement.executeUpdate();
		} finally {
			try {
				statement.close();
			} catch (Exception ex) {
			}
		}
		
	}
	protected void updateThread(int identifier, int category, String name, boolean sticky, boolean closed) throws Exception {
		
		PreparedStatement statement = null;
		
		try {
			statement = connection.prepareStatement("UPDATE threads SET category = ?, name = ?, sticky = ?, closed = ? WHERE identifier = ?;");
			statement.setInt(1, category);
			statement.setString(2, name);
			statement.setBoolean(3, sticky);
			statement.setBoolean(4, closed);
			statement.setInt(5, identifier);
			statement.executeUpdate();
		} finally {
			try {
				statement.close();
			} catch (Exception ex) {
			}
		}
		
	}
	protected void updateComment(int identifier, String content) throws Exception {
		
		PreparedStatement statement = null;
		
		try {
			statement = connection.prepareStatement("UPDATE comments SET content = ? WHERE identifier = ?;");
			statement.setString(1, content);
			statement.setInt(2, identifier);
			statement.executeUpdate();
		} finally {
			try {
				statement.close();
			} catch (Exception ex) {
			}
		}
		
	}

}
