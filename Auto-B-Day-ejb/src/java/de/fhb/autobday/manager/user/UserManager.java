package de.fhb.autobday.manager.user;

import de.fhb.autobday.commons.EMailValidator;
import de.fhb.autobday.commons.PasswordGenerator;
import de.fhb.autobday.dao.AbdUserFacade;
import de.fhb.autobday.data.AbdAccount;
import de.fhb.autobday.data.AbdUser;
import de.fhb.autobday.exception.user.*;
import de.fhb.autobday.manager.mail.MailManagerLocal;
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
	
	@EJB
	private MailManagerLocal mailManager;
	
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
	public void register(String firstName, String name, String userName, String mail) 
			throws IncompleteUserRegisterException, NoValidUserNameException {
		
		LOGGER.log(Level.INFO,"parameter:");
		LOGGER.log(Level.INFO, "firstName: {0}", firstName);
		LOGGER.log(Level.INFO, "name: {1}", name);
		LOGGER.log(Level.INFO, "userName: {2}", userName);
		LOGGER.log(Level.INFO, "mail: {3}", mail);
		
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
		
		if(mail==null){
			LOGGER.log(Level.SEVERE, "No mail given!");
			throw new IncompleteUserRegisterException("No mail given!");
		}
		
		if(!EMailValidator.isEmail(mail)){
			LOGGER.log(Level.SEVERE, "Mail not valid!");
			throw new IncompleteUserRegisterException("Mail not valid!");
		}
		
		user= new AbdUser();
		user.setFirstname(firstName);
		user.setName(name);
		user.setUsername(userName);
		//TODO wo soll man die mailadresse speichern??
		
		// generate Salt
		salt=PasswordGenerator.generateSalt();
		
		user.setSalt(salt);
		
		//save in to db
		userDAO.create(user);
		
		//send mail
		//TODO absender aendern??
		mailManager.sendBdayMail("autobday@smile.de", mail, "Welcome to Autobday", "");
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