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
 * Test the ContactManager
 *
 * @author Andy Klay <klay@fh-brandenburg.de>
 * @author Michael Koppen <koppen@fh-brandenburg.de>
 */
public class ContactManagerTest {

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
		
		//prepare groupToContactRealation
		AbdGroupToContact groupToContact=new AbdGroupToContact();		
		Collection<AbdGroupToContact> allGroupToContact=new ArrayList<AbdGroupToContact>();
		groupToContact.setAbdContact(contact);

		allGroupToContact.add(groupToContact);
		
		// Setting up the expected value of the method call of Mockobject
		EasyMock.expect(contactDAOMock.find(contactId)).andReturn(contact).times(1);
		EasyMock.expect(groupToContactDAOMock.findGroupByContact(contactId)).andReturn(allGroupToContact).times(1);
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
