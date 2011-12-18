package de.fhb.autobday.manager.user;

import javax.ejb.Local;

import de.fhb.autobday.data.AbdUser;
import de.fhb.autobday.exception.user.IncompleteUserRegisterException;
import de.fhb.autobday.exception.user.NoValidUserNameException;
import de.fhb.autobday.exception.user.UserException;

/**
 *
 * @author Andy Klay <klay@fh-brandenburg.de>
 * @author Michael Koppen <koppen@fh-brandenburg.de>
 */
@Local
public interface UserManagerLocal {
	AbdUser getUser(int userid);

	AbdUser login(String loginName, String password) throws UserException ;

	void logout();
	
	void register(String firstName, String name, String userName, String mail) throws IncompleteUserRegisterException, NoValidUserNameException;
}
