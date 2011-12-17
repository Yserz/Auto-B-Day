package de.fhb.autobday.commons;

import java.sql.Date;
import static org.junit.Assert.assertEquals;
import org.junit.*;

import de.fhb.autobday.exception.CanNotConvetGoogleBirthdayException;
import de.fhb.autobday.exception.group.GroupNotFoundException;

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
	public void testConvertBirthdayNormalDateLengthTen() throws Exception{
		System.out.println("ConvertBirthdayNormalDateLengthTen");
		String gbirthday = "1989-01-01";
		Date expResult = new Date(89, 0, 1);
		Date result = GoogleBirthdayConverter.convertBirthday(gbirthday);
		
		//verify
		assertEquals(expResult, result);
	}
	
	@Test
	public void testConvertBirthdaySpecialDateLengthTen() throws Exception{
		System.out.println("ConvertBirthdaySpecialDateLengthTen");
		String gbirthday = "2011-02-29";
		Date expResult = new Date(111, 1, 29);
		Date result = GoogleBirthdayConverter.convertBirthday(gbirthday);
		
		//verify
		assertEquals(expResult, result);
	}
	
	@Test
	public void testConvertBirthdayNewYearLengthSeven() throws Exception{
		System.out.println("ConvertBirthdayNewYearLengthSeven");
		String gbirthday = "--01-01";
		Date expResult = new Date(-1900, 0, 1);
		Date result = GoogleBirthdayConverter.convertBirthday(gbirthday);
		
		//verify
		assertEquals(expResult, result);
	}
	
	@Test
	public void testConvertBirthdayYearEndLengthSeven() throws Exception {
		System.out.println("ConvertBirthdayYearEndLengthSeven");
		String gbirthday = "--12-31";
		Date expResult = new Date(-1900, 11, 31);
		Date result = GoogleBirthdayConverter.convertBirthday(gbirthday);
		
		//verify
		assertEquals(expResult, result);
	}
	
	@Test
	public void testConvertBirthdayLengthNull() throws Exception{
		System.out.println("testConvertBirthdayLengthNull");
		String gbirthday = "";
		Date expResult = null;
		Date result = GoogleBirthdayConverter.convertBirthday(gbirthday);
		
		//verify
		assertEquals(expResult, result);
	}
	
	@Test(expected = CanNotConvetGoogleBirthdayException.class)
	public void testConvertBirthdaySpecialDateLengthTenThrowNumberFormatExceptionFirstPossibility() throws Exception {
		System.out.println("ConvertBirthdayYearEndLengthSeven");
		String gbirthday = "1989-0%-01";
//		Date expResult = null;
		Date result = GoogleBirthdayConverter.convertBirthday(gbirthday);
		
		//verify
//		assertEquals(expResult, result);
	}
	
	@Test(expected = CanNotConvetGoogleBirthdayException.class)
	public void testConvertBirthdaySpecialDateLengthTenThrowNumberFormatExceptionSecoundPossibility() throws Exception {
		System.out.println("ConvertBirthdayYearEndLengthSeven");
		String gbirthday = "198%-01-01";
		Date expResult = null;
		Date result = GoogleBirthdayConverter.convertBirthday(gbirthday);
		
		//verify
//		assertEquals(expResult, result);
	}
	
	@Test(expected = CanNotConvetGoogleBirthdayException.class)
	public void testConvertBirthdaySpecialDateLengthTenThrowNumberFormatExceptionThirdPossibility() throws Exception {
		System.out.println("ConvertBirthdayYearEndLengthSeven");
		String gbirthday = "198%-01-%1";
		Date expResult = null;
		Date result = GoogleBirthdayConverter.convertBirthday(gbirthday);
		
		//verify
//		assertEquals(expResult, result);
	}
	
	@Test(expected = CanNotConvetGoogleBirthdayException.class)
	public void testConvertBirthdayNewYearLengthSevenThrowNumberFormatExceptionFirstPossibility() throws Exception{
		System.out.println("ConvertBirthdayNewYearLengthSeven");
		String gbirthday = "--0%-01";
		Date expResult = new Date(-1900, 0, 1);
		Date result = GoogleBirthdayConverter.convertBirthday(gbirthday);
		
		//verify
//		assertEquals(expResult, result);
	}
	
	
	@Test(expected = CanNotConvetGoogleBirthdayException.class)
	public void testConvertBirthdayNewYearLengthSevenThrowNumberFormatExceptionSecoundPossibility() throws Exception{
		System.out.println("ConvertBirthdayNewYearLengthSeven");
		String gbirthday = "--02-0%";
		Date expResult = new Date(-1900, 0, 1);
		Date result = GoogleBirthdayConverter.convertBirthday(gbirthday);
		
		//verify
//		assertEquals(expResult, result);
	}
}
