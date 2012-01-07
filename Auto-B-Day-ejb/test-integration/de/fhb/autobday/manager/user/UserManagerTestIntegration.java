package de.fhb.autobday.manager.user;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;

import javax.persistence.EntityManager;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.stvconsultants.easygloss.javaee.JavaEEGloss;

import de.fhb.autobday.dao.AbdAccountFacade;
import de.fhb.autobday.dao.AbdUserFacade;
import de.fhb.autobday.manager.connector.google.GoogleImporter;

/**
 * Integrationtest
 * 
 * @author 
 * Andy Klay <klay@fh-brandenburg.de>,
 * Christoph Ott
 */
public class UserManagerTestIntegration {
	
	private static JavaEEGloss gloss;
	
	private static UserManager managerUnderTest;
	
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
		managerUnderTest=gloss.make(UserManager.class);
	}


	/**
	 * 
	 * 
	 */
	@Test
	public void testGetUser()throws Exception {
		System.out.println("");
		
		//TODO implement
		
		//prepare test variables
		
		// Setting up the expected value of the method call of Mockobject
		
		// Setup is finished need to activate the mock
		replay(emMock);
		
		//verify
		verify(emMock);
	}


	@Test
	public void testLogin()throws Exception {
		System.out.println("");
		
		//TODO implement
		
		//prepare test variables
		
		// Setting up the expected value of the method call of Mockobject
		
		// Setup is finished need to activate the mock
		replay(emMock);
		
		//verify
		verify(emMock);
	}
	

	@Test
	public void testRegister()throws Exception {
		System.out.println("");
		
		
		//TODO implement
		
		//prepare test variables
		
		// Setting up the expected value of the method call of Mockobject
		
		// Setup is finished need to activate the mock
		replay(emMock);
		
		//verify
		verify(emMock);
	}
	

	@Test
	public void testGetAllAccountsFromUser()throws Exception {
		System.out.println("");
		
		
		//TODO implement
		
		//prepare test variables
		
		// Setting up the expected value of the method call of Mockobject
		
		// Setup is finished need to activate the mock
		replay(emMock);
		
		//verify
		verify(emMock);
	}
	
	
	
}