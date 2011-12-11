package de.fhb.autobday.commons;

import java.sql.Date;
import static org.junit.Assert.assertEquals;
import org.junit.*;

/**
 *
 * @author Michael Koppen <koppen@fh-brandenburg.de>
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
	public void testConvertBirthdayNormalDateLengthTen() {
		System.out.println("ConvertBirthdayNormalDateLengthTen");
		String gbirthday = "1989-01-01";
		Date expResult = new Date(89, 0, 1);
		Date result = GoogleBirthdayConverter.convertBirthday(gbirthday);
		assertEquals(expResult, result);
	}
	
	@Test
	public void testConvertBirthdaySpecialDateLengthTen() {
		System.out.println("ConvertBirthdaySpecialDateLengthTen");
		String gbirthday = "2011-02-29";
		Date expResult = new Date(111, 1, 29);
		Date result = GoogleBirthdayConverter.convertBirthday(gbirthday);
		assertEquals(expResult, result);
	}
	
	@Test
	public void testConvertBirthdayNewYearLengthSeven() {
		System.out.println("ConvertBirthdayNewYearLengthSeven");
		String gbirthday = "--01-01";
		Date expResult = new Date(-1900, 0, 1);
		Date result = GoogleBirthdayConverter.convertBirthday(gbirthday);
		assertEquals(expResult, result);
	}
	
	@Test
	public void testConvertBirthdayYearEndLengthSeven() {
		System.out.println("ConvertBirthdayYearEndLengthSeven");
		String gbirthday = "--12-31";
		Date expResult = new Date(-1900, 11, 31);
		Date result = GoogleBirthdayConverter.convertBirthday(gbirthday);
		assertEquals(expResult, result);
	}
}
