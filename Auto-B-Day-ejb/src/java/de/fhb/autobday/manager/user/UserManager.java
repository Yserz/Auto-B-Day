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
import de.fhb.autobday.exception.HashFailException;
import de.fhb.autobday.exception.user.IncompleteLoginDataException;
import de.fhb.autobday.exception.user.IncompleteUserRegisterException;
import de.fhb.autobday.exception.user.NoValidUserNameException;
import de.fhb.autobday.exception.user.PasswordInvalidException;
import de.fhb.autobday.exception.user.UserException;
import de.fhb.autobday.exception.user.UserNotFoundException;

/**
 * this class manage the userspecific things
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
	
	/**
	 * (non-Javadoc)
	 * @see de.fhb.autobday.manager.user.UserManagerLocal#getUser(int)
	 */
	@Override
	public AbdUser getUser(int userid){
		return userDAO.find(userid);
	}

	/**
	 * (non-Javadoc)
	 * @throws HashFailException 
	 * @see de.fhb.autobday.manager.user.UserManagerLocal#login(java.lang.String, java.lang.String)
	 */
	@Override
	public AbdUser login(String loginName, String password) throws UserException, HashFailException {
		
		LOGGER.log(Level.INFO,"parameter:");
		LOGGER.log(Level.INFO, "loginName: {0}", loginName);
		
		AbdUser user = null;
		String hash="";
		
		if(loginName==null||password==null||loginName.equals("")||password.equals("")){
			LOGGER.log(Level.SEVERE, "Invalid input!");
			throw new IncompleteLoginDataException("Invalid input!");
		}
		
		try {
			user = userDAO.findUserByUsername(loginName);
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "Invalid loginame!");
			throw new IncompleteLoginDataException("Invalid loginame!");
		}
		
		System.out.println("user: "+user);
		
		if(user==null){
			LOGGER.log(Level.SEVERE, "User not found!");
			throw new UserNotFoundException("User not found!");
		}
		
		
		//check password
		try {
			
			hash=HashHelper.calcSHA1(password+user.getSalt());
			
		} catch (UnsupportedEncodingException e) {
			LOGGER.log(Level.SEVERE, "UnsupportedEncodingException  " + e.getMessage());
			throw new HashFailException("UnsupportedEncodingException in Hashhelper");
		} catch (NoSuchAlgorithmException e) {
			LOGGER.log(Level.SEVERE, "NoSuchAlgorithmException  " + e.getMessage());
			throw new HashFailException("NoSuchAlgorithmException in Hashhelper");
		}
		
		if(!user.getPasswort().equals(hash)){
			LOGGER.log(Level.SEVERE, "Invalid password!");
			throw new PasswordInvalidException("Invalid password!");
		}

		return user;
	}

	/**
	 * (non-Javadoc)
	 * @see de.fhb.autobday.manager.user.UserManagerLocal#logout()
	 */
	@Override
	public void logout() {
		LOGGER.log(Level.INFO,"logout");
	}
	
	/**
	 * (non-Javadoc)
	 * @throws HashFailException 
	 * @see de.fhb.autobday.manager.user.UserManagerLocal#register(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void register(String firstName, String name, String userName, String mail, String password,String passwordRepeat) 
			throws IncompleteUserRegisterException, NoValidUserNameException, HashFailException {
		
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
		
		
		if(firstName.equals("")){
			LOGGER.log(Level.SEVERE, "No firstname given!");
			throw new IncompleteUserRegisterException("No firstname given!");
		}
		
		if(name.equals("")){
			LOGGER.log(Level.SEVERE, "No name given!");
			throw new IncompleteUserRegisterException("No firstname given!");
		}
		
		if (!mail.equals("")) {
			if(!EMailValidator.isEmail(mail)){
				LOGGER.log(Level.SEVERE, "Mail is not a valid mail!");
				throw new IncompleteUserRegisterException("Mail is not a valid mail!");
			}
		}else{
			LOGGER.log(Level.SEVERE, "No mail given!");
			throw new IncompleteUserRegisterException("No mail given!");
		}
		if(userName.equals("")){
			LOGGER.log(Level.SEVERE, "No username given!");
			throw new IncompleteUserRegisterException("No username given");
		}
		
		if(userName.length()<5){
			LOGGER.log(Level.SEVERE, "No valid Username!");
			throw new NoValidUserNameException("No valid Username!");
		}
		
		if(password.equals("")){
			LOGGER.log(Level.SEVERE, "No password given!");
			throw new IncompleteUserRegisterException("No password given");
		}
		
		if(passwordRepeat.equals("")){
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
		
		//hash
		try {
			hash= HashHelper.calcSHA1(password+salt);
		} catch (UnsupportedEncodingException e) {
			LOGGER.log(Level.SEVERE, "UnsupportedEncodingException  " + e.getMessage());
			throw new HashFailException("UnsupportedEncodingException in Hashhelper");
		} catch (NoSuchAlgorithmException e) {
			LOGGER.log(Level.SEVERE, "NoSuchAlgorithmException  " + e.getMessage());
			throw new HashFailException("NoSuchAlgorithmException in Hashhelper");
		}
		
		user.setPasswort(hash);
		
		//save in to db
		userDAO.create(user);
	}
	
	/**
	 * (non-Javadoc)
	 * @see de.fhb.autobday.manager.user.UserManagerLocal#getAllAccountsFromUser(de.fhb.autobday.data.AbdUser)
	 */
	@Override
	public List<AbdAccount> getAllAccountsFromUser(AbdUser user) throws UserNotFoundException{
		return getAllAccountsFromUser(user.getId());
	}
	
	
	/**
	 * (non-Javadoc)
	 * @see de.fhb.autobday.manager.user.UserManagerLocal#getAllAccountsFromUser(int)
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
		//userDAO.refresh(user);
		for(AbdAccount actualAccount :user.getAbdAccountCollection()){
			outputCollection.add(actualAccount);
		}
		
		
		return outputCollection;
	}
	
}