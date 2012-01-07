package de.fhb.autobday.manager.connector.google;

import com.google.gdata.client.contacts.ContactsService;
import com.google.gdata.data.contacts.Gender.Value;
import com.google.gdata.data.contacts.*;
import com.google.gdata.data.extensions.Email;
import com.google.gdata.util.ServiceException;
import de.fhb.autobday.commons.GoogleBirthdayConverter;
import de.fhb.autobday.dao.AbdAccountFacade;
import de.fhb.autobday.dao.AbdContactFacade;
import de.fhb.autobday.dao.AbdGroupFacade;
import de.fhb.autobday.dao.AbdGroupToContactFacade;
import de.fhb.autobday.data.*;
import de.fhb.autobday.exception.CanNotConvetGoogleBirthdayException;
import de.fhb.autobday.exception.connector.ConnectorCouldNotLoginException;
import de.fhb.autobday.exception.connector.ConnectorInvalidAccountException;
import de.fhb.autobday.exception.connector.ConnectorNoConnectionException;
import de.fhb.autobday.manager.LoggerInterceptor;
import de.fhb.autobday.manager.connector.AImporter;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

/**
 * class to import the contact information and map the contacts to us contacts
 * write the contact information in the database
 *
 * variablename if the variable start with abd this is an Auto-B-Day model
 *
 * @author Tino Reuschel
 */
@Stateless
@LocalBean
@Interceptors(LoggerInterceptor.class)
public class GoogleImporter {

	private final static Logger LOGGER = Logger.getLogger(GoogleImporter.class.getName());
	protected boolean connectionEtablished;
	protected AbdAccount accdata;
	protected ContactsService myService;
	
	@EJB
	private AbdContactFacade contactDAO;
	@EJB
	private AbdGroupFacade groupDAO;
	@EJB
	private AbdGroupToContactFacade groupToContactDAO;
	@EJB
	private AbdAccountFacade accountDAO;

	public GoogleImporter() {
		connectionEtablished = false;
		accdata = null;
		myService = null;
	}

	/**
	 * (non-Javadoc)
	 *
	 * @see
	 * de.fhb.autobday.manager.connector.AImporter#getConnection(de.fhb.autobday.data.AbdAccount)
	 * create connection to google contact api
	 */
	public void getConnection(AbdAccount data) throws ConnectorCouldNotLoginException, ConnectorInvalidAccountException {


		connectionEtablished = false;
		
		System.out.println("Account: "+data);
		
		if (data == null){
			throw new ConnectorInvalidAccountException();
		}
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
			throw new ConnectorCouldNotLoginException();
		}
		connectionEtablished = true;
	}

	public void importContacts() throws ConnectorNoConnectionException {


		// if we have a connection and a valid accounddata then import the contacts and groups
		// else throw an exception
		if (connectionEtablished && accdata != null) {
			
			updateGroups();
			updateContacts();
			
			//accountDAO.flush();
			accountDAO.edit(accdata);

		} else {
			throw new ConnectorNoConnectionException();
		}
	}

	public void updateContacts(){
		AbdContact abdContact, abdContactInDB;
		List<ContactEntry> contacts = getAllContacts();
		List<GroupMembershipInfo> groupMembershipInfos;
		AbdGroupToContact abdGroupToContact;
		AbdGroupToContactPK gtcPK;
		
		for (ContactEntry contactEntry : contacts) {
			abdContact = mapGContactToContact(contactEntry);
			System.out.println("Mapped Contact: "+abdContact);
			try {
				abdContactInDB = contactDAO.find(abdContact.getId());
				System.out.println("Contact in db: "+abdContactInDB);
				if (abdContactInDB == null){
					System.out.println("HERE");
					groupMembershipInfos = contactEntry.getGroupMembershipInfos();
					for (GroupMembershipInfo groupMembershipInfo : groupMembershipInfos) {
						System.out.println("HERE HERE");
						//TODO der kommt beim neu erstellen hier nie rein....solange du nicht vorher persistierst(in der updateGroups)
						for(AbdGroup abdGroup: accdata.getAbdGroupCollection()){
							System.out.println("??????????????????????????????????????");
							if (abdGroup.getId().equals(groupMembershipInfo.getHref())){
								System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
								contactDAO.create(abdContact);
								
								abdGroupToContact = new AbdGroupToContact();
								
								gtcPK = new AbdGroupToContactPK(abdGroup.getId(), abdContact.getId());
								abdGroupToContact.setAbdGroupToContactPK(gtcPK);
								
								abdGroupToContact.setAbdContact(abdContact);
								abdGroupToContact.setActive(true);
								abdGroupToContact.setAbdGroup(abdGroup);
								
								System.out.println("Contact: "+abdGroupToContact.getAbdContact());
								System.out.println("Group: "+abdGroupToContact.getAbdGroup());
								System.out.println("Active: "+abdGroupToContact.getActive());
								System.out.println("ContactPK: "+abdGroupToContact.getAbdGroupToContactPK().getContact());
								System.out.println("GroupPK: "+abdGroupToContact.getAbdGroupToContactPK().getGroup());
								
								groupToContactDAO.create(abdGroupToContact);
								groupToContactDAO.flush();
								
								abdGroup.getAbdGroupToContactCollection().add(abdGroupToContact);
								
								
								
								groupDAO.edit(abdGroup);
							}
						}
					}
				}else{
					if (abdContact.getUpdated().after( abdContactInDB.getUpdated())){
						contactDAO.edit(abdContact);
					}
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
				System.out.println("GroupToContact: "+abdContact.getAbdGroupToContactCollection());
				System.out.println("-------------------------------");
				
				
				//TODO add reference from contact to group this couldnt be null!
				
				//contactDAO.create(abdContact);
				
				//groupMembershipInfo = contactEntry.getGroupMembershipInfos();
				//updateGroupMembership(abdContact, groupMembershipInfo);
			}
			
			
		}	
	}
	
	public void updateGroups(){
		AbdGroup abdGroup;
		List<ContactGroupEntry> groups = getAllGroups();
		List<AbdGroup> abdGroups = new ArrayList<AbdGroup>(accdata.getAbdGroupCollection());
		//Collection<AbdGroup> accountGroups = accdata.getAbdGroupCollection();
		boolean foundMatch = false;
		for (ContactGroupEntry contactGroupEntry : groups) {
			abdGroup = mapGGroupToGroup(contactGroupEntry);
			System.out.println("Found GroupID: "+abdGroup+" Name: "+abdGroup.getName());
			//iterare over old groups
			for (AbdGroup abdGroupOld : abdGroups) {
				foundMatch = false;
				//if new group == old group
				if(abdGroup.getId().equals(abdGroupOld.getId())){
					foundMatch = true;
					//and if new group newer than the old group
					if (abdGroup.getUpdated().after(abdGroupOld.getUpdated())){
						abdGroups.remove(abdGroupOld);
						//deleteFromGroupList(abdGroups, abdGroup);
						
						abdGroups.add(abdGroup);
					}
					
				}
			}
			if (!foundMatch) {
				abdGroups.add(abdGroup);
			}
			
		}
		//deleteUnusedGroupsFromDatabase(abdGroups);
		
		accdata.setAbdGroupCollection(abdGroups);
	}
	
	/**
	 * get all groups from google from the connected account
	 *
	 * if dont get information from google return null else a list of Google
	 * ContactGroupEntrys
	 *
	 */
	protected List<ContactGroupEntry> getAllGroups() {


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

	@Deprecated
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
	@Deprecated
	protected void deleteUnusedGroupsFromDatabase(List<AbdGroup> abdGroups){
		for (AbdGroup abdGroup : abdGroups) {
			accdata.getAbdGroupCollection().remove(abdGroup);
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
