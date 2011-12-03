/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhb.autobday.manager.contact;

import static org.junit.Assert.fail;

import javax.ejb.embeddable.EJBContainer;

import org.easymock.EasyMock;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import de.fhb.autobday.data.AbdContact;
import de.fhb.autobday.data.AbdGroup;
import de.fhb.autobday.manager.group.GroupManager;

/**
 *
 * @author MacYser
 */
public class ContactManagerTest {
	
	private AbdContact mock;
	private ContactManager contactTest;
	
	public ContactManagerTest() {
	}

	@BeforeClass
	public static void setUpClass() throws Exception {
	}

	@AfterClass
	public static void tearDownClass() throws Exception {
	}
	
	@Before
	public void setUp() {
		mock = EasyMock.createMock(AbdContact.class);
		contactTest = new ContactManager();
	}
	
	@After
	public void tearDown() {
	}

	/**
	 * Test of setActive method, of class ContactManager.
	 */
	@Test
	public void testSetActive() throws Exception {
		System.out.println("setActive");
		EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
		ContactManagerLocal instance = (ContactManagerLocal)container.getContext().lookup("java:global/classes/ContactManager");
//		instance.setActive(contactId, active)
		container.close();
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}
}
