package de.fhb.autobday.manager.connector.google;

import com.google.gdata.client.contacts.ContactsService;
import com.google.gdata.data.contacts.ContactEntry;
import com.google.gdata.data.contacts.ContactFeed;
import com.google.gdata.util.ServiceException;

import de.fhb.autobday.commons.GoogleBirthdayConverter;
import de.fhb.autobday.dao.ContactFacade;
import de.fhb.autobday.data.Accountdata;
import de.fhb.autobday.data.Contact;
import de.fhb.autobday.manager.connector.AImporter;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Michael Koppen
 */
public class GoogleImporter extends AImporter{
	private final static Logger LOGGER = Logger.getLogger(GoogleImporter.class.getName());
	
	private ContactFeed resultFeed;
	private boolean connectionEtablished;
	private Accountdata accdata;

	public GoogleImporter() {
		connectionEtablished = false;
		resultFeed = null;
		accdata = null;
	}
	
	@Override
	public void getConnection(Accountdata data) {
		connectionEtablished = false;
		accdata = data;
		
		System.out.println("Username: "+accdata.getUsername());
		System.out.println("Passwort: "+accdata.getPasswort());
		
		
		
		System.out.println("WARNING: User credentials not be used by connector!");
		//TODO Wenn daten in Datenbank, credentials aus übergabeparameter setzen
			
		try {
			ContactsService myService = new ContactsService("BDayReminder");
			myService.setUserCredentials("fhbtestacc@googlemail.com", "TestGoogle123");
			
			URL feedUrl = new URL("https://www.google.com/m8/feeds/contacts/default/full");
			resultFeed = myService.getFeed(feedUrl, ContactFeed.class);
			
		} catch (ServiceException ex) {
			LOGGER.log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			LOGGER.log(Level.SEVERE, null, ex);
		}
		connectionEtablished = true;
	}

	@Override
	public void importContacts() {
		if (connectionEtablished && accdata != null) {
			//TODO push the data from RESULTFEED through the ACCOUNTDATA into the DATABASE.
			
			ContactFacade contactFacade = new ContactFacade();
			Contact contact;
			String firstname;
			String name;
			Date birthday;
			String mailadress;
			boolean importing=true;
			
			List<ContactEntry> contacts = resultFeed.getEntries();
			for (ContactEntry contactEntry : contacts) {
				importing=true;
				contact = new Contact();
				firstname=contactEntry.getName().getGivenName().getValue();
				contact.setFirstname(firstname);
				name = contactEntry.getName().getFamilyName().getValue();
				contact.setName(name);
				birthday=GoogleBirthdayConverter.convertBirthday(contactEntry.getBirthday().getValue());
				if (birthday !=null){
					contact.setBday(birthday);
				} else {
					importing=false;
				}
				if (contactEntry.getEmailAddresses().size()==0){
					importing=false;
				} else {
					mailadress = contactEntry.getEmailAddresses().get(0).getAddress();
					contact.setMail(mailadress);
				}
				if (contactEntry.getGender().getValue() == contactEntry.getGender().getValue().FEMALE){
					contact.setSex('w');
				} else {
					contact.setSex('m');
				}
				contact.setActive(true);
				if (importing){
					contactFacade.create(contact);
				}
			}
			
			throw new UnsupportedOperationException("Not supported yet.");
		} else {
			throw new UnsupportedOperationException("Please Connect the service first.");
		}
	}

	
	
}
