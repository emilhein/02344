package web;

import database.User;

public class Page {

	private String title;
	private String filename;
	private int access;
	
	// New

	public Page(String title, String filename) {
		
		this.title = title;
		this.filename = filename;
		this.access = Integer.MAX_VALUE;
		
	}
	public Page(String title, String filename, int access) {
		
		this.title = title;
		this.filename = filename;
		this.access = access;
		
	}
	
	// Properties
	
	public String getTitle() {
		
		return title;
	}
	public String getFilename() {
		
		return filename;
	}
	public String getPath() {
		
		return "WEB-INF/" + filename + ".jsp";
	}

	// Functions
	
	public boolean checkAccess(User user) {
		
		int level = Integer.MAX_VALUE;
		
		if (user != null) {
			level = user.getType();
		}
		
		return level <= access;
	}
	
}
