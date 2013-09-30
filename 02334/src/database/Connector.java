package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Connector {
	
	// ############################# new Connector("sql-lab1.cc.dtu.dk", 3306, "s123115", "s123115", "F5iCtVPs4rtHu4oM")
	
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
	
	// Functions
	
	public void reset() throws Exception {

		Statement statement = null;
		
		try {

			statement = connection.createStatement();
		
			// Drop

			try {
				statement.executeUpdate("DROP TABLE comments;");
			} catch (Exception e) {
			}
			try {
				statement.executeUpdate("DROP TABLE threads;");
			} catch (Exception e) {
			}
			try {
				statement.executeUpdate("DROP TABLE categories;");
			} catch (Exception e) {
			}
			try {
				statement.executeUpdate("DROP TABLE users;");
			} catch (Exception e) {
			}

			// Create
			
			statement.executeUpdate("CREATE TABLE users (identifier INTEGER NOT NULL AUTO_INCREMENT, " +
														"mail VARCHAR(50) NOT NULL UNIQUE, " +
														"name VARCHAR(50) NOT NULL UNIQUE, " +
														"password VARBINARY(32) NOT NULL, " +
														"type INTEGER NOT NULL, " +
														"PRIMARY KEY (identifier));");
			
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
			
			statement.executeUpdate("INSERT INTO users (mail, name, password, type) VALUES ('administrator@test.com', 'Administrator', 'a', 0);"); // TODO: Ændre password til hash!
			statement.executeUpdate("INSERT INTO users (mail, name, password, type) VALUES ('moderator@test.com', 'Moderator', 'm', 1);"); // TODO: Ændre password til hash!
			statement.executeUpdate("INSERT INTO users (mail, name, password, type) VALUES ('bruger@test.com', 'Bruger', 'b', 2);"); // TODO: Ændre password til hash!
			
			statement.executeUpdate("INSERT INTO categories (name, parent) VALUES ('Hovedkategori', NULL);");
			statement.executeUpdate("INSERT INTO categories (name, parent) VALUES ('Underkategori', 1);");
			
			statement.executeUpdate("INSERT INTO threads (user, category, name, sticky, closed) VALUES (1, 2, 'Fremhævet og lukket tråd', TRUE, TRUE);");
			statement.executeUpdate("INSERT INTO threads (user, category, name, sticky, closed) VALUES (1, 2, 'Fremhævet tråd', TRUE, FALSE);");
			statement.executeUpdate("INSERT INTO threads (user, category, name, sticky, closed) VALUES (3, 2, 'Lukket tråd', FALSE, TRUE);");
			statement.executeUpdate("INSERT INTO threads (user, category, name, sticky, closed) VALUES (5, 2, 'Normal tråd', FALSE, FALSE);");
			
			statement.executeUpdate("INSERT INTO comments (user, thread, content) VALUES (1, 1, 'Tekst i første tråd.');");
			statement.executeUpdate("INSERT INTO comments (user, thread, content) VALUES (1, 2, 'Tekst i anden tråd.');");
			statement.executeUpdate("INSERT INTO comments (user, thread, content) VALUES (3, 3, 'Tekst i tredje tråd.');");
			statement.executeUpdate("INSERT INTO comments (user, thread, content) VALUES (5, 4, 'Tekst i fjerde tråd.');");
			
		} finally {
			statement.close();	
		}
		
	}
	
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
			resultSet.close();
			statement.close();
		}
		
	}
	
	protected void createUser() {
		
	}
	
	protected void update(User user) throws Exception {
		
		PreparedStatement statement = null;
		
		try {
			statement = connection.prepareStatement("UPDATE users SET mail = ?, name = ?, password = ?, type = ? WHERE identifier = ?;");
			statement.setString(1, user.getMail());
			statement.setString(2, user.getName());
			statement.setBytes(3, user.getPassword());
			statement.setInt(4, user.getType());
			statement.setInt(5, user.getIdentifier());
			statement.executeUpdate();
		} finally {
			statement.close();
		}
		
	}
	
}