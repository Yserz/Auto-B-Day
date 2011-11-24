package de.fhb.autobday.manager.connector.google;

import com.google.gdata.client.contacts.ContactsService;
import com.google.gdata.data.contacts.ContactFeed;
import com.google.gdata.util.ServiceException;
import de.fhb.autobday.data.Accountdata;
import de.fhb.autobday.manager.connector.AImporter;
import java.io.IOException;
import java.net.URL;
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
			
			
			
			throw new UnsupportedOperationException("Not supported yet.");
		} else {
			throw new UnsupportedOperationException("Please Connect the service first.");
		}
	}

	
	
}
