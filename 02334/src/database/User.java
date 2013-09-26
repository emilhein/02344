package database;

public class User {

	private Connector connector;
	private int identifier;
	private String name;
	private String password;
	private Type type;
	
	// New
	
	protected User(Connector connector, int identifier, String name, String password, Type type) {
		
		this.connector = connector;
		this.identifier = identifier;
		this.name = name;
		this.password = password;
		this.type = type;
		
	}
	
	// Properties
	
	public int getIdentifier() {
		
		return identifier;
	}
	public String getName() {
		
		return name;
	}
	protected String getPassword() {
		
		return password;
	}
	public Type getType() {
		
		return type;
	}
	
	public void setName(String name) throws Exception {
		
		// TODO: Validate
		
		this.name = name;
		
		connector.update(this);
		
	}
	public void setPassword(String password) throws Exception {
		
		// TODO: Validate
		
		this.password = password;

		connector.update(this);
		
	}
	public void setType(Type type) throws Exception {
		
		// TODO: Validate
		
		this.type = type;

		connector.update(this);
		
	}
	
	// Functions
	
	public boolean checkPassword(String password) {
		
		return this.password.compareTo(password) == 0;
	}
	
}
