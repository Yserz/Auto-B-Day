package de.fhb.autobday.manager.contact;

import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import de.fhb.autobday.dao.AbdContactFacade;
import de.fhb.autobday.dao.AbdGroupFacade;
import de.fhb.autobday.dao.AbdGroupToContactFacade;
import de.fhb.autobday.data.AbdContact;
import de.fhb.autobday.data.AbdGroup;
import de.fhb.autobday.data.AbdGroupToContact;
import de.fhb.autobday.exception.contact.ContactNotFoundException;
import de.fhb.autobday.exception.contact.ContactToGroupNotFoundException;
import de.fhb.autobday.exception.group.GroupNotFoundException;
import de.fhb.autobday.manager.LoggerInterceptor;

/**
 * implementation of ContactManager.
 *
 * @author Andy Klay mail: klay@fh-brandenburg.de
 * @author Michael Koppen mail: koppen@fh-brandenburg.de
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
	 * {@inheritDoc}
	 *
	 * @param group
	 * @throws ContactNotFoundException
	 * @throws ContactToGroupNotFoundException
	 * @throws GroupNotFoundException 
	 * @see de.fhb.autobday.manager.contact.ContactManagerLocal#setActive(de.fhb.autobday.data.AbdContact, de.fhb.autobday.data.AbdGroup,boolean)
	 */
	@Override
	public void setActive(AbdContact contact, AbdGroup group, boolean active)
			throws ContactNotFoundException, ContactToGroupNotFoundException, GroupNotFoundException {
		setActive(contact.getId(), group.getId(), active);
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param groupId
	 * @throws ContactNotFoundException
	 * @throws ContactToGroupNotFoundException
	 * @throws GroupNotFoundException 
	 * @see de.fhb.autobday.manager.contact.ContactManagerLocal#setActive(de.fhb.autobday.data.AbdContact, de.fhb.autobday.data.AbdGroup,boolean)
	 */
	@Override
	public void setActive(String contactId, String groupId, boolean active)
			throws ContactNotFoundException, ContactToGroupNotFoundException, GroupNotFoundException {

		Collection<AbdGroupToContact> allGroupToContact;

		//lookup for contact
		AbdContact contact = findContact(contactId);

		allGroupToContact = contact.getAbdGroupToContactCollection();

		if (allGroupToContact.isEmpty()) {
			LOGGER.log(Level.SEVERE, "Relation groupToContact not found!");
			throw new ContactToGroupNotFoundException("Relation groupToContact not found!");
		}

		//lookup for group
		AbdGroup group = findGroup(groupId);

		//search for relation
		for (AbdGroupToContact actualGroupToContact : allGroupToContact) {
			if (actualGroupToContact.getAbdContact().equals(contact)) {
				if (actualGroupToContact.getAbdGroup().equals(group)) {
					actualGroupToContact.setActive(active);
				} else {
					actualGroupToContact.setActive(false);
				}
			}
		}


		//save into database
		for (AbdGroupToContact actualGroupToContact : allGroupToContact) {
			if (actualGroupToContact.getAbdContact().equals(contact)) {
				groupToContactDAO.edit(actualGroupToContact);
				LOGGER.log(Level.INFO, "Manager: isAktive?: {0}", actualGroupToContact.getActive());
			}
		}
	}

	/**
	 * {@inheritDoc}
	 *
	 * @throws ContactNotFoundException
	 * @see
	 * de.fhb.autobday.manager.contact.ContactManagerLocal#getContact(java.lang.String)
	 */
	@Override
	public AbdContact getContact(String contactId)
			throws ContactNotFoundException {

		//lookup for contact
		AbdContact contact = findContact(contactId);

		return contact;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param groupId
	 * @see
	 * de.fhb.autobday.manager.contact.ContactManagerLocal#getContact(java.lang.String)
	 */
	@Override
	public boolean getActive(String contactId, String groupId)
			throws ContactNotFoundException, GroupNotFoundException {

		AbdGroupToContact markedGroupToContact = null;
		groupDAO.flush();
		
		//lookup for contact
		AbdContact contact = findContact(contactId);

		//lookup for group
		AbdGroup group = findGroup(groupId);
		
		//search for GroupToContact
		for (AbdGroupToContact abdGroupToContact : group.getAbdGroupToContactCollection()) {
			if (abdGroupToContact.getAbdContact().equals(contact)) {
				LOGGER.log(Level.INFO, "found match!!!!!!!!!!");
				LOGGER.log(Level.INFO, "isActive?: {0}", abdGroupToContact.getActive());
				markedGroupToContact = abdGroupToContact;
			}
		}



		if (markedGroupToContact == null) {
			LOGGER.log(Level.SEVERE, "GroupToContact not found!");
			throw new GroupNotFoundException("GroupToContact not found!");
		}

		return markedGroupToContact.getActive();
	}
	/**
	 * Method to lookup for a contact.
	 * if no contact exists exception is thrown.
	 * 
	 * @param contactId contact to find
	 * @return found contact
	 * @throws ContactNotFoundException 
	 */
	protected AbdContact findContact(String contactId) throws ContactNotFoundException{
		AbdContact contact;
		
		contact = contactDAO.find(contactId);

		if (contact == null) {
			//if contact not found
			LOGGER.log(Level.SEVERE, "Contact {0} not found!", contactId);
			throw new ContactNotFoundException("Contact " + contactId + " not found!");
		}
		return contact;
	}
	/**
	 * Method to lookup for a group.
	 * if no group exists exception is thrown.
	 * 
	 * @param groupId group to find
	 * @return found group
	 * @throws GroupNotFoundException 
	 */
	protected AbdGroup findGroup(String groupId) throws GroupNotFoundException{
		AbdGroup group;
		
		//find group
		group = groupDAO.find(groupId);

		if (group == null) {
			//if group not found
			LOGGER.log(Level.SEVERE, "Group {0} not found!", groupId);
			throw new GroupNotFoundException("Group " + groupId + " not found!");
		}
		return group;
	}
}
