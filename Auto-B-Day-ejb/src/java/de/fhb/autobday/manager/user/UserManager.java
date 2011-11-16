package de.fhb.autobday.manager.user;

import de.fhb.autobday.dao.AbduserFacade;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Michael Koppen
 */
@Stateless
public class UserManager implements UserManagerLocal {
	private final static Logger LOGGER = Logger.getLogger(UserManager.class.getName());
	
	@EJB
	private AbduserFacade userDAO;
	
	public UserManager() {
	}
	
	// Add business logic below. (Right-click in editor and choose
	// "Insert Code > Add Business Method")

	
	
}
