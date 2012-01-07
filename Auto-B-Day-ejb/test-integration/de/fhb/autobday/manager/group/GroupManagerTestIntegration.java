package de.fhb.autobday.manager.group;

import static org.easymock.EasyMock.createMock;

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
	
	
	/**
	 * 
	 * 
	 */
	@Test
	public void testGetGroup(){
		//TODO implement
	}
	
	
	/**
	 * 
	 * 
	 */
	@Test
	public void testSetTemplate(){
		//TODO implement
	}
	
	/**
	 * 
	 * 
	 */
	@Test
	public void testGetTemplate(){
		//TODO implement
	}
	
	
	/**
	 * 
	 * 
	 */
	@Test
	public void testTestTemplate(){
		//TODO implement
	}
	
	
	/**
	 * 
	 * 
	 */
	@Test
	public void testSetActive(){
		//TODO implement
	}
	
	
	/**
	 * 
	 * 
	 */
	@Test
	public void testParseTemplate(){
		//TODO implement
	}
	
	
	/**
	 * 
	 * 
	 */
	@Test
	public void testParseSlashExpression(){
		//TODO implement
	}
	
	
	/**
	 * 
	 * 
	 */
	@Test
	public void testGetAllContactsFromGroup(){
		//TODO implement
	}
	
	
}
