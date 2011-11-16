package de.fhb.autobday.manager.group;

import de.fhb.autobday.dao.AbdgroupFacade;
import de.fhb.autobday.data.Abdgroup;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Michael Koppen
 */
@Stateless
public class GroupManager implements GroupManagerLocal {
	private final static Logger LOGGER = Logger.getLogger(GroupManager.class.getName());

	@EJB
	private AbdgroupFacade groupDAO;

	

	@Override
	public Abdgroup getGroup(int groupid) {
		return groupDAO.find(groupid);
	}
}
