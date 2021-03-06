package de.fhb.autobday.manager;

import com.stvconsultants.easygloss.javaee.JavaEEGloss;
import de.fhb.autobday.dao.*;
import de.fhb.autobday.data.*;
import de.fhb.autobday.manager.group.GroupManager;
import de.fhb.autobday.manager.mail.GoogleMailManagerLocal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import org.easymock.EasyMock;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 *
 * @author Andy Klay <klay@fh-brandenburg.de> Christoph Ott
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
	private GoogleMailManagerLocal mailManagerMock;

	public ABDManagerTest() {
	}

	@Before
	public void setUp() {
		gloss = new JavaEEGloss();

		//create Mocks
		userDAOMock = EasyMock.createMock(AbdUserFacade.class);
		groupDAOMock = EasyMock.createMock(AbdGroupFacade.class);
		grouptocontactDAOMock = EasyMock.createMock(AbdGroupToContactFacade.class);
		accountDAOMock = EasyMock.createMock(AbdAccountFacade.class);
		contactDAOMock = EasyMock.createMock(AbdContactFacade.class);
		groupManagerMock = EasyMock.createMock(GroupManager.class);
		mailManagerMock = EasyMock.createMock(GoogleMailManagerLocal.class);


		//set Objekts to inject
		gloss.addEJB(userDAOMock);
		gloss.addEJB(groupDAOMock);
		gloss.addEJB(grouptocontactDAOMock);
		gloss.addEJB(accountDAOMock);
		gloss.addEJB(contactDAOMock);
		gloss.addEJB(groupManagerMock);
		gloss.addEJB(mailManagerMock);

		//create Manager with Mocks
		managerUnderTest = gloss.make(AbdManager.class);
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
		String template = "template";
		String sender = "sender";
		String parsedTemplate = "parsedTemplate";
		Date testDate = new Date(2012, 1, 20);

		List<AbdContact> contactCollection;
		contactCollection = new ArrayList<AbdContact>();

		Collection<AbdGroupToContact> contactInGroups;
		contactInGroups = new ArrayList<AbdGroupToContact>();

		AbdGroupToContact groupToContact;
		groupToContact = new AbdGroupToContact();
		groupToContact.setActive(true);

		AbdAccount account = new AbdAccount();
		account.setUsername(sender);

		//add some testcontacts
		AbdGroup group = new AbdGroup();
		group.setActive(true);
		group.setTemplate("template");
		group.setAccount(account);

		AbdContact contactOne;
		contactOne = new AbdContact();
		contactOne.setMail("mailaddress");
		contactOne.setBday(new Date(System.currentTimeMillis()));

		contactCollection.add(contactOne);
		groupToContact.setAbdContact(contactOne);
		groupToContact.setAbdGroup(group);

		contactInGroups.add(groupToContact);
		group.setAbdGroupToContactCollection(contactInGroups);
		contactOne.setAbdGroupToContactCollection(contactInGroups);

		// Setting up the expected value of the method call of Mockobject

		EasyMock.expect(contactDAOMock.findAll()).andReturn(contactCollection);

		EasyMock.expect(groupManagerMock.parseTemplate(template, contactOne)).andReturn(parsedTemplate);

		mailManagerMock.sendUserMail(account, "Happy Birthday", parsedTemplate, contactOne.getMail());

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
	 */
	@Test
	public void testCheckEveryDayBirthdayContactsIsEmpty() throws Exception {
		System.out.println("testCheckEveryDayBirthdayContactsIsEmpty");

		//prepare test variables
		List<AbdContact> contactCollection;
		contactCollection = new ArrayList<AbdContact>();

		// Setting up the expected value of the method call of Mockobject
		EasyMock.expect(contactDAOMock.findAll()).andReturn(contactCollection);

		// Setup is finished need to activate the mock
		EasyMock.replay(contactDAOMock);

		//call method to test
		managerUnderTest.checkEveryDay();

		// verify
		EasyMock.verify(contactDAOMock);
	}
}