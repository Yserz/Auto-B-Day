package de.fhb.autobday.manager.group;

import de.fhb.autobday.dao.AbdgroupFacade;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author MacYser
 */
@Stateless
public class GroupManager implements GroupManagerLocal {

	@EJB
	private AbdgroupFacade groupDAO;
	// Add business logic below. (Right-click in editor and choose
	// "Insert Code > Add Business Method")
	
}
