/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhb.autobday.manager;

import java.util.List;
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
public class ABDManagerTest {
	
	public ABDManagerTest() {
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
	 * Test of getAllUser method, of class ABDManager.
	 */
	@Test
	public void testGetAllUser() throws Exception {
		System.out.println("getAllUser");
		EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
		ABDManagerLocal instance = (ABDManagerLocal)container.getContext().lookup("java:global/classes/ABDManager");
		List expResult = null;
		List result = instance.getAllUser();
		assertEquals(expResult, result);
		container.close();
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of getAllGroups method, of class ABDManager.
	 */
	@Test
	public void testGetAllGroups() throws Exception {
		System.out.println("getAllGroups");
		EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
		ABDManagerLocal instance = (ABDManagerLocal)container.getContext().lookup("java:global/classes/ABDManager");
		List expResult = null;
		List result = instance.getAllGroups();
		assertEquals(expResult, result);
		container.close();
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of getAllAccountdata method, of class ABDManager.
	 */
	@Test
	public void testGetAllAccountdata() throws Exception {
		System.out.println("getAllAccountdata");
		EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
		ABDManagerLocal instance = (ABDManagerLocal)container.getContext().lookup("java:global/classes/ABDManager");
		List expResult = null;
		List result = instance.getAllAccountdata();
		assertEquals(expResult, result);
		container.close();
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of getAllContacts method, of class ABDManager.
	 */
	@Test
	public void testGetAllContacts() throws Exception {
		System.out.println("getAllContacts");
		EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
		ABDManagerLocal instance = (ABDManagerLocal)container.getContext().lookup("java:global/classes/ABDManager");
		List expResult = null;
		List result = instance.getAllContacts();
		assertEquals(expResult, result);
		container.close();
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of hallo method, of class ABDManager.
	 */
	@Test
	public void testHallo() throws Exception {
		System.out.println("hallo");
		EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
		ABDManagerLocal instance = (ABDManagerLocal)container.getContext().lookup("java:global/classes/ABDManager");
		String expResult = "";
		String result = instance.hallo();
		assertEquals(expResult, result);
		container.close();
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}
}
