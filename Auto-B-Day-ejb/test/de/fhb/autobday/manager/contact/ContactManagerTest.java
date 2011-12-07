package de.fhb.autobday.manager.contact;

import com.stvconsultants.easygloss.javaee.JavaEEGloss;
import de.fhb.autobday.dao.AbdContactFacade;
import de.fhb.autobday.dao.AbdGroupToContactFacade;
import de.fhb.autobday.data.AbdContact;
import de.fhb.autobday.data.AbdGroupToContact;
import java.util.ArrayList;
import java.util.Collection;
import org.easymock.EasyMock;
import org.junit.*;

/**
 *
 * @author Andy Klay <klay@fh-brandenburg.de>
 * @author Michael Koppen <koppen@fh-brandenburg.de>
 */
public class ContactManagerTest {
	
//	private EJBContainer container;
	
	private JavaEEGloss gloss;
	
	private AbdContactFacade contactDAOMock;
	private AbdGroupToContactFacade groupToContactDAOMock;
	
	private ContactManager managerUnderTest;
	
	public ContactManagerTest() {
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

		
//		container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
		
		//create Mocks
		contactDAOMock = EasyMock.createMock(AbdContactFacade.class);
		groupToContactDAOMock = EasyMock.createMock(AbdGroupToContactFacade.class);
		
		//set Objekts to inject
		gloss.addEJB(contactDAOMock);
		gloss.addEJB(groupToContactDAOMock);
		
		//create Manager with Mocks
		managerUnderTest=gloss.make(ContactManager.class);
		
	}
	
	@After
	public void tearDown() {
//		container.close();
	}

	/**
	 * Test of setActive method, of class ContactManager.
	 */
	@Test
	public void testSetActive() throws Exception {
		
		
		//test variables
		String contactId="mustermann";
		boolean isActive=true;		
		AbdContact contact=new AbdContact();
		contact.setId(contactId);
		
		AbdGroupToContact groupToContact=new AbdGroupToContact();
		groupToContact.setAbdContact(contact);
		
		Collection<AbdGroupToContact> allGroupToContact=new ArrayList<AbdGroupToContact>();
		allGroupToContact.add(groupToContact);
		
		// Setting up the expected value of the method call of Mockobject
		EasyMock.expect(contactDAOMock.find(contactId)).andReturn(contact).times(1);
		EasyMock.expect(groupToContactDAOMock.findContactByContact(contactId)).andReturn(allGroupToContact).times(1);
		groupToContactDAOMock.edit(groupToContact);
		
		// Setup is finished need to activate the mock
		EasyMock.replay(contactDAOMock);
		EasyMock.replay(groupToContactDAOMock);
		
		// testing Methodcall
		managerUnderTest.setActive(contactId, isActive);
		
		// verify
		EasyMock.verify(contactDAOMock);
		EasyMock.verify(groupToContactDAOMock);
	}
}
