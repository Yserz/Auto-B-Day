package de.fhb.autobday.manager.contact;

import java.util.ArrayList;

import org.easymock.EasyMock;
import org.junit.Before;
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
	
	private AbdContact contactOne;
	private AbdContact contactTwo;
	private AbdGroupToContact groupToContactOne;
	private AbdGroupToContact groupToContactTwo;
	private ArrayList<AbdGroupToContact> allGroupToContact;
	
	
	public ContactManagerTest() {
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
		
		//create Objects needed for tests
		contactOne=new AbdContact("mustermann");
		contactTwo=new AbdContact("otto");
		
		groupToContactOne = new AbdGroupToContact();
		groupToContactTwo = new AbdGroupToContact();
		
		allGroupToContact = new ArrayList<AbdGroupToContact>();
		
		groupToContactOne.setAbdContact(contactOne);
		allGroupToContact.add(groupToContactOne);
		
		groupToContactTwo.setAbdContact(contactTwo);
		allGroupToContact.add(groupToContactTwo);
	}
	
	
	/**
	 * Test of setActive method, of class ContactManager.
	 */
	@Test
	public void testSetActiveWithClass() throws Exception {
		
		System.out.println("testSetActiveWithClass");
		
		boolean isActive = true;
		
		// Setting up the expected value of the method call of Mockobject
		EasyMock.expect(contactDAOMock.find(contactOne.getId())).andReturn(contactOne);
		EasyMock.expect(groupToContactDAOMock.findGroupByContact(contactOne.getId())).andReturn(allGroupToContact);
		
		groupToContactDAOMock.edit(groupToContactOne);
		
		// Setup is finished need to activate the mock
		EasyMock.replay(contactDAOMock);
		EasyMock.replay(groupToContactDAOMock);
		
		//call method to test
		managerUnderTest.setActive(contactOne, isActive);
		
		// verify
		EasyMock.verify(contactDAOMock);
		EasyMock.verify(groupToContactDAOMock);
	}

	/**
	 * Test of setActive method, of class ContactManager.
	 */
	@Test
	public void testSetActive() throws Exception {
		
		System.out.println("testSetActive");
		
		boolean isActive = true;
		// Setting up the expected value of the method call of Mockobject
		EasyMock.expect(contactDAOMock.find(contactOne.getId())).andReturn(contactTwo);
		EasyMock.expect(groupToContactDAOMock.findGroupByContact(contactOne.getId())).andReturn(allGroupToContact);
		groupToContactDAOMock.edit(groupToContactOne);
		
		// Setup is finished need to activate the mock
		EasyMock.replay(contactDAOMock);
		EasyMock.replay(groupToContactDAOMock);
		
		//call method to test
		managerUnderTest.setActive(contactOne.getId(), isActive);
		
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
		
		System.out.println("testSetActiveShouldThrowNoContactInThisGroupException");
		
		boolean isActive = true;
		
		allGroupToContact.get(0).setAbdContact(new AbdContact("maja"));
		
		// willful leave off the adding of groupToContact to Arraylist!
		
		// Setting up the expected value of the method call of Mockobject
		EasyMock.expect(contactDAOMock.find(contactOne.getId())).andReturn(contactOne);
		EasyMock.expect(groupToContactDAOMock.findGroupByContact(contactOne.getId())).andReturn(allGroupToContact);
		groupToContactDAOMock.edit(groupToContactOne);
		
		// Setup is finished need to activate the mock
		EasyMock.replay(contactDAOMock);
		EasyMock.replay(groupToContactDAOMock);
		
		//call method to test
		managerUnderTest.setActive(contactOne, isActive);
		
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
		
		System.out.println("testSetActiveShouldThrowContactNotFoundException");
		
		boolean isActive=true;		
		
		// Setting up the expected value of the method call of Mockobject
		EasyMock.expect(contactDAOMock.find(contactOne.getId())).andReturn(null);
		
		// Setup is finished need to activate the mock
		EasyMock.replay(contactDAOMock);
		EasyMock.replay(groupToContactDAOMock);
		
		//call method to test
		managerUnderTest.setActive(contactOne.getId(), isActive);
		
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
		
		System.out.println("testSetActiveShouldThrowContactToGroupNotFoundException");

		boolean isActive=true;		
		
		// Setting up the expected value of the method call of Mockobject
		EasyMock.expect(contactDAOMock.find(contactOne.getId())).andReturn(contactOne).times(1);
		EasyMock.expect(groupToContactDAOMock.findGroupByContact(contactOne.getId())).andReturn(null);
		
		// Setup is finished need to activate the mock
		EasyMock.replay(contactDAOMock);
		EasyMock.replay(groupToContactDAOMock);
		
		//call method to test
		managerUnderTest.setActive(contactOne.getId(), isActive);
		
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

		// Setting up the expected value of the method call of Mockobject
		EasyMock.expect(contactDAOMock.find(contactOne.getId())).andReturn(contactOne);
		
		// Setup is finished need to activate the mock
		EasyMock.replay(contactDAOMock);
		
		//call method to test
		managerUnderTest.getContact(contactOne.getId());
		
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
		
		// Setting up the expected value of the method call of Mockobject
		EasyMock.expect(contactDAOMock.find(contactOne.getId())).andReturn(null);
		
		// Setup is finished need to activate the mock
		EasyMock.replay(contactDAOMock);
		
		//call method to test
		managerUnderTest.getContact(contactOne.getId());
		
		// verify		
		EasyMock.verify(contactDAOMock);
	}
}
