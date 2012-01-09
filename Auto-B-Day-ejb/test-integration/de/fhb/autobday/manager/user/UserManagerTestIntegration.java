package de.fhb.autobday.manager.user;

import static org.easymock.EasyMock.*;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.stvconsultants.easygloss.javaee.JavaEEGloss;

import de.fhb.autobday.commons.HashHelper;
import de.fhb.autobday.commons.PasswordGenerator;
import de.fhb.autobday.dao.AbdAccountFacade;
import de.fhb.autobday.dao.AbdUserFacade;
import de.fhb.autobday.data.AbdAccount;
import de.fhb.autobday.data.AbdUser;
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
	
	private static AbdUserFacade userDAO;
	
	private EntityManager emMock;
	
	private AbdUser user;
	
	@BeforeClass
	public static void setUpClass(){
		userDAO = new AbdUserFacade();
	}
	
	@Before
	public void setUp() throws Exception{
		
		gloss= new JavaEEGloss();
		
		//create Mocks
		emMock = createMock(EntityManager.class);
		
		//set EntityManagers
		userDAO.setEntityManager(emMock);
		
		//set Objekts to inject
		gloss.addEJB(userDAO);
		
		//create Manager with Mocks
		managerUnderTest=gloss.make(UserManager.class);
		
		String salt = PasswordGenerator.generateSalt();
		String password = "";

		password = HashHelper.calcSHA1("1234abcd"+salt);

		user = new AbdUser(1, "max", password, salt, "mustermann", "max");
	}


	/**
	 * 
	 * 
	 */
	@Test
	public void testGetUser()throws Exception {
		System.out.println("");
		
		expect(emMock.find(AbdUser.class, user.getId())).andReturn(user);
		
		//prepare test variables
		
		// Setting up the expected value of the method call of Mockobject
		
		// Setup is finished need to activate the mock
		replay(emMock);
		
		assertEquals(user, managerUnderTest.getUser(user.getId()));
		
		//verify
		verify(emMock);
	}


	@Test
	public void testLogin()throws Exception {
		System.out.println("");
		
		// Setting up the expected value of the method call of Mockobject
		Query queryMock = createMock(Query.class);
		expect(queryMock.setParameter("username", user.getUsername())).andReturn(queryMock);
		expect(queryMock.getSingleResult()).andReturn(user);
		expect(emMock.createNamedQuery("AbdUser.findByUsername")).andReturn(queryMock);
		
		// Setup is finished need to activate the mock
		replay(emMock);
		replay(queryMock);
		
		assertEquals(user, managerUnderTest.login(user.getUsername(), "1234abcd"));
		
		//verify
		verify(emMock);
		verify(queryMock);
	}
	

	@Test
	public void testRegister()throws Exception {
		System.out.println("");
		
		//prepare test variables
		String firstName = "maja";
		String name = "biene";
		String username = "diemaja";
		String mail = "maja@gmail.com";
		String password = "1234abcd";
		
		AbdUser expectedUser = new AbdUser(Integer.SIZE);
		
		// Setting up the expected value of the method call of Mockobject
		Query queryMock = createMock(Query.class);
		expect(queryMock.setParameter("username", username)).andReturn(queryMock);
		expect(queryMock.getSingleResult()).andReturn(null);
		expect(emMock.createNamedQuery("AbdUser.findByUsername")).andReturn(queryMock);
		
		emMock.persist((AbdUser)anyObject());
		
		// Setup is finished need to activate the mock
		replay(emMock);
		replay(queryMock);
		
		assertEquals(expectedUser, managerUnderTest.register(firstName, name, username, mail, password, password));
		//verify
		verify(emMock);
		verify(queryMock);
	}
	

	@Test
	public void testGetAllAccountsFromUser()throws Exception {
		System.out.println("");
		
		//prepare test variables
		AbdAccount accountOne = new AbdAccount(1);
		AbdAccount accountTwo = new AbdAccount(2);
				
		List<AbdAccount> result;
		ArrayList<AbdAccount> outputCollection=new ArrayList<AbdAccount>();
		outputCollection.add(accountOne);
		outputCollection.add(accountTwo);
				
		user.setAbdAccountCollection(outputCollection);
		
		// Setting up the expected value of the method call of Mockobject
		expect(userDAO.find(user.getId())).andReturn(user);
		userDAO.refresh(user);
		// Setup is finished need to activate the mock
		replay(emMock);
		
		assertEquals(outputCollection, managerUnderTest.getAllAccountsFromUser(user));
		
		//verify
		verify(emMock);
	}
	
	
	
}