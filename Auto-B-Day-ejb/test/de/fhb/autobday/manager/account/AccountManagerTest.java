package de.fhb.autobday.manager.account;

import javax.ejb.embeddable.EJBContainer;
import static org.junit.Assert.fail;

import org.easymock.EasyMock;
import org.junit.*;

import com.stvconsultants.easygloss.javaee.JavaEEGloss;

import de.fhb.autobday.dao.AbdAccountFacade;
import de.fhb.autobday.dao.AbdContactFacade;
import de.fhb.autobday.dao.AbdGroupFacade;
import de.fhb.autobday.data.AbdAccount;
import de.fhb.autobday.manager.group.GroupManager;

/**
 *
 * @author Michael Koppen <koppen@fh-brandenburg.de>
 */
public class AccountManagerTest {
	
	private JavaEEGloss gloss;
	
	private AccountManager managerUnderTest;
	
	private AbdAccountFacade accountDAOMock;
	
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
		
		//set Objekts to inject
		gloss.addEJB(accountDAOMock);
		
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
		
		
		
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
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
	 * Test of importGroupsAndContacts method, of class AccountManager.
	 */
	@Test
	public void testImportGroupsAndContacts() throws Exception {
		System.out.println("importGroupsAndContacts");
		
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}
}
