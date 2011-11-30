package de.fhb.autobday.manager.contact;

import de.fhb.autobday.dao.AbdContactFacade;
import de.fhb.autobday.data.AbdContact;
import de.fhb.autobday.exception.contact.ContactException;
import de.fhb.autobday.exception.group.GroupException;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.sun.org.apache.bcel.internal.classfile.CodeException;

/**
 *
 * @author Michael Koppen, Andy Klay <klay@fh-brandenburg.de>
 */
@Stateless
public class ContactManager implements ContactManagerLocal {
	private final static Logger LOGGER = Logger.getLogger(ContactManager.class.getName());
		
	@EJB
	private AbdContactFacade contactDAO;

	@Override
	public void setActive(String contactId, boolean active) throws ContactException {
		
		LOGGER.log(Level.INFO,"parameter:");
		LOGGER.log(Level.INFO,"active: " + active);	
		
		AbdContact contact=contactDAO.find(contactId);
		
		if(contact==null){
			LOGGER.log(Level.SEVERE, "Contact " + contactId + "not found!");
			throw new ContactException("Contact " + contactId + "not found!");
		}
		
		//TODO
	}
	
	
	
}
