package test;

import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import database.Connector;
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
			assertEquals("UNIT",user1.getName());
			assertEquals(User.USER, user1.getType());
			assertEquals("unit@test.com",user1.getMail());
			assertTrue(user1.checkPassword("bruger123"));
			assertFalse(user1.checkPassword("bruger12345"));
	}
	@Test
	public void createCategory() throws Exception {
		
		connector.createCategory("Animals", null);
		connector.createCategory("Cats", null);
		
	}
}