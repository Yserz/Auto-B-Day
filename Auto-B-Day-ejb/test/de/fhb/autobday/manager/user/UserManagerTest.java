package de.fhb.autobday.manager.user;

import de.fhb.autobday.data.AbdUser;
import javax.ejb.embeddable.EJBContainer;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.*;

/**
 *
 * @author Michael Koppen <koppen@fh-brandenburg.de>
 */
public class UserManagerTest {
	private EJBContainer container;
	public UserManagerTest() {
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
	 * Test of getUser method, of class UserManager.
	 */
	@Test
	public void testGetUser() throws Exception {
		System.out.println("getUser");
		int userid = 0;
		
		UserManagerLocal instance = (UserManagerLocal)container.getContext().lookup("java:global/classes/UserManager");
		AbdUser expResult = null;
		AbdUser result = instance.getUser(userid);
		assertEquals(expResult, result);
		
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of login method, of class UserManager.
	 */
	@Test
	public void testLogin() throws Exception {
		System.out.println("login");
		UserManagerLocal instance = (UserManagerLocal)container.getContext().lookup("java:global/classes/UserManager");
//		instance.login(loginName, password);
		
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of logout method, of class UserManager.
	 */
	@Test
	public void testLogout() throws Exception {
		System.out.println("logout");
		UserManagerLocal instance = (UserManagerLocal)container.getContext().lookup("java:global/classes/UserManager");
		instance.logout();
		
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}
}
