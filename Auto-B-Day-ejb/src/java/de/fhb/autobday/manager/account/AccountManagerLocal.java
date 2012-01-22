package de.fhb.autobday.manager.account;

import de.fhb.autobday.data.AbdAccount;
import de.fhb.autobday.data.AbdGroup;
import de.fhb.autobday.exception.account.AccountAlreadyExsistsException;
import de.fhb.autobday.exception.account.AccountNotFoundException;
import de.fhb.autobday.exception.connector.ConnectorCouldNotLoginException;
import de.fhb.autobday.exception.connector.ConnectorInvalidAccountException;
import de.fhb.autobday.exception.connector.ConnectorNoConnectionException;
import de.fhb.autobday.exception.user.NoValidUserNameException;
import de.fhb.autobday.exception.user.UserNotFoundException;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * The AccountManager processes all accountData specific things.
 *
 * @author Michael Koppen <koppen@fh-brandenburg.de> Andy Klay
 * <klay@fh-brandenburg.de>
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
	 * @return
	 * @throws UserNotFoundException
	 * @throws AccountAlreadyExsistsException
	 * @throws NoValidUserNameException
	 */
	AbdAccount addAccount(int abdUserId, String password, String userName, String type)
			throws UserNotFoundException, AccountAlreadyExsistsException, NoValidUserNameException;

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
	 * @throws AccountNotFoundException
	 * @throws ConnectorCouldNotLoginException
	 * @throws ConnectorInvalidAccountException
	 * @throws ConnectorNoConnectionException
	 */
	void importGroupsAndContacts(int accountId)
			throws AccountNotFoundException, ConnectorCouldNotLoginException, ConnectorInvalidAccountException, ConnectorNoConnectionException;
        
        /**
	 * update all groups and contacts
	 *
	 * @param accountId
	 * @throws AccountNotFoundException
	 * @throws ConnectorCouldNotLoginException
	 * @throws ConnectorInvalidAccountException
	 * @throws ConnectorNoConnectionException
	 */
	void updateGroupsAndContacts(int accountId)
			throws AccountNotFoundException, ConnectorCouldNotLoginException, ConnectorInvalidAccountException, ConnectorNoConnectionException;

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