package de.fhb.autobday.manager;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

import com.stvconsultants.easygloss.javaee.JavaEEGloss;

import de.fhb.autobday.dao.AbdAccountFacade;
import de.fhb.autobday.dao.AbdContactFacade;
import de.fhb.autobday.dao.AbdGroupFacade;
import de.fhb.autobday.dao.AbdGroupToContactFacade;
import de.fhb.autobday.dao.AbdUserFacade;
import de.fhb.autobday.data.AbdAccount;
import de.fhb.autobday.data.AbdContact;
import de.fhb.autobday.data.AbdGroup;
import de.fhb.autobday.data.AbdGroupToContact;
import de.fhb.autobday.data.AbdUser;
import de.fhb.autobday.exception.AbdException;
import de.fhb.autobday.exception.contact.NoContactGivenException;
import de.fhb.autobday.manager.group.GroupManager;
import de.fhb.autobday.manager.mail.MailManagerLocal;

/**
 *
 * @author 
 * Michael Koppen <koppen@fh-brandenburg.de>
 * Andy Klay <klay@fh-brandenburg.de>
 */
@RunWith(PowerMockRunner.class)
public class ABDManagerTest {
	
	private JavaEEGloss gloss;
	
	private AbdManager managerUnderTest;
	
	private AbdUserFacade userDAOMock;	
	private AbdGroupFacade groupDAOMock;
	private AbdGroupToContactFacade grouptocontactDAOMock;
	private AbdAccountFacade accountDAOMock;
	private AbdContactFacade contactDAOMock;
	private GroupManager groupManagerMock;
	
	private MailManagerLocal mailManagerMock;
	
	public ABDManagerTest() {
	}

	@Before
	public void setUp() {
		gloss= new JavaEEGloss();
		
		//create Mocks
		userDAOMock = EasyMock.createMock(AbdUserFacade.class);
		groupDAOMock = EasyMock.createMock(AbdGroupFacade.class);
		grouptocontactDAOMock = EasyMock.createMock(AbdGroupToContactFacade.class);
		accountDAOMock = EasyMock.createMock(AbdAccountFacade.class);
		contactDAOMock = EasyMock.createMock(AbdContactFacade.class);
		groupManagerMock = EasyMock.createMock(GroupManager.class);
		mailManagerMock = EasyMock.createMock(MailManagerLocal.class);
		
		
		//set Objekts to inject
		gloss.addEJB(userDAOMock);
		gloss.addEJB(groupDAOMock);
		gloss.addEJB(grouptocontactDAOMock);
		gloss.addEJB(accountDAOMock);
		gloss.addEJB(contactDAOMock);
		gloss.addEJB(groupManagerMock);
		gloss.addEJB(mailManagerMock);
		
		//create Manager with Mocks
		managerUnderTest=gloss.make(AbdManager.class);
	}
	
	/**
	 * Test of getAllUser method, of class ABDManager.
	 */
	@Test
	public void testGetAllUser() throws Exception {
		System.out.println("getAllUser");
		
		//prepare test variables
		ArrayList<AbdUser> userList = new ArrayList<AbdUser>();
		userList.add(new AbdUser(1));
		userList.add(new AbdUser(2));
		userList.add(new AbdUser(3));
		
		// Setting up the expected value of the method call of Mockobject
		EasyMock.expect(userDAOMock.findAll()).andReturn(userList);
		
		// Setup is finished need to activate the mock
		EasyMock.replay(userDAOMock);
		
		//call method to test
		// verify
		assertEquals(userList, managerUnderTest.getAllUser());
		EasyMock.verify(userDAOMock);
	}

	/**
	 * Test of getAllGroups method, of class ABDManager.
	 */
	@Test
	public void testGetAllGroups() throws Exception {
		System.out.println("getAllGroups");
		
		//prepare test variables
		ArrayList<AbdGroup> groupList = new ArrayList<AbdGroup>();
		groupList.add(new AbdGroup("1"));
		groupList.add(new AbdGroup("2"));
		groupList.add(new AbdGroup("3"));
		
		// Setting up the expected value of the method call of Mockobject
		EasyMock.expect(groupDAOMock.findAll()).andReturn(groupList);
		
		// Setup is finished need to activate the mock
		EasyMock.replay(groupDAOMock);
		
		//call method to test
		// verify
		assertEquals(groupList, managerUnderTest.getAllGroups());
		EasyMock.verify(groupDAOMock);
	}

	/**
	 * Test of getAllAccountdata method, of class ABDManager.
	 */
	@Test
	public void testGetAllAccountdata() throws Exception {
		System.out.println("getAllAccountdata");
		
		//prepare test variables
		ArrayList<AbdAccount> accountList = new ArrayList<AbdAccount>();
		accountList.add(new AbdAccount(1));
		accountList.add(new AbdAccount(2));
		accountList.add(new AbdAccount(3));
		
		// Setting up the expected value of the method call of Mockobject
		EasyMock.expect(accountDAOMock.findAll()).andReturn(accountList);
		
		// Setup is finished need to activate the mock
		EasyMock.replay(accountDAOMock);
		
		//call method to test
		// verify
		assertEquals(accountList, managerUnderTest.getAllAccountdata());
		EasyMock.verify(accountDAOMock);
	}

	/**
	 * Test of getAllContacts method, of class ABDManager.
	 */
	@Test
	public void testGetAllContacts() throws Exception {
		System.out.println("getAllContacts");
		
		//prepare test variables
		ArrayList<AbdContact> contactsList = new ArrayList<AbdContact>();
		contactsList.add(new AbdContact("1"));
		contactsList.add(new AbdContact("2"));
		contactsList.add(new AbdContact("3"));
		
		// Setting up the expected value of the method call of Mockobject
		EasyMock.expect(contactDAOMock.findAll()).andReturn(contactsList);
		
		// Setup is finished need to activate the mock
		EasyMock.replay(contactDAOMock);
		assertEquals(contactsList, managerUnderTest.getAllContacts());
		EasyMock.verify(contactDAOMock);
	}
	
	/**
	 * Test of testCheckEveryDay method, of class ABDManager.
	 * 
	 */
	@Test
	public void testCheckEveryDay() throws Exception {
		System.out.println("testCheckEveryDay");
		
		//prepare test variables
		String template="template";
		String sender="sender";
		String parsedTemplate="parsedTemplate";
		Date testDate= new Date(2012,1,20);
		
		Collection<AbdContact> contactCollection;
		contactCollection=new ArrayList<AbdContact>();
		
		Collection<AbdGroupToContact> contactInGroups;
		contactInGroups=new ArrayList<AbdGroupToContact>();
		
		AbdGroupToContact groupToContact;
		groupToContact=new AbdGroupToContact();
		groupToContact.setActive(true);
				
		AbdAccount account = new AbdAccount();
		account.setUsername(sender);
		
		//add some testcontacts
		AbdGroup group=new AbdGroup();
		group.setActive(true);
		group.setTemplate("template");
		group.setAccount(account);
		
		AbdContact contactOne;
		contactOne=new AbdContact();
		contactOne.setMail("mailaddress");
				
		contactCollection.add(contactOne);
		groupToContact.setAbdContact(contactOne);
		groupToContact.setAbdGroup(group);
		
		contactInGroups.add(groupToContact);
		group.setAbdGroupToContactCollection(contactInGroups);
		contactOne.setAbdGroupToContactCollection(contactInGroups);
		
		// Setting up the expected value of the method call of Mockobject
		
		EasyMock.expect(contactDAOMock.findContactByBday((Date) EasyMock.anyObject())).andReturn(contactCollection);

		EasyMock.expect(groupManagerMock.parseTemplate(template, contactOne)).andReturn(parsedTemplate);
		
		mailManagerMock.sendBdayMail(sender,contactOne.getMail(), "Happy Birthday", parsedTemplate);
		
		// Setup is finished need to activate the mock
		EasyMock.replay(contactDAOMock);
		EasyMock.replay(groupManagerMock);
		EasyMock.replay(mailManagerMock);
		
		//call method to test
		managerUnderTest.checkEveryDay();
		
		// verify
		EasyMock.verify(contactDAOMock);
		EasyMock.verify(groupManagerMock);
		EasyMock.verify(mailManagerMock);
	}

	
	/**
	 * Test of testCheckEveryDay method, of class ABDManager.
	 * 
	 */
	@Test(expected=AbdException.class)
	public void testCheckEveryDayThrowsAbdException() throws Exception {
		System.out.println("testCheckEveryDay");
		
		//prepare test variables
		String template="template";
		String sender="sender";
		String parsedTemplate="parsedTemplate";
		Date testDate= new Date(2012,1,20);
		
		Collection<AbdContact> contactCollection;
		contactCollection=new ArrayList<AbdContact>();
		
		Collection<AbdGroupToContact> contactInGroups;
		contactInGroups=new ArrayList<AbdGroupToContact>();
		
		AbdGroupToContact groupToContact;
		groupToContact=new AbdGroupToContact();
		groupToContact.setActive(true);
				
		AbdAccount account = new AbdAccount();
		account.setUsername(sender);
		
		//add some testcontacts
		AbdGroup group=new AbdGroup();
		group.setActive(true);
		group.setTemplate("template");
		group.setAccount(account);
		
		AbdContact contactOne;
		contactOne=new AbdContact();
		contactOne.setMail("mailaddress");
				
		contactCollection.add(contactOne);
		groupToContact.setAbdContact(contactOne);
		groupToContact.setAbdGroup(group);
		
		contactInGroups.add(groupToContact);
		group.setAbdGroupToContactCollection(contactInGroups);
		contactOne.setAbdGroupToContactCollection(contactInGroups);
		
		// Setting up the expected value of the method call of Mockobject
		
		EasyMock.expect(contactDAOMock.findContactByBday((Date) EasyMock.anyObject())).andReturn(contactCollection);

		EasyMock.expect(groupManagerMock.parseTemplate(template, contactOne)).andThrow(new NoContactGivenException());
		
		// Setup is finished need to activate the mock
		EasyMock.replay(contactDAOMock);
		EasyMock.replay(groupManagerMock);
		
		//call method to test
		managerUnderTest.checkEveryDay();
		
		// verify
		EasyMock.verify(contactDAOMock);
		EasyMock.verify(groupManagerMock);
	}
	
	
	/**
	 * Test of testCheckEveryDay method, of class ABDManager.
	 */
	@Test
	public void testCheckEveryDayBirthdayContactsIsEmpty() throws Exception {
		System.out.println("testCheckEveryDay");
		
		//prepare test variables
		Collection<AbdContact> contactCollection;
		contactCollection=new ArrayList<AbdContact>();
		
		// Setting up the expected value of the method call of Mockobject
		EasyMock.expect(contactDAOMock.findContactByBday((Date) EasyMock.anyObject())).andReturn(contactCollection);
		
		// Setup is finished need to activate the mock
		EasyMock.replay(contactDAOMock);
		
		//call method to test
		managerUnderTest.checkEveryDay();
		
		// verify
		EasyMock.verify(contactDAOMock);
	}
}