package de.fhb.autobday.manager.user;

import de.fhb.autobday.dao.AbduserFacade;
import de.fhb.autobday.data.Abduser;
import de.fhb.autobday.data.Accountdata;
import java.util.Collection;
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
	@Override
	public Abduser getUser(int userid){
		return userDAO.find(userid);
	}

	@Override
	public void login() {
	}

	@Override
	public void logout() {
	}
	
}
