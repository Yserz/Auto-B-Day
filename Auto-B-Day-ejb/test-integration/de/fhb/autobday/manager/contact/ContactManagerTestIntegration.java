package de.fhb.autobday.manager.contact;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;

import java.util.ArrayList;

import javax.persistence.EntityManager;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.stvconsultants.easygloss.javaee.JavaEEGloss;

import de.fhb.autobday.dao.AbdUserFacade;
import de.fhb.autobday.data.AbdContact;
import de.fhb.autobday.data.AbdGroupToContact;


/**
 * Integrationtest
 * 
 * @author 
 * Andy Klay <klay@fh-brandenburg.de>,
 * Christoph Ott
 */
public class ContactManagerTestIntegration {
	
	private static JavaEEGloss gloss;
	
	private static ContactManager managerUnderTest;
	
	private static AbdUserFacade userDAO;
	
	private EntityManager emMock;
	
	private AbdContact contactOne;
	private AbdContact contactTwo;
	private AbdGroupToContact groupToContactOne;
	private AbdGroupToContact groupToContactTwo;
	private ArrayList<AbdGroupToContact> allGroupToContact;
	
	@BeforeClass
	public static void setUpClass(){
		userDAO = new AbdUserFacade();
	}
	
	@Before
	public void setUp() {
		
		gloss= new JavaEEGloss();
		
		//create Mocks
		emMock = createMock(EntityManager.class);
		
		//set EntityManagers
		userDAO.setEntityManager(emMock);
		
		//set Objekts to inject
		gloss.addEJB(userDAO);
		
		//create Manager with Mocks
		managerUnderTest=gloss.make(ContactManager.class);
		
		//create Objects needed for tests
		contactOne=new AbdContact("mustermann");
		contactTwo=new AbdContact("otto");
		
		groupToContactOne = new AbdGroupToContact();
		groupToContactTwo = new AbdGroupToContact();
		
		allGroupToContact = new ArrayList<AbdGroupToContact>();
		
		groupToContactOne.setAbdContact(contactOne);
		allGroupToContact.add(groupToContactOne);
		
		groupToContactTwo.setAbdContact(contactTwo);
		allGroupToContact.add(groupToContactTwo);
	}
	
	
	/**
	 * TODO
	 * 
	 */
	@Ignore
	public void testsetActive()throws Exception {
		System.out.println("testsetActive");
		
		
		//prepare test variables
		boolean isActive = true;
		
		// Setting up the expected value of the method call of Mockobject
		EasyMock.expect(emMock.find(AbdContact.class,contactOne.getId())).andReturn(contactOne);
		//TODO laueft nicht nullpointer
		expect(emMock.createNamedQuery("Contact.findByContact").setParameter("contact", contactOne.getId()).getResultList()).andReturn(allGroupToContact);
		
		// Setup is finished need to activate the mock
		replay(emMock);
		
		//call method to test
		managerUnderTest.setActive(contactOne, isActive);
		
		//verify
		verify(emMock);
	}

	
	/**
	 * 
	 * 
	 */
	@Test
	public void testgetContact()throws Exception {
		System.out.println("testgetContact");
		
		//TODO implement
		
		//prepare test variables
		
		// Setting up the expected value of the method call of Mockobject
		
		// Setup is finished need to activate the mock
		replay(emMock);
		
		//verify
		verify(emMock);
		
	}
	
	/**
	 * TODO
	 * 
	 */
	@Test
	public void testGetContact()throws Exception {
		System.out.println("testGetContact");
		
		//TODO implement
		
		//prepare test variables
		
		// Setting up the expected value of the method call of Mockobject
		
		// Setup is finished need to activate the mock
		replay(emMock);
		
		//verify
		verify(emMock);
	}
	
	
}