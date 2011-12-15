package de.fhb.autobday.manager;

import java.util.List;
import javax.ejb.embeddable.EJBContainer;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.*;

/**
 *
 * @author Michael Koppen <koppen@fh-brandenburg.de>
 */
public class ABDManagerTest {
	private EJBContainer container;
	
	public ABDManagerTest() {
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
	 * Test of getAllUser method, of class ABDManager.
	 */
	@Test
	public void testGetAllUser() throws Exception {
		System.out.println("getAllUser");
		
		ABDManagerLocal instance = (ABDManagerLocal)container.getContext().lookup("java:global/classes/ABDManager");
		List expResult = null;
		List result = instance.getAllUser();
		assertEquals(expResult, result);
		
		// TODO review the generated test code and remove the default call to fail.
		//fail("The test case is a prototype.");
	}

	/**
	 * Test of getAllGroups method, of class ABDManager.
	 */
	@Test
	public void testGetAllGroups() throws Exception {
		System.out.println("getAllGroups");
		ABDManagerLocal instance = (ABDManagerLocal)container.getContext().lookup("java:global/classes/ABDManager");
		List expResult = null;
		List result = instance.getAllGroups();
		assertEquals(expResult, result);
		
		// TODO review the generated test code and remove the default call to fail.
		//fail("The test case is a prototype.");
	}

	/**
	 * Test of getAllAccountdata method, of class ABDManager.
	 */
	@Test
	public void testGetAllAccountdata() throws Exception {
		System.out.println("getAllAccountdata");
		ABDManagerLocal instance = (ABDManagerLocal)container.getContext().lookup("java:global/classes/ABDManager");
		List expResult = null;
		List result = instance.getAllAccountdata();
		assertEquals(expResult, result);
		
		// TODO review the generated test code and remove the default call to fail.
		//fail("The test case is a prototype.");
	}

	/**
	 * Test of getAllContacts method, of class ABDManager.
	 */
	@Test
	public void testGetAllContacts() throws Exception {
		System.out.println("getAllContacts");
		ABDManagerLocal instance = (ABDManagerLocal)container.getContext().lookup("java:global/classes/ABDManager");
		List expResult = null;
		List result = instance.getAllContacts();
		assertEquals(expResult, result);
		
		// TODO review the generated test code and remove the default call to fail.
		//fail("The test case is a prototype.");
	}

	/**
	 * Test of hallo method, of class ABDManager.
	 */
	@Test
	public void testHallo() throws Exception {
		System.out.println("hallo");
		ABDManagerLocal instance = (ABDManagerLocal)container.getContext().lookup("java:global/classes/ABDManager");
		String expResult = "";
		String result = instance.hallo();
		assertEquals(expResult, result);
		
		// TODO review the generated test code and remove the default call to fail.
		//fail("The test case is a prototype.");
	}
}
