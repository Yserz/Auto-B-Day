package de.fhb.autobday.manager.account;

import de.fhb.autobday.dao.AccountdataFacade;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Michael Koppen
 */
@Stateless
public class AccountManager implements AccountManagerLocal {

	@EJB
	private AccountdataFacade accountdataDAO;
	
	public AccountManager() {
	}
	
	// Add business logic below. (Right-click in editor and choose
	// "Insert Code > Add Business Method")

	
	
}
