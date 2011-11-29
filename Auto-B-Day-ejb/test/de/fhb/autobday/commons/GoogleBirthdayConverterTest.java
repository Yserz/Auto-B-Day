/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhb.autobday.commons;

import java.sql.Date;
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
public class GoogleBirthdayConverterTest {
	
	public GoogleBirthdayConverterTest() {
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
	 * Test of convertBirthday method, of class GoogleBirthdayConverter.
	 */
	@Test
	public void testConvertBirthday() {
		System.out.println("convertBirthday");
		String gbirthday = "";
		Date expResult = null;
		Date result = GoogleBirthdayConverter.convertBirthday(gbirthday);
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}
}
