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

	// Tests
	
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
		
		// === User ===
		
		try {
			connector.getUser("testuser");
			fail("Exception not thrown.");
		} catch (Exception e) {
		}
		
		connector.createUser("test@mail.com", "testuser", "testpassword", User.USER);
		User user = connector.getUser("testuser");
		
		try {
			user.setMail(null);
			fail("Exception not thrown.");
		} catch (Exception e) {
		}
		try {
			user.setName(null);
			fail("Exception not thrown.");
		} catch (Exception e) {
		}
		try {
			user.setPassword(null);
			fail("Exception not thrown.");
		} catch (Exception e) {
		}
		try {
			user.setType(-1);
			fail("Exception not thrown.");
		} catch (Exception e) {
		}
		
		verify(user, "test@mail.com", "testuser", "testpassword", User.USER);
		
		user.setMail("test@mail2.com");
		user.setName("testuser2");
		user.setPassword("testpassword2");
		user.setType(User.BLOCKED);
		
		verify(user, "test@mail2.com", "testuser2", "testpassword2", User.BLOCKED);

		// === Category ===

		try {
			connector.getCategory(null, "testcategory");
			fail("Exception not thrown.");
		} catch (Exception e) {
		}
		
		connector.createCategory("testcategory", null);
		Category category = connector.getCategory(null, "testcategory");
		
		try {
			category.setName(null);
			fail("Exception not thrown.");
		} catch (Exception e) {
		}
		
		verify(category, "testcategory", null, 0, 0);

		category.setName("testcategory2");
		
		verify(category, "testcategory2", null, 0, 0);
		
		// === (Subcategory) ===

		try {
			connector.getCategory(category, "testsubcategory");
			fail("Exception not thrown.");
		} catch (Exception e) {
		}
		
		connector.createCategory("testsubcategory", category);
		Category subcategory = connector.getCategory(category, "testsubcategory");
		
		try {
			subcategory.setName(null);
			fail("Exception not thrown.");
		} catch (Exception e) {
		}
		
		verify(category, "testcategory2", null, 1, 0);
		verify(subcategory, "testsubcategory", category, 0, 0);

		subcategory.setName("testsubcategory2");
		subcategory.setParent(null);
		
		verify(category, "testcategory2", null, 0, 0);
		verify(subcategory, "testsubcategory2", null, 0, 0);
		
		// === Thread ===
		
		try {
			connector.getThread(category, "testthread");
			fail("Exception not thrown.");
		} catch (Exception e) {
		}
		
		connector.createThread(user, category, "testthread", false, false);
		database.Thread thread = connector.getThread(category, "testthread");

		try {
			thread.setCategory(null);
			fail("Exception not thrown.");
		} catch (Exception e) {
		}
		try {
			thread.setName(null);
			fail("Exception not thrown.");
		} catch (Exception e) {
		}
		
		verify(category, "testcategory2", null, 0, 1);
		verify(subcategory, "testsubcategory2", null, 0, 0);
		verify(thread, user, category, "testthread", false, false, 0);
		
		thread.setCategory(subcategory);
		thread.setName("testthread2");
		thread.setSticky(true);
		thread.setClosed(true);

		verify(category, "testcategory2", null, 0, 0);
		verify(subcategory, "testsubcategory2", null, 0, 1);
		verify(thread, user, subcategory, "testthread2", true, true, 0);
		
		// === Comment ===
		
		connector.createComment(user, thread, "testcomment");
		database.Comment comment = connector.getComments(thread).get(0);

		try {
			comment.setContent(null);
			fail("Exception not thrown.");
		} catch (Exception e) {
		}

		verify(thread, user, subcategory, "testthread2", true, true, 1);
		verify(comment, user, thread, "testcomment");

		comment.setContent("testcomment2");

		verify(comment, user, thread, "testcomment2");
		
	}

	// Functions
	
	private void verify(User user, String mail, String name, String password, int type) throws Exception {
		
		assertNotNull(user);
		
		int identifier = user.getIdentifier();

		User[] users = new User[] {
				user,
				connector.getUser(identifier),
				connector.getUser(mail),
				connector.getUser(name)
				};
		
		for (User u : users) {
			assertNotNull(u);
			assertEquals(identifier, u.getIdentifier());
			assertEquals(mail, u.getMail());
			assertEquals(name, u.getName());
			assertTrue(u.checkPassword(password));
			assertFalse(u.checkPassword("wrong" + password));
			assertEquals(type, u.getType());
		}
		
	}

	private void verify(Category category, String name, Category parent, int categoryCount, int threadCount) throws Exception {
		
		assertNotNull(category);
		
		int identifier = category.getIdentifier();

		Category[] categories = new Category[] {
				category,
				connector.getCategory(identifier),
				connector.getCategory(parent, name)
				};
		
		for (Category c : categories) {
			assertNotNull(c);
			assertEquals(identifier, c.getIdentifier());
			assertEquals(name, c.getName());
			if (parent == null) {
				assertNull(c.getParent());
			} else {
				assertEquals(parent.getIdentifier(), c.getParent().getIdentifier());
			}
			assertEquals(categoryCount, c.getCategoryCount());
			assertEquals(categoryCount, c.getCategories().size());
			assertEquals(threadCount, c.getThreadCount());
			assertEquals(threadCount, c.getThreads().size());
		}
		
	}

	private void verify(database.Thread thread, User user, Category category, String name, boolean sticky, boolean closed, int commentCount) throws Exception {
		
		assertNotNull(thread);
		
		int identifier = thread.getIdentifier();

		database.Thread[] threads = new database.Thread[] {
				thread,
				connector.getThread(identifier),
				connector.getThread(category, name)
				};
		
		for (database.Thread t : threads) {
			assertNotNull(t);
			assertEquals(identifier, t.getIdentifier());
			assertEquals(user.getIdentifier(), t.getUser().getIdentifier());
			assertEquals(category.getIdentifier(), t.getCategory().getIdentifier());
			assertEquals(name, t.getName());
			assertEquals(sticky, t.getSticky());
			assertEquals(closed, t.getClosed());
			//assertEquals(?, t.getCreated());
			assertEquals(commentCount, t.getCommentCount());
		}
		
	}
	
	private void verify(database.Comment comment, User user, database.Thread thread, String content) throws Exception {
		
		assertNotNull(comment);
		
		int identifier = comment.getIdentifier();

		database.Comment[] comments = new database.Comment[] {
				comment,
				connector.getComment(identifier)
				};
		
		for (database.Comment c : comments) {
			assertNotNull(c);
			assertEquals(identifier, c.getIdentifier());
			assertEquals(user.getIdentifier(), c.getUser().getIdentifier());
			assertEquals(thread.getIdentifier(), c.getThread().getIdentifier());
			assertEquals(content, c.getContent());
			//assertEquals(?, t.getChanged());
		}
		
	}
	
}