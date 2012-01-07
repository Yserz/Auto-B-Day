package de.fhb.autobday.manager.group;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.stvconsultants.easygloss.javaee.JavaEEGloss;

import de.fhb.autobday.dao.AbdAccountFacade;
import de.fhb.autobday.dao.AbdUserFacade;
import de.fhb.autobday.data.AbdContact;
import de.fhb.autobday.data.AbdGroup;
import de.fhb.autobday.exception.contact.ContactException;
import de.fhb.autobday.exception.group.GroupException;
import de.fhb.autobday.exception.group.GroupNotFoundException;
import de.fhb.autobday.exception.group.NoGroupGivenException;
import de.fhb.autobday.manager.account.AccountManager;
import de.fhb.autobday.manager.connector.google.GoogleImporter;


/**
 * Integrationtest
 * 
 * @author 
 * Andy Klay <klay@fh-brandenburg.de>,
 * Christoph Ott
 */
public class GroupManagerTestIntegration {
	
	
	private static JavaEEGloss gloss;
	
	private static GroupManager managerUnderTest;
	
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
		managerUnderTest=gloss.make(GroupManager.class);
	}
	
	
	/**
	 * test GetGroup of GroupManager
	 * 
	 */
	@Test
	public void testGetGroup()throws Exception {
		System.out.println("testGetGroup");
		
		//TODO implement
		
		//prepare test variables
		
		// Setting up the expected value of the method call of Mockobject
		
		// Setup is finished need to activate the mock
		replay(emMock);
		
		//verify
		verify(emMock);
	}
	
	
	/**
	 * test SetTemplate of GroupManager
	 * 
	 */
	@Test
	public void testSetTemplate()throws Exception {
		System.out.println("testSetTemplate");
		
		//TODO implement
		
		//prepare test variables
		
		// Setting up the expected value of the method call of Mockobject
		
		// Setup is finished need to activate the mock
		replay(emMock);
		
		//verify
		verify(emMock);
	}
	
	/**
	 * test GetTemplate of GroupManager
	 * 
	 */
	@Test
	public void testGetTemplate()throws Exception {
		System.out.println("testGetTemplate");
		
		//TODO implement
		
		//prepare test variables
		
		// Setting up the expected value of the method call of Mockobject
		
		// Setup is finished need to activate the mock
		replay(emMock);
		
		//verify
		verify(emMock);
	}
	
	
	/**
	 * test TestTemplate of GroupManager
	 * 
	 */
	@Test
	public void testTestTemplate()throws Exception {
		System.out.println("testTestTemplate");
		
		//TODO implement
		
		//prepare test variables
		
		// Setting up the expected value of the method call of Mockobject
		
		// Setup is finished need to activate the mock
		replay(emMock);
		
		//verify
		verify(emMock);
	}
	
	
	/**
	 * test SetActive of GroupManager
	 * 
	 */
	@Test
	public void testSetActive()throws Exception {
		System.out.println("testSetActive");
		
		//TODO implement
		
		//prepare test variables
		
		// Setting up the expected value of the method call of Mockobject
		
		// Setup is finished need to activate the mock
		replay(emMock);
		
		//verify
		verify(emMock);
	}
	
	
	/**
	 * test ParseTemplate of GroupManager
	 * 
	 */
	@Test
	public void testParseTemplate()throws Exception {
		System.out.println("testParseTemplate");
		
		//TODO implement
		
		//prepare test variables
		
		// Setting up the expected value of the method call of Mockobject
		
		// Setup is finished need to activate the mock
		replay(emMock);
		
		//verify
		verify(emMock);
	}
	
	
	/**
	 * test GetAllContactsFromGroup of GroupManager
	 * 
	 */
	@Test
	public void testGetAllContactsFromGroup()throws Exception {
		System.out.println("testGetAllContactsFromGroup");
		
		//TODO implement
		
		//prepare test variables
		
		// Setting up the expected value of the method call of Mockobject
		
		// Setup is finished need to activate the mock
		replay(emMock);
		
		//verify
		verify(emMock);
	}
	
	
}
