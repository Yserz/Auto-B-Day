package de.fhb.autobday.manager.contact;

import de.fhb.autobday.dao.ContactFacade;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author MacYser
 */
@Stateless
public class ContactManager implements ContactManagerLocal {
	@EJB
	private ContactFacade contactDAO;
	
	// Add business logic below. (Right-click in editor and choose
	// "Insert Code > Add Business Method")
	
}
