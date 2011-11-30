package de.fhb.autobday.manager.user;

import de.fhb.autobday.data.AbdUser;
import de.fhb.autobday.exception.user.UserException;

import javax.ejb.Local;

/**
 *
 * @author Michael Koppen
 */
@Local
public interface UserManagerLocal {
	AbdUser getUser(int userid);

	AbdUser login(String loginName, String password) throws UserException ;

	void logout();
}
