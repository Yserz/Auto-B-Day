package de.fhb.autobday.commons;

import de.fhb.autobday.exception.commons.CanNotConvetGoogleBirthdayException;
import java.sql.Date;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 * This testclass test the GoogleBirthdayConverter class
 *
 * @author Michael Koppen <koppen@fh-brandenburg.de> Andy Klay
 * <klay@fh-brandenburg.de>
 *
 */
public class GoogleBirthdayConverterTest {

	public GoogleBirthdayConverterTest() {
	}

	/**
	 * Test the default konstruktor
	 */
	@Test
	public void testKonstruktor() {
		assertEquals(true, new GoogleBirthdayConverter() instanceof GoogleBirthdayConverter);
	}

	/**
	 * Test of convertBirthday method, of class GoogleBirthdayConverter.
	 */
	@Test
	public void testConvertBirthdayNormalDateLengthTen() throws Exception {
		System.out.println("ConvertBirthdayNormalDateLengthTen");

		//prepare variables to test
		String gbirthday = "1989-01-01";
		@SuppressWarnings("deprecation")
		Date expResult = new Date(89, 0, 1);
		Date result;

		//call method to test
		result = GoogleBirthdayConverter.convertBirthday(gbirthday);

		//verify
		assertEquals(expResult, result);
	}

	/**
	 * Test of convertBirthday method, of class GoogleBirthdayConverter.
	 */
	@Test
	public void testConvertBirthdaySpecialDateLengthTen() throws Exception {
		System.out.println("ConvertBirthdaySpecialDateLengthTen");

		//prepare variables to test
		String gbirthday = "2011-02-29";
		@SuppressWarnings("deprecation")
		Date expResult = new Date(111, 1, 29);
		Date result;

		//call method to test
		result = GoogleBirthdayConverter.convertBirthday(gbirthday);

		//verify
		assertEquals(expResult, result);
	}

	/**
	 * Test of convertBirthday method, of class GoogleBirthdayConverter.
	 */
	@Test
	public void testConvertBirthdayNewYearLengthSeven() throws Exception {
		System.out.println("ConvertBirthdayNewYearLengthSeven");

		//prepare variables to test
		String gbirthday = "--01-01";
		@SuppressWarnings("deprecation")
		Date expResult = new Date(-1900, 0, 1);
		Date result;

		//call method to test
		result = GoogleBirthdayConverter.convertBirthday(gbirthday);

		//verify
		assertEquals(expResult, result);
	}

	/**
	 * Test of convertBirthday method, of class GoogleBirthdayConverter.
	 */
	@Test
	public void testConvertBirthdayYearEndLengthSeven() throws Exception {
		System.out.println("ConvertBirthdayYearEndLengthSeven");

		//prepare variables to test
		String gbirthday = "--12-31";
		@SuppressWarnings("deprecation")
		Date expResult = new Date(-1900, 11, 31);
		Date result;

		//call method to test
		result = GoogleBirthdayConverter.convertBirthday(gbirthday);

		//verify
		assertEquals(expResult, result);
	}

	/**
	 * Test of convertBirthday method, of class GoogleBirthdayConverter.
	 */
	@Test
	public void testConvertBirthdayLengthNull() throws Exception {
		System.out.println("testConvertBirthdayLengthNull");

		//prepare variables to test
		String gbirthday = "";
		Date expResult = null;
		Date result;

		//call method to test
		result = GoogleBirthdayConverter.convertBirthday(gbirthday);

		//verify
		assertEquals(expResult, result);
	}

	/**
	 * Test of convertBirthday method, of class GoogleBirthdayConverter. This
	 * test provokes a CanNotConvetGoogleBirthdayException!
	 */
	@Test(expected = CanNotConvetGoogleBirthdayException.class)
	public void testConvertBirthdaySpecialDateLengthTenThrowNumberFormatExceptionFirstPossibility() throws Exception {
		System.out.println("testConvertBirthdaySpecialDateLengthTenThrowNumberFormatExceptionFirstPossibility");

		//prepare variables to test
		String gbirthday = "1989-0%-01";

		//call method to test
		GoogleBirthdayConverter.convertBirthday(gbirthday);
	}

	/**
	 * Test of convertBirthday method, of class GoogleBirthdayConverter. This
	 * test provokes a CanNotConvetGoogleBirthdayException!
	 */
	@Test(expected = CanNotConvetGoogleBirthdayException.class)
	public void testConvertBirthdaySpecialDateLengthTenThrowNumberFormatExceptionSecoundPossibility() throws Exception {
		System.out.println("testConvertBirthdaySpecialDateLengthTenThrowNumberFormatExceptionSecoundPossibility");

		//prepare variables to test
		String gbirthday = "198%-01-01";

		//call method to test
		GoogleBirthdayConverter.convertBirthday(gbirthday);
	}

	/**
	 * Test of convertBirthday method, of class GoogleBirthdayConverter. This
	 * test provokes a CanNotConvetGoogleBirthdayException!
	 */
	@Test(expected = CanNotConvetGoogleBirthdayException.class)
	public void testConvertBirthdaySpecialDateLengthTenThrowNumberFormatExceptionThirdPossibility() throws Exception {
		System.out.println("testConvertBirthdaySpecialDateLengthTenThrowNumberFormatExceptionThirdPossibility");

		//prepare variables to test
		String gbirthday = "1980-01-1%";

		//call method to test
		GoogleBirthdayConverter.convertBirthday(gbirthday);
	}

	/**
	 * Test of convertBirthday method, of class GoogleBirthdayConverter. This
	 * test provokes a CanNotConvetGoogleBirthdayException!
	 */
	@Test(expected = CanNotConvetGoogleBirthdayException.class)
	public void testConvertBirthdayNewYearLengthSevenThrowNumberFormatExceptionFirstPossibility() throws Exception {
		System.out.println("testConvertBirthdayNewYearLengthSevenThrowNumberFormatExceptionFirstPossibility");

		//prepare variables to test
		String gbirthday = "--0%-01";

		//call method to test
		GoogleBirthdayConverter.convertBirthday(gbirthday);
	}

	/**
	 * Test of convertBirthday method, of class GoogleBirthdayConverter. This
	 * test provokes a CanNotConvetGoogleBirthdayException!
	 */
	@Test(expected = CanNotConvetGoogleBirthdayException.class)
	public void testConvertBirthdayNewYearLengthSevenThrowNumberFormatExceptionSecoundPossibility() throws Exception {
		System.out.println("testConvertBirthdayNewYearLengthSevenThrowNumberFormatExceptionSecoundPossibility");

		//prepare variables to test
		String gbirthday = "--02-0%";

		//call method to test
		GoogleBirthdayConverter.convertBirthday(gbirthday);
	}

	/**
	 * Test with a Null Object
	 */
	@Test
	public void testConvertBirthdayNULL() {
		try {
			assertEquals(null, GoogleBirthdayConverter.convertBirthday(null));
		} catch (CanNotConvetGoogleBirthdayException e) {
			e.printStackTrace();
		}
	}
}