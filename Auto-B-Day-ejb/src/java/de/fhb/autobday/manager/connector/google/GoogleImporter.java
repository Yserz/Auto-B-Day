package de.fhb.autobday.manager.connector.google;

import com.google.gdata.client.contacts.ContactsService;
import com.google.gdata.data.contacts.Gender.Value;
import com.google.gdata.data.contacts.*;
import com.google.gdata.data.extensions.Email;
import com.google.gdata.util.ServiceException;
import de.fhb.autobday.commons.GoogleBirthdayConverter;
import de.fhb.autobday.dao.AbdContactFacade;
import de.fhb.autobday.dao.AbdGroupFacade;
import de.fhb.autobday.dao.AbdGroupToContactFacade;
import de.fhb.autobday.data.AbdAccount;
import de.fhb.autobday.data.AbdContact;
import de.fhb.autobday.data.AbdGroup;
import de.fhb.autobday.data.AbdGroupToContact;
import de.fhb.autobday.exception.CanNotConvetGoogleBirthdayException;
import de.fhb.autobday.manager.connector.AImporter;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * class to import the contact information and map the contacts to us contacts
 * write the contact information in the database
 *
 * variablename if the variable start with abd this is an Auto-B-Day model
 *
 * @author Tino Reuschel
 * @author Michael Koppen <koppen@fh-brandenburg.de>
 */
public class GoogleImporter extends AImporter {

	private final static Logger LOGGER = Logger.getLogger(GoogleImporter.class.getName());
	protected boolean connectionEtablished;
	protected AbdAccount accdata;
	protected ContactsService myService;
	
	private AbdContactFacade contactDAO;
	private AbdGroupFacade groupDAO;
	private AbdGroupToContactFacade groupToContactDAO;

	public GoogleImporter() {
		connectionEtablished = false;
		accdata = null;
		myService = null;
	}

	@Override
	/**
	 * (non-Javadoc)
	 *
	 * @see
	 * de.fhb.autobday.manager.connector.AImporter#getConnection(de.fhb.autobday.data.AbdAccount)
	 * create connection to google contact api
	 */
	public void getConnection(AbdAccount data) {

		LOGGER.info("getConnection");
		LOGGER.log(Level.INFO, "data : {0}", data.getId());

		connectionEtablished = false;
		accdata = data;

		// testausgabe
		System.out.println("Username: " + accdata.getUsername());
		System.out.println("Passwort: " + accdata.getPasswort());

		//connect to google
		try {
			myService = new ContactsService("BDayReminder");
			myService.setUserCredentials(accdata.getUsername(), accdata.getPasswort());

		} catch (ServiceException ex) {
			LOGGER.log(Level.SEVERE, null, ex);
		}
		connectionEtablished = true;
	}

	@Override
	public void importContacts() {

		LOGGER.info("importContacts");

		// if we have a connection and a valid accounddata then import the contacts and groups
		// else throw an exception
		if (connectionEtablished && accdata != null) {
			
			updateGroups();
			updateContacts();

			/*
			for (ContactEntry contactEntry : contacts) {
				abdcontact = mapGContactToContact(contactEntry);
				//look in the database if the contact exist
				abdcontacthelp = abdContactFacade.find(abdcontact.getId());
				if (abdcontacthelp == null) {
					if ((abdcontact.getBday() != null) && (!abdcontact.getMail().equals(""))) {
						abdContactFacade.create(abdcontact);
					}
				} else {
					// check if data has been modify
					if (!abdcontact.equals(abdcontacthelp)) {
						abdContactFacade.edit(abdcontact);
					}
				}
				groupMembershipInfo = contactEntry.getGroupMembershipInfos();
				updateGroupMembership(contactEntry.getId(), groupMembershipInfo);
				
			}
			*/
		} else {
			//TODO Exception ersetzen
			throw new UnsupportedOperationException("Please Connect the service first.");
		}
	}

	public void updateContacts(){
		AbdContact abdContact, abdContactInDB;
		List<ContactEntry> contacts = getAllContacts();
		List<GroupMembershipInfo> groupMembershipInfo;
		
		for (ContactEntry contactEntry : contacts) {
			abdContact = mapGContactToContact(contactEntry);
			try {
				abdContactInDB = contactDAO.find(abdContact.getId());
				if (abdContact.getUpdated().after( abdContactInDB.getUpdated())){
					contactDAO.edit(abdContact);
				}
			} catch (NullPointerException ex) {
				System.out.println("-------------------------------");
				System.out.println("ABDContact: "+abdContact);
				System.out.println("Firstname: "+abdContact.getFirstname());
				System.out.println("hashid: "+abdContact.getHashid());
				System.out.println("mail: "+abdContact.getMail());
				System.out.println("name: "+abdContact.getName());
				System.out.println("bday: "+abdContact.getBday());
				System.out.println("sex: "+abdContact.getSex());
				System.out.println("updated: "+abdContact.getUpdated());
				System.out.println("-------------------------------");
				
				groupMembershipInfo = contactEntry.getGroupMembershipInfos();
				updateGroupMembership(abdContact, groupMembershipInfo);
				
				
				contactDAO.create(abdContact);
			}
			
			
		}	
	}
	
	public void updateGroups(){
		AbdGroup abdGroup;
		List<ContactGroupEntry> groups = getAllGroups();
		List<AbdGroup> abdGroups = new ArrayList<AbdGroup>(accdata.getAbdGroupCollection());
		
		for (ContactGroupEntry contactGroupEntry : groups) {
			abdGroup = mapGGroupToGroup(contactGroupEntry);
			for (AbdGroup abdGroupOld : abdGroups) {
				if(abdGroup.getId().equals(abdGroupOld.getId())){
					if (abdGroup.getUpdated().after(abdGroupOld.getUpdated())){
						groupDAO.edit(abdGroup);
						deleteFromGroupList(abdGroups, abdGroup);
					}
				}else{
					groupDAO.create(abdGroup);
				}
			}
		}
		deleteUnusedGroupsFromDatabase(abdGroups);
	}
	
	/**
	 * get all groups from google from the connected account
	 *
	 * if dont get information from google return null else a list of Google
	 * ContactGroupEntrys
	 *
	 */
	protected List<ContactGroupEntry> getAllGroups() {

		LOGGER.info("getAllGroups");

		URL feedUrl;
		try {
			//url to get all groups
			feedUrl = new URL("https://www.google.com/m8/feeds/groups/default/full");
			ContactGroupFeed resultFeed = myService.getFeed(feedUrl, ContactGroupFeed.class);
			if (resultFeed == null) {
				return null;
			}
			return resultFeed.getEntries();

		} catch (IOException ex) {
			LOGGER.log(Level.SEVERE, null, ex);
		} catch (ServiceException ex) {
			LOGGER.log(Level.SEVERE, null, ex);
		}
		return null;
	}

	/**
	 * get all contacts from the connected acoount
	 *
	 * if dont get information from google return null else a list of Google
	 * ContactEntrys
	 *
	 */
	protected List<ContactEntry> getAllContacts() {

		LOGGER.info("getAllContacts");

		URL feedUrl;
		try {
			feedUrl = new URL("https://www.google.com/m8/feeds/contacts/default/full");
			ContactFeed resultFeed = myService.getFeed(feedUrl, ContactFeed.class);
			if (resultFeed == null) {
				return null;
			}
			return resultFeed.getEntries();
		} catch (IOException ex) {
			LOGGER.log(Level.SEVERE, null, ex);
		} catch (ServiceException ex) {
			LOGGER.log(Level.SEVERE, null, ex);
		}
		return null;
	}

	/**
	 * @return the connectionEtablished
	 */
	public boolean isConnectionEtablished() {
		return connectionEtablished;
	}

	/**
	 * @param connectionEtablished the connectionEtablished to set
	 */
	public void setConnectionEtablished(boolean connectionEtablished) {
		this.connectionEtablished = connectionEtablished;
	}

	/**
	 * @return the accdata
	 */
	public AbdAccount getAccdata() {
		return accdata;
	}

	/**
	 * @param accdata the accdata to set
	 */
	public void setAccdata(AbdAccount accdata) {
		this.accdata = accdata;
	}

	/**
	 * @return the myService
	 */
	public ContactsService getMyService() {
		return myService;
	}

	/**
	 * @param myService the myService to set
	 */
	public void setMyService(ContactsService myService) {
		this.myService = myService;
	}

	/**
	 * methode to map a google contact to a auto-b-day contact
	 *
	 * @param ContactEntry contactEntry
	 *
	 * @return AbdContact
	 *
	 */
	protected AbdContact mapGContactToContact(ContactEntry contactEntry) {

		LOGGER.info("mapGContacttoContact");
		LOGGER.log(Level.INFO, "contactEntry :{0}", contactEntry.getId());
		

		AbdContact abdContact;
		String firstname;
		String name;
		Date birthday;
		String mailadress;
		String id;
		Date updated;

		abdContact = new AbdContact();
		System.out.println("-------------------------------------------------");
		abdContact.setHashid("12");
		
		
		id = contactEntry.getId();
		System.out.println("ID: "+id);
		abdContact.setId(id);
		
		firstname = getGContactFirstname(contactEntry);
		System.out.println("Firstname: "+firstname);
		abdContact.setFirstname(firstname);
		
		name = getGContactFamilyname(contactEntry);
		System.out.println("Name: "+name);
		abdContact.setName(name);
		
		birthday = getGContactBirthday(contactEntry);
		System.out.println("birthday: "+birthday);
		
		
		if (birthday != null) {
			abdContact.setBday(birthday);
		}
		if (!contactEntry.getEmailAddresses().isEmpty()) {
			mailadress = getGContactFirstMailAdress(contactEntry);
			abdContact.setMail(mailadress);
		}
		try {
			if (contactEntry.getGender().getValue() == Value.FEMALE) {
				abdContact.setSex('w');
			} else {
				abdContact.setSex('m');
			}
		} catch (NullPointerException ex) {
			abdContact.setSex(null);
		}
		
		updated = new Date(contactEntry.getUpdated().getValue());
		abdContact.setUpdated(updated);
		return abdContact;
	}
	
	protected AbdGroup mapGGroupToGroup(ContactGroupEntry contactGroupEntry){
		AbdGroup abdGroupEntry;
		//TODO Template einbinden
		String template = "Hier soll das Template rein";
		
		abdGroupEntry = new AbdGroup();
		abdGroupEntry.setId(contactGroupEntry.getId());
		abdGroupEntry.setName(getGroupName(contactGroupEntry));
		abdGroupEntry.setAccount(accdata);
		abdGroupEntry.setActive(true);
		abdGroupEntry.setTemplate(template);
		abdGroupEntry.setUpdated(new Date(contactGroupEntry.getUpdated().getValue()));
		
		return abdGroupEntry;
	}

	protected void deleteFromGroupList(List<AbdGroup> abdGroups, AbdGroup abdGroup){
		int i= -1;
		for (int j = 0; j < abdGroups.size(); j++) {
			if(abdGroups.get(j).getId().equalsIgnoreCase(abdGroup.getId())){
				i = j;
			}
		}
		if (i != -1){
			abdGroups.remove(i);
		}
	}
	
	protected void deleteUnusedGroupsFromDatabase(List<AbdGroup> abdGroups){
		for (AbdGroup abdGroup : abdGroups) {
			groupDAO.remove(abdGroup);
		}
	}
	
	/**
	 * update the membership of a contact
	 *
	 * @param String contactid
	 * @param List<GroupMembershipInfo> groupMembership
	 */
	protected void updateGroupMembership(AbdContact abdContact, List<GroupMembershipInfo> groupMemberships) {
		
		AbdGroupToContact abdGroupMembership, abdGroupMembershipTemp;
		AbdGroup abdGroup;
		//TODO nullpointer 
		List<AbdGroupToContact> abdGroupMemberships = new ArrayList<AbdGroupToContact>(
				groupToContactDAO.findGroupByContact(abdContact.getId()));
		
		/*
		for (GroupMembershipInfo groupMembership : groupMemberships) {
			abdGroupMembership = new AbdGroupToContact();
			abdGroupMembership.setAbdContact(abdContact);
			abdGroupMembership.setAbdGroup(abdGroupFacade.find(groupMembership.getHref()));
			abdGroupMembership.setActive(true);
			abdGroupMembershipTemp = abdGroupToContactFacade.find(abdGroupMembership);
			if (abdGroupMembershipTemp == null){
				abdGroupToContactFacade.create(abdGroupMembership);
			}
		}
		*/
		
		int i = 0;

		// check if the membership exist and remove the membership out of the list
		while (i < groupMemberships.size()) {
			if (diffMembership(groupMemberships.get(i).getHref(), abdGroupMemberships)) {
				groupMemberships.remove(i);
			} else {
				i = i + 1;
			}
		}
		// delete all unused memberships
		if (!abdGroupMemberships.isEmpty()) {
			for (AbdGroupToContact abdGroupToContact : abdGroupMemberships) {
				groupToContactDAO.remove(abdGroupToContact);
			}
		}
		//create new memberships
		if (!groupMemberships.isEmpty()) {
			for (GroupMembershipInfo groupMembershipInfo : groupMemberships) {
				abdGroupMembership = new AbdGroupToContact();
				abdGroupMembership.setAbdContact(contactDAO.find(abdContact.getId()));
				abdGroupMembership.setAbdGroup(groupDAO.find(groupMembershipInfo.getHref()));
				abdGroupMembership.setActive(true);
				groupToContactDAO.create(abdGroupMembership);
			}
		}
		
	}

	/**
	 * if the membership exist remove the membership out of the list of the
	 * exist memberships
	 *
	 * return a boolean if the membership exist
	 *
	 * @param String groupid
	 * @param List<AbdGroupToContact> abdGroupMembership
	 *
	 * @return boolean
	 */
	protected boolean diffMembership(String groupid, List<AbdGroupToContact> abdGroupMembership) {
		AbdGroup abdGroup;
		for (int i = 0; i < abdGroupMembership.size(); i++) {
			abdGroup = abdGroupMembership.get(i).getAbdGroup();
			if (abdGroup.getId().equals(groupid)) {
				abdGroupMembership.remove(i);
				return true;
			}
		}
		return false;
	}

	/**
	 * methode that return the firstname of a given contact
	 *
	 * @param ContactEntry contactEntry
	 *
	 * @return String
	 */
	protected String getGContactFirstname(ContactEntry contactEntry) {
		String firstname;
		firstname = contactEntry.getName().getGivenName().getValue();
		return firstname;

	}

	/**
	 * methode that return the familyname of a given Contact
	 *
	 * @param ContactEntry contactEntry
	 *
	 * @return String
	 */
	protected String getGContactFamilyname(ContactEntry contactEntry) {
		String familyname;
		familyname = contactEntry.getName().getFamilyName().getValue();
		return familyname;

	}

	/**
	 * methode that return the birthday of a given Contact
	 *
	 * @param ContactEntry contactEntry
	 *
	 * @ return Date
	 */
	protected Date getGContactBirthday(ContactEntry contactEntry) {
		String gContactBirthday = contactEntry.getBirthday().getValue();
		try {
			return GoogleBirthdayConverter.convertBirthday(gContactBirthday);
		} catch (CanNotConvetGoogleBirthdayException e) {
			return null;
		}
	}

	/**
	 * Methode that return a mailadress of a given Contact
	 *
	 * @param ContactEntry contactEntry
	 *
	 * @return String
	 */
	protected String getGContactFirstMailAdress(ContactEntry contactEntry) {
		List<Email> mailadresses;
		String mailadress;
		mailadresses = contactEntry.getEmailAddresses();
		if (mailadresses.size() > 0) {
			mailadress = mailadresses.get(0).getAddress();
			return mailadress;
		}
		return "";
	}
	
	protected String getGroupName(ContactGroupEntry contactGroupEntry){
		String groupName;
		groupName = contactGroupEntry.getTitle().getPlainText();
		return groupName;
	}
}
