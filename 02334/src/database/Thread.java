package database;

import java.sql.Timestamp;
import java.util.List;

public class Thread {

	private Connector connector;
	private int identifier;
	private int user;
	private int category;
	private String name;
	private boolean sticky;
	private boolean closed;
	private Timestamp created;
	
	// New
	
	protected Thread(Connector connector, int identifier, int user, int category, String name, boolean sticky, boolean closed, Timestamp created) {
		
		this.connector = connector;
		this.identifier = identifier;
		this.user = user;
		this.category = category;
		this.name = name;
		this.sticky = sticky;
		this.closed = closed;
		this.created = created;
		
	}
	
	// Properties
	
	public int getIdentifier() {
		
		return identifier;
	}
	public User getUser() throws Exception {
		
		return connector.getUser(user);
	}
	public Category getCategory() throws Exception {
		
		return connector.getCategory(category);
	}
	public String getName() {
		
		return name;
	}
	public boolean getSticky() {
		
		return sticky;
	}
	public boolean getClosed() {
		
		return closed;
	}
	public Timestamp getCreated() {
		
		return created;
	}
	public List<Comment> getComments() throws Exception {
		
		return connector.getComments(this);
	}
	
	public void setCategory(Category category) throws Exception {

		connector.updateThread(identifier, category.getIdentifier(), name, sticky, closed);
		
		this.category = category.getIdentifier();
		
	}
	public void setName(String name) throws Exception {
		
		Tools.validateThreadName(name);
		
		connector.updateThread(identifier, category, name, sticky, closed);
		
		this.name = name;
		
	}
	public void setSticky(boolean sticky) throws Exception {
		
		connector.updateThread(identifier, category, name, sticky, closed);
		
		this.sticky = sticky;
		
	}
	public void setClosed(boolean closed) throws Exception {
		
		connector.updateThread(identifier, category, name, sticky, closed);
		
		this.closed = closed;
		
	}
	
}
