package de.fhb.autobday.manager.account;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import de.fhb.autobday.dao.AbdAccountFacade;
import de.fhb.autobday.data.AbdAccount;
import de.fhb.autobday.exception.account.AccountException;
import de.fhb.autobday.exception.account.AccountNotFoundException;

/**
 *
 *
 * @author Andy Klay <klay@fh-brandenburg.de>
 * 
 */
@Stateless
public class AccountManager implements AccountManagerLocal {
	
	private final static Logger LOGGER = Logger.getLogger(AccountManager.class.getName());
	
	
	@EJB
	private AbdAccountFacade accountdataDAO;
	
	
	public AccountManager() {
		
		
		
	}
	

	@Override
	public void addAccount() {
		
		LOGGER.log(Level.INFO,"parameter:");
//		LOGGER.log(Level.INFO,"groupid: " + groupId);
		
		//create
		AbdAccount createdAccount=new AbdAccount();
		
		accountdataDAO.create(createdAccount);
		
		//TODO noch nicht fertig
		
	}

	@Override
	public void removeAccount(int accountId) throws AccountException {
		
		LOGGER.log(Level.INFO,"parameter:");
		LOGGER.log(Level.INFO, "accountId: {0}", accountId);
		
		//search
		AbdAccount account=accountdataDAO.find(accountId);
		
		//if account not found
		if(account==null){
			LOGGER.log(Level.SEVERE, "Account {0}not found!", accountId);
			throw new AccountNotFoundException("Account " + accountId + "not found!");
		}
		
		
		//delete
		accountdataDAO.remove(account);
		
	}

	@Override
	public void importGroupsAndContacts() {
		
		//TODO ncoh implementieren
		
		
	}

	
	
}
