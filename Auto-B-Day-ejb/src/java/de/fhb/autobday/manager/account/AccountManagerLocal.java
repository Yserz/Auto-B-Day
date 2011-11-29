package de.fhb.autobday.manager.account;

import javax.ejb.Local;

/**
 *
 * @author Michael Koppen
 */
@Local
public interface AccountManagerLocal {

	void addAccount();

	void removeAccount();

	void importGroupsAndContacts();
	
}
