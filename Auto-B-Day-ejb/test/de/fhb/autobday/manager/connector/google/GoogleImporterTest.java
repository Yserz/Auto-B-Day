package de.fhb.autobday.manager.connector.google;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.google.gdata.client.contacts.ContactsService;
import com.google.gdata.data.DateTime;
import com.google.gdata.data.PlainTextConstruct;
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
import de.fhb.autobday.data.AbdAccount;
import de.fhb.autobday.data.AbdContact;
import de.fhb.autobday.data.AbdGroup;
import de.fhb.autobday.exception.connector.ConnectorCouldNotLoginException;
import de.fhb.autobday.exception.connector.ConnectorInvalidAccountException;

/**
 *
 * @author
 * Tino Reuschel <reuschel@fh-brandenburg.de>
 */
public class GoogleImporterTest {
	
private JavaEEGloss gloss;
	
	private ContactsService contactsServiceMock;
	private ContactEntry contactEntry;
	private GoogleImporter gImporterUnderTest;
	
	public GoogleImporterTest() {
	}

	@Before
	public void setUp() {
		
		gloss= new JavaEEGloss();
		
		//create Mocks
		contactsServiceMock = createMock(ContactsService.class);
		
		//set Objekts to inject
		gImporterUnderTest=gloss.make(GoogleImporter.class);
		
		
		//prepare test variables
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

	/**
	 * Test of getConnection method, of class GoogleImporter.
	 * @throws AuthenticationException 
	 * @throws ConnectorInvalidAccountException 
	 * @throws ConnectorCouldNotLoginException 
	 */
	@Test
	public void testGetConnection() throws AuthenticationException, ConnectorCouldNotLoginException, ConnectorInvalidAccountException{
		System.out.println("getConnection");

		AbdAccount data = new AbdAccount(1, "fhbtestacc@googlemail.com", "TestGoogle123", null);
		
		// Setup is finished need to activate the mock
		replay(contactsServiceMock);
		gImporterUnderTest.getConnection(data);
		
		//call method to test and verify
		assertEquals(true, gImporterUnderTest.isConnectionEtablished());
		verify(contactsServiceMock);
	}
	
	/**
	 * Test of getConnection method, of class GoogleImporter.
	 * @throws AuthenticationException 
	 * @throws ConnectorInvalidAccountException 
	 * @throws ConnectorCouldNotLoginException 
	 */
	@Test (expected = ConnectorInvalidAccountException.class)
	public void testGetConnectionCouldThrowConnectorInvalidAccountException() throws AuthenticationException, ConnectorCouldNotLoginException, ConnectorInvalidAccountException{
		System.out.println("getConnection");

		// Setup is finished need to activate the mock
		replay(contactsServiceMock);
		
		//call method to test and verify
		gImporterUnderTest.getConnection(null);
		verify(contactsServiceMock);
	}

	/**
	 * Test of getAllGroups method, of class GoogleImporter.
	 * @throws ServiceException 
	 * @throws IOException 
	 */
	@Test
	public void testGetAllGroupsWithNull() throws IOException, ServiceException {
		System.out.println("getAllGroups");
		
		//prepare test variables
		GoogleImporter instance = new GoogleImporter();
		ContactsService myServiceMock = createMock(ContactsService.class);
		URL feedUrl;
		feedUrl = new URL("https://www.google.com/m8/feeds/groups/default/full");
		
		// Setting up the expected value of the method call of Mockobject
		expect(myServiceMock.getFeed(feedUrl, ContactGroupFeed.class)).andReturn(null);
		
		// Setup is finished need to activate the mock
		replay(myServiceMock);
		instance.myService = myServiceMock;
		assertEquals(null, instance.getAllGroups());
	}
	
	/**
	 * Test of getAllGroups method, of class GoogleImporter.
	 * @throws ServiceException 
	 * @throws IOException 
	 */
	@Test
	public void testGetAllGroupsWithGroupFeed() throws IOException, ServiceException {
		System.out.println("getAllGroups");
		
		//prepare test variables
		GoogleImporter instance = new GoogleImporter();
		ContactsService myServiceMock = createMock(ContactsService.class);
		URL feedUrl;
		ContactGroupFeed resultFeed = new ContactGroupFeed();
		List<ContactGroupEntry> contactGroupList = new ArrayList<ContactGroupEntry>();
		ContactGroupEntry contactGroup = new ContactGroupEntry();
		contactGroup.setId("hsfsfd");
		contactGroupList.add(contactGroup);
		resultFeed.setEntries(contactGroupList);
		feedUrl = new URL("https://www.google.com/m8/feeds/groups/default/full");
		
		// Setting up the expected value of the method call of Mockobject
		expect(myServiceMock.getFeed(feedUrl, ContactGroupFeed.class)).andReturn(resultFeed);
		
		// Setup is finished need to activate the mock
		replay(myServiceMock);
		
		//call method to test and verify
		instance.myService = myServiceMock;
		assertEquals(contactGroupList, instance.getAllGroups());
	}
	
	@Test
	public void testGetAllGroupsByThrowingIOException() throws IOException, ServiceException{
		System.out.println("testGetAllGroupsByThrowingIOException");
		
		GoogleImporter instance = new GoogleImporter();
		ContactsService myServiceMock = createMock(ContactsService.class);
		URL feedUrl;
		feedUrl = new URL("https://www.google.com/m8/feeds/groups/default/full");
		
		// Setting up the expected value of the method call of Mockobject
		expect(myServiceMock.getFeed(feedUrl, ContactGroupFeed.class)).andThrow(new IOException());
		
		// Setup is finished need to activate the mock
		replay(myServiceMock);
		
		//call method to test and verify
		instance.myService = myServiceMock;
		assertEquals(null,instance.getAllGroups());
	}
	
	@Test
	public void testGetAllGroupsByThrowingServiceException() throws IOException, ServiceException{
		System.out.println("testGetAllGroupsByThrowingServiceException");
		
		//prepare test variables
		GoogleImporter instance = new GoogleImporter();
		ContactsService myServiceMock = createMock(ContactsService.class);
		URL feedUrl;
		feedUrl = new URL("https://www.google.com/m8/feeds/groups/default/full");
		
		// Setting up the expected value of the method call of Mockobject
		expect(myServiceMock.getFeed(feedUrl, ContactGroupFeed.class)).andThrow(new ServiceException(""));
		
		// Setup is finished need to activate the mock
		replay(myServiceMock);
		
		//call method to test and verify
		instance.myService = myServiceMock;
		assertEquals(null,instance.getAllGroups());
	}

	/**
	 * Test of getAllContacts method, of class GoogleImporter.
	 * @throws ServiceException 
	 * @throws IOException 
	 */
	@Test
	public void testGetAllContactsReturnNull() throws IOException, ServiceException {
		System.out.println("getAllContacts");
		
		//prepare test variables
		GoogleImporter instance = new GoogleImporter();
		ContactsService myServiceMock = createMock(ContactsService.class);
		URL feedUrl;
		feedUrl = new URL("https://www.google.com/m8/feeds/contacts/default/full");
		
		// Setting up the expected value of the method call of Mockobject
		expect(myServiceMock.getFeed(feedUrl, ContactFeed.class)).andReturn(null);
		
		// Setup is finished need to activate the mock
		replay(myServiceMock);
		
		//call method to test and verify
		instance.myService = myServiceMock;
		assertEquals(null, instance.getAllContacts());
	}
	
	/**
	 * Test of getAllContacts method, of class GoogleImporter.
	 * @throws ServiceException 
	 * @throws IOException 
	 */
	@Test
	public void testGetAllContactsReturnList() throws IOException, ServiceException {
		System.out.println("getAllContacts");
		
		//prepare test variables
		GoogleImporter instance = new GoogleImporter();
		ContactsService myServiceMock = createMock(ContactsService.class);
		URL feedUrl;
		List<ContactEntry> contactEntryList = new ArrayList<ContactEntry>();
		contactEntryList.add(contactEntry);
		ContactFeed resultFeed = new ContactFeed();
		resultFeed.setEntries(contactEntryList);
		feedUrl = new URL("https://www.google.com/m8/feeds/contacts/default/full");
		
		// Setting up the expected value of the method call of Mockobject
		expect(myServiceMock.getFeed(feedUrl, ContactFeed.class)).andReturn(resultFeed);
		
		// Setup is finished need to activate the mock
		replay(myServiceMock);
		
		//call method to test and verify
		instance.myService = myServiceMock;
		assertEquals(contactEntryList, instance.getAllContacts());
	}
	
	@Test
	public void testGetAllContactsByThrowingIOException() throws IOException, ServiceException{
		
		//prepare test variables
		GoogleImporter instance = new GoogleImporter();
		ContactsService myServiceMock = createMock(ContactsService.class);
		URL feedUrl;
		feedUrl = new URL("https://www.google.com/m8/feeds/contacts/default/full");
		
		// Setting up the expected value of the method call of Mockobject
		expect(myServiceMock.getFeed(feedUrl, ContactFeed.class)).andThrow(new IOException());
		
		// Setup is finished need to activate the mock
		replay(myServiceMock);
		
		//call method to test and verify
		instance.myService = myServiceMock;
		assertEquals(null,instance.getAllContacts());
	}
	
	@Test
	public void testGetAllContactsByThrowingServiceException() throws IOException, ServiceException{
		
		//prepare test variables
		GoogleImporter instance = new GoogleImporter();
		ContactsService myServiceMock = createMock(ContactsService.class);
		URL feedUrl;
		feedUrl = new URL("https://www.google.com/m8/feeds/contacts/default/full");
		
		// Setting up the expected value of the method call of Mockobject
		expect(myServiceMock.getFeed(feedUrl, ContactFeed.class)).andThrow(new ServiceException(""));
		
		// Setup is finished need to activate the mock
		replay(myServiceMock);
		
		//call method to test and verify
		instance.myService = myServiceMock;
		assertEquals(null,instance.getAllContacts());
	}

	/**
	 * Test of importContacts method, of class GoogleImporter.
	 */
	@Test
	@Ignore
	public void testImportContacts() {
		System.out.println("importContacts");
		
		
		
	}
	
	@Test
	public void testgetGContactFirstMailAdressWithOneAdress(){
		System.out.println("getGContactFirstMailAdress");
		
		//prepare test variables
		GoogleImporter instance = new GoogleImporter();
		Email mail = new Email();
		mail.setAddress("test@aol.de");
		contactEntry.addEmailAddress(mail);
		
		//call method to test and verify
		assertEquals("test@aol.de", instance.getGContactFirstMailAdress(contactEntry));
	}
	
	@Test
	public void testgetGContactFirstMailAdressWithManyAdress(){
		System.out.println("getGContactFirstMailAdress");
		
		//prepare test variables
		GoogleImporter instance = new GoogleImporter();
		Email mail = new Email();
		mail.setAddress("test@aol.de");
		contactEntry.addEmailAddress(mail);
		mail.setAddress("test@fhb.de");
		contactEntry.addEmailAddress(mail);
		mail.setAddress("test@cks.de");
		contactEntry.addEmailAddress(mail);
		
		//call method to test and verify
		assertEquals(contactEntry.getEmailAddresses().get(0).getAddress(), instance.getGContactFirstMailAdress(contactEntry));
	}
	
	@Test
	public void testgetGContactFirstMailAdressWithNoAdress(){
		System.out.println("getGContactFirstMailAdress");
		
		//call method to test and verify
		GoogleImporter instance = new GoogleImporter();
		assertEquals("", instance.getGContactFirstMailAdress(contactEntry));
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testgetGContactBirthdayWithABirthday(){
		System.out.println("getGContactBirthday");
	
		GoogleImporter instance = new GoogleImporter();		
		
		//call method to test and verify
		assertEquals(new Date(90, 4, 22), instance.getGContactBirthday(contactEntry));
	}
	
	@Test
	public void testgetGContactBirthdayWithoutABirthday(){
		System.out.println("getGContactBirthday");
		
		//prepare test variables
		GoogleImporter instance = new GoogleImporter();
		contactEntry.setBirthday(new Birthday("---"));
		
		//call method to test and verify
		assertEquals(null, instance.getGContactBirthday(contactEntry));
	}

	@Test
	public void testgetGContactFamilyname(){
		GoogleImporter instance = new GoogleImporter();
		
		//call method to test and verify
		assertEquals("Peter",instance.getGContactFamilyname(contactEntry));
	}
	
	@Test
	public void testgetGContactFirstname(){
		GoogleImporter instance = new GoogleImporter();
		
		//call method to test and verify
		assertEquals("Hans",instance.getGContactFirstname(contactEntry));
	}
	
	@Test
	public void testMapGContacttoContactFemale() {
		System.out.println("mapGContacttoContact");
		
		//prepare test variables
		Email mail = new Email();
		mail.setAddress("test@aol.de");
		DateTime dateTime = new DateTime();
		dateTime = DateTime.now();
		contactEntry.setUpdated(dateTime);
		contactEntry.addEmailAddress(mail);
		@SuppressWarnings("deprecation")
		AbdContact exptected = new AbdContact("1", "test@fhb.de", new Date(90, 4, 22), "");
		exptected.setFirstname("Hans");
		exptected.setName("Peter");
		exptected.setSex('w');
		exptected.setUpdated(new Date(dateTime.getValue()));
		
		//call method to test and verify
		assertEquals(exptected, gImporterUnderTest.mapGContactToContact(contactEntry));
	}
	
	@Test
	public void testMapGContacttoContactMale() {
		System.out.println("mapGContacttoContact");
		
		//prepare test variables
		Email mail = new Email();
		mail.setAddress("test@aol.de");
		DateTime dateTime = new DateTime();
		dateTime = DateTime.now();
		contactEntry.setUpdated(dateTime);
		contactEntry.addEmailAddress(mail);
		contactEntry.setGender(new Gender(Value.MALE));
		@SuppressWarnings("deprecation")
		AbdContact exptected = new AbdContact("1", "test@fhb.de", new Date(90, 4, 22), "");
		exptected.setFirstname("Hans");
		exptected.setName("Peter");
		exptected.setSex('m');
		exptected.setUpdated(new Date(dateTime.getValue()));
		
		//call method to test and verify
		assertEquals(exptected, gImporterUnderTest.mapGContactToContact(contactEntry));
	}
	
	/**
	 * TODO das sollte hier null sein
	 */
	@Test
	@Ignore
	public void testMapGContacttoContactWithoutEmailandBirthday() {
		System.out.println("mapGContacttoContact");
		
		//prepare test variables
		DateTime dateTime = new DateTime();
		dateTime = DateTime.now();
		contactEntry.setUpdated(dateTime);
		contactEntry.setGender(null);
		contactEntry.setBirthday(new Birthday("---"));
		AbdContact exptected = new AbdContact("1", null, null, "");
		exptected.setFirstname("Hans");
		exptected.setName("Peter");
		exptected.setSex(null);
		exptected.setUpdated(new Date(dateTime.getValue()));
		
		//call method to test and verify
		assertEquals(exptected, gImporterUnderTest.mapGContactToContact(contactEntry));
	}

	@Test
	public void testGetGroupname(){
		ContactGroupEntry contactGroupEntry = new ContactGroupEntry();
		PlainTextConstruct title = new PlainTextConstruct();
		title.setText("Dies ist der Titel");
		contactGroupEntry.setTitle(title);
		
		//call method to test and verify
		assertEquals("Dies ist der Titel",gImporterUnderTest.getGroupName(contactGroupEntry));
	}
	
	@Test
	public void testMapGgroupToGroup(){
		System.out.println("testMapGgroupToGroup");
		
		//prepare test variables
		ContactGroupEntry contactGroupEntry = new ContactGroupEntry();
		AbdGroup abdGroup = new AbdGroup();
		AbdAccount abdAccount = new AbdAccount();
		PlainTextConstruct title = new PlainTextConstruct();
		title.setText("Dies ist der Titel");
		DateTime dateTime = new DateTime();
		dateTime = DateTime.now();
		contactGroupEntry.setTitle(title);
		contactGroupEntry.setId("id");
		contactGroupEntry.setUpdated(dateTime);
		abdGroup.setActive(true);
		abdGroup.setId("id");
		abdGroup.setName("Dies ist der Titel");
		abdGroup.setTemplate("Hier soll das Template rein");
		abdGroup.setUpdated(new Date(dateTime.getValue()));
		abdGroup.setAccount(abdAccount);
		gImporterUnderTest.accdata = abdAccount;
		
		//call method to test and verify
		assertEquals(abdGroup, gImporterUnderTest.mapGGroupToGroup(contactGroupEntry));
	}
	
	@Test
	public void testupdateGroups() throws IOException, ServiceException{
		System.out.println("testupdateGroups");
		
		//prepare test variables
		ContactsService myServiceMock = createMock(ContactsService.class);
		URL feedUrl;
		ContactGroupFeed resultFeed = new ContactGroupFeed();
		List<ContactGroupEntry> contactGroupList = new ArrayList<ContactGroupEntry>();
		ContactGroupEntry contactGroupEntry = new ContactGroupEntry();
		AbdAccount accdata = new AbdAccount();
		AbdAccount exceptedAccount = new AbdAccount();
		AbdGroup abdGroup = new AbdGroup();
		
		PlainTextConstruct title = new PlainTextConstruct();
		title.setText("Dies ist der Titel");
		DateTime dateTime = new DateTime();
		dateTime = DateTime.now();
		contactGroupEntry.setTitle(title);
		contactGroupEntry.setId("id");
		contactGroupEntry.setUpdated(dateTime);
		contactGroupList.add(contactGroupEntry);
		resultFeed.setEntries(contactGroupList);
		
		abdGroup.setActive(true);
		abdGroup.setId("id");
		abdGroup.setName("Dies ist der Titel");
		abdGroup.setTemplate("Hier soll das Template rein");
		abdGroup.setUpdated(new Date(dateTime.getValue()));
		abdGroup.setAccount(accdata);
		accdata.setAbdGroupCollection(new ArrayList<AbdGroup>());
		
		gImporterUnderTest.accdata = accdata;
		ArrayList<AbdGroup> groupList = new ArrayList<AbdGroup>();
		groupList.add(abdGroup);
		exceptedAccount.setAbdGroupCollection(groupList);
		
		feedUrl = new URL("https://www.google.com/m8/feeds/groups/default/full");
		
		// Setting up the expected value of the method call of Mockobject
		expect(myServiceMock.getFeed(feedUrl, ContactGroupFeed.class)).andReturn(resultFeed);
		replay(myServiceMock);
		gImporterUnderTest.myService = myServiceMock;
		
		gImporterUnderTest.updateGroups();
		
		//call method to test and verify
		assertEquals(exceptedAccount,gImporterUnderTest.accdata);
	}
	
	@Test
	public void testupdateGroupsWithANewGroup() throws IOException, ServiceException{
		System.out.println("testupdateGroupsWithANewGroup");
		
		//prepare test variables
		ContactsService myServiceMock = createMock(ContactsService.class);
		URL feedUrl;
		ContactGroupFeed resultFeed = new ContactGroupFeed();
		List<ContactGroupEntry> contactGroupList = new ArrayList<ContactGroupEntry>();
		ContactGroupEntry contactGroupEntry = new ContactGroupEntry();
		AbdAccount accdata = new AbdAccount();
		AbdAccount exceptedAccount = new AbdAccount();
		AbdGroup abdGroup = new AbdGroup();
		AbdGroup abdGroupOld = new AbdGroup();
		ArrayList<AbdGroup> groupList = new ArrayList<AbdGroup>();
		
		PlainTextConstruct title = new PlainTextConstruct();
		title.setText("Dies ist der Titel");
		DateTime dateTime = new DateTime();
		dateTime = DateTime.now();
		contactGroupEntry.setTitle(title);
		contactGroupEntry.setId("id");
		contactGroupEntry.setUpdated(dateTime);
		contactGroupList.add(contactGroupEntry);
		resultFeed.setEntries(contactGroupList);
		
		abdGroup.setActive(true);
		abdGroup.setId("id1");
		abdGroup.setName("Dies ist der Titel");
		abdGroup.setTemplate("Hier soll das Template rein");
		abdGroup.setUpdated(new Date(dateTime.getValue()));
		abdGroup.setAccount(accdata);
		
		abdGroupOld.setActive(true);
		abdGroupOld.setId("id1");
		abdGroupOld.setName("Dies ist der Titel");
		abdGroupOld.setTemplate("Hier soll das Template rein");
		abdGroupOld.setUpdated(new Date(dateTime.getValue()));
		abdGroupOld.setAccount(accdata);

		groupList.add(abdGroupOld);
		accdata.setAbdGroupCollection(groupList);
		
		gImporterUnderTest.accdata = accdata;
		groupList = new ArrayList<AbdGroup>();
		groupList.add(abdGroup);
		groupList.add(abdGroupOld);
		exceptedAccount.setAbdGroupCollection(groupList);
		
		feedUrl = new URL("https://www.google.com/m8/feeds/groups/default/full");
		
		// Setting up the expected value of the method call of Mockobject
		expect(myServiceMock.getFeed(feedUrl, ContactGroupFeed.class)).andReturn(resultFeed);
		
		// Setup is finished need to activate the mock
		replay(myServiceMock);
		
		//call method to test and verify
		gImporterUnderTest.myService = myServiceMock;
		gImporterUnderTest.updateGroups();
		assertEquals(exceptedAccount,gImporterUnderTest.accdata);
	}
	
	@Test
	public void testupdateGroupsWithNoUpdates() throws IOException, ServiceException{
		System.out.println("testupdateGroupsWithNoUpdates");
		
		//prepare test variables
		ContactsService myServiceMock = createMock(ContactsService.class);
		URL feedUrl;
		ContactGroupFeed resultFeed = new ContactGroupFeed();
		List<ContactGroupEntry> contactGroupList = new ArrayList<ContactGroupEntry>();
		ContactGroupEntry contactGroupEntry = new ContactGroupEntry();
		AbdAccount accdata = new AbdAccount();
		AbdAccount exceptedAccount = new AbdAccount();
		AbdGroup abdGroup = new AbdGroup();
		ArrayList<AbdGroup> groupList = new ArrayList<AbdGroup>();
		
		PlainTextConstruct title = new PlainTextConstruct();
		title.setText("Dies ist der Titel");
		DateTime dateTime = new DateTime();
		dateTime = DateTime.now();
		contactGroupEntry.setTitle(title);
		contactGroupEntry.setId("id");
		contactGroupEntry.setUpdated(dateTime);
		contactGroupList.add(contactGroupEntry);
		resultFeed.setEntries(contactGroupList);
		
		abdGroup.setActive(true);
		abdGroup.setId("id");
		abdGroup.setName("Dies ist der Titel");
		abdGroup.setTemplate("Hier soll das Template rein");
		abdGroup.setUpdated(new Date(dateTime.getValue()));
		abdGroup.setAccount(accdata);

		groupList.add(abdGroup);
		accdata.setAbdGroupCollection(groupList);
		
		gImporterUnderTest.accdata = accdata;
		groupList = new ArrayList<AbdGroup>();
		groupList.add(abdGroup);
		exceptedAccount.setAbdGroupCollection(groupList);
		
		feedUrl = new URL("https://www.google.com/m8/feeds/groups/default/full");
		
		// Setting up the expected value of the method call of Mockobject
		expect(myServiceMock.getFeed(feedUrl, ContactGroupFeed.class)).andReturn(resultFeed);
		
		// Setup is finished need to activate the mock
		replay(myServiceMock);
		
		//call method to test and verify
		gImporterUnderTest.myService = myServiceMock;
		gImporterUnderTest.updateGroups();
		assertEquals(exceptedAccount,gImporterUnderTest.accdata);
	}
	
	@Test
	public void testupdateGroupsWithAUpdateGroup() throws IOException, ServiceException{
		System.out.println("testupdateGroupsWithAUpdateGroup");
		
		//prepare test variables
		ContactsService myServiceMock = createMock(ContactsService.class);
		URL feedUrl;
		ContactGroupFeed resultFeed = new ContactGroupFeed();
		List<ContactGroupEntry> contactGroupList = new ArrayList<ContactGroupEntry>();
		ContactGroupEntry contactGroupEntry = new ContactGroupEntry();
		AbdAccount accdata = new AbdAccount();
		AbdAccount exceptedAccount = new AbdAccount();
		AbdGroup abdGroup = new AbdGroup();
		AbdGroup abdGroupOld = new AbdGroup();
		ArrayList<AbdGroup> groupList = new ArrayList<AbdGroup>();
		
		PlainTextConstruct title = new PlainTextConstruct();
		title.setText("Dies ist der Titel");
		DateTime dateTime = new DateTime();
		dateTime = DateTime.now();
		contactGroupEntry.setTitle(title);
		contactGroupEntry.setId("id");
		contactGroupEntry.setUpdated(dateTime);
		contactGroupList.add(contactGroupEntry);
		resultFeed.setEntries(contactGroupList);
		
		abdGroup.setActive(true);
		abdGroup.setId("id");
		abdGroup.setName("Dies ist der Titel");
		abdGroup.setTemplate("Hier soll das Template rein");
		abdGroup.setUpdated(new Date(dateTime.getValue()));
		abdGroup.setAccount(accdata);
		
		abdGroupOld.setActive(true);
		abdGroupOld.setId("id");
		abdGroupOld.setName("Dies ist der Titel23");
		abdGroupOld.setTemplate("Hier soll das Template rein");
		abdGroupOld.setUpdated(new Date(dateTime.getValue()-10));
		abdGroupOld.setAccount(accdata);

		groupList.add(abdGroupOld);
		accdata.setAbdGroupCollection(groupList);
		
		gImporterUnderTest.accdata = accdata;
		groupList = new ArrayList<AbdGroup>();
		groupList.add(abdGroup);
		exceptedAccount.setAbdGroupCollection(groupList);
		
		feedUrl = new URL("https://www.google.com/m8/feeds/groups/default/full");
		
		// Setting up the expected value of the method call of Mockobject
		expect(myServiceMock.getFeed(feedUrl, ContactGroupFeed.class)).andReturn(resultFeed);
		
		// Setup is finished need to activate the mock
		replay(myServiceMock);
		
		//call method to test and verify
		gImporterUnderTest.myService = myServiceMock;
		gImporterUnderTest.updateGroups();
		assertEquals(exceptedAccount,gImporterUnderTest.accdata);
	}
	
	@Test
	public void updateContactThrowNullPointerException() throws IOException, ServiceException{
		System.out.println("updateContactThrowNullPointerException");
		
		//prepare test variables
		GoogleImporter instance = new GoogleImporter();
		ContactsService myServiceMock = createMock(ContactsService.class);
		AbdContactFacade contactDAOMock = createMock(AbdContactFacade.class);
		URL feedUrl;
		List<ContactEntry> contactEntryList = new ArrayList<ContactEntry>();
		DateTime dateTime = new DateTime();
		dateTime = DateTime.now();
		contactEntry.setUpdated(dateTime);
		Email mail = new Email();
		mail.setAddress("test@aol.de");
		contactEntry.addEmailAddress(mail);
		contactEntryList.add(contactEntry);
		ContactFeed resultFeed = new ContactFeed();
		resultFeed.setEntries(contactEntryList);
		feedUrl = new URL("https://www.google.com/m8/feeds/contacts/default/full");
		
		// Setting up the expected value of the method call of Mockobject
		expect(myServiceMock.getFeed(feedUrl, ContactFeed.class)).andReturn(resultFeed);
		expect(contactDAOMock.find(contactEntry.getId())).andThrow(new NullPointerException());
		
		// Setup is finished need to activate the mock
		replay(myServiceMock);
		instance.myService = myServiceMock;
		instance.updateContacts();
	}
	
	@Test
	public void updateContactWithNewContact() throws IOException, ServiceException{
		System.out.println("updateContactThrowNullPointerException");
		
		//prepare test variables
		GoogleImporter instance = new GoogleImporter();
		ContactsService myServiceMock = createMock(ContactsService.class);
		AbdContactFacade contactDAOMock = createMock(AbdContactFacade.class);
		URL feedUrl;
		List<ContactEntry> contactEntryList = new ArrayList<ContactEntry>();
		DateTime dateTime = new DateTime();
		dateTime = DateTime.now();
		contactEntry.setUpdated(dateTime);
		Email mail = new Email();
		mail.setAddress("test@aol.de");
		contactEntry.addEmailAddress(mail);
		contactEntryList.add(contactEntry);
		ContactFeed resultFeed = new ContactFeed();
		resultFeed.setEntries(contactEntryList);
		feedUrl = new URL("https://www.google.com/m8/feeds/contacts/default/full");
		
		@SuppressWarnings("deprecation")
		AbdContact exptected = new AbdContact("1", "test@fhb.de", new Date(90, 4, 22), "");
		exptected.setFirstname("Hans");
		exptected.setName("Peter");
		exptected.setSex('w');
		exptected.setUpdated(new Date(dateTime.getValue()));
		
		// Setting up the expected value of the method call of Mockobject
		expect(myServiceMock.getFeed(feedUrl, ContactFeed.class)).andReturn(resultFeed);
		expect(contactDAOMock.find(contactEntry.getId())).andReturn(exptected);
		
		// Setup is finished need to activate the mock
		replay(myServiceMock);
		instance.myService = myServiceMock;
		instance.updateContacts();
	}
	
}