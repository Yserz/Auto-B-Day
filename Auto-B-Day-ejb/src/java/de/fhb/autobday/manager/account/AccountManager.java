package de.fhb.autobday.manager.account;

import de.fhb.autobday.dao.AbdAccountFacade;
import de.fhb.autobday.dao.AbdUserFacade;
import de.fhb.autobday.data.AbdAccount;
import de.fhb.autobday.data.AbdGroup;
import de.fhb.autobday.data.AbdUser;
import de.fhb.autobday.exception.account.AccountException;
import de.fhb.autobday.exception.account.AccountNotFoundException;
import de.fhb.autobday.exception.user.UserNotFoundException;
import de.fhb.autobday.manager.connector.google.GoogleImporter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * The AccountManager processes all accountData specific things.
 *
 * @author 
 * Andy Klay <klay@fh-brandenburg.de>
 * Michael Koppen <koppen@fh-brandenburg.de>
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
		LOGGER.log(Level.INFO, "abdUserId: {0}", abdUserId);
		
		AbdUser actualUser=null;
		
		//search User
		actualUser=userDAO.find(abdUserId);
		
		//if account not found
		if(actualUser==null){
			LOGGER.log(Level.SEVERE, "User {0}not found!", abdUserId);
			throw new UserNotFoundException("User " + abdUserId + "not found!");
		}
		
		//add new Account
		AbdAccount createdAccount=new AbdAccount();	
		createdAccount.setId(Integer.SIZE);
		createdAccount.setAbduser(actualUser);
		createdAccount.setPasswort(password);
		createdAccount.setUsername(userName);
		createdAccount.setType(type);

		//create and save into db
		accountDAO.create(createdAccount);
		
	}

	
	@Override
	public void removeAccount(AbdAccount account) throws AccountException{
		removeAccount(account.getId());
	}
	
	@Override
	public void removeAccount(int accountId) throws AccountException {
		
		LOGGER.log(Level.INFO,"parameter:");
		LOGGER.log(Level.INFO, "accountId: {0}", accountId);
		
		AbdAccount account=null;
		
		//search
		account=accountDAO.find(accountId);
		
		//if account not found
		if(account==null){
			LOGGER.log(Level.SEVERE, "Account {0}not found!", accountId);
			throw new AccountNotFoundException("Account " + accountId + "not found!");
		}
		
		//delete
		accountDAO.remove(account);
	}

	@Override
	public void importGroupsAndContacts(int accountId) throws AccountNotFoundException {
		
		LOGGER.log(Level.INFO,"parameter:");
		LOGGER.log(Level.INFO, "accountId: {0}", accountId);
		
		AbdAccount account=null;
		GoogleImporter importer=null;
		
		//search
		account=accountDAO.find(accountId);
		
		//if account not found
		if(account==null){
			LOGGER.log(Level.SEVERE, "Account {0}not found!", accountId);
			throw new AccountNotFoundException("Account " + accountId + "not found!");
		}
		
		importer= new GoogleImporter();
		
		//connect and import
		importer.getConnection(account);
		importer.importContacts();
	}
	
	
	@Override
	public List<AbdGroup> getAllGroupsFromAccount(AbdAccount account) throws AccountNotFoundException{
		return getAllGroupsFromAccount(account.getId());
	}
	
	
	/**
	 * 
	 * @param accountInputObject
	 * @return
	 * @throws AccountNotFoundException
	 */
	@Override
	public List<AbdGroup> getAllGroupsFromAccount(int accountId) throws AccountNotFoundException{
		
		AbdAccount account=null;
		ArrayList<AbdGroup> outputCollection=new ArrayList<AbdGroup>();
		
		//find object, verify input
		account=accountDAO.find(accountId);
		
		if(account==null){
			LOGGER.log(Level.SEVERE, "Account does not exist!");
			throw new AccountNotFoundException("Account does not exist!");
		}
		
		for(AbdGroup actualGroup :account.getAbdGroupCollection()){
			outputCollection.add(actualGroup);
		}
		
		return outputCollection;
	}
	
	
}
