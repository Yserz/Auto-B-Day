package de.fhb.autobday.manager.connector.google;

import static org.easymock.EasyMock.*;

import com.google.gdata.client.contacts.ContactsService;
import com.google.gdata.data.contacts.Birthday;
import com.google.gdata.data.contacts.ContactEntry;
import com.google.gdata.data.contacts.ContactFeed;
import com.google.gdata.data.contacts.ContactGroupEntry;
import com.google.gdata.data.contacts.ContactGroupFeed;
import com.google.gdata.data.contacts.Gender;
import com.google.gdata.data.contacts.Gender.Value;
import com.google.gdata.data.extensions.Email;
import com.google.gdata.data.extensions.FamilyName;
import com.google.gdata.data.extensions.GivenName;
import com.google.gdata.data.extensions.Name;
import com.google.gdata.util.AuthenticationException;
import com.google.gdata.util.ServiceException;
import com.stvconsultants.easygloss.javaee.JavaEEGloss;

import de.fhb.autobday.dao.AbdContactFacade;
import de.fhb.autobday.dao.AbdGroupFacade;
import de.fhb.autobday.data.AbdAccount;
import de.fhb.autobday.data.AbdContact;
import de.fhb.autobday.data.AbdGroup;
import de.fhb.autobday.data.AbdGroupToContact;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

import org.junit.*;

/**
 *
 * @author Michael Koppen <koppen@fh-brandenburg.de>
 */
public class GoogleImporterTest {
	
private JavaEEGloss gloss;
	
	private ContactsService contactsServiceMock;
	
	private ContactEntry contactEntry;
	private GoogleImporter gImporterUnderTest;
	
	public GoogleImporterTest() {
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
		contactsServiceMock = createMock(ContactsService.class);
		//set Objekts to inject
		//gloss.addEJB(contactsServiceMock);
		
		gImporterUnderTest=gloss.make(GoogleImporter.class);
		
		Name name = new Name();
		gImporterUnderTest = new GoogleImporter();
		contactEntry = new ContactEntry();
		
		name.setGivenName(new GivenName("Hans", ""));
		name.setFamilyName(new FamilyName("Peter", ""));
		contactEntry.setId("1");
		contactEntry.setName(name);
		contactEntry.setGender(new Gender(Value.FEMALE));
		contactEntry.setBirthday(new Birthday("1990-05-22"));
	}
	
	@After
	public void tearDown() {
	}

	/**
	 * Test of getConnection method, of class GoogleImporter.
	 * @throws AuthenticationException 
	 */
	@Test
	@Ignore
	public void testGetConnection() throws AuthenticationException{
		System.out.println("getConnection");

		AbdAccount data = new AbdAccount(1, "fhbtestacc@googlemail.com", "TestGoogle123", null);
		replay(contactsServiceMock);
		//gImporterUnderTest.getConnection(data);
		assertEquals(true, gImporterUnderTest.isConnectionEtablished());
		verify(contactsServiceMock);
	}

	/**
	 * Test of getAllGroups method, of class GoogleImporter.
	 */
	@Test
	public void testGetAllGroupsWithNull() {
		System.out.println("getAllGroups");
		GoogleImporter instance = new GoogleImporter();
		ContactsService myServiceMock = createMock(ContactsService.class);
		URL feedUrl;
		try {
			feedUrl = new URL("https://www.google.com/m8/feeds/groups/default/full");
			expect(myServiceMock.getFeed(feedUrl, ContactGroupFeed.class)).andReturn(null);
			replay(myServiceMock);
			instance.setMyService(myServiceMock);
			assertEquals(null, instance.getAllGroups());
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Test of getAllGroups method, of class GoogleImporter.
	 */
	@Test
	public void testGetAllGroupsWithGroupFeed() {
		System.out.println("getAllGroups");
		GoogleImporter instance = new GoogleImporter();
		ContactsService myServiceMock = createMock(ContactsService.class);
		URL feedUrl;
		try {
			ContactGroupFeed resultFeed = new ContactGroupFeed();
			List<ContactGroupEntry> contactGroupList = new ArrayList<ContactGroupEntry>();
			ContactGroupEntry contactGroup = new ContactGroupEntry();
			contactGroup.setId("hsfsfd");
			contactGroupList.add(contactGroup);
			resultFeed.setEntries(contactGroupList);
			feedUrl = new URL("https://www.google.com/m8/feeds/groups/default/full");
			expect(myServiceMock.getFeed(feedUrl, ContactGroupFeed.class)).andReturn(resultFeed);
			replay(myServiceMock);
			instance.setMyService(myServiceMock);
			assertEquals(contactGroupList, instance.getAllGroups());
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Test of getAllContacts method, of class GoogleImporter.
	 */
	@Test
	public void testGetAllContactsReturnNull() {
		System.out.println("getAllContacts");
		GoogleImporter instance = new GoogleImporter();
		ContactsService myServiceMock = createMock(ContactsService.class);
		URL feedUrl;
		try {
			feedUrl = new URL("https://www.google.com/m8/feeds/contacts/default/full");
			expect(myServiceMock.getFeed(feedUrl, ContactFeed.class)).andReturn(null);
			replay(myServiceMock);
			instance.setMyService(myServiceMock);
			assertEquals(null, instance.getAllContacts());
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Test of getAllContacts method, of class GoogleImporter.
	 */
	@Test
	public void testGetAllContactsReturnList() {
		System.out.println("getAllContacts");
		GoogleImporter instance = new GoogleImporter();
		ContactsService myServiceMock = createMock(ContactsService.class);
		URL feedUrl;
		try {
			List<ContactEntry> contactEntryList = new ArrayList<ContactEntry>();
			contactEntryList.add(contactEntry);
			ContactFeed resultFeed = new ContactFeed();
			resultFeed.setEntries(contactEntryList);
			feedUrl = new URL("https://www.google.com/m8/feeds/contacts/default/full");
			expect(myServiceMock.getFeed(feedUrl, ContactFeed.class)).andReturn(resultFeed);
			replay(myServiceMock);
			instance.setMyService(myServiceMock);
			assertEquals(contactEntryList, instance.getAllContacts());
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Test of importContacts method, of class GoogleImporter.
	 */
	@Test
	public void testImportContacts() {
		System.out.println("importContacts");
		
		//accdata
		AbdAccount accdata = new AbdAccount();
		 
		//Mocks
		AbdGroupFacade abdGroupFacade = createMock(AbdGroupFacade.class);
		AbdContactFacade abdContactFacade = createMock(AbdContactFacade.class);
		ContactsService myServiceMock = createMock(ContactsService.class);
		
		//mocks methods
		//URL feedUrl = new URL("https://www.google.com/m8/feeds/contacts/default/full");
		//expect(myServiceMock.getFeed(feedUrl, ContactFeed.class)).andReturn(resultFeed);
		//feedUrl = new URL("https://www.google.com/m8/feeds/groups/default/full");
		//expect(myServiceMock.getFeed(feedUrl, ContactGroupFeed.class)).andReturn(resultFeed);
		
		//Variables setting
		gImporterUnderTest.setConnectionEtablished(true);
		gImporterUnderTest.setAccdata(accdata);
		
		
	}
	
	@Test
	public void testgetGContactFirstMailAdressWithOneAdress(){
		System.out.println("getGContactFirstMailAdress");
		GoogleImporter instance = new GoogleImporter();
		Email mail = new Email();
		mail.setAddress("test@aol.de");
		contactEntry.addEmailAddress(mail);
		assertEquals("test@aol.de", instance.getGContactFirstMailAdress(contactEntry));
	}
	
	@Test
	public void testgetGContactFirstMailAdressWithManyAdress(){
		System.out.println("getGContactFirstMailAdress");
		GoogleImporter instance = new GoogleImporter();
		Email mail = new Email();
		mail.setAddress("test@aol.de");
		contactEntry.addEmailAddress(mail);
		mail.setAddress("test@fhb.de");
		contactEntry.addEmailAddress(mail);
		mail.setAddress("test@cks.de");
		contactEntry.addEmailAddress(mail);
		assertEquals(contactEntry.getEmailAddresses().get(0).getAddress(), instance.getGContactFirstMailAdress(contactEntry));
	}
	
	@Test
	public void testgetGContactFirstMailAdressWithNoAdress(){
		System.out.println("getGContactFirstMailAdress");
		GoogleImporter instance = new GoogleImporter();
		assertEquals("", instance.getGContactFirstMailAdress(contactEntry));
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testgetGContactBirthdayWithABirthday(){
		System.out.println("getGContactBirthday");
		GoogleImporter instance = new GoogleImporter();
		assertEquals(new Date(90, 4, 22), instance.getGContactBirthday(contactEntry));
	}
	
	@Test
	public void testgetGContactBirthdayWithoutABirthday(){
		System.out.println("getGContactBirthday");
		GoogleImporter instance = new GoogleImporter();
		contactEntry.setBirthday(new Birthday("---"));
		assertEquals(null, instance.getGContactBirthday(contactEntry));
	}

	@Test
	public void testgetGContactFamilyname(){
		GoogleImporter instance = new GoogleImporter();
		assertEquals("Peter",instance.getGContactFamilyname(contactEntry));
	}
	
	@Test
	public void testgetGContactFirstname(){
		GoogleImporter instance = new GoogleImporter();
		assertEquals("Hans",instance.getGContactFirstname(contactEntry));
	}
	
	@Test
	public void testdiffMembershipWithAnExistingGroup(){
		GoogleImporter instance = new GoogleImporter();
		List<AbdGroupToContact> abdGroupToContactList = new ArrayList<AbdGroupToContact>();
		AbdGroupToContact abdGroupToContact = new AbdGroupToContact();
		AbdGroup abdGroup = new AbdGroup();
		abdGroup.setId("4");
		abdGroupToContact.setAbdGroup(abdGroup);
		abdGroupToContactList.add(abdGroupToContact);
		abdGroupToContact = new AbdGroupToContact();
		abdGroup = new AbdGroup();
		abdGroup.setId("2");
		abdGroupToContact.setAbdGroup(abdGroup);
		abdGroupToContactList.add(abdGroupToContact);
		assertEquals(true,instance.diffMembership("2", abdGroupToContactList));
	}
	
	@Test
	public void testdiffMembershipWitouthAnExistingGroup(){
		GoogleImporter instance = new GoogleImporter();
		List<AbdGroupToContact> abdGroupToContactList = new ArrayList<AbdGroupToContact>();
		AbdGroupToContact abdGroupToContact = new AbdGroupToContact();
		AbdGroup abdGroup = new AbdGroup();
		abdGroup.setId("4");
		abdGroupToContact.setAbdGroup(abdGroup);
		abdGroupToContactList.add(abdGroupToContact);
		abdGroupToContact = new AbdGroupToContact();
		abdGroup = new AbdGroup();
		abdGroup.setId("2");
		abdGroupToContact.setAbdGroup(abdGroup);
		abdGroupToContactList.add(abdGroupToContact);
		assertEquals(false,instance.diffMembership("3", abdGroupToContactList));
	}

	@Test
	public void testexistGroupWithGroup(){
		GoogleImporter instance = new GoogleImporter();
		List<AbdGroup> abdGroups = new ArrayList<AbdGroup>();
		AbdGroup abdGroup = new AbdGroup();
		abdGroup.setId("3");
		abdGroups.add(abdGroup);
		abdGroup = new AbdGroup();
		abdGroup.setId("4");
		abdGroups.add(abdGroup);
		//assertEquals(true, instance.existGroup(abdGroups, "4"));
	}
	
	@Test
	public void testexistGroupWithoutGroup(){
		GoogleImporter instance = new GoogleImporter();
		List<AbdGroup> abdGroups = new ArrayList<AbdGroup>();
		AbdGroup abdGroup = new AbdGroup();
		abdGroup.setId("3");
		abdGroups.add(abdGroup);
		abdGroup = new AbdGroup();
		abdGroup.setId("4");
		abdGroups.add(abdGroup);
		//assertEquals(false, instance.existGroup(abdGroups, "2"));
	}
	
	@Test
	public void testexistGroup(){
		GoogleImporter instance = new GoogleImporter();
		List<AbdGroup> abdGroups = new ArrayList<AbdGroup>();
		//assertEquals(false, instance.existGroup(abdGroups, "4"));
	}
	
	@Test
	@Ignore
	public void testMapGContacttoContactFemale() {
		System.out.println("mapGContacttoContact");
		Email mail = new Email();
		mail.setAddress("test@aol.de");
		contactEntry.addEmailAddress(mail);
		@SuppressWarnings("deprecation")
		AbdContact exptected = new AbdContact("1", "test@fhb.de", new Date(90, 4, 22), "");
		exptected.setFirstname("Hans");
		exptected.setName("Peter");
		exptected.setSex('w');
		assertEquals(exptected, gImporterUnderTest.mapGContactToContact(contactEntry));
	}
	
	@Test
	@Ignore
	public void testMapGContacttoContactMale() {
		System.out.println("mapGContacttoContact");
		Email mail = new Email();
		mail.setAddress("test@aol.de");
		contactEntry.addEmailAddress(mail);
		contactEntry.setGender(new Gender(Value.MALE));
		@SuppressWarnings("deprecation")
		AbdContact exptected = new AbdContact("1", "test@fhb.de", new Date(90, 4, 22), "");
		exptected.setFirstname("Hans");
		exptected.setName("Peter");
		exptected.setSex('m');
		assertEquals(exptected, gImporterUnderTest.mapGContactToContact(contactEntry));
	}
	
	@Test
	@Ignore
	public void testMapGContacttoContactWithoutEmailandBirthday() {
		System.out.println("mapGContacttoContact");
		contactEntry.setGender(new Gender(Value.MALE));
		contactEntry.setBirthday(new Birthday("---"));
		AbdContact exptected = new AbdContact("1", null, null, "");
		exptected.setFirstname("Hans");
		exptected.setName("Peter");
		exptected.setSex('m');
		assertEquals(exptected, gImporterUnderTest.mapGContactToContact(contactEntry));
	}

	@Test
	public void testsetmyService(){
		ContactsService myService = new ContactsService(null);
		GoogleImporter instance = new GoogleImporter();
		instance.setMyService(myService);
		assertEquals(myService,instance.myService);
	}
	
	@Test
	public void testgetmyService(){
		ContactsService myService = new ContactsService(null);
		GoogleImporter instance = new GoogleImporter();
		instance.myService=myService;
		assertEquals(myService,instance.getMyService());
	}
	
	@Test
	public void testsetAccdata(){
		AbdAccount accdata = new AbdAccount();
		GoogleImporter instance = new GoogleImporter();
		instance.setAccdata(accdata);
		assertEquals(accdata,instance.accdata);
	}
	
	@Test
	public void testgetAccdata(){
		AbdAccount accdata = new AbdAccount();
		GoogleImporter instance = new GoogleImporter();
		instance.accdata = accdata;
		assertEquals(accdata,instance.getAccdata());
	}
	
	@Test
	public void testsetConnected(){
		GoogleImporter instance = new GoogleImporter();
		instance.setConnectionEtablished(true);
		assertEquals(true,instance.connectionEtablished);
	}
	
}
