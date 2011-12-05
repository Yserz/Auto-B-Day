/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhb.autobday.commons;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.text.DateFormatter;

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
		String gbirthday = "1989-01-01";
		Date expResult = new Date(89, 0, 1);
		Date result = GoogleBirthdayConverter.convertBirthday(gbirthday);
		assertEquals(expResult, result);
		
		gbirthday = "2011-02-29";
		expResult = new Date(111, 1, 29);
		result = GoogleBirthdayConverter.convertBirthday(gbirthday);
		assertEquals(expResult, result);
		
		gbirthday = "--01-01";
		expResult = new Date(-1900, 0, 1);
		result = GoogleBirthdayConverter.convertBirthday(gbirthday);
		assertEquals(expResult, result);
		
		gbirthday = "--12-31";
		expResult = new Date(-1900, 11, 31);
		result = GoogleBirthdayConverter.convertBirthday(gbirthday);
		assertEquals(expResult, result);
	}
}
