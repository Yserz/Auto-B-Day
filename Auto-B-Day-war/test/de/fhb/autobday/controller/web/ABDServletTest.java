package de.fhb.autobday.controller.web;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.*;

/**
 *
 * @author @author Michael Koppen <koppen@fh-brandenburg.de>
 */
public class ABDServletTest {
	
	public ABDServletTest() {
	}

	@BeforeClass
	public static void setUpClass() throws Exception {
	}

	@AfterClass
	public static void tearDownClass() throws Exception {
	}
	
	@Before
	public void setUp() {
	}
	
	@After
	public void tearDown() {
	}

	/**
	 * Test of doGet method, of class ABDServlet.
	 */
	@Test
	public void testDoGet() throws Exception {
		System.out.println("doGet");
	}

	/**
	 * Test of doPost method, of class ABDServlet.
	 */
	@Test
	public void testDoPost() throws Exception {
		System.out.println("doPost");
	}

	/**
	 * Test of getServletInfo method, of class ABDServlet.
	 */
	@Test
	public void testGetServletInfo() {
		System.out.println("getServletInfo");
	}

	/**
	 * Test of getOperation method, of class ABDServlet.
	 */
	@Test
	public void testGetOperation() {
		System.out.println("getOperation");
	}

	/**
	 * Test of init method, of class ABDServlet.
	 */
	@Test
	public void testInit() throws Exception {
		System.out.println("init");
	}
}
