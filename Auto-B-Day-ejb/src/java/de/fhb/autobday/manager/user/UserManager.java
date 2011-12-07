package de.fhb.autobday.manager.user;

import de.fhb.autobday.dao.AbdUserFacade;
import de.fhb.autobday.data.AbdUser;
import de.fhb.autobday.exception.user.IncompleteLoginDataException;
import de.fhb.autobday.exception.user.PasswordInvalidException;
import de.fhb.autobday.exception.user.UserException;
import de.fhb.autobday.exception.user.UserNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
* @author Andy Klay <klay@fh-brandenburg.de>
* @author Michael Koppen <koppen@fh-brandenburg.de>
* 
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
	public AbdUser login(String loginName, String password) throws UserException {
		
		LOGGER.log(Level.INFO,"parameter:");
		LOGGER.log(Level.INFO, "loginName: {0}", loginName);
		
		AbdUser user = null;
		
		if(loginName==null||password==null||password.equals("")){
			LOGGER.log(Level.SEVERE, "Invalid input!");
			throw new IncompleteLoginDataException("Invalid input!");
		}
		
		user=userDAO.find(loginName);
		
		if(user==null){
			LOGGER.log(Level.SEVERE, "User not found!");
			throw new UserNotFoundException("User not found!");
		}
		
		
		if(!user.getPasswort().equals(password)){
			LOGGER.log(Level.SEVERE, "Invalid password!");
			throw new PasswordInvalidException("Invalid password!");
		}
		
		return user;
	}

	@Override
	public void logout() {
		LOGGER.log(Level.INFO,"logout");
	}
	
}
