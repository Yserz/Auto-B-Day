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
		assertEquals("Hash(SHA1) of 'hallo'",		"fd4cef7a4e607f1fcc920ad6329a6df2df99a4e8", HashHelper.calcSHA1("hallo"));
		assertEquals("Hash(SHA1) of nothing",		"da39a3ee5e6b4b0d3255bfef95601890afd80709", HashHelper.calcSHA1(""));
	}

	/**
	 * Test of calcMD5 method, of class HashHelper.
	 */
	@Test
	public void testCalcMD5() throws Exception {
		System.out.println("calcMD5");
		assertEquals("Hash(MD5) of 'hallo'",		"598d4c200461b81522a3328565c25f7c", HashHelper.calcMD5("hallo"));
		assertEquals("Hash(MD5) of nothing",		"d41d8cd98f00b204e9800998ecf8427e", HashHelper.calcMD5(""));
	}
}
