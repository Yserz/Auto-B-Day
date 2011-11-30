/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhb.autobday.manager.user;

import de.fhb.autobday.data.AbdUser;
import javax.ejb.embeddable.EJBContainer;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author MacYser
 */
public class UserManagerTest {
	
	public UserManagerTest() {
	}

	@BeforeClass
	public static void setUpClass() throws Exception {
	}

	@AfterClass
	public static void tearDownClass() throws Exception {
	}
	
	@Before
	public void setUp() {
	}
	
	@After
	public void tearDown() {
	}

	/**
	 * Test of getUser method, of class UserManager.
	 */
	@Test
	public void testGetUser() throws Exception {
		System.out.println("getUser");
		int userid = 0;
		EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
		UserManagerLocal instance = (UserManagerLocal)container.getContext().lookup("java:global/classes/UserManager");
		AbdUser expResult = null;
		AbdUser result = instance.getUser(userid);
		assertEquals(expResult, result);
		container.close();
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of login method, of class UserManager.
	 */
	@Test
	public void testLogin() throws Exception {
		System.out.println("login");
		EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
		UserManagerLocal instance = (UserManagerLocal)container.getContext().lookup("java:global/classes/UserManager");
//		instance.login(loginName, password);
		container.close();
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of logout method, of class UserManager.
	 */
	@Test
	public void testLogout() throws Exception {
		System.out.println("logout");
		EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
		UserManagerLocal instance = (UserManagerLocal)container.getContext().lookup("java:global/classes/UserManager");
		instance.logout();
		container.close();
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}
}
