package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Connector {
	
	// ############################# new Connector("sql-lab1.cc.dtu.dk", 3306, "s123115", "s123115", "F5iCtVPs4rtHu4oM")
	
	private Connection connection;
	private Statement statement;
	
	// New
	
	public Connector(String server, int port, String database, String username, String password) throws Exception {
		
		try {
			connection = DriverManager.getConnection("jdbc:mysql://" + server + ":" + port + "/" + database, username, password);
			statement = connection.createStatement();
		} catch (Exception ex) {
			close();
		}
		
	}
	
	// Close
	
	public void close() {

		try {
			connection.close();
		} catch (Exception ex) {
		}
		try {
			statement.close();
		} catch (Exception ex) {
		}
		
	}
	
	// Functions
	
	public void reset() throws Exception {

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
													"name VARCHAR(20) NOT NULL UNIQUE, " + 
													"password VARCHAR(20) NOT NULL, " + 
													"type INTEGER NOT NULL, " + 
													"PRIMARY KEY (identifier));");
		
		statement.executeUpdate("CREATE TABLE categories (identifier INTEGER NOT NULL AUTO_INCREMENT, " +
														 "name VARCHAR(100) NOT NULL, " +
														 "parent INTEGER, " +
														 "PRIMARY KEY (identifier), " +
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
		
		statement.executeUpdate("INSERT INTO users (name, password, type) VALUES ('Administrator', 'a', 1);");
		statement.executeUpdate("INSERT INTO users (name, password, type) VALUES ('Moderator', 'm', 2);");
		statement.executeUpdate("INSERT INTO users (name, password, type) VALUES ('Bruger', 'b', 3);");
		
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
		
	}
	
}