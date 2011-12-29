package de.fhb.autobday.manager.user;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import de.fhb.autobday.commons.EMailValidator;
import de.fhb.autobday.commons.HashHelper;
import de.fhb.autobday.commons.PasswordGenerator;
import de.fhb.autobday.dao.AbdUserFacade;
import de.fhb.autobday.data.AbdAccount;
import de.fhb.autobday.data.AbdUser;
import de.fhb.autobday.exception.user.IncompleteLoginDataException;
import de.fhb.autobday.exception.user.IncompleteUserRegisterException;
import de.fhb.autobday.exception.user.NoValidUserNameException;
import de.fhb.autobday.exception.user.PasswordInvalidException;
import de.fhb.autobday.exception.user.UserException;
import de.fhb.autobday.exception.user.UserNotFoundException;

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
		String hash="";
		
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
		
		
//		if(!user.getPasswort().equals(password)){
//			LOGGER.log(Level.SEVERE, "Invalid password!");
//			throw new PasswordInvalidException("Invalid password!");
//		}
		
		//check password
		
		try {
			hash=HashHelper.calcSHA1(password+user.getSalt());
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(!user.getPasswort().equals(hash)){
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
	public void register(String firstName, String name, String userName, String mail, String password,String passwordRepeat) 
			throws IncompleteUserRegisterException, NoValidUserNameException {
		
		LOGGER.log(Level.INFO,"parameter:");
		LOGGER.log(Level.INFO, "firstName: {0}", firstName);
		LOGGER.log(Level.INFO, "name: {0}", name);
		LOGGER.log(Level.INFO, "userName: {0}", userName);
		LOGGER.log(Level.INFO, "mail: {0}", mail);
		LOGGER.log(Level.INFO, "password: {0}", password);
		LOGGER.log(Level.INFO, "passwordRepeat: {0}", passwordRepeat);
		
		AbdUser user = null;
		AbdUser checkUser = null;
		String salt="";
		String hash="";
		
		
		if(firstName==null){
			LOGGER.log(Level.SEVERE, "No firstname given!");
			throw new IncompleteUserRegisterException("No firstname given!");
		}
		
		if(name==null){
			LOGGER.log(Level.SEVERE, "No name given!");
			throw new IncompleteUserRegisterException("No firstname given!");
		}
		
		
		if(EMailValidator.isEmail(mail)){
			LOGGER.log(Level.SEVERE, "Mail is not a valid mail!");
			throw new IncompleteUserRegisterException("Mail is not a valid mail!");
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
		
		
		//check if userName is unique
		checkUser=userDAO.findUserByUsername(userName);
		
		if(checkUser!=null){
			LOGGER.log(Level.SEVERE, "UserName does already exists!");
			throw new NoValidUserNameException("UserName does already exists!");
		}
		
		
		// generate Salt
		salt=PasswordGenerator.generateSalt();
		
		
		//user init
		user= new AbdUser();
		user.setId(Integer.SIZE);
		user.setFirstname(firstName);
		user.setName(name);
		user.setUsername(userName);
		user.setMail(mail);
		user.setSalt(salt);
		
		try {
			hash= HashHelper.calcSHA1(password+salt);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		user.setPasswort(hash);
		
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