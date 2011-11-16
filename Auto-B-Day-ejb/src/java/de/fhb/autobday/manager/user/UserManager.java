package de.fhb.autobday.manager.user;

import de.fhb.autobday.dao.AbduserFacade;
import de.fhb.autobday.manager.account.AccountManagerLocal;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author MacYser
 */
@Stateless
public class UserManager implements UserManagerLocal {
	@EJB
	private AbduserFacade userDAO;
	
	public UserManager() {
	}
	
	// Add business logic below. (Right-click in editor and choose
	// "Insert Code > Add Business Method")

	
	
}
