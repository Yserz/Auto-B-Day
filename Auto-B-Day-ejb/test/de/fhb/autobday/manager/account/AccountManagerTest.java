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
import de.fhb.autobday.exception.user.UserNotFoundException;

/**
 *
 * @author Andy Klay <klay@fh-brandenburg.de>
 * @author Michael Koppen <koppen@fh-brandenburg.de>
 * @author Christoph Ott <>
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
		EasyMock.expect(userDAOMock.find(userId)).andReturn(user);
		
		accountDAOMock.create((AbdAccount) EasyMock.anyObject());
		accountDAOMock.edit((AbdAccount) EasyMock.anyObject());
		
		// Setup is finished need to activate the mock
		EasyMock.replay(userDAOMock);
		EasyMock.replay(accountDAOMock);
		
		// testing Methodcall
		managerUnderTest.addAccount(userId, password, userName, type);
		
		// verify		
		EasyMock.verify(userDAOMock);
		EasyMock.verify(accountDAOMock);
		
		
	}
	
	/**
	 * Test of addAccount method, of class AccountManager.
	 */
	@Test(expected = UserNotFoundException.class)
	public void testAddAccountShouldThrowUserNotFoundException() throws Exception {
		
		System.out.println("testAddAccountShouldThrowUserNotFoundException");
		
		int abduserid = EasyMock.anyInt();
		String password="password";
		String userName="mustermann";
		String type="type";
		
		// Setting up the expected value of the method call of Mockobject
		EasyMock.expect(userDAOMock.find(abduserid)).andReturn(null);
		
		EasyMock.replay(userDAOMock);
		
		managerUnderTest.addAccount(abduserid, password, userName, type);
		
		// verify		
		EasyMock.verify(userDAOMock);

		
		
	}

	/**
	 * Test of removeAccount method, of class AccountManager.
	 */
	@Test
	public void testRemoveAccount() throws Exception {
		System.out.println("removeAccount");

		//prepare test variables
		int accountid = 1;
		AbdAccount account = new AbdAccount(1);
		
		// Setting up the expected value of the method call of Mockobject
		EasyMock.expect(accountDAOMock.find(accountid)).andReturn(account);
		accountDAOMock.remove(account);
		
		// Setup is finished need to activate the mock
		EasyMock.replay(accountDAOMock);
		
		// testing Methodcall
		managerUnderTest.removeAccount(1);
		
		// verify		
		EasyMock.verify(accountDAOMock);
	}
	
	/**
	 * Test of removeAccount method, of class AccountManager.
	 */
	@Test(expected = AccountNotFoundException.class)
	public void testRemoveAccountShouldThrowAccountNotFoundException() throws Exception {
		System.out.println("testRemoveAccountShouldThrowAccountNotFoundException");

		//prepare test variables
		int accountid = 1;
		AbdAccount account = new AbdAccount(1);
		
		// Setting up the expected value of the method call of Mockobject
		EasyMock.expect(accountDAOMock.find(accountid)).andReturn(null);
		accountDAOMock.remove(account);
		
		// Setup is finished need to activate the mock
		EasyMock.replay(accountDAOMock);
		
		// testing Methodcall
		managerUnderTest.removeAccount(1);
		
		// verify		
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
