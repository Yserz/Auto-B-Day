package de.fhb.autobday.manager.contact;

import com.stvconsultants.easygloss.javaee.JavaEEGloss;
import de.fhb.autobday.dao.AbdContactFacade;
import de.fhb.autobday.dao.AbdGroupFacade;
import de.fhb.autobday.dao.AbdGroupToContactFacade;
import de.fhb.autobday.data.AbdContact;
import de.fhb.autobday.data.AbdGroup;
import de.fhb.autobday.data.AbdGroupToContact;
import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import static org.easymock.EasyMock.*;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Integrationtest
 *
 * @author Andy Klay <klay@fh-brandenburg.de>, Christoph Ott
 */
public class ContactManagerTestIntegration {

	private static JavaEEGloss gloss;
	private static ContactManager managerUnderTest;
	private static AbdGroupToContactFacade groupContactFacade;
	private static AbdContactFacade contactDAO;
	private static AbdGroupFacade groupDAO;
	private EntityManager emMock;
	private AbdContact contactOne;
	private AbdContact contactTwo;
	private AbdGroupToContact groupToContactOne;
	private AbdGroupToContact groupToContactTwo;
	private ArrayList<AbdGroupToContact> allGroupToContact;
	private AbdGroup groupOne;
	private AbdGroup groupTwo;

	@BeforeClass
	public static void setUpClass() {
		groupContactFacade = new AbdGroupToContactFacade();
		contactDAO = new AbdContactFacade();
		groupDAO = new AbdGroupFacade();
	}

	@Before
	public void setUp() {

		gloss = new JavaEEGloss();

		//create Mocks
		emMock = createMock(EntityManager.class);

		//set EntityManagers
		groupContactFacade.setEntityManager(emMock);
		contactDAO.setEntityManager(emMock);
		groupDAO.setEntityManager(emMock);

		//set Objekts to inject
		gloss.addEJB(groupContactFacade);
		gloss.addEJB(contactDAO);
		gloss.addEJB(groupDAO);

		//create Manager with Mocks
		managerUnderTest = gloss.make(ContactManager.class);

		//create Objects needed for tests
		contactOne = new AbdContact("mustermann");
		contactTwo = new AbdContact("otto");

		groupOne = new AbdGroup("friends");
		groupTwo = new AbdGroup("family");

		groupToContactOne = new AbdGroupToContact();
		groupToContactTwo = new AbdGroupToContact();

		allGroupToContact = new ArrayList<AbdGroupToContact>();

		groupToContactOne.setAbdContact(contactOne);
		groupToContactOne.setAbdGroup(groupOne);
		allGroupToContact.add(groupToContactOne);

		groupToContactTwo.setAbdContact(contactTwo);
		groupToContactOne.setAbdGroup(groupTwo);
		allGroupToContact.add(groupToContactTwo);
	}

	/**
	 * test setActive of ContactManage
	 */
	@Test
	public void testSetActive() throws Exception {
		System.out.println("testsetActive");

		Query queryMock = createMock(Query.class);

		//prepare test variables
		boolean isActive = true;
		contactOne.setAbdGroupToContactCollection(allGroupToContact);

		// Setting up the expected value of the method call of Mockobject
		expect(emMock.find(AbdContact.class, contactOne.getId())).andReturn(contactOne);
		expect(emMock.find(AbdGroup.class, groupOne.getId())).andReturn(groupOne);

		expect(emMock.merge(groupToContactOne)).andReturn(groupToContactOne);

		// Setup is finished need to activate the mock
		replay(emMock);
		replay(queryMock);

		//call method to test
		managerUnderTest.setActive(contactOne, groupOne, isActive);

		//verify
		verify(emMock);
		verify(queryMock);
	}

	/**
	 * test getContact of ContactManager
	 */
	@Test
	public void testGetContact() throws Exception {
		System.out.println("testgetContact");
		// Setting up the expected value of the method call of Mockobject
		expect(emMock.find(AbdContact.class, contactOne.getId())).andReturn(contactOne);

		// Setup is finished need to activate the mock
		replay(emMock);

		assertEquals(managerUnderTest.getContact(contactOne.getId()), contactOne);

		//verify
		verify(emMock);
	}
}