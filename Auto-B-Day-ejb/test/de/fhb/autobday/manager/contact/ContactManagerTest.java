/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhb.autobday.manager.contact;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.embeddable.EJBContainer;

import org.easymock.EasyMock;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import de.fhb.autobday.dao.AbdContactFacade;
import de.fhb.autobday.dao.AbdGroupToContactFacade;
import de.fhb.autobday.data.AbdContact;
import de.fhb.autobday.data.AbdGroupToContact;

/**
 *
 * @author Andy Klay <klay@fh-brandenburg.de>
 */
public class ContactManagerTest {
	private EJBContainer container;
	
	private AbdContactFacade contactDAOMock;
	private AbdGroupToContactFacade groupToContactDAOMock;
	
	private ContactManager managerUnderTest;
	
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
		container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
		
		contactDAOMock = EasyMock.createMock(AbdContactFacade.class);
		groupToContactDAOMock = EasyMock.createMock(AbdGroupToContactFacade.class);
		
		managerUnderTest = new ContactManager();
	}
	
	@After
	public void tearDown() {
		container.close();
	}

	/**
	 * Test of setActive method, of class ContactManager.
	 */
	@Test
	public void testSetActive() throws Exception {
		
		
		//test variables
		String contactId="mustermann";
		boolean isActive=true;		
		AbdContact contact=new AbdContact();
		contact.setId(contactId);
		
		AbdGroupToContact groupToContact=new AbdGroupToContact();
		groupToContact.setAbdContact(contact);
		
		Collection<AbdGroupToContact> allGroupToContact=new ArrayList<AbdGroupToContact>();
		allGroupToContact.add(groupToContact);
		
		// Setting up the expected value of the method call of Mockobject
		EasyMock.expect(contactDAOMock.find(contactId)).andReturn(contact).times(1);
		EasyMock.expect(groupToContactDAOMock.findContactByContact(contactId)).andReturn(allGroupToContact).times(1);
		
		// Setup is finished need to activate the mock
		EasyMock.replay(contactDAOMock);
		EasyMock.replay(groupToContactDAOMock);
		
		ContactManagerLocal instance = (ContactManagerLocal)container.getContext().lookup("java:global/classes/ContactManager");
		
		instance.setActive(contactId, isActive);
		
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}
}
