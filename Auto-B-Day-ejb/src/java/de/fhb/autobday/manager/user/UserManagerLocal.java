package de.fhb.autobday.manager.user;

import de.fhb.autobday.data.AbdAccount;
import de.fhb.autobday.data.AbdUser;
import de.fhb.autobday.exception.user.IncompleteUserRegisterException;
import de.fhb.autobday.exception.user.NoValidUserNameException;
import de.fhb.autobday.exception.user.UserException;
import de.fhb.autobday.exception.user.UserNotFoundException;

import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author
 * Andy Klay <klay@fh-brandenburg.de>
 * Michael Koppen <koppen@fh-brandenburg.de>
 */
@Local
public interface UserManagerLocal {
	AbdUser getUser(int userid);

	AbdUser login(String loginName, String password) throws UserException ;

	void logout();
	
	void register(String firstName, String name, String userName,String password,String passwordRepeat) throws IncompleteUserRegisterException, NoValidUserNameException;
	
	List<AbdAccount> getAllAccountsFromUser(AbdUser user) throws UserNotFoundException;
	List<AbdAccount> getAllAccountsFromUser(int userId) throws UserNotFoundException;
}
