package de.fhb.autobday.manager.user;

import de.fhb.autobday.data.AbdAccount;
import de.fhb.autobday.data.AbdUser;
import de.fhb.autobday.exception.commons.HashFailException;
import de.fhb.autobday.exception.mail.MailException;
import de.fhb.autobday.exception.user.*;
import java.util.List;
import javax.ejb.Local;

/**
 * this class manage the userspecific things
 *
 * @author Andy Klay mail: klay@fh-brandenburg.de
 * @author Michael Koppen mail: koppen@fh-brandenburg.de
 */
@Local
public interface UserManagerLocal {

	/**
	 * get a abdUser object by userid
	 *
	 * @param userid
	 * @return AbdUser
	 */
	AbdUser getUser(int userid);

	/**
	 * login a user by username and password
	 *
	 * @param loginName
	 * @param password
	 * @return User which currently logged in
	 * @throws IncompleteLoginDataException 
	 * @throws UserNotFoundException 
	 * @throws HashFailException
	 * @throws PasswordInvalidException  
	 */
	AbdUser login(String loginName, String password)
			throws HashFailException, IncompleteLoginDataException, UserNotFoundException, PasswordInvalidException;

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
	 * @return AbdUser
	 * @throws IncompleteUserRegisterException
	 * @throws NoValidUserNameException
	 * @throws HashFailException
	 */
	AbdUser register(String firstName, String name, String userName, String mail,
			String password, String passwordRepeat)
			throws IncompleteUserRegisterException, NoValidUserNameException, HashFailException;

	/**
	 *
	 * get all accounts of a user by abdUser object
	 *
	 * @param user
	 * @return List<AbdAccount>
	 * @throws UserNotFoundException
	 */
	List<AbdAccount> getAllAccountsFromUser(AbdUser user)
			throws UserNotFoundException;

	/**
	 * get all accounts of a user by userid
	 *
	 * @param userId
	 * @return AbdAccountList from User
	 * @throws UserNotFoundException
	 */
	List<AbdAccount> getAllAccountsFromUser(int userId)
			throws UserNotFoundException;

	/**
	 * send a mail with a new password
	 *
	 * @param userName
	 * @throws MailException
	 * @throws UserNotFoundException
	 * @throws HashFailException
	 */
	void sendForgotPasswordMail(String userName)
			throws MailException, UserNotFoundException, HashFailException;

	/**
	 * change a users password by userid
	 *
	 * @param userId
	 * @param oldPassword
	 * @param newPassword
	 * @param newPasswordRepeat
	 * @throws PasswordInvalidException
	 * @throws HashFailException
	 * @throws UserNotFoundException
	 */
	void changePassword(int userId, String oldPassword, String newPassword, String newPasswordRepeat)
			throws UserNotFoundException, PasswordInvalidException, HashFailException;

	/**
	 * change a users password by userobject
	 *
	 * @param user
	 * @param password
	 * @param passwordRepeat
	 * @param oldPassword
	 * @throws PasswordInvalidException
	 * @throws UserNotFoundException
	 * @throws HashFailException
	 */
	void changePassword(AbdUser user, String oldPassword, String password, String passwordRepeat)
			throws UserNotFoundException, PasswordInvalidException, HashFailException;
}