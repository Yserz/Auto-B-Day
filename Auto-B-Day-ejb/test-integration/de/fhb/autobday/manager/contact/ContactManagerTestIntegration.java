package de.fhb.autobday.manager.contact;

import static org.easymock.EasyMock.createMock;

import javax.persistence.EntityManager;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.stvconsultants.easygloss.javaee.JavaEEGloss;

import de.fhb.autobday.dao.AbdAccountFacade;
import de.fhb.autobday.dao.AbdUserFacade;
import de.fhb.autobday.manager.account.AccountManager;
import de.fhb.autobday.manager.connector.google.GoogleImporter;


/**
 * Integrationtest
 * 
 * @author 
 * Andy Klay <klay@fh-brandenburg.de>,
 * Christoph Ott
 */
public class ContactManagerTestIntegration {
	
	private static JavaEEGloss gloss;
	
	private static AccountManager managerUnderTest;
	
	private static AbdAccountFacade accountDAO;
	
	private static AbdUserFacade userDAO;
	
	private static GoogleImporter gImporter;
	
	private EntityManager emMock;
	
	@BeforeClass
	public static void setUpClass(){
		accountDAO = new AbdAccountFacade();
		userDAO = new AbdUserFacade();
		gImporter = new GoogleImporter();
	}
	
	@Before
	public void setUp() {
		
		gloss= new JavaEEGloss();
		
		//create Mocks
		emMock = createMock(EntityManager.class);
		
		//set EntityManagers
		accountDAO.setEntityManager(emMock);
		userDAO.setEntityManager(emMock);
		
		//set Objekts to inject
		gloss.addEJB(accountDAO);
		gloss.addEJB(userDAO);
		gloss.addEJB(gImporter);
		
		//create Manager with Mocks
		managerUnderTest=gloss.make(AccountManager.class);
	}
	
//	/**
//	 * Test of addAccount method, of class AccountManager.
//	 */
//	@Test
//	public void testAddAccount() throws Exception {
//		
//		System.out.println("testIntegrationAddAccount");
//		
//		//prepare test variables
//		String password="password";
//		String userName="test@googlemail.com";
//		String type="type";	
//
//		//prepare a user object
//		int userId=1;
//		AbdUser user = new AbdUser(userId, "mustermann", "password", "salt", "mustermann", "max");
//
//		// Setting up the expected value of the method call of Mockobject
//		expect(emMock.find(AbdUser.class, user.getId())).andReturn(user);
//		emMock.persist((AbdAccount)anyObject());
//		emMock.refresh((AbdUser)anyObject());
//		replay(emMock);
//		
//		// testing Methodcall
//		managerUnderTest.addAccount(userId, password, userName, type);
//		verify(emMock);
//	}
	
	
	/**
	 * 
	 * 
	 */
	@Test
	public void testsetActive()throws Exception {
		//TODO implement
	}

	
	/**
	 * 
	 * 
	 */
	@Test
	public void testgetContact()throws Exception {
		//TODO implement
	}
	
	
}