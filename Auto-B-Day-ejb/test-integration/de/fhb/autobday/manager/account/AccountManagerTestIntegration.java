package de.fhb.autobday.manager.account;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.EntityManager;

import static org.easymock.EasyMock.*;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.powermock.api.easymock.PowerMock;

import com.stvconsultants.easygloss.javaee.JavaEEGloss;

import de.fhb.autobday.commons.EMailValidator;
import de.fhb.autobday.dao.AbdAccountFacade;
import de.fhb.autobday.dao.AbdUserFacade;
import de.fhb.autobday.data.AbdAccount;
import de.fhb.autobday.data.AbdUser;
import de.fhb.autobday.manager.connector.google.GoogleImporter;

public class AccountManagerTestIntegration {
	
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
		emMock = createMock(EntityManager.class);
		accountDAO.setEntityManager(emMock);
		userDAO.setEntityManager(emMock);
		gloss= new JavaEEGloss();
		gloss.addEJB(accountDAO);
		gloss.addEJB(userDAO);
		gloss.addEJB(gImporter);
		managerUnderTest=gloss.make(AccountManager.class);
	}
	/**
	 * Test of addAccount method, of class AccountManager.
	 */
	@Test
	public void testAddAccount() throws Exception {
		
		System.out.println("testIntegrationAddAccount");
		
		//prepare test variables
		String password="password";
		String userName="test@googlemail.com";
		String type="type";	

		//prepare a user object
		int userId=1;
		AbdUser user = new AbdUser(userId, "mustermann", "password", "salt", "mustermann", "max");

		// Setting up the expected value of the method call of Mockobject
		expect(emMock.find(AbdUser.class, user.getId())).andReturn(user);
		emMock.persist((AbdAccount)anyObject());
		emMock.refresh((AbdUser)anyObject());
		replay(emMock);
		
		// testing Methodcall
		managerUnderTest.addAccount(userId, password, userName, type);
		verify(emMock);
	}
	
}
