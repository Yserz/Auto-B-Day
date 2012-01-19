package de.fhb.autobday.commons;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author Michael Koppen <koppen@fh-brandenburg.de>
 */
public class HashHelperTest {

	public HashHelperTest() {
	}

	/**
	 * Test the default konstruktor
	 */
	@Test
	public void testKonstruktor() {
		assertEquals(true, new HashHelper() instanceof HashHelper);
	}

	/**
	 * Test of calcSHA1 method, of class HashHelper.
	 */
	@Test
	public void testCalcSHA1() throws Exception {
		System.out.println("calcSHA1");
		assertEquals("Hash(SHA1) of 'hallo'", "fd4cef7a4e607f1fcc920ad6329a6df2df99a4e8", HashHelper.calcSHA1("hallo"));
		assertEquals("Hash(SHA1) of nothing", "da39a3ee5e6b4b0d3255bfef95601890afd80709", HashHelper.calcSHA1(""));
	}

	/**
	 * Test of calcMD5 method, of class HashHelper.
	 */
	@Test
	public void testCalcMD5() throws Exception {
		System.out.println("calcMD5");
		assertEquals("Hash(MD5) of 'hallo'", "598d4c200461b81522a3328565c25f7c", HashHelper.calcMD5("hallo"));
		assertEquals("Hash(MD5) of nothing", "d41d8cd98f00b204e9800998ecf8427e", HashHelper.calcMD5(""));
	}
}
