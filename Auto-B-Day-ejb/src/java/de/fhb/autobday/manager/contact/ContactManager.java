package de.fhb.autobday.manager.contact;

import de.fhb.autobday.dao.ContactFacade;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Michael Koppen
 */
@Stateless
public class ContactManager implements ContactManagerLocal {
	private final static Logger LOGGER = Logger.getLogger(ContactManager.class.getName());
		
	@EJB
	private ContactFacade contactDAO;
	
	
	
}
