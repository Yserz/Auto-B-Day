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
		assertTrue("standard mail",					EMailValidator.isEmail("test@dawanda.com"));
		assertTrue("mail with numbers",				EMailValidator.isEmail("test123@dawanda.com"));
		assertTrue("mail with number in domain",	EMailValidator.isEmail("test@123dawanda.com"));
		assertFalse("mail without domain",			EMailValidator.isEmail("test@dawanda"));
		assertFalse("mail too short",				EMailValidator.isEmail("t@dawanda.com"));
	}

	/**
	 * Test of isGoogleMail method, of class EMailValidator.
	 */
	@Test
	public void testIsGoogleMail() {
		System.out.println("isGoogleMail");
		assertTrue("standard googlemail",			EMailValidator.isEmail("test@googlemail.com"));
		assertTrue("standard gmail",				EMailValidator.isEmail("test@gmail.com"));
		assertFalse("mail without domain",			EMailValidator.isEmail("test@gmail"));
		assertFalse("mail without domain",			EMailValidator.isEmail("test@googlemail"));
		assertFalse("mail too",						EMailValidator.isEmail("t@googlemail.com"));
		assertFalse("mail too short",				EMailValidator.isEmail("t@gmail.com"));
	}
}
