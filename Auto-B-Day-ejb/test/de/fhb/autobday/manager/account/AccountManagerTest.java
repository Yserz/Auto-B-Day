package de.fhb.autobday.manager.account;

import org.easymock.EasyMock;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.stvconsultants.easygloss.javaee.JavaEEGloss;

import de.fhb.autobday.dao.AbdAccountFacade;
import de.fhb.autobday.dao.AbdUserFacade;
import de.fhb.autobday.data.AbdAccount;
import de.fhb.autobday.data.AbdUser;
import de.fhb.autobday.exception.account.AccountNotFoundException;

/**
 *
 * @author Michael Koppen <koppen@fh-brandenburg.de>
 */
public class AccountManagerTest {
	
	private JavaEEGloss gloss;
	
	private AccountManager managerUnderTest;
	
	private AbdAccountFacade accountDAOMock;
	
	private AbdUserFacade userDAOMock;
	
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
		gloss= new JavaEEGloss();
		
		//create Mocks
		accountDAOMock = EasyMock.createMock(AbdAccountFacade.class);
		userDAOMock = EasyMock.createMock(AbdUserFacade.class);
		
		//set Objekts to inject
		gloss.addEJB(accountDAOMock);
		gloss.addEJB(userDAOMock);
		
		//create Manager with Mocks
		managerUnderTest=gloss.make(AccountManager.class);
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
		
		//prepare test variables
		String password="password";
		String userName="mustermann";
		String type="type";
		

		//prepare a user object
		int userId=1;	
		AbdUser user = new AbdUser();
		user.setFirstname("");
		user.setName("");
		user.setId(userId);
		user.setPasswort("password");
		user.setSalt("salt");
		user.setUsername("username");
		
		
		
		// Setting up the expected value of the method call of Mockobject
		EasyMock.expect(userDAOMock.find(userId)).andReturn(user).times(1);
//		EasyMock.expect(accountDAOMock.create(userId)).andReturn(user).times(1);
		//TODO dringend!! weiß nicht wie man das create mocken soll,
		//da die objekt referenz hier nciht verfügbar ist sondern nur loakl in der methode !!!
//		accountDAOMock.create(null);
//		accountDAOMock.edit(null);
		
		// Setup is finished need to activate the mock
		EasyMock.replay(userDAOMock);
//		EasyMock.replay(accountDAOMock);
		
		// testing Methodcall
		managerUnderTest.addAccount(userId, password, userName, type);
		
		// verify		
		EasyMock.verify(userDAOMock);
//		EasyMock.verify(accountDAOMock);
		
		
	}

	/**
	 * Test of removeAccount method, of class AccountManager.
	 */
	@Test
	public void testRemoveAccount() throws Exception {
		System.out.println("removeAccount");

		int accountid = 1;
		AbdAccount account = new AbdAccount(1);
		
		EasyMock.expect(accountDAOMock.find(accountid)).andReturn(account);
		accountDAOMock.remove(account);
		EasyMock.replay(accountDAOMock);
		managerUnderTest.removeAccount(1);
		
		EasyMock.verify(accountDAOMock);
	}
	
	/**
	 * Test of removeAccount method, of class AccountManager.
	 */
	@Test(expected = AccountNotFoundException.class)
	public void testRemoveAccountShouldThrowAccountNotFoundException() throws Exception {
		System.out.println("testRemoveAccountShouldThrowAccountNotFoundException");

		int accountid = 1;
		AbdAccount account = new AbdAccount(1);
		
		EasyMock.expect(accountDAOMock.find(accountid)).andReturn(null);
		accountDAOMock.remove(account);
		EasyMock.replay(accountDAOMock);
		managerUnderTest.removeAccount(1);
		
		EasyMock.verify(accountDAOMock);
	}

	/**
	 * Test of importGroupsAndContacts method, of class AccountManager.
	 */
	@Test
	public void testImportGroupsAndContacts() throws Exception {
		System.out.println("importGroupsAndContacts");
		
		// TODO review the generated test code and remove the default call to fail.
		//fail("The test case is a prototype.");
	}
}
