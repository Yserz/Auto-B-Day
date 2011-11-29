package de.fhb.autobday.manager;

import de.fhb.autobday.data.Abdgroup;
import de.fhb.autobday.data.Abduser;
import de.fhb.autobday.data.Accountdata;
import de.fhb.autobday.data.Contact;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Michael Koppen
 */
@Local
public interface ABDManagerLocal {

	List<Abduser> getAllUser();

	List<Abdgroup> getAllGroups();

	List<Accountdata> getAllAccountdata();

	List<Contact> getAllContacts();
	
	String hallo();
}
