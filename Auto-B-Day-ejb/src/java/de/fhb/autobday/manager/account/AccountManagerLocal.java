package de.fhb.autobday.manager.account;

import java.util.List;

import javax.ejb.Local;

import de.fhb.autobday.data.AbdAccount;
import de.fhb.autobday.data.AbdGroup;
import de.fhb.autobday.exception.account.AccountAlreadyExsistsException;
import de.fhb.autobday.exception.account.AccountException;
import de.fhb.autobday.exception.account.AccountNotFoundException;
import de.fhb.autobday.exception.account.NoConnectionException;
import de.fhb.autobday.exception.user.NoValidUserNameException;
import de.fhb.autobday.exception.user.UserNotFoundException;

/**
 * 
 * The AccountManager processes all accountData specific things.
 * 
 * @author 
 * Michael Koppen <koppen@fh-brandenburg.de>
 * Andy Klay <klay@fh-brandenburg.de>
 * 
 */
@Local
public interface AccountManagerLocal {

	/**
	 * add a account of a user
	 * 
	 * @param abdUserId
	 * @param password
	 * @param userName
	 * @param type
	 * @throws UserNotFoundException
	 */
	AbdAccount addAccount(int abdUserId, String password, String userName, String type) throws UserNotFoundException, AccountAlreadyExsistsException, NoValidUserNameException;

	/**
	 * remove a account of a user by a AbdAccountobject
	 * 
	 * @param account - AbdAccount
	 * @throws AccountException
	 */
	void removeAccount(AbdAccount account) throws AccountException;
	
	/**
	 * remove a account of a user by a accountid
	 * 
	 * @param accountId - int
	 * @throws AccountException
	 */
	void removeAccount(int accountId) throws AccountException;

	/**
	 * import all groups and contacts
	 * 
	 * @param accountId
	 * @throws AccountNotFoundException
	 */
	void importGroupsAndContacts(int accountId) throws AccountNotFoundException, NoConnectionException  ;
	
	/**
	 * get all groups of a specific account by a AbdAccountobject
	 * 
	 * @param account
	 * @return List<AbdGroup> 
	 * @throws AccountNotFoundException
	 */
	List<AbdGroup> getAllGroupsFromAccount(AbdAccount account) throws AccountNotFoundException;
	
	/**
	 * 	get all groups of a specific account by a accountid
	 * 
	 * @param accountId
	 * @return List<AbdGroup> 
	 * @throws Exception
	 */
	List<AbdGroup> getAllGroupsFromAccount(int accountId) throws Exception;
	
	
}