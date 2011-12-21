package de.fhb.autobday.manager.account;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import de.fhb.autobday.dao.AbdAccountFacade;
import de.fhb.autobday.dao.AbdGroupFacade;
import de.fhb.autobday.dao.AbdUserFacade;
import de.fhb.autobday.data.AbdAccount;
import de.fhb.autobday.data.AbdContact;
import de.fhb.autobday.data.AbdGroup;
import de.fhb.autobday.data.AbdGroupToContact;
import de.fhb.autobday.data.AbdUser;
import de.fhb.autobday.exception.account.AccountException;
import de.fhb.autobday.exception.account.AccountNotFoundException;
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
	
	@EJB
	private AbdGroupFacade groupDAO;
	
	public AccountManager() {
	}

	@Override
	public void addAccount(int abdUserId, String password, String userName, String type) throws UserNotFoundException {
		
		LOGGER.log(Level.INFO,"parameter:");
		LOGGER.log(Level.INFO,"abdUserId: " + abdUserId);
		
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
		createdAccount.setAbduser(actualUser);
		createdAccount.setPasswort(password);
		createdAccount.setUsername(userName);
		createdAccount.setType(type);

		//create
		accountDAO.create(createdAccount);
		
//		//save in to db
//		accountDAO.edit(createdAccount);
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
	
	

	
	/**
	 * 
	 * @param accountInputObject
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<AbdGroup> getAllGroupsFromAccount(AbdAccount accountInputObject) throws Exception{
		
		AbdAccount account=null;
		ArrayList<AbdGroup> outputCollection=new ArrayList<AbdGroup>();
		
		//find object, verify input
		account=accountDAO.find(accountInputObject);
		
		if(account==null){
			//TODO exception verbessern
			LOGGER.log(Level.SEVERE, "inputobject does not exist!");
			throw new Exception("inputobject does not exist!");
		}
		
		for(AbdGroup actualGroup :account.getAbdGroupCollection()){
			outputCollection.add(actualGroup);
		}
		
		return outputCollection;
	}
	
	
	/**
	 * 
	 * @param userInputObject
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<AbdAccount> getAllAccounts(AbdUser userInputObject) throws Exception{
		
		AbdUser user=null;
		ArrayList<AbdAccount> outputCollection=new ArrayList<AbdAccount>();
		
		//find object, verify input
		user=userDAO.find(userInputObject);
		
		if(user==null){
			//TODO exception verbessern
			LOGGER.log(Level.SEVERE, "inputobject does not exist!");
			throw new Exception("inputobject does not exist!");
		}
		
		for(AbdAccount actualAccount :user.getAbdAccountCollection()){
			outputCollection.add(actualAccount);
		}
		
		
		return outputCollection;
	}
	
	/**
	 * 
	 * @param groupInputObject
	 * @return
	 * @throws Exception 
	 */
	@Override
	public List<AbdContact> getAllContactsFromGroup(AbdGroup groupInputObject) throws Exception{
		
		AbdGroup group=null;
		ArrayList<AbdContact> outputCollection=new ArrayList<AbdContact>();
		
		//find object, verify input
		group=groupDAO.find(groupInputObject);
		
		if(group==null){
			//TODO exception verbessern
			LOGGER.log(Level.SEVERE, "inputobject does not exist!");
			throw new Exception("inputobject does not exist!");
		}
		
		for(AbdGroupToContact actualGroupToContact :group.getAbdGroupToContactCollection()){
			outputCollection.add(actualGroupToContact.getAbdContact());
		}
		
		
		return outputCollection;
	}

}
