/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhb.autobday.commons;

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
public class EMailValidatorTest {
	
	public EMailValidatorTest() {
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
	 * Test of isEmail method, of class EMailValidator.
	 */
	@Test
	public void testIsEmail() {
		System.out.println("isEmail");
		String mailAddress = "";
		boolean expResult = false;
		boolean result = EMailValidator.isEmail(mailAddress);
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of isGoogleMail method, of class EMailValidator.
	 */
	@Test
	public void testIsGoogleMail() {
		System.out.println("isGoogleMail");
		String mailAddress = "";
		boolean expResult = false;
		boolean result = EMailValidator.isGoogleMail(mailAddress);
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}
}
