package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import database.Category;
import database.Comment;
import database.Connector;
import database.Thread;
import database.User;

public class Databse {
	private Connector connector;

	@Before
	public void createConnector() throws Exception {

		connector = new Connector("sql-lab1.cc.dtu.dk", 3306, "s123115",
				"s123115", "F5iCtVPs4rtHu4oM");
		connector.reset();
	}

	@After
	public void closeConnector() {
		connector.close();
	}

	@Test
	public void createUser() throws Exception {
		connector.createUser("unit@test.com", "UNIT", "bruger123", User.USER);
		User user1 = connector.getUser("UNIT");
		assertNotNull(user1);
		assertEquals("UNIT", user1.getName());
		assertEquals(User.USER, user1.getType());
		assertEquals("unit@test.com", user1.getMail());
		assertTrue(user1.checkPassword("bruger123"));
		assertFalse(user1.checkPassword("bruger12345"));
	}

	@Test
	public void createCategory() throws Exception {

		connector.createCategory("Animals", null);

		database.Category category1 = connector.getCategory(null, "Animals");

		connector.createCategory("Cats", category1);
		database.Category category2 = connector.getCategory(category1, "Cats");
		assertNotNull(category1);
		assertNotNull(category2);
		assertEquals("Animals", category1.getName());
		assertEquals("Cats", category2.getName());
		assertNull(category1.getParent());
		assertEquals(category1.getIdentifier(), category2.getParent()
				.getIdentifier());

	}

	@Test
	public void createThread() throws Exception {
		connector.createUser("createThread@test.com", "CreateThread",
				"password", User.USER);
		connector.createCategory("createThread", null);
		User user = connector.getUser("CreateThread");
		Category category = connector.getCategory(null, "createthread");
		connector.createThread(user, category, "CreateThread", true, true);
		database.Thread thread = connector.getThread(category, "createthread");

		assertNotNull(thread);
		assertEquals(user.getIdentifier(), thread.getUser().getIdentifier());
		assertEquals(category.getIdentifier(), thread.getCategory().getIdentifier());
		assertEquals("createThread@test.com","CreateThread","createThread"  );

	}

	@Test
	public void createComment() throws Exception {
		
		connector.createUser("createComment@test.com", "CreateComment",
				"password", User.USER);
		connector.createCategory("createcomment", null);
		User user = connector.getUser("CreateComment");
		database.Category category = connector.getCategory(null, "createcomment");
		database.Thread thread = connector.getThread(category, "createthread");
		connector.createComment(user, thread, "CreateComment");
		database.Comment comment = connector.getComments(thread).get(0);
		assertNotNull(comment);
		assertEquals("createCommet@test.com", "CreateComment", "createcomment");
	}
}