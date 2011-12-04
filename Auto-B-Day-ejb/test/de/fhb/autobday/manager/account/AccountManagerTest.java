/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhb.autobday.manager.account;

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
public class AccountManagerTest {
	
	public AccountManagerTest() {
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
	 * Test of addAccount method, of class AccountManager.
	 */
	@Test
	public void testAddAccount() throws Exception {
		System.out.println("addAccount");
		EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
		AccountManagerLocal instance = (AccountManagerLocal)container.getContext().lookup("java:global/classes/AccountManager");
		instance.addAccount();
		container.close();
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of removeAccount method, of class AccountManager.
	 */
	@Test
	public void testRemoveAccount() throws Exception {
		System.out.println("removeAccount");
		EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
		AccountManagerLocal instance = (AccountManagerLocal)container.getContext().lookup("java:global/classes/AccountManager");
//		instance.removeAccount(accountId);
		container.close();
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of importGroupsAndContacts method, of class AccountManager.
	 */
	@Test
	public void testImportGroupsAndContacts() throws Exception {
		System.out.println("importGroupsAndContacts");
		EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
		AccountManagerLocal instance = (AccountManagerLocal)container.getContext().lookup("java:global/classes/AccountManager");
		instance.importGroupsAndContacts();
		container.close();
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}
}
