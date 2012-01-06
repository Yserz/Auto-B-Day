package de.fhb.autobday.manager.account;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import de.fhb.autobday.commons.EMailValidator;
import de.fhb.autobday.dao.AbdAccountFacade;
import de.fhb.autobday.dao.AbdUserFacade;
import de.fhb.autobday.data.AbdAccount;
import de.fhb.autobday.data.AbdGroup;
import de.fhb.autobday.data.AbdUser;
import de.fhb.autobday.exception.account.AccountAlreadyExsistsException;
import de.fhb.autobday.exception.account.AccountException;
import de.fhb.autobday.exception.account.AccountNotFoundException;
import de.fhb.autobday.exception.account.NoConnectionException;
import de.fhb.autobday.exception.user.NoValidUserNameException;
import de.fhb.autobday.exception.user.UserNotFoundException;
import de.fhb.autobday.manager.connector.google.GoogleImporter;

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

	
	/**
	 * (non-Javadoc)
	 * @throws AccountAlreadyExsistsException 
	 * @throws NoValidUserNameException 
	 * @see de.fhb.autobday.manager.account.AccountManagerLocal#addAccount(int, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public AbdAccount addAccount(int abdUserId, String password, String userName, String type) throws UserNotFoundException, AccountAlreadyExsistsException, NoValidUserNameException {
		
		LOGGER.log(Level.INFO,"parameter:");
		LOGGER.log(Level.INFO, "abdUserId: {0}", abdUserId);
		LOGGER.log(Level.INFO, "password: {0}", password);
		LOGGER.log(Level.INFO, "userName: {0}", userName);
		LOGGER.log(Level.INFO, "type: {0}", type);
		
		AbdUser actualUser=null;
		
		//search User
		actualUser=userDAO.find(abdUserId);
		
		//if account not found
		if(actualUser==null){
			LOGGER.log(Level.SEVERE, "User {0} not found!", abdUserId);
			throw new UserNotFoundException("User " + abdUserId + " not found!");
		}
		
		//search if account already exists
		for( AbdAccount actAccount : actualUser.getAbdAccountCollection()){
			if(actAccount.getUsername().equals(userName)&&actAccount.getType().equals(type)){
				LOGGER.log(Level.SEVERE, "Account already exists!");
				throw new AccountAlreadyExsistsException("Account already exists!");
			}
		}
		
		//check if userName is mailaddress
		if(!EMailValidator.isGoogleMail(userName)){
			LOGGER.log(Level.SEVERE, "UserName is no GoogleMail-address!");
			throw new NoValidUserNameException("UserName is no GoogleMail-address!");
		}
		
		//add new Account
		//TODO nicht dringend! Password verschluesseln...achtung googleimporter greift drauf zu im klartext dann
		AbdAccount createdAccount=new AbdAccount();	
		createdAccount.setId(Integer.SIZE);
		createdAccount.setAbduser(actualUser);
		createdAccount.setPasswort(password);
		createdAccount.setUsername(userName);
		createdAccount.setType("google");

		//create and save into db
		accountDAO.create(createdAccount);
		userDAO.refresh(actualUser);
		
		return createdAccount;
	}

	/**
	 * (non-Javadoc)
	 * @see de.fhb.autobday.manager.account.AccountManagerLocal#removeAccount(de.fhb.autobday.data.AbdAccount)
	 */
	@Override
	public void removeAccount(AbdAccount account) throws AccountException{
		
		removeAccount(account.getId());
	}
	
	/**
	 * (non-Javadoc)
	 * @see de.fhb.autobday.manager.account.AccountManagerLocal#removeAccount(int)
	 */
	@Override
	public void removeAccount(int accountId) throws AccountException {
		
		LOGGER.log(Level.INFO,"parameter:");
		LOGGER.log(Level.INFO, "accountId: {0}", accountId);
		
		AbdAccount account=null;
		
		//search
		account=accountDAO.find(accountId);
		
		//if account not found
		if(account==null){
			LOGGER.log(Level.SEVERE, "Account {0} not found!", accountId);
			throw new AccountNotFoundException("Account " + accountId + " not found!");
		}
		
		//delete
		accountDAO.remove(account);
	}

	/**
	 * (non-Javadoc)
	 * @throws NoConnectionException 
	 * @see de.fhb.autobday.manager.account.AccountManagerLocal#importGroupsAndContacts(int)
	 */
	@Override
	public void importGroupsAndContacts(int accountId) throws AccountNotFoundException, NoConnectionException {
		
		LOGGER.log(Level.INFO,"parameter:");
		LOGGER.log(Level.INFO, "accountId: {0}", accountId);
		
		AbdAccount account=null;
		GoogleImporter importer=null;
		
		//search
		account=accountDAO.find(accountId);
		
		//if account not found
		if(account==null){
			LOGGER.log(Level.SEVERE, "Account {0} not found!", accountId);
			throw new AccountNotFoundException("Account " + accountId + " not found!");
		}
		
		importer= new GoogleImporter();
		
		//connect and import
		importer.getConnection(account);
		
		if(!importer.isConnectionEtablished()){
			LOGGER.log(Level.SEVERE, "Cant etablish connection to google!");
			throw new NoConnectionException("Cant etablish connection to google!");
		}
		
		importer.importContacts();
	}
	
	/**
	 * (non-Javadoc)
	 * @see de.fhb.autobday.manager.account.AccountManagerLocal#getAllGroupsFromAccount(de.fhb.autobday.data.AbdAccount)
	 */
	@Override
	public List<AbdGroup> getAllGroupsFromAccount(AbdAccount account) throws AccountNotFoundException{
		return getAllGroupsFromAccount(account.getId());
	}
	
	
	/**
	 * (non-Javadoc)
	 * @see de.fhb.autobday.manager.account.AccountManagerLocal#getAllGroupsFromAccount(int)
	 */
	@Override
	public List<AbdGroup> getAllGroupsFromAccount(int accountId) throws AccountNotFoundException{
		
		LOGGER.log(Level.INFO,"parameter:");
		LOGGER.log(Level.INFO, "accountId: {0}", accountId);
		
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