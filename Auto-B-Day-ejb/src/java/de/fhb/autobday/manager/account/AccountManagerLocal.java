package de.fhb.autobday.manager.account;

import de.fhb.autobday.exception.account.AccountException;
import de.fhb.autobday.exception.account.AccountNotFoundException;
import de.fhb.autobday.exception.user.UserNotFoundException;

import javax.ejb.Local;

/**
 *
 * @author 
 * Michael Koppen <koppen@fh-brandenburg.de>
 * Andy Klay <klay@fh-brandenburg.de>
 */
@Local
public interface AccountManagerLocal {

	void addAccount(int abdUserId, String password, String userName, String type) throws UserNotFoundException;

	void removeAccount(int accountId) throws AccountException;

	void importGroupsAndContacts(int accountId) throws AccountNotFoundException ;
	
}
