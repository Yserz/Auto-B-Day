package de.fhb.autobday.manager.contact;

import de.fhb.autobday.dao.AbdContactFacade;
import de.fhb.autobday.dao.AbdGroupToContactFacade;
import de.fhb.autobday.data.AbdContact;
import de.fhb.autobday.data.AbdGroupToContact;
import de.fhb.autobday.exception.contact.ContactException;
import de.fhb.autobday.exception.contact.ContactNotFoundException;
import de.fhb.autobday.exception.contact.NoContactInThisGroupException;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * The Contactmanager processes all contact specific things.
 * 
 * @author Andy Klay <klay@fh-brandenburg.de>
 * @author Michael Koppen <koppen@fh-brandenburg.de>
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
		LOGGER.log(Level.INFO, "contactId: {0}", contactId);
		LOGGER.log(Level.INFO, "active: {1}", active);
		
		AbdGroupToContact groupToContact=null;
		Collection<AbdGroupToContact> allGroupToContact=null;
		
		AbdContact contact=contactDAO.find(contactId);
		
		
		if(contact==null){
			//if contact not found
			LOGGER.log(Level.SEVERE, "Contact {0}not found!", contactId);
			throw new ContactNotFoundException("Contact " + contactId + "not found!");
		}
		
		allGroupToContact = groupToContactDAO.findGroupByContact(contactId);
		
		if(allGroupToContact==null){
			LOGGER.log(Level.SEVERE, "Relation groupToContact not found!");
			throw new NoContactInThisGroupException("Relation groupToContact not found!");
		}
		
		//search for relation
		for(AbdGroupToContact actualGroupToContact:allGroupToContact){
			if(actualGroupToContact.getAbdContact().equals(contact)){
				groupToContact=actualGroupToContact;
			}
		}
		
		if(groupToContact==null){
			//if no compatible realtion found
			LOGGER.log(Level.SEVERE, "Relation groupToContact not found!");
			throw new NoContactInThisGroupException("Relation groupToContact not found!");
		}
		
		
		groupToContact.setActive(active);
		
		//save into database
		groupToContactDAO.edit(groupToContact);
		
	}
	
}