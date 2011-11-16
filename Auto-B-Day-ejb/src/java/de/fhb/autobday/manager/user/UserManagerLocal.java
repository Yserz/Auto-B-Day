package de.fhb.autobday.manager.user;

import de.fhb.autobday.data.Abduser;
import javax.ejb.Local;

/**
 *
 * @author Michael Koppen
 */
@Local
public interface UserManagerLocal {
	Abduser getUser(int userid);
}
