package de.fhb.autobday.manager.account;

import javax.ejb.embeddable.EJBContainer;
import static org.junit.Assert.fail;
import org.junit.*;

/**
 *
 * @author Michael Koppen <koppen@fh-brandenburg.de>
 */
public class AccountManagerTest {
	private EJBContainer container;
	
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
		container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
	}
	
	@After
	public void tearDown() {
		container.close();
	}

	/**
	 * Test of addAccount method, of class AccountManager.
	 */
	@Test
	public void testAddAccount() throws Exception {
		System.out.println("addAccount");
		AccountManagerLocal instance = (AccountManagerLocal)container.getContext().lookup("java:global/classes/AccountManager");
		instance.addAccount();
		
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of removeAccount method, of class AccountManager.
	 */
	@Test
	public void testRemoveAccount() throws Exception {
		System.out.println("removeAccount");
		AccountManagerLocal instance = (AccountManagerLocal)container.getContext().lookup("java:global/classes/AccountManager");
//		instance.removeAccount(accountId);
		
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of importGroupsAndContacts method, of class AccountManager.
	 */
	@Test
	public void testImportGroupsAndContacts() throws Exception {
		System.out.println("importGroupsAndContacts");
		AccountManagerLocal instance = (AccountManagerLocal)container.getContext().lookup("java:global/classes/AccountManager");
		instance.importGroupsAndContacts();
		
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}
}
