package de.fhb.autobday.manager.contact;

import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import de.fhb.autobday.dao.AbdContactFacade;
import de.fhb.autobday.dao.AbdGroupToContactFacade;
import de.fhb.autobday.data.AbdContact;
import de.fhb.autobday.data.AbdGroupToContact;
import de.fhb.autobday.exception.contact.ContactException;

/**
 *
 * @author Andy Klay <klay@fh-brandenburg.de>
 */
@Stateless
public class ContactManager implements ContactManagerLocal {
	
	private final static Logger LOGGER = Logger.getLogger(ContactManager.class.getName());
		
	@EJB
	private AbdContactFacade contactDAO;

	@EJB
	private AbdGroupToContactFacade groupToContactDAO;
	
	@Override
	public void setActive(String contactId, boolean active) throws ContactException {
		
		
		LOGGER.log(Level.INFO,"parameter:");
		LOGGER.log(Level.INFO,"contactId: " + contactId);
		LOGGER.log(Level.INFO,"active: " + active);
		
		AbdGroupToContact groupToContact=null;
		Collection<AbdGroupToContact> allGroupToContact=null;
		
		AbdContact contact=contactDAO.find(contactId);
		
		
		if(contact==null){
			LOGGER.log(Level.SEVERE, "Contact " + contactId + "not found!");
			throw new ContactException("Contact " + contactId + "not found!");
		}
		
		
		allGroupToContact = groupToContactDAO.findContactByContact(contactId);
		
		for(AbdGroupToContact actualGroupToContact:allGroupToContact){
			if(actualGroupToContact.getAbdContact().equals(contact)){
				groupToContact=actualGroupToContact;
			}
		}
		
		
		if(groupToContact==null){
			LOGGER.log(Level.SEVERE, "Relation groupToContact not found!");
			throw new ContactException("Relation groupToContact not found!");
		}
		
		
		groupToContact.setActive(active);
		
		//save into database
		groupToContactDAO.edit(groupToContact);
		
		
	}
	
}
