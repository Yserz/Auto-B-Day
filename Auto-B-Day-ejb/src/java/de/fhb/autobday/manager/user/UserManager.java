package de.fhb.autobday.manager.user;

import de.fhb.autobday.commons.PasswordGenerator;
import de.fhb.autobday.dao.AbdAccountFacade;
import de.fhb.autobday.dao.AbdUserFacade;
import de.fhb.autobday.data.AbdAccount;
import de.fhb.autobday.data.AbdUser;
import de.fhb.autobday.exception.user.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
* @author 
* Andy Klay <klay@fh-brandenburg.de>
* Michael Koppen <koppen@fh-brandenburg.de>
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
		
		try {
			user = userDAO.findUserByUsername(loginName);
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "Invalid loginame!");
			throw new IncompleteLoginDataException("Invalid loginame!");
		}
		
		
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
	public void register(String firstName, String name, String userName,String password,String passwordRepeat) 
			throws IncompleteUserRegisterException, NoValidUserNameException {
		
		LOGGER.log(Level.INFO,"parameter:");
		LOGGER.log(Level.INFO, "firstName: {0}", firstName);
		LOGGER.log(Level.INFO, "name: {0}", name);
		LOGGER.log(Level.INFO, "userName: {0}", userName);
		LOGGER.log(Level.INFO, "password: {0}", password);
		LOGGER.log(Level.INFO, "passwordRepeat: {0}", passwordRepeat);
		
		AbdUser user = null;
		String salt="";
		
		
		if(firstName==null){
			LOGGER.log(Level.SEVERE, "No firstname given!");
			throw new IncompleteUserRegisterException("No firstname given!");
		}
		
		if(name==null){
			LOGGER.log(Level.SEVERE, "No name given!");
			throw new IncompleteUserRegisterException("No firstname given!");
		}
		
		if(userName==null){
			LOGGER.log(Level.SEVERE, "No username given!");
			throw new IncompleteUserRegisterException("No username given");
		}
		
		if(userName.length()<5){
			LOGGER.log(Level.SEVERE, "No valid Username!");
			throw new NoValidUserNameException("No valid Username!");
		}
		
		if(password==null){
			LOGGER.log(Level.SEVERE, "No password given!");
			throw new IncompleteUserRegisterException("No password given");
		}
		
		if(passwordRepeat==null){
			LOGGER.log(Level.SEVERE, "No password repetition given!");
			throw new IncompleteUserRegisterException("No  password repetition given");
		}
		
		if(!password.equals(passwordRepeat)){
			LOGGER.log(Level.SEVERE, "Password not similar to the repetition!");
			throw new IncompleteUserRegisterException("Password not similar to the repetition!");
		}
		
		// generate Salt
		salt=PasswordGenerator.generateSalt();
		
		//user init
		user= new AbdUser();
		user.setFirstname(firstName);
		user.setName(name);
		user.setUsername(userName);
		user.setSalt(salt);
		user.setPasswort(password);
				
		//save in to db
		userDAO.create(user);
		
	}
	
	
	@Override
	public List<AbdAccount> getAllAccountsFromUser(AbdUser user) throws UserNotFoundException{
		return getAllAccountsFromUser(user.getId());
	}
	
	/**
	 * 
	 * @param userInputObject
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<AbdAccount> getAllAccountsFromUser(int userId) throws UserNotFoundException{
		
		AbdUser user=null;
		ArrayList<AbdAccount> outputCollection=new ArrayList<AbdAccount>();
		
		//find object, verify input
		user=userDAO.find(userId);
		
		if(user==null){
			LOGGER.log(Level.SEVERE, "User does not exist!");
			throw new UserNotFoundException("User does not exist!");
		}
		
		for(AbdAccount actualAccount :user.getAbdAccountCollection()){
			outputCollection.add(actualAccount);
		}
		
		
		return outputCollection;
	}
	
}