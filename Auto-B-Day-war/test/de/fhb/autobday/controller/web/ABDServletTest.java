/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhb.autobday.controller.web;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author MacYser
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
		HttpServletRequest req = null;
		HttpServletResponse resp = null;
		ABDServlet instance = new ABDServlet();
		instance.doGet(req, resp);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of doPost method, of class ABDServlet.
	 */
	@Test
	public void testDoPost() throws Exception {
		System.out.println("doPost");
		HttpServletRequest req = null;
		HttpServletResponse resp = null;
		ABDServlet instance = new ABDServlet();
		instance.doPost(req, resp);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of getServletInfo method, of class ABDServlet.
	 */
	@Test
	public void testGetServletInfo() {
		System.out.println("getServletInfo");
		ABDServlet instance = new ABDServlet();
		String expResult = "";
		String result = instance.getServletInfo();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of getOperation method, of class ABDServlet.
	 */
	@Test
	public void testGetOperation() {
		System.out.println("getOperation");
		HttpServletRequest req = null;
		ABDServlet instance = new ABDServlet();
		String expResult = "";
		String result = instance.getOperation(req);
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of init method, of class ABDServlet.
	 */
	@Test
	public void testInit() throws Exception {
		System.out.println("init");
		ServletConfig conf = null;
		ABDServlet instance = new ABDServlet();
		instance.init(conf);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}
}
