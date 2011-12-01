package de.fhb.autobday.manager.account;

import javax.ejb.Local;

import de.fhb.autobday.exception.account.AccountException;

/**
 *
 * @author Michael Koppen
 */
@Local
public interface AccountManagerLocal {

	void addAccount();

	void removeAccount(int accountId) throws AccountException;

	void importGroupsAndContacts();
	
}
