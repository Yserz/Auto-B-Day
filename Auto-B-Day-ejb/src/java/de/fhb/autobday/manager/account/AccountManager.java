package de.fhb.autobday.manager.account;

import de.fhb.autobday.dao.AccountdataFacade;
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
	private AccountdataFacade accountdataDAO;
	
	public AccountManager() {
	}
	
	// Add business logic below. (Right-click in editor and choose
	// "Insert Code > Add Business Method")

	
	
}
