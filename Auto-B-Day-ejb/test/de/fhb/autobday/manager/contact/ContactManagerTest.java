package de.fhb.autobday.manager.contact;

import java.util.ArrayList;

import static org.easymock.EasyMock.*;
import org.junit.Before;
import org.junit.Test;

import com.stvconsultants.easygloss.javaee.JavaEEGloss;

import de.fhb.autobday.dao.AbdContactFacade;
import de.fhb.autobday.dao.AbdGroupFacade;
import de.fhb.autobday.dao.AbdGroupToContactFacade;
import de.fhb.autobday.data.AbdContact;
import de.fhb.autobday.data.AbdGroup;
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
	private AbdGroupFacade groupDAOMock;
	private ContactManager managerUnderTest;
	
	private AbdContact contactOne;
	private AbdContact contactTwo;
	private AbdGroupToContact groupToContactOne;
	private AbdGroupToContact groupToContactTwo;
	private ArrayList<AbdGroupToContact> allGroupToContact;
	private AbdGroup groupOne;
	private AbdGroup groupTwo;
	
	
	public ContactManagerTest() {
	}
	
	@Before
	public void setUp() {
		
		gloss= new JavaEEGloss();
		
		//create Mocks
		contactDAOMock = createMock(AbdContactFacade.class);
		groupToContactDAOMock = createMock(AbdGroupToContactFacade.class);
		groupDAOMock = createMock(AbdGroupFacade.class);
		
		//set Objekts to inject
		gloss.addEJB(contactDAOMock);
		gloss.addEJB(groupToContactDAOMock);
		gloss.addEJB(groupDAOMock);
		
		//create Manager with Mocks
		managerUnderTest=gloss.make(ContactManager.class);
		
		//create Objects needed for tests
		contactOne=new AbdContact("mustermann");
		contactTwo=new AbdContact("otto");
		
		groupOne = new AbdGroup("friends");
		groupTwo = new AbdGroup("family");
		
		groupToContactOne = new AbdGroupToContact();
		groupToContactTwo = new AbdGroupToContact();
		
		allGroupToContact = new ArrayList<AbdGroupToContact>();
		
		groupToContactOne.setAbdContact(contactOne);
		groupToContactOne.setAbdGroup(groupOne);
		allGroupToContact.add(groupToContactOne);
		
		groupToContactTwo.setAbdContact(contactTwo);
		groupToContactOne.setAbdGroup(groupTwo);
		allGroupToContact.add(groupToContactTwo);
		
		
	}
	
	
	/**
	 * Test of setActive method, of class ContactManager.
	 */
	@Test
	public void testSetActiveWithClass() throws Exception {
		
		System.out.println("testSetActiveWithClass");
		
		boolean isActive = true;
		contactOne.setAbdGroupToContactCollection(allGroupToContact);
		
		// Setting up the expected value of the method call of Mockobject
		expect(contactDAOMock.find(contactOne.getId())).andReturn(contactOne);
		expect(groupDAOMock.find(groupOne.getId())).andReturn(groupOne);
		
		groupToContactDAOMock.edit(groupToContactOne);
		
		// Setup is finished need to activate the mock
		replay(contactDAOMock);
		replay(groupDAOMock);
		replay(groupToContactDAOMock);
		
		//call method to test
		managerUnderTest.setActive(contactOne, groupOne, isActive);
		
		// verify
		verify(contactDAOMock);
		verify(groupDAOMock);
		verify(groupToContactDAOMock);
	}
	
	/**
	 * Test fail of setActive method, of class ContactManager.
	 * This test provokes a ContactNotFoundException!
	 */
	@Test(expected = ContactNotFoundException.class)
	public void testSetActiveShouldThrowContactNotFoundException() throws Exception {
		System.out.println("testSetActiveShouldThrowContactNotFoundException");
		
		//prepare test variables
		boolean isActive=true;		
		
		// Setting up the expected value of the method call of Mockobject
		expect(contactDAOMock.find(contactOne.getId())).andReturn(null);
		
		// Setup is finished need to activate the mock
		replay(contactDAOMock);
		replay(groupDAOMock);
		replay(groupToContactDAOMock);
		
		//call method to test
		managerUnderTest.setActive(contactOne, groupOne, isActive);
		
		// verify
		verify(contactDAOMock);
		verify(groupDAOMock);
		verify(groupToContactDAOMock);
	}
	
	/**
	 * Test fail of setActive method, of class ContactManager.
	 * This test provokes a ContactNotFoundException!
	 */
	@Test(expected = ContactNotFoundException.class)
	public void testSetActiveShouldThrowContactNotFoundExceptionBecauseNoGroup() throws Exception {
		System.out.println("testSetActiveShouldThrowContactNotFoundException");
		
		//prepare test variables
		boolean isActive=true;		
		
		// Setting up the expected value of the method call of Mockobject
		expect(contactDAOMock.find(contactOne.getId())).andReturn(contactOne);
		expect(groupDAOMock.find(groupOne.getId())).andReturn(null);
		
		// Setup is finished need to activate the mock
		replay(contactDAOMock);
		replay(groupDAOMock);
		replay(groupToContactDAOMock);
		
		//call method to test
		managerUnderTest.setActive(contactOne, groupOne, isActive);
		
		// verify
		verify(contactDAOMock);
		verify(groupDAOMock);
		verify(groupToContactDAOMock);
	}
	
	/**
	 * Test fail of setActive method, of class ContactManager.
	 * This test provokes a ContactToGroupNotFoundException!
	 */
	@Test(expected = ContactToGroupNotFoundException.class)
	public void testSetActiveShouldThrowContactToGroupNotFoundException() throws Exception {
		System.out.println("testSetActiveShouldThrowContactToGroupNotFoundException");

		//prepare test variables
		boolean isActive=true;
		allGroupToContact = new ArrayList<AbdGroupToContact>();
		contactOne.setAbdGroupToContactCollection(allGroupToContact);
		
		// Setting up the expected value of the method call of Mockobject
		expect(contactDAOMock.find(contactOne.getId())).andReturn(contactOne);
		expect(groupDAOMock.find(groupOne.getId())).andReturn(groupOne);
		
		// Setup is finished need to activate the mock
		replay(contactDAOMock);
		replay(groupDAOMock);
		replay(groupToContactDAOMock);
		
		//call method to test
		managerUnderTest.setActive(contactOne, groupOne, isActive);
		
		// verify
		verify(contactDAOMock);
		verify(groupDAOMock);
		verify(groupToContactDAOMock);
	}
	
	/**
	 * Test of getContact method, of class ContactManager.
	 */
	@Test
	public void testGetContact() throws Exception {
		System.out.println("testGetContact");

		// Setting up the expected value of the method call of Mockobject
		expect(contactDAOMock.find(contactOne.getId())).andReturn(contactOne);
		
		// Setup is finished need to activate the mock
		replay(contactDAOMock);
		
		//call method to test
		managerUnderTest.getContact(contactOne.getId());
		
		// verify
		verify(contactDAOMock);
	}
	
	/**
	 * Test fail of getContact method, of class ContactManager.
	 * This test provokes a ContactNotFoundException!
	 */
	@Test(expected = ContactNotFoundException.class)
	public void testGetContactShouldThrowContactNotFoundException() throws Exception {
		System.out.println("testGetContactShouldThrowContactNotFoundException");
		
		// Setting up the expected value of the method call of Mockobject
		expect(contactDAOMock.find(contactOne.getId())).andReturn(null);
		
		// Setup is finished need to activate the mock
		replay(contactDAOMock);
		
		//call method to test
		managerUnderTest.getContact(contactOne.getId());
		
		// verify		
		verify(contactDAOMock);
	}
}
