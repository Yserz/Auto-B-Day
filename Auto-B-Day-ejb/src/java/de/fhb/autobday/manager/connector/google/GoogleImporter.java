package de.fhb.autobday.manager.connector.google;

import com.google.gdata.client.contacts.ContactsService;
import com.google.gdata.data.contacts.Gender.Value;
import com.google.gdata.data.contacts.*;
import com.google.gdata.data.extensions.Email;
import com.google.gdata.util.ServiceException;
import de.fhb.autobday.commons.CipherHelper;
import de.fhb.autobday.commons.GoogleBirthdayConverter;
import de.fhb.autobday.commons.PropertyLoader;
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
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
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
 * @author Tino Reuschel <reuschel@fh-brandenburg.de>
 */
@Stateless
@LocalBean
@Interceptors(LoggerInterceptor.class)
public class GoogleImporter extends AImporter {
	
	private final static Logger LOGGER = Logger.getLogger(GoogleImporter.class.getName());
	protected boolean connectionEtablished;
	protected AbdAccount accdata;
	protected ContactsService myService;
	@EJB
	protected AbdContactFacade contactDAO;
	@EJB
	protected AbdGroupFacade groupDAO;
	@EJB
	protected AbdGroupToContactFacade groupToContactDAO;
	@EJB
	protected AbdAccountFacade accountDAO;
	private PropertyLoader propLoader;
	
	public GoogleImporter() {
		connectionEtablished = false;
		accdata = null;
		myService = null;
		propLoader = new PropertyLoader();
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param data
	 * @see
	 * de.fhb.autobday.manager.connector.AImporter#getConnection(de.fhb.autobday.data.AbdAccount)
	 * create connection to google contact api
	 */
	@Override
	public void getConnection(AbdAccount data)
			throws ConnectorCouldNotLoginException,
			ConnectorInvalidAccountException {
		
		String password = "";
		Properties masterPassword;
		connectionEtablished = false;
		
		LOGGER.log(Level.INFO, "Account: {0}", data);
		
		if (data == null) {
			throw new ConnectorInvalidAccountException();
		}
		accdata = data;
		// testausgabe
		LOGGER.log(Level.INFO, "Username: {0}", accdata.getUsername());
		LOGGER.log(Level.INFO, "Passwort: {0}", accdata.getPasswort());

		// connect to google
		try {
			myService = new ContactsService("BDayReminder");
			
			try {
				masterPassword = propLoader.loadSystemProperty("/SystemChiperPassword.properties");
				password = CipherHelper.decipher(accdata.getPasswort(), masterPassword.getProperty("master"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidKeyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchPaddingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalBlockSizeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (BadPaddingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			myService.setUserCredentials(accdata.getUsername(),
					password);
		} catch (ServiceException ex) {
			LOGGER.log(Level.SEVERE, null, ex);
			throw new ConnectorCouldNotLoginException(
					"Importer cant connect to the account!");
		}
		connectionEtablished = true;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @see de.fhb.autobday.manager.connector.AImporter#importContacts()
	 */
	@Override
	public void importContacts() throws ConnectorNoConnectionException {

		// if we have a connection and a valid accounddata then import the
		// contacts and groups
		// else throw an exception
		if (connectionEtablished && accdata != null) {
			accountDAO.flush();
			
			updateGroups();
			updateContacts();
			
			accountDAO.edit(accdata);
			
		} else {
			throw new ConnectorNoConnectionException();
		}
	}

	/**
	 * Methode that update the Contact of an Account
	 */
	protected void updateContacts() {
		AbdContact abdContact, abdContactInDB;
		List<ContactEntry> contacts = getAllContacts();
		List<GroupMembershipInfo> groupMembershipInfos;
		AbdGroupToContact abdGroupToContact;
		AbdGroupToContactPK gtcPK;
		
		int membershipCounter;
		
		for (ContactEntry contactEntry : contacts) {
			abdContact = mapGContactToContact(contactEntry);
			if (abdContact != null) {
				LOGGER.log(Level.INFO, "Mapped Contact: {0}", abdContact);
				
				abdContactInDB = contactDAO.find(abdContact.getId());
				LOGGER.log(Level.INFO, "Contact in db: {0}", abdContactInDB);
				if (abdContactInDB == null) {
					groupMembershipInfos = contactEntry.getGroupMembershipInfos();
					membershipCounter = 0;
					for (GroupMembershipInfo groupMembershipInfo : groupMembershipInfos) {
						membershipCounter++;
						for (AbdGroup abdGroup : accdata.getAbdGroupCollection()) {
							if (abdGroup.getId().equals(groupMembershipInfo.getHref())) {
								contactDAO.create(abdContact);
								contactDAO.flush();
								abdGroupToContact = new AbdGroupToContact();
								
								gtcPK = new AbdGroupToContactPK(
										abdGroup.getId(),
										abdContact.getId());
								abdGroupToContact.setAbdGroupToContactPK(gtcPK);
								
								abdGroupToContact.setAbdContact(abdContact);
								if (membershipCounter > 1) {
									abdGroupToContact.setActive(false);
								} else {
									abdGroupToContact.setActive(true);
								}
								
								abdGroupToContact.setAbdGroup(abdGroup);
								
								LOGGER.log(Level.INFO, "Contact: {0}", abdGroupToContact.getAbdContact());
								LOGGER.log(Level.INFO, "Group: {0}", abdGroupToContact.getAbdGroup());
								LOGGER.log(Level.INFO, "Active: {0}", abdGroupToContact.getActive());
								LOGGER.log(Level.INFO, "ContactPK: {0}", abdGroupToContact.getAbdGroupToContactPK().getContact());
								LOGGER.log(Level.INFO, "GroupPK: {0}", abdGroupToContact.getAbdGroupToContactPK().getGroup());
								
								groupToContactDAO.create(abdGroupToContact);
								groupToContactDAO.flush();
								
								abdGroup.getAbdGroupToContactCollection().add(abdGroupToContact);
								
								groupDAO.edit(abdGroup);
								groupDAO.flush();
							}
						}
					}
				} else {
					if (abdContact.getUpdated().after(abdContactInDB.getUpdated())) {
						contactDAO.edit(abdContact);
					}
				}
			}
		}
	}

	/**
	 * Method that update the Groups of an Account
	 */
	protected void updateGroups() {
		AbdGroup abdGroup;
		List<ContactGroupEntry> groups = getAllGroups();
		List<AbdGroup> abdGroups = new ArrayList<AbdGroup>(
				accdata.getAbdGroupCollection());
		
		boolean foundMatch = false;
		for (ContactGroupEntry contactGroupEntry : groups) {
			abdGroup = mapGGroupToGroup(contactGroupEntry);
			LOGGER.log(Level.INFO, "Found GroupID: {0} Name: {1}", new Object[]{abdGroup, abdGroup.getName()});
			// iterare over old groups
			for (AbdGroup abdGroupOld : abdGroups) {
				foundMatch = false;
				// if new group == old group
				if (abdGroup.getId().equals(abdGroupOld.getId())) {
					foundMatch = true;
					// and if new group newer than the old group
					if (abdGroup.getUpdated().after(abdGroupOld.getUpdated())) {
						abdGroups.remove(abdGroupOld);
						abdGroups.add(abdGroup);
					}
					
				}
			}
			if (!foundMatch) {
				abdGroups.add(abdGroup);
			}
			
		}
		accdata.setAbdGroupCollection(abdGroups);
	}

	/**
	 * get all groups from google from the connected account
	 *
	 * if dont get information from google return null else a list of Google
	 * ContactGroupEntrys
	 *
	 *
	 * @return List<ContactGroupEntry>
	 */
	protected List<ContactGroupEntry> getAllGroups() {
		
		URL feedUrl;
		try {
			// url to get all groups
			feedUrl = new URL(
					"https://www.google.com/m8/feeds/groups/default/full");
			ContactGroupFeed resultFeed = myService.getFeed(feedUrl,
					ContactGroupFeed.class);
			if (resultFeed == null) {
				return null;
			}
			return resultFeed.getEntries();
			
		} catch (IOException ex) {
			LOGGER.log(Level.SEVERE, null, ex);
			//TODO Exception
		} catch (ServiceException ex) {
			LOGGER.log(Level.SEVERE, null, ex);
			//TODO Exception
		}
		return null;
	}

	/**
	 * get all contacts from the connected acoount
	 *
	 * if dont get information from google return null else a list of Google
	 * ContactEntrys
	 *
	 * @return List<ContactEntry>
	 */
	protected List<ContactEntry> getAllContacts() {
		
		URL feedUrl;
		try {
			feedUrl = new URL(
					"https://www.google.com/m8/feeds/contacts/default/full");
			ContactFeed resultFeed = myService.getFeed(feedUrl,
					ContactFeed.class);
			if (resultFeed == null) {
				return null;
			}
			return resultFeed.getEntries();
		} catch (IOException ex) {
			LOGGER.log(Level.SEVERE, null, ex);
			//TODO Exception
		} catch (ServiceException ex) {
			LOGGER.log(Level.SEVERE, null, ex);
			//TODO Exception
		}
		return null;
	}

	/**
	 * methode to map a google contact to a auto-b-day contact
	 *
	 * @param contactEntry
	 * @return AbdContact
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
		LOGGER.log(Level.INFO, "-------------------------------------------------");
		abdContact.setHashid("12");
		
		id = contactEntry.getId();
		LOGGER.log(Level.INFO, "ID: {0}", id);
		abdContact.setId(id);
		
		firstname = getGContactFirstname(contactEntry);
		LOGGER.log(Level.INFO, "Firstname: {0}", firstname);
		if (!firstname.equals("")) {
			abdContact.setFirstname(firstname);
		} else {
			LOGGER.log(Level.SEVERE, "Skipping current Contact: No Firstname");
			return null;
		}
		
		name = getGContactFamilyname(contactEntry);
		LOGGER.log(Level.INFO, "Name: {0}", name);
		if (!name.equals("")) {
			abdContact.setName(name);
		} else {
			LOGGER.log(Level.SEVERE, "Skipping current Contact: No Name");
			return null;
		}
		
		birthday = getGContactBirthday(contactEntry);
		LOGGER.log(Level.INFO, "birthday: {0}", birthday);
		if (birthday != null) {
			abdContact.setBday(birthday);
		} else {
			LOGGER.log(Level.SEVERE, "Skipping current Contact: No Bday");
			return null;
		}
		
		if (!contactEntry.getEmailAddresses().isEmpty()) {
			mailadress = getGContactFirstMailAdress(contactEntry);
			abdContact.setMail(mailadress);
		} else {
			LOGGER.log(Level.SEVERE, "Skipping current Contact: No Mail");
			return null;
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

	/**
	 * Method that map a GoogleGroup to a Auto-B-Day-Group
	 *
	 * @param contactGroupEntry
	 * @return AbdGroup
	 */
	protected AbdGroup mapGGroupToGroup(ContactGroupEntry contactGroupEntry) {
		AbdGroup abdGroupEntry;
		// TODO Template einbinden
		String template = "Herzlichen Glückwunsch zum Geburtstag ${firstname}.";
		
		abdGroupEntry = new AbdGroup();
		abdGroupEntry.setId(contactGroupEntry.getId());
		abdGroupEntry.setName(getGroupName(contactGroupEntry));
		abdGroupEntry.setAccount(accdata);
		abdGroupEntry.setActive(true);
		abdGroupEntry.setTemplate(template);
		abdGroupEntry.setUpdated(new Date(contactGroupEntry.getUpdated().getValue()));
		
		return abdGroupEntry;
	}

	/**
	 * methode that return the firstname of a given contact
	 *
	 * @param contactEntry
	 *
	 * @return String
	 */
	protected String getGContactFirstname(ContactEntry contactEntry) {
		String firstname = "";
		try {
			firstname = contactEntry.getName().getGivenName().getValue();
		} catch (NullPointerException ex) {
			// LOGGER.log(Level.SEVERE, null, ex);
		}
		
		return firstname;
		
	}

	/**
	 * methode that return the familyname of a given Contact
	 *
	 * @param contactEntry
	 *
	 * @return String
	 */
	protected String getGContactFamilyname(ContactEntry contactEntry) {
		String familyname = "";
		try {
			familyname = contactEntry.getName().getFamilyName().getValue();
		} catch (NullPointerException ex) {
			// LOGGER.log(Level.SEVERE, null, ex);
		}
		return familyname;
		
	}

	/**
	 * methode that return the birthday of a given Contact
	 *
	 * @param contactEntry
	 *
	 * @return Date
	 */
	protected Date getGContactBirthday(ContactEntry contactEntry) {
		String gContactBirthday = null;
		Date bday = null;
		try {
			gContactBirthday = contactEntry.getBirthday().getValue();
			bday = GoogleBirthdayConverter.convertBirthday(gContactBirthday);
		} catch (CanNotConvetGoogleBirthdayException ex) {
			LOGGER.log(Level.SEVERE, null, ex);
		} catch (NullPointerException ex) {
			// LOGGER.log(Level.SEVERE, null, ex);
		}
		return bday;
	}

	/**
	 * Methode that return a mailadress of a given Contact
	 *
	 * @param contactEntry
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

	/**
	 * Methode that return the Groupname from a GoogleGroup
	 *
	 * @param contactGroupEntry
	 * @return String
	 */
	protected String getGroupName(ContactGroupEntry contactGroupEntry) {
		String groupName = "";
		
		try {
			groupName = contactGroupEntry.getTitle().getPlainText();
		} catch (NullPointerException ex) {
			// LOGGER.log(Level.SEVERE, null, ex);
		}
		return groupName;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @see de.fhb.autobday.manager.connector.AImporter#isConnectionEtablished()
	 */
	@Override
	public boolean isConnectionEtablished() {
		return connectionEtablished;
	}
	
	public PropertyLoader getPropLoader() {
		return propLoader;
	}
	
	public void setPropLoader(PropertyLoader propLoader) {
		this.propLoader = propLoader;
	}
}
