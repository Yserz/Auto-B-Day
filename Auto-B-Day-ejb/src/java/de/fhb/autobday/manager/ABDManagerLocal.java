package de.fhb.autobday.manager;

import de.fhb.autobday.data.AbdAccount;
import de.fhb.autobday.data.AbdContact;
import de.fhb.autobday.data.AbdGroup;
import de.fhb.autobday.data.AbdUser;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Michael Koppen <koppen@fh-brandenburg.de>
 */
@Local
public interface ABDManagerLocal {

	List<AbdUser> getAllUser();

	List<AbdGroup> getAllGroups();

	List<AbdAccount> getAllAccountdata();

	List<AbdContact> getAllContacts();
	
	String hallo();
}
