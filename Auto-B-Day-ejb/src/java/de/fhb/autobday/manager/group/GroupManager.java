package de.fhb.autobday.manager.group;

import de.fhb.autobday.dao.AbdgroupFacade;
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
	// Add business logic below. (Right-click in editor and choose
	// "Insert Code > Add Business Method")
	
}
