package database;

import java.util.List;

public class Category {

	private Connector connector;
	private int identifier;
	private String name;
	private int parent;
	
	// New
	
	protected Category(Connector connector, int identifier, String name, int parent) {
		
		this.connector = connector;
		this.identifier = identifier;
		this.name = name;
		this.parent = parent;
		
	}
	
	// Properties
	
	public int getIdentifier() {
		
		return identifier;
	}
	public String getName() {
		
		return name;
	}
	public Category getParent() throws Exception {
		
		if (parent == 0) {
			return null;
		} else {
			return connector.getCategory(parent);
		}

	}
	public List<Category> getCategories() throws Exception {
		
		return connector.getCategories(this);
	}
	public List<Thread> getThreads() throws Exception {
		
		return connector.getThreads(this);
	}
	
	public void setName(String name) throws Exception {
		
		Tools.validateCategoryName(name);
		
		connector.updateCategory(identifier, name, parent);
		
		this.name = name;
		
	}
	public void setParent(Category parent) throws Exception {
		
		if (parent == null) {
			
			connector.updateCategory(identifier, name, 0);
			this.parent = 0;
		
		} else {
		
			connector.updateCategory(identifier, name, parent.getIdentifier());
			this.parent = parent.getIdentifier();
		
		}
		
	}
	
}
