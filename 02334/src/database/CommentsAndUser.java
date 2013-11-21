package database;



public class CommentsAndUser {
		
	private User user;
	private int comments;
	
	// New
	
	protected CommentsAndUser(User user, int comments) {
		this.user = user;
		this.comments = comments;
		
	}

	public User getUser() {
		return user;
	}


	public int getComments() {
		return comments;
	}

	
	
	
}
