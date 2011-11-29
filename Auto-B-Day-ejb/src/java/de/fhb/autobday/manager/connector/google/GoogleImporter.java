package de.fhb.autobday.manager.connector.google;

import com.google.gdata.client.contacts.ContactsService;
import com.google.gdata.data.contacts.ContactEntry;
import com.google.gdata.data.contacts.ContactFeed;
import com.google.gdata.data.contacts.ContactGroupEntry;
import com.google.gdata.data.contacts.ContactGroupFeed;
import com.google.gdata.data.contacts.GroupMembershipInfo;
import com.google.gdata.data.extensions.Name;
import com.google.gdata.util.ServiceException;

import de.fhb.autobday.commons.GoogleBirthdayConverter;
import de.fhb.autobday.dao.AbdContactFacade;
import de.fhb.autobday.dao.AbdGroupFacade;
import de.fhb.autobday.data.AbdAccount;
import de.fhb.autobday.data.AbdContact;
import de.fhb.autobday.data.AbdGroup;
import de.fhb.autobday.manager.connector.AImporter;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Michael Koppen
 */
public class GoogleImporter extends AImporter {

	private final static Logger LOGGER = Logger.getLogger(GoogleImporter.class.getName());
	private boolean connectionEtablished;
	private AbdAccount accdata;
	private ContactsService myService;

	public GoogleImporter() {
		connectionEtablished = false;
		accdata = null;
		myService = null;
	}

	@Override
	public void getConnection(AbdAccount data) {
		connectionEtablished = false;
		accdata = data;

		System.out.println("Username: " + accdata.getUsername());
		System.out.println("Passwort: " + accdata.getPasswort());



		System.out.println("WARNING: User credentials not be used by connector!");
		//TODO Wenn daten in Datenbank, credentials aus übergabeparameter setzen

		try {
			myService = new ContactsService("BDayReminder");
			myService.setUserCredentials("fhbtestacc@googlemail.com", "TestGoogle123");

		} catch (ServiceException ex) {
			LOGGER.log(Level.SEVERE, null, ex);
		}
		connectionEtablished = true;
	}
	
	public List<ContactGroupEntry> getAllGroups() {
		URL feedUrl;
		try {
			feedUrl = new URL("https://www.google.com/m8/feeds/groups/default/full");
			ContactGroupFeed resultFeed = myService.getFeed(feedUrl, ContactGroupFeed.class);
			for (ContactGroupEntry groupEntry : resultFeed.getEntries()) {
				System.out.println("Atom Id: " + groupEntry.getId());
				System.out.println("Group Name: " + groupEntry.getTitle().getPlainText());
				System.out.println("Last Updated: " + groupEntry.getUpdated());

				if (groupEntry.hasSystemGroup()) {
					System.out.println("System Group Id: "
									   + groupEntry.getSystemGroup().getId());
				}
			}
			
			return resultFeed.getEntries();
		
		} catch (IOException ex) {
			Logger.getLogger(GoogleImporter.class.getName()).log(Level.SEVERE, null, ex);
		} catch (ServiceException ex) {
			Logger.getLogger(GoogleImporter.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
	}
	
	public void getSingleGroup(String id) {
		URL entryUrl;
		try {
			entryUrl = new URL("https://www.google.com/m8/feeds/groups/default/"+id);
			ContactGroupEntry resultEntry = myService.getEntry(entryUrl, ContactGroupEntry.class);
			
			System.out.println("Atom Id: " + resultEntry.getId());
			System.out.println("Group Name: " + resultEntry.getTitle().getPlainText());
			System.out.println("Last Updated: " + resultEntry.getUpdated());

			if (resultEntry.hasSystemGroup()) {
				System.out.println("System Group Id: "+ resultEntry.getSystemGroup().getId());
			}
			
		} catch (IOException ex) {
			Logger.getLogger(GoogleImporter.class.getName()).log(Level.SEVERE, null, ex);
		} catch (ServiceException ex) {
			Logger.getLogger(GoogleImporter.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public List<ContactEntry> getAllContacts() {
		URL feedUrl;
		try {
			feedUrl = new URL("https://www.google.com/m8/feeds/contacts/default/full");
			ContactFeed resultFeed = myService.getFeed(feedUrl, ContactFeed.class);
			return resultFeed.getEntries();
		} catch (IOException ex) {
			Logger.getLogger(GoogleImporter.class.getName()).log(Level.SEVERE, null, ex);
		} catch (ServiceException ex) {
			Logger.getLogger(GoogleImporter.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
	}
	public void getSingleContact(String id) {
		URL entryUrl;
		try {
			entryUrl = new URL("https://www.google.com/m8/feeds/contacts/default/full/"+id);
			ContactEntry resultEntry = myService.getEntry(entryUrl, ContactEntry.class);
			
			System.out.println("ID: "+resultEntry.getId());
			Name name = resultEntry.getName();
			if (name.hasFullName()) {
				String fullNameToDisplay = name.getFullName().getValue();
				if (name.getFullName().hasYomi()) {
					fullNameToDisplay += " (" + name.getFullName().getYomi() + ")";
				}
				System.out.println("\t\t" + fullNameToDisplay);
			} else {
				System.out.println("\t\t (no full name found)");
			}
			
		} catch (IOException ex) {
			Logger.getLogger(GoogleImporter.class.getName()).log(Level.SEVERE, null, ex);
		} catch (ServiceException ex) {
			Logger.getLogger(GoogleImporter.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@Override
	public void importContacts() {
		if (connectionEtablished && accdata != null) {
				//TODO push the data from RESULTFEED through the ACCOUNTDATA into the DATABASE.

				/*TODO may take a look at: 
				 * http://code.google.com/intl/de-DE/apis/contacts/docs/3.0/developers_guide.html#retrieving_with_query
				 * 
				 * setStringCustomParameter("group", groupId)
				 *  - Retrieve contacts belonging to the specified group Atom Id.
				 * 
				 * 
				 */
				
				String groupid,groupname,grouptemplate="Hier soll das Template rein";
				Boolean active=true;
				AbdGroup abdgroupEntry;
				AbdGroupFacade abdGroupFacade = new AbdGroupFacade();
				List<ContactGroupEntry> groups = getAllGroups();
				List<AbdGroup> abdgroups = new ArrayList<AbdGroup>(accdata.getAbdGroupCollection());
				List<ContactEntry> contacts = getAllContacts();
				
				for (ContactGroupEntry groupentry : groups) {
					groupid = groupentry.getId();
					if (existGroup(abdgroups,groupid)==null){
						abdgroupEntry = new AbdGroup(groupid);
						groupname=groupentry.getTitle().getPlainText();
						abdgroupEntry.setName(groupname);
						abdgroupEntry.setTemplate(grouptemplate);
						abdgroupEntry.setActive(active);
						abdGroupFacade.create(abdgroupEntry);
					}
				}
				
				for (ContactEntry contactEntry : contacts) {
					contactEntry.getGroupMembershipInfos().get(0).getHref();
				}
				
		} else {
			throw new UnsupportedOperationException("Please Connect the service first.");
		}
	}
	
	private AbdContact mapGContacttoContact(ContactEntry contactEntry){
		AbdContact contact;
		String firstname;
		String name;
		Date birthday;
		String mailadress;

		contact = new AbdContact();
		firstname = contactEntry.getName().getGivenName().getValue();
		contact.setFirstname(firstname);
		name = contactEntry.getName().getFamilyName().getValue();
		contact.setName(name);
		birthday = GoogleBirthdayConverter.convertBirthday(contactEntry.getBirthday().getValue());
		if (birthday != null) {
			contact.setBday(birthday);
		} 
		if (!contactEntry.getEmailAddresses().isEmpty()) {
			mailadress = contactEntry.getEmailAddresses().get(0).getAddress();
			contact.setMail(mailadress);
		}
		if (contactEntry.getGender().getValue() == contactEntry.getGender().getValue().FEMALE) {
			contact.setSex('w');
		} else {
			contact.setSex('m');
		}
		// //////////////////
		
		System.out.println("");
		System.out.println("Groups:");
		for (GroupMembershipInfo group : contactEntry.getGroupMembershipInfos()) {
			String groupHref = group.getHref();
			System.out.println("  Id: " + groupHref);
		}
		
		// //////////////////
		
		//contact.setActive(true);
		
		return contact;

	}
	
	private AbdGroup existGroup(List<AbdGroup> groups, String group){
		for (AbdGroup abdgroup : groups) {
			if (abdgroup.getId().equals(group)){
				return abdgroup;
			}
		}
		return null;
	}
}
