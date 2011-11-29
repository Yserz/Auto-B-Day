package de.fhb.autobday.manager.account;

import de.fhb.autobday.dao.AbdAccountFacade;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Michael Koppen
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
	}

	@Override
	public void removeAccount() {
	}

	@Override
	public void importGroupsAndContacts() {
	}

	
	
}
