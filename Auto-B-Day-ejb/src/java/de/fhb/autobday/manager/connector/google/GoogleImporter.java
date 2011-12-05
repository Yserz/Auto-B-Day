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
import de.fhb.autobday.dao.AbdGroupToContactFacade;
import de.fhb.autobday.data.AbdAccount;
import de.fhb.autobday.data.AbdContact;
import de.fhb.autobday.data.AbdGroup;
import de.fhb.autobday.data.AbdGroupToContact;
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
 * @author Tino Reuschel
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
		
		LOGGER.info("getConnection");
		LOGGER.info("data :" + data.getId());
		
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
	
		LOGGER.info("getAllGroups");
		
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
			LOGGER.log(Level.SEVERE, null, ex);
		} catch (ServiceException ex) {
			LOGGER.log(Level.SEVERE, null, ex);
		}
		return null;
	}
	
	public void getSingleGroup(String groupid) {
		
		LOGGER.info("getSingleGroup");
		LOGGER.log(Level.INFO, "groupid :{0}", groupid);
		
		
		URL entryUrl;
		try {
			entryUrl = new URL("https://www.google.com/m8/feeds/groups/default/"+groupid);
			ContactGroupEntry resultEntry = myService.getEntry(entryUrl, ContactGroupEntry.class);
			
			System.out.println("Atom Id: " + resultEntry.getId());
			System.out.println("Group Name: " + resultEntry.getTitle().getPlainText());
			System.out.println("Last Updated: " + resultEntry.getUpdated());

			if (resultEntry.hasSystemGroup()) {
				System.out.println("System Group Id: "+ resultEntry.getSystemGroup().getId());
			}
			
		} catch (IOException ex) {
			LOGGER.log(Level.SEVERE, null, ex);
		} catch (ServiceException ex) {
			LOGGER.log(Level.SEVERE, null, ex);
		}
	}

	public List<ContactEntry> getAllContacts() {

		LOGGER.info("getAllContacts");

		URL feedUrl;
		try {
			feedUrl = new URL("https://www.google.com/m8/feeds/contacts/default/full");
			ContactFeed resultFeed = myService.getFeed(feedUrl, ContactFeed.class);
			return resultFeed.getEntries();
		} catch (IOException ex) {
			LOGGER.log(Level.SEVERE, null, ex);
		} catch (ServiceException ex) {
			LOGGER.log(Level.SEVERE, null, ex);
		}
		return null;
	}
	
	public void getSingleContact(String contactid) {
		
		LOGGER.info("getSingleContact");
		LOGGER.info("contactid :" + contactid);
		
		URL entryUrl;
		try {
			entryUrl = new URL("https://www.google.com/m8/feeds/contacts/default/full/"+contactid);
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
			LOGGER.log(Level.SEVERE, null, ex);
		} catch (ServiceException ex) {
			LOGGER.log(Level.SEVERE, null, ex);
		}
	}

	@Override
	public void importContacts() {
		
		LOGGER.info("importContacts");
		
		if (connectionEtablished && accdata != null) {
				
				String groupid,groupname,grouptemplate="Hier soll das Template rein";
				Boolean active=true;
				AbdGroup abdgroupEntry;
				AbdContact abdcontact;
				AbdContact abdcontacthelp;
				AbdGroupFacade abdGroupFacade = new AbdGroupFacade();
				AbdContactFacade abdContactFacade = new AbdContactFacade();
				List<ContactGroupEntry> groups = getAllGroups();
				List<AbdGroup> abdgroups = new ArrayList<AbdGroup>(accdata.getAbdGroupCollection());
				List<ContactEntry> contacts = getAllContacts();
				List<GroupMembershipInfo> groupMembershipInfo;
				
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
					abdcontact = mapGContacttoContact(contactEntry);
					abdcontacthelp=abdContactFacade.find(abdcontact.getId());
					if (abdcontacthelp ==  null){
						abdContactFacade.create(abdcontact);
					} else {
						if (!abdcontact.equals(abdcontacthelp)){
							abdContactFacade.edit(abdcontact);
						}
					}
					groupMembershipInfo=contactEntry.getGroupMembershipInfos();
					updateGroupMembership(contactEntry.getId(),groupMembershipInfo);
				}
				

		} else {
			throw new UnsupportedOperationException("Please Connect the service first.");
		}
	}
	
	private AbdContact mapGContacttoContact(ContactEntry contactEntry){
		
		LOGGER.info("mapGContacttoContact");
		LOGGER.log(Level.INFO, "contactEntry :{0}", contactEntry.getId());
		
		AbdContact contact;
		String firstname;
		String name;
		Date birthday;
		String mailadress;
		String id;

		contact = new AbdContact();
		id = contactEntry.getId();
		contact.setId(id);
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
		
		return contact;

	}
	
	private AbdGroup existGroup(List<AbdGroup> groups, String group){
		
		LOGGER.info("existGroup");
		LOGGER.log(Level.INFO, "group :{0}", group);
		
		for (AbdGroup abdgroup : groups) {
			if (abdgroup.getId().equals(group)){
				return abdgroup;
			}
		}
		return null;
	}

	private void updateGroupMembership(String id, List<GroupMembershipInfo> groupMembership){
		AbdGroupToContactFacade abdGroupToContactFacade = new AbdGroupToContactFacade();
		AbdGroupToContact abdGroupToContactEntity;
		AbdContactFacade abdContactFacade = new AbdContactFacade();
		AbdGroupFacade abdGroupFacade = new AbdGroupFacade();
		List<AbdGroupToContact> abdGroupMembership = new ArrayList<AbdGroupToContact> (abdGroupToContactFacade.findContactByContact(id));
		for (int i = 0; i < groupMembership.size(); i++) {
			if(diffMembership(groupMembership.get(i).getHref(), abdGroupMembership)){
				groupMembership.remove(i);
			}
		}
		if (!abdGroupMembership.isEmpty()){
			for (AbdGroupToContact abdGroupToContact : abdGroupMembership) {
				abdGroupToContactFacade.remove(abdGroupToContact);
			}
		}
		if (!groupMembership.isEmpty()){
			for (GroupMembershipInfo groupMembershipInfo : groupMembership) {
				abdGroupToContactEntity = new AbdGroupToContact();
				abdGroupToContactEntity.setAbdContact(abdContactFacade.find(id));
				abdGroupToContactEntity.setAbdGroup(abdGroupFacade.find(groupMembershipInfo.getHref()));
				abdGroupToContactEntity.setActive(true);
				abdGroupToContactFacade.create(abdGroupToContactEntity);
			}
		}
	}
	
	private boolean diffMembership(String groupid, List<AbdGroupToContact> abdGroupMembership){
		for (int i = 0; i < abdGroupMembership.size(); i++) {
			if(abdGroupMembership.get(i).getAbdGroup().getId().equals(groupid)){
				abdGroupMembership.remove(i);
				return true;
			}
		}
		return false;
	}
}
