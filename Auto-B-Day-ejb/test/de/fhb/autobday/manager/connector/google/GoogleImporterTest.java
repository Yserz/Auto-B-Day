/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhb.autobday.manager.connector.google;

import java.sql.Date;

import com.google.gdata.client.contacts.ContactsService;
import com.google.gdata.data.contacts.Birthday;
import com.google.gdata.data.contacts.ContactEntry;
import com.google.gdata.data.contacts.ContactGroupFeed;
import com.google.gdata.data.contacts.Gender;
import com.google.gdata.data.contacts.Gender.Value;
import com.google.gdata.data.extensions.FamilyName;
import com.google.gdata.data.extensions.GivenName;
import com.google.gdata.data.extensions.Name;

import de.fhb.autobday.commons.GoogleBirthdayConverter;
import de.fhb.autobday.data.AbdAccount;
import de.fhb.autobday.data.AbdContact;

import org.easymock.EasyMock;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author MacYser
 */
public class GoogleImporterTest {
	
	public GoogleImporterTest() {
	}
	private ContactEntry contactEntry;
	private GoogleImporter gImporter;

	@BeforeClass
	public static void setUpClass() throws Exception {
		
	}

	@AfterClass
	public static void tearDownClass() throws Exception {
	}
	
	@Before
	public void setUp() {
		Name name = new Name();
		gImporter = new GoogleImporter();
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

	@Test
	public void testMapGContacttoContact() {
		System.out.println("mapGContacttoContact");
		AbdContact exptected = new AbdContact("1", "", new Date(0), "");

		assertEquals(exptected, gImporter.mapGContacttoContact(contactEntry));

	}

	/**
	 * Test of getConnection method, of class GoogleImporter.
	 */
	@Test
	public void testGetConnection() {
		System.out.println("getConnection");
		AbdAccount data = null;
		GoogleImporter instance = new GoogleImporter();
		instance.getConnection(data);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of getAllGroups method, of class GoogleImporter.
	 */
	@Test
	public void testGetAllGroups() {
		System.out.println("getAllGroups");
		GoogleImporter instance = new GoogleImporter();
		ContactGroupFeed expResult = null;
		//ContactGroupFeed result = instance.getAllGroups();
		//assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of getSingleGroup method, of class GoogleImporter.
	 */
	@Test
	public void testGetSingleGroup() {
		System.out.println("getSingleGroup");
		String id = "";
		GoogleImporter instance = new GoogleImporter();
		instance.getSingleGroup(id);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of getAllContacts method, of class GoogleImporter.
	 */
	@Test
	public void testGetAllContacts() {
		System.out.println("getAllContacts");
		GoogleImporter instance = new GoogleImporter();
		instance.getAllContacts();
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of getSingleContact method, of class GoogleImporter.
	 */
	@Test
	public void testGetSingleContact() {
		System.out.println("getSingleContact");
		String id = "";
		GoogleImporter instance = new GoogleImporter();
		instance.getSingleContact(id);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of importContacts method, of class GoogleImporter.
	 */
	@Test
	public void testImportContacts() {
		System.out.println("importContacts");
		GoogleImporter instance = new GoogleImporter();
		instance.importContacts();
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}
}
