package de.fhb.autobday.manager.account;

import de.fhb.autobday.dao.AbdAccountFacade;
import de.fhb.autobday.dao.AbdUserFacade;
import de.fhb.autobday.data.AbdAccount;
import de.fhb.autobday.data.AbdUser;
import de.fhb.autobday.exception.account.AccountException;
import de.fhb.autobday.exception.account.AccountNotFoundException;
import de.fhb.autobday.exception.user.UserNotFoundException;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 *
 * @author Andy Klay <klay@fh-brandenburg.de>
 * @author Michael Koppen <koppen@fh-brandenburg.de>
 * 
 */
@Stateless
public class AccountManager implements AccountManagerLocal {
	
	private final static Logger LOGGER = Logger.getLogger(AccountManager.class.getName());
	
	
	@EJB
	private AbdAccountFacade accountDAO;
	
	@EJB
	private AbdUserFacade userDAO;
	
	public AccountManager() {
	}

	@Override
	public void addAccount(int abdUserId, String password, String userName, String type) throws UserNotFoundException {
		
		LOGGER.log(Level.INFO,"parameter:");
		LOGGER.log(Level.INFO,"abdUserId: " + abdUserId);
		
		//search User
		AbdUser actualUser = userDAO.find(abdUserId);
		
		//if account not found
		if(actualUser==null){
			LOGGER.log(Level.SEVERE, "User {0}not found!", abdUserId);
			throw new UserNotFoundException("User " + abdUserId + "not found!");
		}
		
		//add new Account
		AbdAccount createdAccount=new AbdAccount();

		//create
		accountDAO.create(createdAccount);
		createdAccount.setAbduser(actualUser);
		createdAccount.setPasswort(password);
		createdAccount.setUsername(userName);
		createdAccount.setType(type);
		
		//save in to db
		accountDAO.edit(createdAccount);
	}

	@Override
	public void removeAccount(int accountId) throws AccountException {
		
		LOGGER.log(Level.INFO,"parameter:");
		LOGGER.log(Level.INFO, "accountId: {0}", accountId);
		
		//search
		AbdAccount account=accountDAO.find(accountId);
		
		//if account not found
		if(account==null){
			LOGGER.log(Level.SEVERE, "Account {0}not found!", accountId);
			throw new AccountNotFoundException("Account " + accountId + "not found!");
		}
		
		//delete
		accountDAO.remove(account);
	}

	@Override
	public void importGroupsAndContacts() {
		
		//TODO ncoh implementieren ???
		
		
	}

	
	
}
