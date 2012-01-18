package de.fhb.autobday.commons;

import static org.junit.Assert.assertEquals;
import org.junit.*;

/**
 *
 * @author Michael Koppen <koppen@fh-brandenburg.de>
 */
@Ignore
public class CipherHelperTest {
	
	public CipherHelperTest() {
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
	 * Test of cipher method, of class CipherHelper.
	 */
	@Test
	public void testCipher() throws Exception {
		System.out.println("cipher");
		String raw = "bennol";
		String key = "01234567";
		String expResult = "ì?gc≥£uê";
		String result = CipherHelper.cipher(raw, key);
		System.out.println(result);
		assertEquals(expResult, result);
	}

	/**
	 * Test of decipher method, of class CipherHelper.
	 */
	@Test
	public void testDecipher() throws Exception {
		System.out.println("decipher");
		String raw = "ì?gc≥£uê";
		String key = "01234567";
		String expResult = "bennol";
		String result = CipherHelper.decipher(raw, key);
		System.out.println(result);
		assertEquals(expResult, result);
	}
	//TODO Fehlerfaelle testen
}
