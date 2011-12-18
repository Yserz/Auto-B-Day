package de.fhb.autobday.commons;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.*;

/**
 *
 * @author 
 * Michael Koppen <koppen@fh-brandenburg.de>
 * Andy Klay <klay@fh-brandenburg.de>
 * 
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
	public void testIsEmailStandardMail() {
		System.out.println("testIsEmailStandardMail");
		
		assertTrue("standard mail",					EMailValidator.isEmail("test@dawanda.com"));
	}
	
	/**
	 * Test of isEmail method, of class EMailValidator.
	 */
	@Test
	public void testIsEmailMailWithNumbers() {
		System.out.println("testIsEmailMailWithNumbers");

		assertTrue("mail with numbers",				EMailValidator.isEmail("test123@dawanda.com"));
	}
	
	/**
	 * Test of isEmail method, of class EMailValidator.
	 */
	@Test
	public void testIsEmailMailWithNumberInDomain() {
		System.out.println("testIsEmailMailWithNumberInDomain");

		assertTrue("mail with number in domain",	EMailValidator.isEmail("test@123dawanda.com"));
	}
	
	/**
	 * Test of isEmail method, of class EMailValidator.
	 */
	@Test
	public void testIsEmailMailWithoutDomain() {
		System.out.println("testIsEmailMailWithoutDomain");

		assertFalse("mail without domain",			EMailValidator.isEmail("test@dawanda"));
	}
	
	/**
	 * Test of isEmail method, of class EMailValidator.
	 */
	@Test
	public void testIsEmailMailTooShort() {
		System.out.println("testIsEmailMailTooShort");
		
		assertFalse("mail too short",				EMailValidator.isEmail("t@dawanda.com"));
	}

	/**
	 * Test of isGoogleMail method, of class EMailValidator.
	 */
	@Test
	public void testIsGoogleMailStandardGoogleMail() {
		System.out.println("testIsGoogleMailStandardGoogleMail");
		
		assertTrue("standard googlemail",			EMailValidator.isGoogleMail("test@googlemail.com"));
	}
	
	
	/**
	 * Test of isGoogleMail method, of class EMailValidator.
	 */
	@Test
	public void testIsGoogleMailStandardGmail() {
		System.out.println("testIsGoogleMailStandardGmail");
		
		assertTrue("standard gmail",				EMailValidator.isGoogleMail("test@gmail.com"));
	}
	
	/**
	 * Test of isGoogleMail method, of class EMailValidator.
	 */
	@Test
	public void testIsGoogleMailMailWithoutDomain() {
		System.out.println("testIsGoogleMailMailWithoutDomain");

		assertFalse("mail without domain",			EMailValidator.isGoogleMail("test@gmail"));
	}
	
	/**
	 * Test of isGoogleMail method, of class EMailValidator.
	 */
	@Test
	public void testIsGoogleMailMailWithoutDomainTwo() {
		System.out.println("testIsGoogleMailMailWithoutDomainTwo");
		
		assertFalse("mail without domain",			EMailValidator.isGoogleMail("test@googlemail"));
	}
	
	/**
	 * Test of isGoogleMail method, of class EMailValidator.
	 */
	@Test
	public void testIsGoogleMailToo() {
		System.out.println("testIsGoogleMailToo");

		assertFalse("mail too",						EMailValidator.isGoogleMail("t@googlemail.com"));
	}
	
	/**
	 * Test of isGoogleMail method, of class EMailValidator.
	 */
	@Test
	public void testIsGoogleMailmailTooShort() {
		System.out.println("testIsGoogleMailmailTooShort");

		assertFalse("mail too short",				EMailValidator.isGoogleMail("t@gmail.com"));
	}

	
}