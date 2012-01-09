package de.fhb.autobday.manager.contact;

import de.fhb.autobday.dao.AbdContactFacade;
import de.fhb.autobday.dao.AbdGroupFacade;
import de.fhb.autobday.dao.AbdGroupToContactFacade;
import de.fhb.autobday.data.AbdContact;
import de.fhb.autobday.data.AbdGroup;
import de.fhb.autobday.data.AbdGroupToContact;
import de.fhb.autobday.exception.contact.ContactException;
import de.fhb.autobday.exception.contact.ContactNotFoundException;
import de.fhb.autobday.exception.contact.ContactToGroupNotFoundException;
import de.fhb.autobday.exception.contact.NoContactInThisGroupException;
import de.fhb.autobday.manager.LoggerInterceptor;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

/**
 * The Contactmanager processes all contact specific things.
 * 
 * @author
 * Andy Klay <klay@fh-brandenburg.de>
 * Michael Koppen <koppen@fh-brandenburg.de>
 */
@Stateless
@Interceptors(LoggerInterceptor.class)
public class ContactManager implements ContactManagerLocal {
	
	private final static Logger LOGGER = Logger.getLogger(ContactManager.class.getName());
		
	@EJB
	private AbdContactFacade contactDAO;
	
	@EJB
	private AbdGroupFacade groupDAO;

	@EJB
	private AbdGroupToContactFacade groupToContactDAO;
	
	/**
	 * (non-Javadoc)
	 * @see de.fhb.autobday.manager.contact.ContactManagerLocal#setActive(de.fhb.autobday.data.AbdContact, boolean)
	 */
	@Override
	public void setActive(AbdContact contact, AbdGroup group, boolean active) throws ContactException{
		//TODO missing group param?
		setActive(contact.getId(), group.getId(), active);
	}
	
	/**
	 * (non-Javadoc)
	 * @see de.fhb.autobday.manager.contact.ContactManagerLocal#setActive(java.lang.String, boolean)
	 */
	@Override
	public void setActive(String contactId, String groupId, boolean active) throws ContactException {
		//TODO missing group param?
		
		AbdGroupToContact groupToContact=null;
		Collection<AbdGroupToContact> allGroupToContact=null;
		
		AbdContact contact=contactDAO.find(contactId);
		
		
		if(contact==null){
			//if contact not found
			LOGGER.log(Level.SEVERE, "Contact {0} not found!", contactId);
			throw new ContactNotFoundException("Contact " + contactId + " not found!");
		}
		
		AbdGroup group=groupDAO.find(groupId);
		
		
		if(group==null){
			//if contact not found
			LOGGER.log(Level.SEVERE, "Group {0} not found!", groupId);
			throw new ContactNotFoundException("Group " + groupId + " not found!");
		}
		
		allGroupToContact = contact.getAbdGroupToContactCollection();
		
		if(allGroupToContact.isEmpty()){
			LOGGER.log(Level.SEVERE, "Relation groupToContact not found!");
			throw new ContactToGroupNotFoundException("Relation groupToContact not found!");
		}
		
		if (active) {
			for(AbdGroupToContact actualGroupToContact:allGroupToContact){
				if(actualGroupToContact.getAbdContact().equals(contact)){
					actualGroupToContact.setActive(false);
				}
			}
		}
		
		
		//search for relation
		for(AbdGroupToContact actualGroupToContact:allGroupToContact){
			if(actualGroupToContact.getAbdContact().equals(contact)){
				if (actualGroupToContact.getAbdGroup().equals(group)) {
					actualGroupToContact.setActive(active);
				}
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
	
	
	/**
	 * (non-Javadoc)
	 * @see de.fhb.autobday.manager.contact.ContactManagerLocal#getContact(java.lang.String)
	 */
	public AbdContact getContact(String contactId)throws ContactException{
		
		//find group
		AbdContact contact = contactDAO.find(contactId);
		
		if(contact==null){
			//if group not found
			LOGGER.log(Level.SEVERE, "Contact {0} not found!", contactId);
			throw new ContactNotFoundException("Contact " + contactId + "not found!");
		}
		
		return contact;
	}

	
}