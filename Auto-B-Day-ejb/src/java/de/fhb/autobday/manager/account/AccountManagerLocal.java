package de.fhb.autobday.manager.account;

import de.fhb.autobday.exception.account.AccountException;
import javax.ejb.Local;

/**
 *
 * @author Michael Koppen <koppen@fh-brandenburg.de>
 */
@Local
public interface AccountManagerLocal {

	void addAccount();

	void removeAccount(int accountId) throws AccountException;

	void importGroupsAndContacts();
	
}
