package database;

import java.sql.Timestamp;

public class Comment {

	private Connector connector;
	private int identifier;
	private int user;
	private int thread;
	private String content;
	private Timestamp changed;
	
	// New
	
	protected Comment(Connector connector, int identifier, int user, int thread, String content, Timestamp changed) {
		
		this.connector = connector;
		this.identifier = identifier;
		this.user = user;
		this.thread = thread;
		this.content = content;
		this.changed = changed;
		
	}
	
	// Properties
	
	public int getIdentifier() {
		
		return identifier;
	}
	public User getUser() throws Exception {
		
		return connector.getUser(user);
	}
	public Thread getThread() throws Exception {
		
		return connector.getThread(thread);
	}
	public String getContent() {
		
		return content;
	}
	public Timestamp getChanged() {
		
		// Kræver at objektet bliver genindlæst fra databasen for at blive opdateret.
		
		return changed;
	}
	
	public void setContent(String content) throws Exception {
		
		Tools.validateCommentContent(content);
		
		connector.updateComment(content, identifier);
		
		this.content = content; 
		
	}
	
}
