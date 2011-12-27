package de.fhb.autobday.manager.contact;

import java.util.ArrayList;
import java.util.Collection;

import org.easymock.EasyMock;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.stvconsultants.easygloss.javaee.JavaEEGloss;

import de.fhb.autobday.dao.AbdContactFacade;
import de.fhb.autobday.dao.AbdGroupToContactFacade;
import de.fhb.autobday.data.AbdContact;
import de.fhb.autobday.data.AbdGroupToContact;
import de.fhb.autobday.exception.contact.ContactNotFoundException;
import de.fhb.autobday.exception.contact.ContactToGroupNotFoundException;
import de.fhb.autobday.exception.contact.NoContactInThisGroupException;

/**
 * Test the ContactManager
 *
 * @author 
 * Andy Klay <klay@fh-brandenburg.de>
 * Michael Koppen <koppen@fh-brandenburg.de>
 * Christoph Ott <>
 */
public class ContactManagerTest {

	private JavaEEGloss gloss;
	
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
		
		gloss= new JavaEEGloss();
		
		//create Mocks
		contactDAOMock = EasyMock.createMock(AbdContactFacade.class);
		groupToContactDAOMock = EasyMock.createMock(AbdGroupToContactFacade.class);
		
		//set Objekts to inject
		gloss.addEJB(contactDAOMock);
		gloss.addEJB(groupToContactDAOMock);
		
		//create Manager with Mocks
		managerUnderTest=gloss.make(ContactManager.class);
		
	}
	
	@After
	public void tearDown() {
	}
	
	/**
	 * Test of setActive method, of class ContactManager.
	 */
	@Test
	public void testSetActiveWithClass() throws Exception {
		
		//test variables
		String contactId="mustermann";
		boolean isActive=true;		
		AbdContact contact=new AbdContact();
		contact.setId(contactId);
		
		//prepare groupToContactRealation
		AbdGroupToContact groupToContact=new AbdGroupToContact();		
		Collection<AbdGroupToContact> allGroupToContact=new ArrayList<AbdGroupToContact>();
		groupToContact.setAbdContact(contact);

		allGroupToContact.add(groupToContact);
		
		// Setting up the expected value of the method call of Mockobject
		EasyMock.expect(contactDAOMock.find(contactId)).andReturn(contact).times(1);
		EasyMock.expect(groupToContactDAOMock.findGroupByContact(contactId)).andReturn(allGroupToContact).times(1);
		groupToContactDAOMock.edit(groupToContact);
		
		// Setup is finished need to activate the mock
		EasyMock.replay(contactDAOMock);
		EasyMock.replay(groupToContactDAOMock);
		
		//call method to test
		managerUnderTest.setActive(contact, isActive);
		
		// verify
		EasyMock.verify(contactDAOMock);
		EasyMock.verify(groupToContactDAOMock);
	}

	/**
	 * Test of setActive method, of class ContactManager.
	 */
	@Test
	public void testSetActive() throws Exception {
		
		//test variables
		String contactId="mustermann";
		boolean isActive=true;		
		AbdContact contactOne=new AbdContact();
		contactOne.setId(contactId);
		
		AbdContact contactTwo=new AbdContact();
		contactTwo.setId("otto");
		
		//prepare groupToContactRealation
		AbdGroupToContact groupToContact=new AbdGroupToContact();
		AbdGroupToContact groupToContactTwo=new AbdGroupToContact();
		Collection<AbdGroupToContact> allGroupToContact=new ArrayList<AbdGroupToContact>();
		
		groupToContact.setAbdContact(contactOne);
		allGroupToContact.add(groupToContact);
		
		groupToContactTwo.setAbdContact(contactTwo);
		allGroupToContact.add(groupToContactTwo);
		
		// Setting up the expected value of the method call of Mockobject
		EasyMock.expect(contactDAOMock.find(contactId)).andReturn(contactTwo).times(1);
		EasyMock.expect(groupToContactDAOMock.findGroupByContact(contactId)).andReturn(allGroupToContact).times(1);
		groupToContactDAOMock.edit(groupToContact);
		
		// Setup is finished need to activate the mock
		EasyMock.replay(contactDAOMock);
		EasyMock.replay(groupToContactDAOMock);
		
		//call method to test
		managerUnderTest.setActive(contactId, isActive);
		
		// verify
		EasyMock.verify(contactDAOMock);
		EasyMock.verify(groupToContactDAOMock);
	}
	

	
	/**
	 * Test fail of setActive method, of class ContactManager.
	 * This test provokes a NoContactInThisGroupException!
	 */
	@Test(expected = NoContactInThisGroupException.class)
	public void testSetActiveShouldThrowNoContactInThisGroupException() throws Exception {
		
		//test variables
		String contactId="mustermann";
		boolean isActive=true;		
		AbdContact contact=new AbdContact();
		contact.setId(contactId);
		
		//prepare groupToContactRealation
		AbdGroupToContact groupToContact=new AbdGroupToContact();		
		Collection<AbdGroupToContact> allGroupToContact=new ArrayList<AbdGroupToContact>();
		groupToContact.setAbdContact(contact);
		
		// willful leave off the adding of groupToContact to Arraylist!
		
		// Setting up the expected value of the method call of Mockobject
		EasyMock.expect(contactDAOMock.find(contactId)).andReturn(contact).times(1);
		EasyMock.expect(groupToContactDAOMock.findGroupByContact(contactId)).andReturn(allGroupToContact).times(1);
		groupToContactDAOMock.edit(groupToContact);
		
		// Setup is finished need to activate the mock
		EasyMock.replay(contactDAOMock);
		EasyMock.replay(groupToContactDAOMock);
		
		//call method to test
		managerUnderTest.setActive(contactId, isActive);
		
		// verify
		EasyMock.verify(contactDAOMock);
		EasyMock.verify(groupToContactDAOMock);
	}
	
	/**
	 * Test fail of setActive method, of class ContactManager.
	 * This test provokes a ContactNotFoundException!
	 */
	@Test(expected = ContactNotFoundException.class)
	public void testSetActiveShouldThrowContactNotFoundException() throws Exception {
		
		//test variables
		String contactId="mustermann";
		boolean isActive=true;		
		
		// Setting up the expected value of the method call of Mockobject
		EasyMock.expect(contactDAOMock.find(contactId)).andReturn(null).times(1);
		
		// Setup is finished need to activate the mock
		EasyMock.replay(contactDAOMock);
		EasyMock.replay(groupToContactDAOMock);
		
		//call method to test
		managerUnderTest.setActive(contactId, isActive);
		
		// verify
		EasyMock.verify(contactDAOMock);
		EasyMock.verify(groupToContactDAOMock);
	}
	
	/**
	 * Test fail of setActive method, of class ContactManager.
	 * This test provokes a ContactToGroupNotFoundException!
	 */
	@Test(expected = ContactToGroupNotFoundException.class)
	public void testSetActiveShouldThrowContactToGroupNotFoundException() throws Exception {
		
		//test variables
		String contactId="mustermann";
		boolean isActive=true;		
		AbdContact contact=new AbdContact();
		contact.setId(contactId);
		
		// Setting up the expected value of the method call of Mockobject
		EasyMock.expect(contactDAOMock.find(contactId)).andReturn(contact).times(1);
		EasyMock.expect(groupToContactDAOMock.findGroupByContact(contactId)).andReturn(null).times(1);
		
		// Setup is finished need to activate the mock
		EasyMock.replay(contactDAOMock);
		EasyMock.replay(groupToContactDAOMock);
		
		//call method to test
		managerUnderTest.setActive(contactId, isActive);
		
		// verify
		EasyMock.verify(contactDAOMock);
		EasyMock.verify(groupToContactDAOMock);
	}
	
	/**
	 * Test of getContact method, of class ContactManager.
	 */
	@Test
	public void testGetContact() throws Exception {
		
		System.out.println("testGetContact");

		//test variables
		String contactId="friends";	
		AbdContact contact=new AbdContact();
		contact.setId(contactId);
		
		// Setting up the expected value of the method call of Mockobject
		EasyMock.expect(contactDAOMock.find(contactId)).andReturn(contact).times(1);
		
		// Setup is finished need to activate the mock
		EasyMock.replay(contactDAOMock);
		
		//call method to test
		managerUnderTest.getContact(contactId);
		
		// verify		
		EasyMock.verify(contactDAOMock);
	}
	
	/**
	 * Test fail of getContact method, of class ContactManager.
	 * This test provokes a ContactNotFoundException!
	 */
	@Test(expected = ContactNotFoundException.class)
	public void testGetContactShouldThrowContactNotFoundException() throws Exception {
		
		System.out.println("testGetContactShouldThrowContactNotFoundException");

		//test variables
		String contactId="friends";	
		AbdContact contact=new AbdContact();
		contact.setId(contactId);
		
		// Setting up the expected value of the method call of Mockobject
		EasyMock.expect(contactDAOMock.find(contactId)).andReturn(null).times(1);
		
		// Setup is finished need to activate the mock
		EasyMock.replay(contactDAOMock);
		
		//call method to test
		managerUnderTest.getContact(contactId);
		
		// verify		
		EasyMock.verify(contactDAOMock);
	}
}
