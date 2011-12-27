package de.fhb.autobday.manager.account;

import java.util.List;

import javax.ejb.Local;

import de.fhb.autobday.data.AbdGroup;
import de.fhb.autobday.exception.account.AccountException;
import de.fhb.autobday.exception.account.AccountNotFoundException;
import de.fhb.autobday.exception.user.UserNotFoundException;

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
	
	List<AbdGroup> getAllGroupsFromAccount(int accountId) throws Exception;
	
	
	
	
}
