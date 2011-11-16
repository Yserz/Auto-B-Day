package de.fhb.autobday.manager.group;

import de.fhb.autobday.data.Abdgroup;
import javax.ejb.Local;

/**
 *
 * @author Michael Koppen
 */
@Local
public interface GroupManagerLocal {

	Abdgroup getGroup(int groupid);
}