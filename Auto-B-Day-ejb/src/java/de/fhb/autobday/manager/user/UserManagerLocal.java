package de.fhb.autobday.manager.user;

import de.fhb.autobday.data.AbdAccount;
import de.fhb.autobday.data.AbdUser;
import de.fhb.autobday.exception.HashFailException;
import de.fhb.autobday.exception.user.IncompleteUserRegisterException;
import de.fhb.autobday.exception.user.NoValidUserNameException;
import de.fhb.autobday.exception.user.UserException;
import de.fhb.autobday.exception.user.UserNotFoundException;

import java.util.List;
import javax.ejb.Local;

/**
 * this class manage the userspecific things
 *
 * @author
 * Andy Klay <klay@fh-brandenburg.de>
 * Michael Koppen <koppen@fh-brandenburg.de>
 */
@Local
public interface UserManagerLocal {
	
	/**
	 * get a abdUser object by userid
	 * 
	 * @param userid
	 * @return
	 */
	AbdUser getUser(int userid);

	/**
	 * login a user by username and password
	 * 
	 * @param loginName
	 * @param password
	 * @return
	 * @throws UserException
	 * @throws HashFailException
	 */
	AbdUser login(String loginName, String password) throws UserException, HashFailException ;

	/**
	 * logout
	 * 
	 */
	void logout();
	
	/**
	 * register a user for using of autobdaysystem
	 * 
	 * @param firstName
	 * @param name
	 * @param userName
	 * @param mail
	 * @param password
	 * @param passwordRepeat
	 * @throws IncompleteUserRegisterException
	 * @throws NoValidUserNameException
	 * @throws HashFailException
	 */
	void register(String firstName, String name, String userName, String mail, String password,String passwordRepeat) throws IncompleteUserRegisterException, NoValidUserNameException, HashFailException;
	
	/**
	 * 
	 * get all accounts of a user by abdUser object
	 * 
	 * @param user
	 * @return List<AbdAccount>
	 * @throws UserNotFoundException
	 */
	List<AbdAccount> getAllAccountsFromUser(AbdUser user) throws UserNotFoundException;
	
	/**
	 * get all accounts of a user by userid
	 * 
	 * @param userId
	 * @return
	 * @throws UserNotFoundException
	 */
	List<AbdAccount> getAllAccountsFromUser(int userId) throws UserNotFoundException;
	
	
}