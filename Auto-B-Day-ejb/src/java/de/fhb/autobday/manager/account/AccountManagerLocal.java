package de.fhb.autobday.manager.account;

import de.fhb.autobday.data.AbdAccount;
import de.fhb.autobday.data.AbdGroup;
import de.fhb.autobday.exception.account.AccountAlreadyExsistsException;
import de.fhb.autobday.exception.account.AccountNotFoundException;
import de.fhb.autobday.exception.commons.CouldNotDecryptException;
import de.fhb.autobday.exception.commons.CouldNotLoadMasterPasswordException;
import de.fhb.autobday.exception.connector.ConnectorCouldNotLoginException;
import de.fhb.autobday.exception.connector.ConnectorInvalidAccountException;
import de.fhb.autobday.exception.connector.ConnectorNoConnectionException;
import de.fhb.autobday.exception.connector.ConnectorRequestFailedException;
import de.fhb.autobday.exception.user.NoValidUserNameException;
import de.fhb.autobday.exception.user.UserNotFoundException;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * The AccountManager processes all accountData specific things.
 *
 * @author Michael Koppen mail: koppen@fh-brandenburg.de
 * @author Andy Klay mail: klay@fh-brandenburg.de
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
	 * @return the currently added Account
	 * @throws UserNotFoundException
	 * @throws AccountAlreadyExsistsException
	 * @throws NoValidUserNameException
	 * @throws CouldNotLoadMasterPasswordException
	 * @throws CouldNotDecryptException  
	 */
	AbdAccount addAccount(int abdUserId, String password, String userName, String type)
			throws UserNotFoundException, AccountAlreadyExsistsException, NoValidUserNameException, CouldNotLoadMasterPasswordException, CouldNotDecryptException;

	/**
	 * remove a account of a user by a AbdAccountobject
	 *
	 * @param account - AbdAccount
	 * @throws AccountNotFoundException
	 */
	void removeAccount(AbdAccount account)
			throws AccountNotFoundException;

	/**
	 * remove a account of a user by a accountid
	 *
	 * @param accountId - int
	 * @throws AccountNotFoundException
	 */
	void removeAccount(int accountId)
			throws AccountNotFoundException;

	/**
	 * import all groups and contacts
	 *
	 * @param accountId
	 * @return errorStack
	 * @throws AccountNotFoundException
	 * @throws ConnectorCouldNotLoginException
	 * @throws ConnectorInvalidAccountException
	 * @throws ConnectorNoConnectionException
	 */
	List<String> importGroupsAndContacts(int accountId)
			throws AccountNotFoundException, ConnectorCouldNotLoginException, ConnectorInvalidAccountException, ConnectorNoConnectionException, ConnectorRequestFailedException, CouldNotDecryptException, CouldNotLoadMasterPasswordException;
        
    /**
	 * update all groups and contacts
	 *
	 * @param accountId
	 * @return errorStack
	 * @throws AccountNotFoundException
	 * @throws ConnectorCouldNotLoginException
	 * @throws ConnectorInvalidAccountException
	 * @throws ConnectorNoConnectionException
	 */
	List<String> updateGroupsAndContacts(int accountId)
			throws AccountNotFoundException, ConnectorCouldNotLoginException, ConnectorInvalidAccountException, ConnectorNoConnectionException, ConnectorRequestFailedException, CouldNotDecryptException, CouldNotLoadMasterPasswordException;

	/**
	 * get all groups of a specific account by a AbdAccountobject
	 *
	 * @param account
	 * @return List<AbdGroup>
	 * @throws AccountNotFoundException
	 */
	List<AbdGroup> getAllGroupsFromAccount(AbdAccount account)
			throws AccountNotFoundException;

	/**
	 * get all groups of a specific account by a accountid
	 *
	 * @param accountId
	 * @return List<AbdGroup>
	 * @throws AccountNotFoundException
	 */
	List<AbdGroup> getAllGroupsFromAccount(int accountId)
			throws AccountNotFoundException;
}