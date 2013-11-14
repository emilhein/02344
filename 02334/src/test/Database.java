package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import database.Category;
import database.Connector;
import database.User;

public class Database {
	private Connector connector;

	@Before
	public void before() throws Exception {

		connector = new Connector("sql-lab1.cc.dtu.dk", 3306, "s123115", "s123115", "F5iCtVPs4rtHu4oM");
		connector.reset();
		
	}

	@After
	public void after() {
		
		connector.close();
	
	}

	@Test
	public void test() throws Exception {
		
		// User
		
		try {
			connector.getUser("testuser");
			fail("Exception not thrown.");
		} catch (Exception e) {
		}
		
		connector.createUser("test@mail.com", "testuser", "testpassword", User.USER);
		User user = connector.getUser("testuser");
		
		verify(user, "test@mail.com", "testuser", "testpassword", User.USER);
		
		user.setMail("test@mail2.com");
		user.setName("testuser2");
		user.setPassword("testpassword2");
		user.setType(User.BLOCKED);
		
		verify(user, "test@mail2.com", "testuser2", "testpassword2", User.BLOCKED);
		
		// Category
		
	}

	private void verify(User user, String mail, String name, String password, int type) throws Exception {
		
		assertNotNull(user);

		User[] users = new User[] {
				user,
				connector.getUser(user.getIdentifier()),
				connector.getUser(mail),
				connector.getUser(name)
				};
		
		for (User u : users) {
			assertNotNull(u);
			assertEquals(mail, u.getMail());
			assertEquals(name, u.getName());
			assertTrue(u.checkPassword(password));
			assertFalse(u.checkPassword("wrong" + password));
			assertEquals(type, u.getType());	
		}
		
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
		assertEquals(category.getIdentifier(), thread.getCategory()
				.getIdentifier());
		assertEquals("CreateThread", thread.getName());
		assertTrue(thread.getSticky());
		assertTrue(thread.getClosed());
	}

	@Test
	public void createComment() throws Exception {

		connector.createUser("createComment@test.com", "CreateComment",
				"password", User.USER);
		connector.createCategory("createcomment", null);
		User user = connector.getUser("CreateComment");
		database.Category category = connector.getCategory(null,
				"createcomment");
		connector.createThread(user, category, "CreateComment", true, true);
		database.Thread thread = connector.getThread(category, "CreateComment");
		connector.createComment(user, thread, "CreateComment");
		database.Comment comment = connector.getComments(thread).get(0);
		assertNotNull(comment);
		assertEquals("CreateComment", comment.getContent());
		assertEquals(user.getIdentifier(), comment.getUser().getIdentifier());
		assertEquals(thread.getIdentifier(), comment.getThread()
				.getIdentifier());
	}
}