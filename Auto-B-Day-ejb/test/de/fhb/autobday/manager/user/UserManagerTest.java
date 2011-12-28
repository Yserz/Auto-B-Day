package de.fhb.autobday.manager.user;

import javax.persistence.NoResultException;

import com.stvconsultants.easygloss.javaee.JavaEEGloss;

import de.fhb.autobday.commons.EMailValidator;
import de.fhb.autobday.commons.PasswordGenerator;
import de.fhb.autobday.commons.PasswortGeneratorTest;
import de.fhb.autobday.dao.AbdUserFacade;
import de.fhb.autobday.data.AbdUser;
import de.fhb.autobday.exception.user.*;
import de.fhb.autobday.manager.mail.MailManagerLocal;
import org.easymock.EasyMock;
import static org.junit.Assert.assertEquals;

import org.hibernate.validator.constraints.impl.EmailValidator;
import org.junit.*;
import org.junit.runner.RunWith;
import org.powermock.api.easymock.PowerMock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * Tests the userManager class and their methods.
 *
 * @author Michael Koppen <koppen@fh-brandenburg.de>
 * 
 * 
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({PasswordGenerator.class, EMailValidator.class})
public class UserManagerTest {

	private JavaEEGloss gloss;
	
	private UserManager managerUnderTest;
	
	private AbdUserFacade userDAOMock;
	
	private MailManagerLocal mailManagerMock;
	
	public UserManagerTest() {
	}

	@BeforeClass
	public static void setUpClass() throws Exception {
	}

	@AfterClass
	public static void tearDownClass() throws Exception {
	}
	
	@Before
	public void setUp() {
		gloss= new JavaEEGloss();
		
		//create Mocks
		userDAOMock = EasyMock.createMock(AbdUserFacade.class);
		mailManagerMock = EasyMock.createMock(MailManagerLocal.class);
		
		//set Objekts to inject
		gloss.addEJB(userDAOMock);
		gloss.addEJB(mailManagerMock);
		
		//create Manager with Mocks
		managerUnderTest=gloss.make(UserManager.class);
		PowerMock.mockStatic(EMailValidator.class);
		PowerMock.mockStatic(PasswordGenerator.class);
	}
	
	@After
	public void tearDown() {
		
	}

	/**
	 * Test of getUser method, of class UserManager.
	 */
	@Test
	public void testGetUser() throws Exception {
		System.out.println("getUser");
		
		//prepare test variables
		int userid = 1;
		AbdUser expResult = new AbdUser(1);
		
		// Setting up the expected value of the method call of Mockobject
		EasyMock.expect(userDAOMock.find(userid)).andReturn(new AbdUser(1));
		
		// Setup is finished need to activate the mock
		EasyMock.replay(userDAOMock);
		
		// verify	
		assertEquals(expResult, managerUnderTest.getUser(1));
		EasyMock.verify(userDAOMock);
	}

	/**
	 * Test of login method, of class UserManager.
	 */
	@Test
	public void testLoginShouldBeEquals() throws Exception {
		System.out.println("loginShouldBeEquals");
		
		//prepare test variables
		String loginName = "ott";
		String password = "1234";
		
		AbdUser user = new AbdUser(1, "ott", "1234", null, "Ott", "Chris");
		
		// Setting up the expected value of the method call of Mockobject
		EasyMock.expect(userDAOMock.findUserByUsername(loginName)).andReturn(user);
		
		// Setup is finished need to activate the mock
		EasyMock.replay(userDAOMock);
		
		// verify	
		assertEquals(user, managerUnderTest.login(loginName, password));
		EasyMock.verify(userDAOMock);
	}
	
	/**
	 * Test of login method, of class UserManager.
	 */
	@Test(expected = IncompleteLoginDataException.class)
	public void testLoginShouldThrowIncompleteLoginDataExceptionPasswordNull() throws Exception {
		System.out.println("testLoginShouldThrowIncompleteLoginDataExceptionPasswordNull");
		
		//prepare test variables
		String loginName = "ott";
		String password = null;
		
		AbdUser user = new AbdUser(1, "ott", "1234", null, "Ott", "Chris");
		
		// verify	
		assertEquals(user, managerUnderTest.login(loginName, password));
	}
	
	/**
	 * Test of login method, of class UserManager.
	 */
	@Test(expected = IncompleteLoginDataException.class)
	public void testLoginShouldThrowIncompleteLoginDataExceptionPasswordEmpty() throws Exception {
		System.out.println("testLoginShouldThrowIncompleteLoginDataExceptionPasswordNull");
		
		//prepare test variables
		String loginName = "ott";
		String password = "";
		
		AbdUser user = new AbdUser(1, "ott", "1234", null, "Ott", "Chris");
		
		// verify	
		assertEquals(user, managerUnderTest.login(loginName, password));
	}
	
	/**
	 * Test of login method, of class UserManager.
	 */
	@Test(expected = IncompleteLoginDataException.class)
	public void testLoginShouldThrowIncompleteLoginDataExceptionLoginnameNull() throws Exception {
		System.out.println("testLoginShouldThrowIncompleteLoginDataExceptionLoginnameNull");
		
		//prepare test variables
		String loginName = "";
		String password = "1234";
		
		AbdUser user = new AbdUser(1, "ott", "1234", null, "Ott", "Chris");
		
		// Setting up the expected value of the method call of Mockobject
		EasyMock.expect(userDAOMock.findUserByUsername(loginName)).andThrow(new NoResultException());
				
		// Setup is finished need to activate the mock
		EasyMock.replay(userDAOMock);
		
		// verify	
		assertEquals(user, managerUnderTest.login(loginName, password));
	}
	
	
	
	/**
	 * Test of login method, of class UserManager.
	 */
	@Test(expected = IncompleteLoginDataException.class)
	public void testLoginShouldThrowIncompleteLoginDataExceptionInvalidLoginname() throws Exception {
		System.out.println("testLoginShouldThrowIncompleteLoginDataExceptionInvalidLoginname");
		
		//prepare test variables
		String loginName = "ott";
		String password = "1234";
		
		AbdUser user = new AbdUser(1, "ott", "1234", null, "Ott", "Chris");
		
		// Setting up the expected value of the method call of Mockobject
		EasyMock.expect(userDAOMock.findUserByUsername(loginName)).andThrow(new NoResultException());
		
		// Setup is finished need to activate the mock
		EasyMock.replay(userDAOMock);
		
		// verify	
		assertEquals(user, managerUnderTest.login(loginName, password));
		EasyMock.verify(userDAOMock);
	}
	
	/**
	 * Test of login method, of class UserManager.
	 */
	@Test(expected = UserNotFoundException.class)
	public void testLoginShouldThrowUserNotFoundException() throws Exception {
		System.out.println("loginShouldThrowUserNotFoundException");
		
		//prepare test variables
		String loginName = "ott";
		String password = "1234";
		
		AbdUser user = new AbdUser(1, "ott", "1234", null, "Ott", "Chris");
		
		// Setting up the expected value of the method call of Mockobject
		EasyMock.expect(userDAOMock.findUserByUsername(loginName)).andReturn(null);
		
		// Setup is finished need to activate the mock
		EasyMock.replay(userDAOMock);
		
		// verify	
		assertEquals(user, managerUnderTest.login(loginName, password));
		EasyMock.verify(userDAOMock);
	}
	
	/**
	 * Test of login method, of class UserManager.
	 */
	@Test(expected = PasswordInvalidException.class)
	public void testLoginShouldThrowPasswordInvalidException() throws Exception {
		System.out.println("loginShouldThrowPasswordInvalidException");
		
		//prepare test variables
		String loginName = "ott";
		String password = "1234";
		
		AbdUser user = new AbdUser(1, "ott", "123", null, "Ott", "Chris");
		
		// Setting up the expected value of the method call of Mockobject
		EasyMock.expect(userDAOMock.findUserByUsername(loginName)).andReturn(user);
		
		// Setup is finished need to activate the mock
		EasyMock.replay(userDAOMock);
		
		// verify	
		assertEquals(user, managerUnderTest.login(loginName, password));
		EasyMock.verify(userDAOMock);
	}
	
	
	
	/**
	 * Test of logout method, of class UserManager.
	 */
	@Test
	public void testLogout() throws Exception {
		System.out.println("testLogout");
		
		
		// TODO review the generated test code and remove the default call to fail.
		//fail("The test case is a prototype.");
	}
	
	
	/**
	 * Test of register method, of class UserManager.
	 */
	@Test
	
	public void testRegister() throws Exception {
		System.out.println("testRegister");
		
		//prepare test variables
		String firstName = "biene";
		String name = "maja";
		String userName = "summsesum";
		String mail = "biene@maja.com";
		
		// Setting up the expected value of the method call of Mockobject
		EasyMock.expect(EMailValidator.isEmail(mail)).andReturn(true);
		PowerMock.replay(EMailValidator.class);
		
		EasyMock.expect(PasswordGenerator.generateSalt()).andReturn("4aSe5");
		PowerMock.replay(PasswordGenerator.class);

		userDAOMock.create((AbdUser)EasyMock.anyObject());
		
		
		
		//TODO funktioniert noch nicht
//		mailManagerMock.sendBdayMail((String) EasyMock.anyObject(), mail,(String) EasyMock.anyObject(), (String) EasyMock.anyObject());
		
		// Setup is finished need to activate the mock
		EasyMock.replay(userDAOMock);
//		EasyMock.replay(mailManagerMock);
		
		//call method to test
		managerUnderTest.register(firstName, name, userName, mail);
		
		// verify	
		EasyMock.verify(userDAOMock);
		PowerMock.verify(EMailValidator.class);
//		EasyMock.verify(mailManagerMock);		
	}
	
	
	/**
	 * Test of register method, of class UserManager.
	 * This test provokes a IncompleteUserRegisterException!
	 */
	@Test(expected = IncompleteUserRegisterException.class)
	public void testRegisterThrowIncompleteUserRegisterExceptionFirst() throws Exception {
		System.out.println("testRegister");
		
		//prepare test variables
		String firstName = null;
		String name = "maja";
		String userName = "summsesum";
		String mail = "biene@maja.com";
		
		EasyMock.expect(EMailValidator.isEmail(mail)).andReturn(true);
		PowerMock.replay(EmailValidator.class);
		//call method to test
		managerUnderTest.register(firstName, name, userName, mail);
		PowerMock.verify(EMailValidator.class);
	}
	
	/**
	 * Test of register method, of class UserManager.
	 * This test provokes a IncompleteUserRegisterException!
	 */
	@Test(expected = IncompleteUserRegisterException.class)
	public void testRegisterThrowIncompleteUserRegisterExceptionSecound() throws Exception {
		System.out.println("testRegister");
		
		//prepare test variables
		String firstName = "biene";
		String name = null;
		String userName = "summsesum";
		String mail = "biene@maja.com";
		
		//call method to test
		managerUnderTest.register(firstName, name, userName, mail);
		
	}
	
	/**
	 * Test of register method, of class UserManager.
	 * This test provokes a IncompleteUserRegisterException!
	 */
	@Test(expected = IncompleteUserRegisterException.class)
	public void testRegisterThrowIncompleteUserRegisterExceptionThird() throws Exception {
		System.out.println("testRegister");
		
		//prepare test variables
		String firstName = "biene";
		String name = "maja";
		String userName = null;
		String mail = "biene@maja.com";
		
		//call method to test
		managerUnderTest.register(firstName, name, userName, mail);
		
	}
	
	/**
	 * Test of register method, of class UserManager.
	 * This test provokes a IncompleteUserRegisterException!
	 */
	@Test(expected = IncompleteUserRegisterException.class)
	public void testRegisterThrowIncompleteUserRegisterExceptionFourth() throws Exception {
		System.out.println("testRegister");
		
		//prepare test variables
		String firstName = "biene";
		String name = "maja";
		String userName = "summsesum";
		String mail = null;
		
		//call method to test
		managerUnderTest.register(firstName, name, userName, mail);
		
	}
	
	/**
	 * Test of register method, of class UserManager.
	 * This test provokes a NoValidUserNameException!
	 */
	@Test(expected = NoValidUserNameException.class)
	public void testRegisterThrowNoValidUserNameExceptionTooShort() throws Exception {
		System.out.println("testRegisterThrowNoValidUserNameExceptionTooShort");
		
		//prepare test variables
		String firstName = "biene";
		String name = "maja";
		String userName = "su";
		
		//call method to test
		managerUnderTest.register(firstName, name, userName, null);
		
	}
	
	/**
	 * Test of register method, of class UserManager.
	 * This test provokes a NoValidUserNameException!
	 */
	@Test(expected = IncompleteUserRegisterException.class)
	public void testRegisterThrowIncompleteUserRegisterException() throws Exception {
		System.out.println("testRegisterThrowIncompleteUserRegisterException");
		
		//prepare test variables
		String firstName = "biene";
		String name = "maja";
		String userName = "sumsebiene";
		String mail = "bienemaja.com";
		
		EasyMock.expect(EMailValidator.isEmail(mail)).andReturn(false);
		PowerMock.replay(EmailValidator.class);
		
		//call method to test
		managerUnderTest.register(firstName, name, userName, mail);
		PowerMock.verify(EMailValidator.class);
	}

	
}