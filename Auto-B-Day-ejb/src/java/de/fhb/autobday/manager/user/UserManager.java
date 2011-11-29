package de.fhb.autobday.manager.user;

import de.fhb.autobday.dao.AbdUserFacade;
import de.fhb.autobday.data.AbdUser;
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
	private AbdUserFacade userDAO;
	
	public UserManager() {
		
	}
	@Override
	public AbdUser getUser(int userid){
		return userDAO.find(userid);
	}

	@Override
	public void login() {
	}

	@Override
	public void logout() {
	}
	
}
