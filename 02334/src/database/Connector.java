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
	
	public List<User> getUsers() throws Exception {
		
		Statement statement = null;
		ResultSet resultSet = null;
		
		try {
			
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM users;");
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
	public List<Category> getCategories() throws Exception {
		
		Statement statement = null;
		ResultSet resultSet = null;
		
		try {
			
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM categories;");
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
	public List<Thread> getThreads() throws Exception {
		
		Statement statement = null;
		ResultSet resultSet = null;
		
		try {
			
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM threads;");
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
	public List<Thread> getThreads(Category category) throws Exception {
		
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		try {

			statement = connection.prepareStatement("SELECT * FROM threads WHERE category = ?;");
			statement.setInt(1, category.getIdentifier());
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
															 "parent INTEGER NOT NULL, " +
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
			
			createUser("administrator@test.com", "Administrator", "a1234567", 0); // TODO: Lav unit tests
			createUser("moderator@test.com", "Moderator", "m1234567", 1);
			createUser("bruger@test.com", "Bruger", "b1234567", 2);

			createCategory("Hovedkategori 1", null); // TODO: Lav unit tests
			createCategory("Hovedkategori 2", null);
			createCategory("Hovedkategori 3", null);
			createCategory("Underkategori 1", getCategory(1));
			createCategory("Underkategori 2", getCategory(1));
			createCategory("Underkategori 3", getCategory(1));

			createThread(getUser(1), getCategory(1), "Fremhævet og lukket tråd", true, true);
			createThread(getUser(2), getCategory(1), "Fremhævet tråd", true, false);
			createThread(getUser(3), getCategory(2), "Lukket tråd", false, true);
			createThread(getUser(3), getCategory(2), "Normal tråd", false, false);
			
			statement.executeUpdate("INSERT INTO comments (user, thread, content) VALUES (1, 1, 'Tekst i første tråd.');");
			statement.executeUpdate("INSERT INTO comments (user, thread, content) VALUES (2, 2, 'Tekst i anden tråd.');");
			statement.executeUpdate("INSERT INTO comments (user, thread, content) VALUES (3, 3, 'Tekst i tredje tråd.');");
			statement.executeUpdate("INSERT INTO comments (user, thread, content) VALUES (3, 4, 'Tekst i fjerde tråd.');");
			
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
	
}
