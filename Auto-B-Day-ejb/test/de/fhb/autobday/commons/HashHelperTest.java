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
public class HashHelperTest {
	
	public HashHelperTest() {
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
	 * Test of calcSHA1 method, of class HashHelper.
	 */
	@Test
	public void testCalcSHA1() throws Exception {
		System.out.println("calcSHA1");
		String pw = "hallo";
		HashHelper instance = new HashHelper();
		String expResult = "fd4cef7a4e607f1fcc920ad6329a6df2df99a4e8";
		String result = instance.calcSHA1(pw);
		assertEquals(expResult, result);
	}

	/**
	 * Test of calcMD5 method, of class HashHelper.
	 */
	@Test
	public void testCalcMD5() throws Exception {
		System.out.println("calcMD5");
		String pw = "hallo";
		HashHelper instance = new HashHelper();
		String expResult = "598d4c200461b81522a3328565c25f7c";
		String result = instance.calcMD5(pw);
		assertEquals(expResult, result);
	}
}
