package de.fhb.autobday.manager;

import de.fhb.autobday.data.Abdaccount;
import de.fhb.autobday.data.Abdcontact;
import de.fhb.autobday.data.Abdgroup;
import de.fhb.autobday.data.Abduser;
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

	List<Abdaccount> getAllAccountdata();

	List<Abdcontact> getAllContacts();
	
	String hallo();
}
