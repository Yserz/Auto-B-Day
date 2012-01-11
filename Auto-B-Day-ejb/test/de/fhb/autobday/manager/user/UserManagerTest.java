package de.fhb.autobday.manager.user;

import static org.easymock.EasyMock.anyObject;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.easymock.EasyMock.expectLastCall;
import static org.junit.Assert.assertEquals;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.mail.Transport;
import javax.persistence.NoResultException;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.easymock.PowerMock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.stvconsultants.easygloss.javaee.JavaEEGloss;

import de.fhb.autobday.commons.EMailValidator;
import de.fhb.autobday.commons.HashHelper;
import de.fhb.autobday.commons.PasswordGenerator;
import de.fhb.autobday.dao.AbdUserFacade;
import de.fhb.autobday.data.AbdAccount;
import de.fhb.autobday.data.AbdUser;
import de.fhb.autobday.exception.HashFailException;
import de.fhb.autobday.exception.mail.FailedToSendMailException;
import de.fhb.autobday.exception.mail.MailException;
import de.fhb.autobday.exception.user.IncompleteLoginDataException;
import de.fhb.autobday.exception.user.IncompleteUserRegisterException;
import de.fhb.autobday.exception.user.NoValidUserNameException;
import de.fhb.autobday.exception.user.PasswordInvalidException;
import de.fhb.autobday.exception.user.UserNotFoundException;
import de.fhb.autobday.manager.mail.GoogleMailManagerLocal;

/**
 * Tests the userManager class and their methods.
 *
 * @author 
 * Andy Klay <klay@fh-brandenburg.de>
 * Christoph Ott
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({PasswordGenerator.class, EMailValidator.class, HashHelper.class, Transport.class})
public class UserManagerTest {

	private JavaEEGloss gloss;
	
	private UserManager managerUnderTest;
	
	private AbdUserFacade userDAOMock;
	
	private GoogleMailManagerLocal mailMock;
	
	public UserManagerTest() {
	}
	
	@Before
	public void setUp() {
		gloss= new JavaEEGloss();
		
		//create Mocks
		userDAOMock = EasyMock.createMock(AbdUserFacade.class);
		mailMock = EasyMock.createMock(GoogleMailManagerLocal.class);
		
		//set Objekts to inject
		gloss.addEJB(userDAOMock);
		gloss.addEJB(mailMock);
		
		//create Manager with Mocks
		managerUnderTest=gloss.make(UserManager.class);
		PowerMock.mockStatic(HashHelper.class);
		PowerMock.mockStatic(PasswordGenerator.class);
		PowerMock.mockStatic(EMailValidator.class);
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
		AbdUser result;
		
		// Setting up the expected value of the method call of Mockobject
		EasyMock.expect(userDAOMock.find(userid)).andReturn(new AbdUser(1));
		
		// Setup is finished need to activate the mock
		EasyMock.replay(userDAOMock);
		
		//call method to test
		result=managerUnderTest.getUser(1);
		
		// verify	
		assertEquals(expResult, result);
		EasyMock.verify(userDAOMock);
	}

	/**
	 * Test of login method, of class UserManager.
	 */
	@Test
	public void testLoginShouldBeEquals() throws Exception {
		System.out.println("loginShouldBeEquals");
		
		//prepare test variables
		String userName = "ott";
		String password = "1234";
		String salt = "testSalt";
		String hash = "blubb";
		AbdUser user;
		
		user = new AbdUser(1, userName, hash, salt, "Ott", "Chris");
		
		// Setting up the expected value of the method call of Mockobject
		EasyMock.expect(userDAOMock.findUserByUsername(userName)).andReturn(user);
		EasyMock.expect(HashHelper.calcSHA1(password+salt)).andReturn(hash);
		
		// Setup is finished need to activate the mock		
		PowerMock.replay(HashHelper.class);
		EasyMock.replay(userDAOMock);
		
		//call method to test
		managerUnderTest.login(userName, password);
		
		// verify	
		EasyMock.verify(userDAOMock);
		PowerMock.verify(HashHelper.class);	
	}
	
	/**
	 * Test of login method, of class UserManager.
	 * This test provokes a IncompleteLoginDataException!
	 */
	@Test(expected = IncompleteLoginDataException.class)
	public void testLoginShouldThrowIncompleteLoginDataExceptionPasswordNull() throws Exception {
		System.out.println("testLoginShouldThrowIncompleteLoginDataExceptionPasswordNull");
		
		//prepare test variables
		String loginName = "ott";
		String password = null;
		AbdUser user, result;
		
		user = new AbdUser(1, "ott", "1234", null, "Ott", "Chris");
		
		//call method to test
		result = managerUnderTest.login(loginName, password);
		 
		// verify	
		assertEquals(user, result);
	}
	
	/**
	 * Test of login method, of class UserManager.
	 * This test provokes a IncompleteLoginDataException!
	 */
	@Test(expected = IncompleteLoginDataException.class)
	public void testLoginShouldThrowIncompleteLoginDataExceptionPasswordEmpty() throws Exception {
		System.out.println("testLoginShouldThrowIncompleteLoginDataExceptionPasswordNull");
		
		//prepare test variables
		String loginName = "ott";
		String password = "";
		AbdUser user, result;
		
		user = new AbdUser(1, "ott", "1234", null, "Ott", "Chris");
		
		//call method to test
		result = managerUnderTest.login(loginName, password);
		
		// verify	
		assertEquals(user, result);
	}
	
	/**
	 * Test of login method, of class UserManager.
	 * This test provokes a IncompleteLoginDataException!
	 */
	@Test(expected = IncompleteLoginDataException.class)
	public void testLoginShouldThrowIncompleteLoginDataExceptionLoginNameNull() throws Exception {
		System.out.println("testLoginShouldThrowIncompleteLoginDataExceptionPasswordNull");
		
		//prepare test variables
		String loginName = null;
		String password = "1234";
		AbdUser user, result;
		
		user = new AbdUser(1, "ott", "1234", null, "Ott", "Chris");
		
		//call method to test
		result = managerUnderTest.login(loginName, password);
		
		// verify	
		assertEquals(user, result);
	}
	
	/**
	 * Test of login method, of class UserManager.
	 * This test provokes a IncompleteLoginDataException!
	 */
	@Test(expected = IncompleteLoginDataException.class)
	public void testLoginShouldThrowIncompleteLoginDataExceptionLoginNameNotFound() throws Exception {
		System.out.println("testLoginShouldThrowIncompleteLoginDataExceptionLoginNameNotFound");
		
		//prepare test variables
		String loginName = "notfound";
		String password = "1234";
		AbdUser user, result;
		
		user = new AbdUser(1, "ott", "1234", null, "Ott", "Chris");
		
		// Setting up the expected value of the method call of Mockobject
		EasyMock.expect(userDAOMock.findUserByUsername(loginName)).andThrow(new NoResultException());
				
		// Setup is finished need to activate the mock
		EasyMock.replay(userDAOMock);
		
		//call method to test
		result = managerUnderTest.login(loginName, password);
		
		// verify	
		assertEquals(user, result);
	}
	
	/**
	 * Test of login method, of class UserManager.
	 * This test provokes a IncompleteLoginDataException!
	 */
	@Test(expected = IncompleteLoginDataException.class)
	public void testLoginShouldThrowIncompleteLoginDataExceptionLoginNameEmpty() throws Exception {
		System.out.println("testLoginShouldThrowIncompleteLoginDataExceptionLoginNameNotFound");
		
		//prepare test variables
		String loginName = "";
		String password = "1234";
		AbdUser user, result;
		
		user = new AbdUser(1, "ott", "1234", null, "Ott", "Chris");
				
		//call method to test
		result = managerUnderTest.login(loginName, password);
		
	}
	
	/**
	 * Test of login method, of class UserManager.
	 * This test provokes a IncompleteLoginDataException!
	 */
	@Test(expected = IncompleteLoginDataException.class)
	public void testLoginShouldThrowIncompleteLoginDataExceptionInvalidLoginname() throws Exception {
		System.out.println("testLoginShouldThrowIncompleteLoginDataExceptionInvalidLoginname");
		
		//prepare test variables
		String loginName = "ott";
		String password = "1234";
		AbdUser user, result;
		
		user = new AbdUser(1, "ott", "1234", null, "Ott", "Chris");
		
		// Setting up the expected value of the method call of Mockobject
		EasyMock.expect(userDAOMock.findUserByUsername(loginName)).andThrow(new NoResultException());
		
		// Setup is finished need to activate the mock
		EasyMock.replay(userDAOMock);
		
		//call method to test
		result = managerUnderTest.login(loginName, password);
		
		// verify	
		assertEquals(user, result);
		EasyMock.verify(userDAOMock);
	}
	
	/**
	 * Test of login method, of class UserManager.
	 * This test provokes a UserNotFoundException!
	 */
	@Test(expected = UserNotFoundException.class)
	public void testLoginShouldThrowUserNotFoundException() throws Exception {
		System.out.println("testLoginShouldThrowUserNotFoundException");
		
		//prepare test variables
		String loginName = "ott";
		String password = "1234";
		AbdUser user, result;
		
		user = new AbdUser(1, "ott", "1234", null, "Ott", "Chris");
		
		// Setting up the expected value of the method call of Mockobject
		EasyMock.expect(userDAOMock.findUserByUsername(loginName)).andReturn(null);
		
		// Setup is finished need to activate the mock
		EasyMock.replay(userDAOMock);
		
		//call method to test
		result = managerUnderTest.login(loginName, password);
		
		// verify	
		assertEquals(user, result);
		EasyMock.verify(userDAOMock);
	}
	
	/**
	 * Test of login method, of class UserManager.
	 * This test provokes a HashFailException!
	 */
	@Test(expected = HashFailException.class)
	public void testLoginShouldThrowHashFailExceptionBecauseOFUnsupportedEncodingException() throws Exception {
		System.out.println("loginShouldThrowHashFailException");
		
		//prepare test variables
		String userName = "ott";
		String password = "1234";
		String salt = "testSalt";
		String hash = "blubb";
		AbdUser user;
		
		user = new AbdUser(1, userName, hash, salt, "Ott", "Chris");
		
		// Setting up the expected value of the method call of Mockobject
		EasyMock.expect(userDAOMock.findUserByUsername(userName)).andReturn(user);
		EasyMock.expect(HashHelper.calcSHA1(password+salt)).andThrow(new UnsupportedEncodingException());
		
		// Setup is finished need to activate the mock		
		PowerMock.replay(HashHelper.class);
		EasyMock.replay(userDAOMock);
		
		//call method to test
		managerUnderTest.login(userName, password);
		
		// verify	
		EasyMock.verify(userDAOMock);
		PowerMock.verify(HashHelper.class);	
	}
	
	/**
	 * Test of login method, of class UserManager.
	 * This test provokes a HashFailException!
	 */
	@Test(expected = HashFailException.class)
	public void testLoginShouldThrowHashFailExceptionBecauseOfNoSuchAlgorithmException() throws Exception {
		System.out.println("loginShouldThrowHashFailException");
		
		//prepare test variables
		String userName = "ott";
		String password = "1234";
		String salt = "testSalt";
		String hash = "blubb";
		AbdUser user;
		
		user = new AbdUser(1, userName, hash, salt, "Ott", "Chris");
		
		// Setting up the expected value of the method call of Mockobject
		EasyMock.expect(userDAOMock.findUserByUsername(userName)).andReturn(user);
		EasyMock.expect(HashHelper.calcSHA1(password+salt)).andThrow(new NoSuchAlgorithmException());
		
		// Setup is finished need to activate the mock		
		PowerMock.replay(HashHelper.class);
		EasyMock.replay(userDAOMock);
		
		//call method to test
		managerUnderTest.login(userName, password);
		
		// verify	
		EasyMock.verify(userDAOMock);
		PowerMock.verify(HashHelper.class);	
	}
	
	/**
	 * Test of login method, of class UserManager.
	 * This test provokes a PasswordInvalidException!
	 */
	@Test(expected = PasswordInvalidException.class)
	public void testLoginShouldThrowPasswordInvalidException() throws Exception {
		System.out.println("testLoginShouldThrowPasswordInvalidException");
		
		//prepare test variables
		String loginName = "ott";
		String password = "1234";
		AbdUser user, result;
		
		user = new AbdUser(1, "ott", "123", null, "Ott", "Chris");
		
		// Setting up the expected value of the method call of Mockobject
		EasyMock.expect(userDAOMock.findUserByUsername(loginName)).andReturn(user);
		
		// Setup is finished need to activate the mock
		EasyMock.replay(userDAOMock);
		
		//call method to test
		result = managerUnderTest.login(loginName, password);
		
		// verify	
		assertEquals(user, result);
		EasyMock.verify(userDAOMock);
	}
	
	
	
	/**
	 * Test of logout method, of class UserManager.
	 */
	@Test
	public void testLogout() throws Exception {
		System.out.println("testLogout");
		
		//call method to test
		managerUnderTest.logout();
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
		String password = "123test";
		String passwordRepeat = "123test";
		
		// Setting up the expected value of the method call of Mockobject	
		EasyMock.expect(HashHelper.calcSHA1((String)EasyMock.anyObject())).andReturn("4aSe5");
		EasyMock.expect(EMailValidator.isEmail(mail)).andReturn(true);
		EasyMock.expect(userDAOMock.findUserByUsername(userName)).andReturn(null);
		EasyMock.expect(PasswordGenerator.generateSalt()).andReturn("salt");
		
		userDAOMock.create((AbdUser)EasyMock.anyObject());
		
		// Setup is finished need to activate the mock		
		PowerMock.replay(EMailValidator.class);
		PowerMock.replay(HashHelper.class);
		PowerMock.replay(PasswordGenerator.class);
		EasyMock.replay(userDAOMock);
		
		//call method to test
		managerUnderTest.register(firstName, name, userName, mail, password, passwordRepeat);
		
		// verify	
		EasyMock.verify(userDAOMock);
		PowerMock.verify(HashHelper.class);
		PowerMock.verify(EMailValidator.class);
		PowerMock.verify(PasswordGenerator.class);
	}
	
	/**
	 * Test of register method, of class UserManager.
	 * This test provokes a IncompleteUserRegisterException!
	 */
	@Test(expected = IncompleteUserRegisterException.class)
	public void testRegisterTooShortPasswordThrowIncompleteUserRegisterException() throws Exception {
		
		System.out.println("testRegisterTooShortPasswordThrowIncompleteUserRegisterException");
		
		//prepare test variables
		String firstName = "biene";
		String name = "maja";
		String userName = "summsesum";
		String mail = "biene@maja.com";
		String password = "123t";
		String passwordRepeat = "123t";
		
		// Setting up the expected value of the method call of Mockobject	
		EasyMock.expect(HashHelper.calcSHA1((String)EasyMock.anyObject())).andReturn("4aSe5");
		EasyMock.expect(EMailValidator.isEmail(mail)).andReturn(true);
		EasyMock.expect(userDAOMock.findUserByUsername(userName)).andReturn(null);
		EasyMock.expect(PasswordGenerator.generateSalt()).andReturn("salt");
		
		userDAOMock.create((AbdUser)EasyMock.anyObject());
		
		// Setup is finished need to activate the mock		
		PowerMock.replay(EMailValidator.class);
		PowerMock.replay(HashHelper.class);
		PowerMock.replay(PasswordGenerator.class);
		EasyMock.replay(userDAOMock);
		
		//call method to test
		managerUnderTest.register(firstName, name, userName, mail, password, passwordRepeat);
		
		// verify	
		EasyMock.verify(userDAOMock);
		PowerMock.verify(HashHelper.class);
		PowerMock.verify(EMailValidator.class);
		PowerMock.verify(PasswordGenerator.class);
	}
	
	
	/**
	 * Test of register method, of class UserManager.
	 * This test provokes a IncompleteUserRegisterException!
	 */
	@Test(expected = IncompleteUserRegisterException.class)
	public void testRegisterNoFistNameThrowIncompleteUserRegisterException() throws Exception {
		System.out.println("testRegisterNoFistNameThrowIncompleteUserRegisterException");
		
		//prepare test variables
		String firstName = "";
		String name = "maja";
		String userName = "summsesum";
		String mail = "biene@maja.com";
		String password = "123test";
		String passwordRepeat = "123test";
		
		// Setting up the expected value of the method call of Mockobject	
		EasyMock.expect(HashHelper.calcSHA1((String)EasyMock.anyObject())).andReturn("4aSe5");
		EasyMock.expect(EMailValidator.isEmail(mail)).andReturn(true);
		EasyMock.expect(userDAOMock.findUserByUsername(userName)).andReturn(null);
		EasyMock.expect(PasswordGenerator.generateSalt()).andReturn("salt");
		
		userDAOMock.create((AbdUser)EasyMock.anyObject());
		
		// Setup is finished need to activate the mock		
		PowerMock.replay(EMailValidator.class);
		PowerMock.replay(HashHelper.class);
		PowerMock.replay(PasswordGenerator.class);
		EasyMock.replay(userDAOMock);
		
		//call method to test
		managerUnderTest.register(firstName, name, userName, mail, password, passwordRepeat);
		
		// verify	
		EasyMock.verify(userDAOMock);
		PowerMock.verify(HashHelper.class);
		PowerMock.verify(EMailValidator.class);
		PowerMock.verify(PasswordGenerator.class);
	}
	
	/**
	 * Test of register method, of class UserManager.
	 * This test provokes a IncompleteUserRegisterException!
	 */
	@Test(expected = IncompleteUserRegisterException.class)
	public void testRegisterNoNameThrowIncompleteUserRegisterException() throws Exception {
		System.out.println("testRegisterNoNameThrowIncompleteUserRegisterException");
		
		//prepare test variables
		String firstName = "biene";
		String name = "";
		String userName = "summsesum";
		String mail = "biene@maja.com";
		String password = "123test";
		String passwordRepeat = "123test";
		
		// Setting up the expected value of the method call of Mockobject	
		EasyMock.expect(HashHelper.calcSHA1((String)EasyMock.anyObject())).andReturn("4aSe5");
		EasyMock.expect(EMailValidator.isEmail(mail)).andReturn(true);
		EasyMock.expect(userDAOMock.findUserByUsername(userName)).andReturn(null);
		EasyMock.expect(PasswordGenerator.generateSalt()).andReturn("salt");
		
		userDAOMock.create((AbdUser)EasyMock.anyObject());
		
		// Setup is finished need to activate the mock		
		PowerMock.replay(EMailValidator.class);
		PowerMock.replay(HashHelper.class);
		PowerMock.replay(PasswordGenerator.class);
		EasyMock.replay(userDAOMock);
		
		//call method to test
		managerUnderTest.register(firstName, name, userName, mail, password, passwordRepeat);
		
		// verify	
		EasyMock.verify(userDAOMock);
		PowerMock.verify(HashHelper.class);
		PowerMock.verify(EMailValidator.class);
		PowerMock.verify(PasswordGenerator.class);
		
	}
	
	/**
	 * Test of register method, of class UserManager.
	 * This test provokes a IncompleteUserRegisterException!
	 */
	@Test(expected = IncompleteUserRegisterException.class)
	public void testRegisterInvalidMailThrowIncompleteUserRegisterException() throws Exception {
		System.out.println("testRegisterInvalidMailThrowIncompleteUserRegisterException");
		
		//prepare test variables
		String firstName = "biene";
		String name = "maja";
		String userName = "summsesum";
		String mail = "biene@maja.com";
		String password = "123test";
		String passwordRepeat = "123test";
		
		// Setting up the expected value of the method call of Mockobject	
		EasyMock.expect(HashHelper.calcSHA1((String)EasyMock.anyObject())).andReturn("4aSe5");
		EasyMock.expect(EMailValidator.isEmail(mail)).andReturn(false);
		EasyMock.expect(userDAOMock.findUserByUsername(userName)).andReturn(null);
		EasyMock.expect(PasswordGenerator.generateSalt()).andReturn("salt");
		
		userDAOMock.create((AbdUser)EasyMock.anyObject());
		
		// Setup is finished need to activate the mock		
		PowerMock.replay(EMailValidator.class);
		PowerMock.replay(HashHelper.class);
		PowerMock.replay(PasswordGenerator.class);
		EasyMock.replay(userDAOMock);
		
		//call method to test
		managerUnderTest.register(firstName, name, userName, mail, password, passwordRepeat);
		
		// verify	
		EasyMock.verify(userDAOMock);
		PowerMock.verify(HashHelper.class);
		PowerMock.verify(EMailValidator.class);
		PowerMock.verify(PasswordGenerator.class);	
	}
	
//	/**
//	 * Test of register method, of class UserManager.
//	 * This test provokes a IncompleteUserRegisterException!
//	 */
//	@Test(expected = IncompleteUserRegisterException.class)
//	public void testRegisterNoMailThrowIncompleteUserRegisterException() throws Exception {
//		System.out.println("testRegisterNoMailThrowIncompleteUserRegisterException");
//		
//		//prepare test variables
//		String firstName = "biene";
//		String name = "maja";
//		String userName = "summsesum";
//		String mail = "";
//		String password = "123test";
//		String passwordRepeat = "123test";
//		
//		// Setting up the expected value of the method call of Mockobject	
//		EasyMock.expect(HashHelper.calcSHA1((String)EasyMock.anyObject())).andReturn("4aSe5");
//		EasyMock.expect(EMailValidator.isEmail(mail)).andReturn(true);
//		EasyMock.expect(userDAOMock.findUserByUsername(userName)).andReturn(null);
//		EasyMock.expect(PasswordGenerator.generateSalt()).andReturn("salt");
//		
//		userDAOMock.create((AbdUser)EasyMock.anyObject());
//		
//		// Setup is finished need to activate the mock		
//		PowerMock.replay(EMailValidator.class);
//		PowerMock.replay(HashHelper.class);
//		PowerMock.replay(PasswordGenerator.class);
//		EasyMock.replay(userDAOMock);
//		
//		//call method to test
//		managerUnderTest.register(firstName, name, userName, mail, password, passwordRepeat);
//		
//		// verify	
//		EasyMock.verify(userDAOMock);
//		PowerMock.verify(HashHelper.class);
//		PowerMock.verify(EMailValidator.class);
//		PowerMock.verify(PasswordGenerator.class);	
//	}
	
	/**
	 * Test of register method, of class UserManager.
	 * This test provokes a IncompleteUserRegisterException!
	 */
	@Test(expected = IncompleteUserRegisterException.class)
	public void testRegisterNoUserNameThrowIncompleteUserRegisterException() throws Exception {
		System.out.println("testRegisterNoUserNameThrowIncompleteUserRegisterException");
		
		//prepare test variables
		String firstName = "biene";
		String name = "maja";
		String userName = "";
		String mail = "biene@maja.com";
		String password = "123test";
		String passwordRepeat = "123test";
		
		// Setting up the expected value of the method call of Mockobject	
		EasyMock.expect(HashHelper.calcSHA1((String)EasyMock.anyObject())).andReturn("4aSe5");
		EasyMock.expect(EMailValidator.isEmail(mail)).andReturn(true);
		EasyMock.expect(userDAOMock.findUserByUsername(userName)).andReturn(null);
		EasyMock.expect(PasswordGenerator.generateSalt()).andReturn("salt");
		
		userDAOMock.create((AbdUser)EasyMock.anyObject());
		
		// Setup is finished need to activate the mock		
		PowerMock.replay(EMailValidator.class);
		PowerMock.replay(HashHelper.class);
		PowerMock.replay(PasswordGenerator.class);
		EasyMock.replay(userDAOMock);
		
		//call method to test
		managerUnderTest.register(firstName, name, userName, mail, password, passwordRepeat);
		
		// verify	
		EasyMock.verify(userDAOMock);
		PowerMock.verify(HashHelper.class);
		PowerMock.verify(EMailValidator.class);
		PowerMock.verify(PasswordGenerator.class);	
	}
	
	/**
	 * Test of register method, of class UserManager.
	 * This test provokes a NoValidUserNameException!
	 */
	@Test(expected = NoValidUserNameException.class)
	public void testRegisterThrowTooShortUserNameException() throws Exception {
		System.out.println("testRegisterThrowTooShortUserNameException");
		
		
		//prepare test variables
		String firstName = "biene";
		String name = "maja";
		String userName = "abc";
		String mail = "biene@maja.com";
		String password = "123test";
		String passwordRepeat = "123test";
		
		// Setting up the expected value of the method call of Mockobject	
		EasyMock.expect(HashHelper.calcSHA1((String)EasyMock.anyObject())).andReturn("4aSe5");
		EasyMock.expect(EMailValidator.isEmail(mail)).andReturn(true);
		EasyMock.expect(userDAOMock.findUserByUsername(userName)).andReturn(null);
		EasyMock.expect(PasswordGenerator.generateSalt()).andReturn("salt");
		
		userDAOMock.create((AbdUser)EasyMock.anyObject());
		
		// Setup is finished need to activate the mock		
		PowerMock.replay(EMailValidator.class);
		PowerMock.replay(HashHelper.class);
		PowerMock.replay(PasswordGenerator.class);
		EasyMock.replay(userDAOMock);
		
		//call method to test
		managerUnderTest.register(firstName, name, userName, mail, password, passwordRepeat);
		
		// verify	
		EasyMock.verify(userDAOMock);
		PowerMock.verify(HashHelper.class);
		PowerMock.verify(EMailValidator.class);
		PowerMock.verify(PasswordGenerator.class);	
		
	}
	
	/**
	 * Test of register method, of class UserManager.
	 * This test provokes a IncompleteUserRegisterException!
	 */
	@Test(expected = IncompleteUserRegisterException.class)
	public void testRegisterNoPasswordThrowIncompleteUserRegisterException() throws Exception {
		System.out.println("testRegisterNoPasswordThrowIncompleteUserRegisterException");
		
		//prepare test variables
		String firstName = "biene";
		String name = "maja";
		String userName = "summsesum";
		String mail = "biene@maja.com";
		String password = "";
		String passwordRepeat = "123test";
		
		// Setting up the expected value of the method call of Mockobject	
		EasyMock.expect(HashHelper.calcSHA1((String)EasyMock.anyObject())).andReturn("4aSe5");
		EasyMock.expect(EMailValidator.isEmail(mail)).andReturn(true);
		EasyMock.expect(userDAOMock.findUserByUsername(userName)).andReturn(null);
		EasyMock.expect(PasswordGenerator.generateSalt()).andReturn("salt");
		
		userDAOMock.create((AbdUser)EasyMock.anyObject());
		
		// Setup is finished need to activate the mock		
		PowerMock.replay(EMailValidator.class);
		PowerMock.replay(HashHelper.class);
		PowerMock.replay(PasswordGenerator.class);
		EasyMock.replay(userDAOMock);
		
		//call method to test
		managerUnderTest.register(firstName, name, userName, mail, password, passwordRepeat);
		
		// verify	
		EasyMock.verify(userDAOMock);
		PowerMock.verify(HashHelper.class);
		PowerMock.verify(EMailValidator.class);
		PowerMock.verify(PasswordGenerator.class);
	}
	
	
	/**
	 * Test of register method, of class UserManager.
	 * This test provokes a IncompleteUserRegisterException!
	 */
	@Test(expected = IncompleteUserRegisterException.class)
	public void testRegisterNoPasswordRepeatThrowIncompleteUserRegisterException() throws Exception {
		System.out.println("testRegisterNoPasswordRepeatThrowIncompleteUserRegisterException");
		
		//prepare test variables
		String firstName = "biene";
		String name = "maja";
		String userName = "summsesum";
		String mail = "biene@maja.com";
		String password = "123test";
		String passwordRepeat = "";
		
		// Setting up the expected value of the method call of Mockobject	
		EasyMock.expect(HashHelper.calcSHA1((String)EasyMock.anyObject())).andReturn("4aSe5");
		EasyMock.expect(EMailValidator.isEmail(mail)).andReturn(true);
		EasyMock.expect(userDAOMock.findUserByUsername(userName)).andReturn(null);
		EasyMock.expect(PasswordGenerator.generateSalt()).andReturn("salt");
		
		userDAOMock.create((AbdUser)EasyMock.anyObject());
		
		// Setup is finished need to activate the mock		
		PowerMock.replay(EMailValidator.class);
		PowerMock.replay(HashHelper.class);
		PowerMock.replay(PasswordGenerator.class);
		EasyMock.replay(userDAOMock);
		
		//call method to test
		managerUnderTest.register(firstName, name, userName, mail, password, passwordRepeat);
		
		// verify	
		EasyMock.verify(userDAOMock);
		PowerMock.verify(HashHelper.class);
		PowerMock.verify(EMailValidator.class);
		PowerMock.verify(PasswordGenerator.class);
	}
	
	/**
	 * Test of register method, of class UserManager.
	 * This test provokes a IncompleteUserRegisterException!
	 */
	@Test(expected = IncompleteUserRegisterException.class)
	public void testRegisterPasswordEqaulFailThrowIncompleteUserRegisterException() throws Exception {
		System.out.println("testRegisterPasswordEqaulFailThrowIncompleteUserRegisterException");
		
		//prepare test variables
		String firstName = "biene";
		String name = "maja";
		String userName = "summsesum";
		String mail = "biene@maja.com";
		String password = "1234test";
		String passwordRepeat = "123test";
		
		// Setting up the expected value of the method call of Mockobject	
		EasyMock.expect(HashHelper.calcSHA1((String)EasyMock.anyObject())).andReturn("4aSe5");
		EasyMock.expect(EMailValidator.isEmail(mail)).andReturn(true);
		EasyMock.expect(userDAOMock.findUserByUsername(userName)).andReturn(null);
		EasyMock.expect(PasswordGenerator.generateSalt()).andReturn("salt");
		
		userDAOMock.create((AbdUser)EasyMock.anyObject());
		
		// Setup is finished need to activate the mock		
		PowerMock.replay(EMailValidator.class);
		PowerMock.replay(HashHelper.class);
		PowerMock.replay(PasswordGenerator.class);
		EasyMock.replay(userDAOMock);
		
		//call method to test
		managerUnderTest.register(firstName, name, userName, mail, password, passwordRepeat);
		
		// verify	
		EasyMock.verify(userDAOMock);
		PowerMock.verify(HashHelper.class);
		PowerMock.verify(EMailValidator.class);
		PowerMock.verify(PasswordGenerator.class);
	}
	
	/**
	 * Test of register method, of class UserManager.
	 * This test provokes a IncompleteRegisterException
	 */
	@Test
	(expected = IncompleteUserRegisterException.class)
	public void testRegisterShouldThrowIncompleteUserRegisterException() throws Exception {
		System.out.println("testRegister");
		
		//prepare test variables
		String firstName = "biene";
		String name = "maja";
		String userName = "summsesum";
		String mail = "";
		String password = "123test";
		String passwordRepeat = "123test";
		
		// Setting up the expected value of the method call of Mockobject	
		EasyMock.expect(HashHelper.calcSHA1((String)EasyMock.anyObject())).andReturn("4aSe5");
		EasyMock.expect(EMailValidator.isEmail(mail)).andReturn(true);
		EasyMock.expect(userDAOMock.findUserByUsername(userName)).andReturn(null);
		EasyMock.expect(PasswordGenerator.generateSalt()).andReturn("salt");
		
		userDAOMock.create((AbdUser)EasyMock.anyObject());
		
		// Setup is finished need to activate the mock		
		PowerMock.replay(EMailValidator.class);
		PowerMock.replay(HashHelper.class);
		PowerMock.replay(PasswordGenerator.class);
		EasyMock.replay(userDAOMock);
		
		//call method to test
		managerUnderTest.register(firstName, name, userName, mail, password, passwordRepeat);
		
		// verify	
		EasyMock.verify(userDAOMock);
		PowerMock.verify(HashHelper.class);
		PowerMock.verify(EMailValidator.class);
		PowerMock.verify(PasswordGenerator.class);
	}
	
	/**
	 * Test of register method, of class UserManager.
	 * This test provokes a NoValidUserNameException
	 */
	@Test (expected = NoValidUserNameException.class)
	public void testRegisterShouldThrowNoValidUserNameException() throws Exception {
		System.out.println("testRegister");
		
		//prepare test variables
		String firstName = "biene";
		String name = "maja";
		String userName = "summsesum";
		String mail = "biene@maja.com";
		String password = "123test";
		String passwordRepeat = "123test";
		
		// Setting up the expected value of the method call of Mockobject	
		EasyMock.expect(HashHelper.calcSHA1((String)EasyMock.anyObject())).andReturn("4aSe5");
		EasyMock.expect(EMailValidator.isEmail(mail)).andReturn(true);
		EasyMock.expect(userDAOMock.findUserByUsername(userName)).andReturn(new AbdUser());
		EasyMock.expect(PasswordGenerator.generateSalt()).andReturn("salt");
		
		userDAOMock.create((AbdUser)EasyMock.anyObject());
		
		// Setup is finished need to activate the mock		
		PowerMock.replay(EMailValidator.class);
		PowerMock.replay(HashHelper.class);
		PowerMock.replay(PasswordGenerator.class);
		EasyMock.replay(userDAOMock);
		
		//call method to test
		managerUnderTest.register(firstName, name, userName, mail, password, passwordRepeat);
		
		// verify	
		EasyMock.verify(userDAOMock);
		PowerMock.verify(HashHelper.class);
		PowerMock.verify(EMailValidator.class);
		PowerMock.verify(PasswordGenerator.class);
	}
	
	/**
	 * Test of register method, of class UserManager.
	 * This test provokes a HashFailException
	 */
	@Test (expected = HashFailException.class)
	public void testRegisterShouldThrowHashFailExceptionBecauseOfUnsupportedEncodingException() throws Exception {
		System.out.println("testRegister");
		
		//prepare test variables
		String firstName = "biene";
		String name = "maja";
		String userName = "summsesum";
		String mail = "biene@maja.com";
		String password = "123test";
		String passwordRepeat = "123test";
		
		// Setting up the expected value of the method call of Mockobject	
		EasyMock.expect(HashHelper.calcSHA1((String)EasyMock.anyObject())).andThrow(new UnsupportedEncodingException());
		EasyMock.expect(EMailValidator.isEmail(mail)).andReturn(true);
		EasyMock.expect(userDAOMock.findUserByUsername(userName)).andReturn(null);
		EasyMock.expect(PasswordGenerator.generateSalt()).andReturn("salt");
		
		userDAOMock.create((AbdUser)EasyMock.anyObject());
		
		// Setup is finished need to activate the mock		
		PowerMock.replay(EMailValidator.class);
		PowerMock.replay(HashHelper.class);
		PowerMock.replay(PasswordGenerator.class);
		EasyMock.replay(userDAOMock);
		
		//call method to test
		managerUnderTest.register(firstName, name, userName, mail, password, passwordRepeat);
		
		// verify	
		EasyMock.verify(userDAOMock);
		PowerMock.verify(HashHelper.class);
		PowerMock.verify(EMailValidator.class);
		PowerMock.verify(PasswordGenerator.class);
	}
	
	/**
	 * Test of register method, of class UserManager.
	 * This test provokes a HashFailException
	 */
	@Test (expected = HashFailException.class)
	public void testRegisterShouldThrowHashFailExceptionBecauseOfNoSuchAlgorithmException() throws Exception {
		System.out.println("testRegister");
		
		//prepare test variables
		String firstName = "biene";
		String name = "maja";
		String userName = "summsesum";
		String mail = "biene@maja.com";
		String password = "123test";
		String passwordRepeat = "123test";
		
		// Setting up the expected value of the method call of Mockobject	
		EasyMock.expect(HashHelper.calcSHA1((String)EasyMock.anyObject())).andThrow(new NoSuchAlgorithmException());
		EasyMock.expect(EMailValidator.isEmail(mail)).andReturn(true);
		EasyMock.expect(userDAOMock.findUserByUsername(userName)).andReturn(null);
		EasyMock.expect(PasswordGenerator.generateSalt()).andReturn("salt");
		
		userDAOMock.create((AbdUser)EasyMock.anyObject());
		
		// Setup is finished need to activate the mock		
		PowerMock.replay(EMailValidator.class);
		PowerMock.replay(HashHelper.class);
		PowerMock.replay(PasswordGenerator.class);
		EasyMock.replay(userDAOMock);
		
		//call method to test
		managerUnderTest.register(firstName, name, userName, mail, password, passwordRepeat);
		
		// verify	
		EasyMock.verify(userDAOMock);
		PowerMock.verify(HashHelper.class);
		PowerMock.verify(EMailValidator.class);
		PowerMock.verify(PasswordGenerator.class);
	}
	
	/**
	 * Test of getAllAccountsFromUser method, of class UserManager.
	 */
	@Test
	public void testgetAllAccountsFromUserWithClass() throws Exception{
		System.out.println("testgetAllAccountsFromUserWithClass");
		
		//prepare test variables
		AbdAccount accountOne = new AbdAccount(1);
		AbdAccount accountTwo = new AbdAccount(2);
		
		AbdUser user = new AbdUser(22);
		
		List<AbdAccount> result;
		ArrayList<AbdAccount> outputCollection=new ArrayList<AbdAccount>();
		outputCollection.add(accountOne);
		outputCollection.add(accountTwo);
		
		user.setAbdAccountCollection(outputCollection);

		// Setting up the expected value of the method call of Mockobject
		EasyMock.expect(userDAOMock.find(user.getId())).andStubReturn(user);
		userDAOMock.refresh(user);
		// Setup is finished need to activate the mock	
		EasyMock.replay(userDAOMock);
		
		//call method to test
		result=managerUnderTest.getAllAccountsFromUser(user);
		
		// verify
		assertEquals(outputCollection, result);
		EasyMock.verify(userDAOMock);
	}
	
	/**
	 * Test of getAllAccountsFromUser method, of class UserManager.
	 */
	@Test
	public void testgetAllAccountsFromUserWithInt() throws Exception{
		System.out.println("testgetAllAccountsFromUserWithInt");
		
		//prepare test variables
		AbdAccount accountOne = new AbdAccount(1);
		AbdAccount accountTwo = new AbdAccount(2);
		
		AbdUser user = new AbdUser(22);
		
		List<AbdAccount> result;
		ArrayList<AbdAccount> outputCollection=new ArrayList<AbdAccount>();
		outputCollection.add(accountOne);
		outputCollection.add(accountTwo);
		
		user.setAbdAccountCollection(outputCollection);
		
		// Setting up the expected value of the method call of Mockobject
		EasyMock.expect(userDAOMock.find(user.getId())).andStubReturn(user);
		userDAOMock.refresh(user);
		// Setup is finished need to activate the mock	
		EasyMock.replay(userDAOMock);
		
		//call method to test
		result=managerUnderTest.getAllAccountsFromUser(user.getId());
		
		// verify	
		assertEquals(outputCollection, result);
		EasyMock.verify(userDAOMock);
	}
	
	/**
	 * Test of getAllAccountsFromUser method, of class UserManager.
	 * This test provokes a UserNotFoundException!
	 */
	@Test(expected = UserNotFoundException.class)
	public void testgetAllAccountsFromUserShouldThrowUserNotFoundException() throws Exception{
		System.out.println("testgetAllAccountsFromUserShouldThrowUserNotFoundException");
		
		//prepare test variables
		AbdAccount accountOne = new AbdAccount(1);
		AbdAccount accountTwo = new AbdAccount(2);
		
		AbdUser user = new AbdUser(22);
		
		List<AbdAccount> result;
		ArrayList<AbdAccount> outputCollection=new ArrayList<AbdAccount>();
		outputCollection.add(accountOne);
		outputCollection.add(accountTwo);
		user.setAbdAccountCollection(outputCollection);
		
		// Setting up the expected value of the method call of Mockobject
		EasyMock.expect(userDAOMock.find(user.getId())).andStubReturn(null);
		
		// Setup is finished need to activate the mock	
		EasyMock.replay(userDAOMock);
		
		//call method to test
		result=managerUnderTest.getAllAccountsFromUser(user.getId());
		
		// verify
		assertEquals(outputCollection, result);
		EasyMock.verify(userDAOMock);
	}
	
	/**
	 *  tests the sendForgotPasswordMail method
	 * @throws Exception
	 */
	@Test
	public void testSendForgotPasswordMail() throws Exception{
		System.out.println("testSendForgotPasswordMailShouldThrowUserNotFoundException");
		
		//prepare test variables
		AbdUser user;
		user = new AbdUser(1, "bienemaja", "1234abcd", "salt", "mustermann", "max");
		user.setMail("bienemaja@googlemail.com");
		String newPassword = "abcd1234";
		String newSalt = "salt1234";
		
		// Setting up the expected value of the method call of Mockobject
		expect(userDAOMock.findUserByUsername(user.getUsername())).andReturn(user);
		userDAOMock.edit(user);
		
		
		expect(PasswordGenerator.generatePassword()).andReturn(newPassword);
		expect(PasswordGenerator.generateSalt()).andReturn(newSalt);
		expect(HashHelper.calcSHA1(newPassword+newSalt)).andReturn("passwordPlusSalt");
		
		String mailBody = "You recieved a new password for your autobdayaccount: "
				+ newPassword + "\n\n" + "greetz your Autobdayteam";

		mailMock.sendSystemMail("Autobday Notification", mailBody, user.getMail());


		// Setup is finished need to activate the mock
		replay(userDAOMock);
		replay(mailMock);
		PowerMock.replay(PasswordGenerator.class);
		PowerMock.replay(HashHelper.class);
		
		//call method to test
		managerUnderTest.sendForgotPasswordMail(user.getUsername());
		
		// verify
		verify(userDAOMock);
		verify(mailMock);
		PowerMock.verify(PasswordGenerator.class);
		PowerMock.verify(HashHelper.class);
	}
	
	/**
	 *  tests the sendForgotPasswordMail method
	 * @throws Exception
	 */
	@Test(expected = UserNotFoundException.class)
	public void testSendForgotPasswordMailShouldThrowUserNotFoundException() throws Exception{
		System.out.println("testSendForgotPasswordMailShouldThrowUserNotFoundException");
		
		//prepare test variables
		AbdUser user;
		user = new AbdUser(1, "bienemaja", "1234abcd", "salt", "mustermann", "max");
		user.setMail("bienemaja@googlemail.com");
		
		// Setting up the expected value of the method call of Mockobject
		expect(userDAOMock.findUserByUsername(user.getUsername())).andReturn(null);
		userDAOMock.edit(user);

		
		// Setup is finished need to activate the mock
		replay(userDAOMock);
		replay(mailMock);
		
		//call method to test
		managerUnderTest.sendForgotPasswordMail(user.getUsername());
		
		// verify
		verify(userDAOMock);
		verify(mailMock);
	}
	
	/**
	 *  tests the sendForgotPasswordMail method
	 * @throws Exception
	 */
	@Test(expected = MailException.class)
	public void testSendForgotPasswordMailShouldThrowMailException() throws Exception{
		System.out.println("testSendForgotPasswordMailShouldThrowUserNotFoundException");
		
		//prepare test variables
		AbdUser user;
		user = new AbdUser(1, "bienemaja", "1234abcd", "salt", "mustermann", "max");
		user.setMail("bienemaja@googlemail.com");
		String newPassword = "abcd1234";
		String newSalt = "salt1234";
		
		// Setting up the expected value of the method call of Mockobject
		expect(userDAOMock.findUserByUsername(user.getUsername())).andReturn(user);
		userDAOMock.edit(user);
		
		expect(PasswordGenerator.generatePassword()).andReturn(newPassword);
		expect(PasswordGenerator.generateSalt()).andReturn(newSalt);
		expect(HashHelper.calcSHA1(newPassword+newSalt)).andReturn("passwordPlusSalt");
		
		String mailBody = "You recieved a new password for your autobdayaccount: "
				+ newPassword + "\n\n" + "greetz your Autobdayteam";

		mailMock.sendSystemMail("Autobday Notification", mailBody, user.getMail());
		
		expectLastCall().andThrow(new FailedToSendMailException("TestExcesption"));
		
		// Setup is finished need to activate the mock
		replay(userDAOMock);
		replay(mailMock);
		PowerMock.replay(PasswordGenerator.class);
		PowerMock.replay(HashHelper.class);
		
		//call method to test
		managerUnderTest.sendForgotPasswordMail(user.getUsername());
		
		// verify
		verify(userDAOMock);
		verify(mailMock);
		PowerMock.verify(PasswordGenerator.class);
		PowerMock.verify(HashHelper.class);
	}
	
	/**
	 *  tests the changePassword method
	 * @throws Exception
	 */
	@Test
	public void testChangePassword() throws Exception{
		AbdUser user = new AbdUser(1, "max", "hash", "salt", "max", "mustermann");
		String oldPassword = user.getPasswort();
		String newPassword = "1234abcd";
		String newSalt = "saltNew";
		
		expect(userDAOMock.find(user.getId())).andReturn(user);
		expect(PasswordGenerator.generateSalt()).andReturn(newSalt);
		expect(HashHelper.calcSHA1(newPassword+newSalt)).andReturn("newHash");
		userDAOMock.edit(user);
		
		replay(userDAOMock);
		PowerMock.replay(HashHelper.class);
		PowerMock.replay(PasswordGenerator.class);
		
		managerUnderTest.changePassword(user, oldPassword, newPassword, newPassword);
		
		verify(userDAOMock);
		PowerMock.verify(HashHelper.class);
		PowerMock.verify(PasswordGenerator.class);
	}

	/**
	 *  tests the changePassword method
	 *  This test provokes a PasswordInvalidException
	 *  @throws Exception
	 */
	@Test(expected = PasswordInvalidException.class)
	public void testChangePasswordThrowPasswordInvalidExceptionByForgottenPasswords() throws Exception{
		AbdUser user = new AbdUser(1, "max", "hash", "salt", "max", "mustermann");
		String oldPassword = "";
		String newPassword = "1234abcd";
		
		managerUnderTest.changePassword(user, oldPassword, newPassword, newPassword);

	}
	/**
	 *  tests the changePassword method
	 *  This test provokes a PasswordInvalidException
	 *  @throws Exception
	 */
	@Test(expected = PasswordInvalidException.class)
	public void testChangePasswordThrowPasswordInvalidExceptionByNotEqualPasswords() throws Exception{
		AbdUser user = new AbdUser(1, "max", "hash", "salt", "max", "mustermann");
		String oldPassword = "abcdefg";
		String newPassword = "1234abcd";
		
		managerUnderTest.changePassword(user, oldPassword, newPassword, newPassword+"sth");
	}
	/**
	 *  tests the changePassword method
	 *  This test provokes a PasswordInvalidException
	 *  @throws Exception
	 */
	@Test(expected = PasswordInvalidException.class)
	public void testChangePasswordThrowPasswordInvalidExceptionByTooShortPassword() throws Exception{
		AbdUser user = new AbdUser(1, "max", "hash", "salt", "max", "mustermann");
		String oldPassword = "abcdefg";
		String newPassword = "1234";
		
		managerUnderTest.changePassword(user, oldPassword, newPassword, newPassword);
	}

	/**
	 *  tests the changePassword method
	 *  This test provokes a UserNotFoundException
	 *  @throws Exception
	 */
	@Test(expected = UserNotFoundException.class)
	public void testChangePasswordThrowUserNotFoundException() throws Exception{
		AbdUser user = new AbdUser(1, "max", "hash", "salt", "max", "mustermann");
		String oldPassword = user.getPasswort();
		String newPassword = "1234abcd";
		String newSalt = "saltNew";
		
		expect(userDAOMock.find(user.getId())).andReturn(null);
		replay(userDAOMock);
		
		managerUnderTest.changePassword(user, oldPassword, newPassword, newPassword);
		
		verify(userDAOMock);
	}

	
}