package de.fhb.autobday.manager.user;

import de.fhb.autobday.dao.AbdUserFacade;
import de.fhb.autobday.data.AbdUser;
import de.fhb.autobday.exception.user.IncompleteLoginDataException;
import de.fhb.autobday.exception.user.IncompleteUserRegisterException;
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
	@Override
	public void register(String firstName, String name, String salt, String userName, String mail) throws IncompleteUserRegisterException {
		
		LOGGER.log(Level.INFO,"parameter:");
		LOGGER.log(Level.INFO, "firstName: {0}", firstName);
		LOGGER.log(Level.INFO, "name: {1}", name);
		LOGGER.log(Level.INFO, "salt: {2}", salt);
		LOGGER.log(Level.INFO, "userName: {3}", userName);
		LOGGER.log(Level.INFO, "mail: {4}", mail);
		
		
		AbdUser user = null;
		
		if(firstName==null){
			LOGGER.log(Level.SEVERE, "No firstname given!");
			throw new IncompleteUserRegisterException("No firstname given!");
		}
		
		if(name==null){
			LOGGER.log(Level.SEVERE, "No firstname given!");
			throw new IncompleteUserRegisterException("No firstname given!");
		}
		
		if(userName==null){
			LOGGER.log(Level.SEVERE, "No username given!");
			throw new IncompleteUserRegisterException("No username given");
		}
		
		if(mail==null){
			LOGGER.log(Level.SEVERE, "No mail given!");
			throw new IncompleteUserRegisterException("No mail given!");
		}
		
		
		userDAO.create(user);
		user.setFirstname(firstName);
		user.setName(name);
		user.setUsername(userName);
		user.setSalt(salt);
		
		//TODO mail senden mit generierten passwort
		
	}
	
}
