package de.fhb.autobday.manager.user;

import de.fhb.autobday.data.AbdUser;
import javax.ejb.Local;

/**
 *
 * @author Michael Koppen
 */
@Local
public interface UserManagerLocal {
	AbdUser getUser(int userid);

	void login();

	void logout();
}
