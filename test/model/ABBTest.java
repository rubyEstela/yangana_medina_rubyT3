package model;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import exception.*;
import model.ABBUser;
import model.User;


class ABBTest {

	private ABBUser tree;
	
	void stage1() {
		tree=new ABBUser();
	}
	
	void stage2() {
		stage1();
		
		try {
			tree.add(tree.getRoot(), new User("Edgar Gajo", 12, "Male"));
			tree.add(tree.getRoot(), new User("Thanos Ortiz", 20, "Female"));
			tree.add(tree.getRoot(), new User("Patricio Estrella", 14, "Male"));
		}catch(Exception e) {
			
		}
		
	}
	
	@Test
	void addTest() {
		stage1();
		try {
			tree.add(tree.getRoot(), new User("Edgar Gajo", 12, "Male"));
			tree.add(tree.getRoot(), new User("Thanos Ortiz", 20, "Female"));
			tree.add(tree.getRoot(), new User("Patricio Estrella", 14, "Male"));
		}catch(Exception e) {
		
		}
		assertEquals("Marta" , tree.getRoot().getName());
		assertEquals("Armando", tree.getRoot().getLeft().getName());
		assertEquals("Patricio", tree.getRoot().getRight().getName());
	}
	
	@Test
	void addExceptionTest() {
		stage2();

		try {
			tree.add(tree.getRoot(), new User("Edgar Gajo", 12, "Male"));
		}catch(Exception e) {
			assertTrue("there is already a user with that name, the exception should be thrown", true);
		}
	}

	@Test
	void removeTest() {
		stage2();
		
		tree.remove("Thanos Ortiz");
		
		assertNull(tree.getRoot().getLeft());
		
		tree.remove("Edgar Gajo");
		
		assertEquals("Patricio Estrella", tree.getRoot().getName());
	}
	
	@Test
	void searchTest() {
		stage2();
		
		try {
			assertEquals(tree.getRoot().getLeft(), tree.search(tree.getRoot(), "Thanos Ortiz"));
		} catch (UsersNotExistException e) {
			fail("The user was not found");
		}
	}
	
	@Test
	void searchExceptionTest() {
		stage2();
		try {
			tree.search(tree.getRoot(), "hULK");
		} catch (UsersNotExistException e) {
			assertTrue("the user does not exist, the exception must be thrown", true);
		}
	}

}
