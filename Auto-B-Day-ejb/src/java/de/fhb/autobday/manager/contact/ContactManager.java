package de.fhb.autobday.manager.contact;

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
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

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
	 * @see de.fhb.autobday.manager.contact.ContactManagerLocal#setActive(de.fhb.autobday.data.AbdContact, de.fhb.autobday.data.AbdGroup,boolean)
	 */
	@Override
	public void setActive(AbdContact contact, AbdGroup group, boolean active)
			throws ContactNotFoundException, ContactToGroupNotFoundException {
		setActive(contact.getId(), group.getId(), active);
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param groupId
	 * @throws ContactNotFoundException
	 * @throws ContactToGroupNotFoundException
	 * @see de.fhb.autobday.manager.contact.ContactManagerLocal#setActive(de.fhb.autobday.data.AbdContact, de.fhb.autobday.data.AbdGroup,boolean)
	 */
	@Override
	public void setActive(String contactId, String groupId, boolean active)
			throws ContactNotFoundException, ContactToGroupNotFoundException {

		Collection<AbdGroupToContact> allGroupToContact = null;

		AbdContact contact = contactDAO.find(contactId);

		if (contact == null) {
			//if contact not found
			LOGGER.log(Level.SEVERE, "Contact {0} not found!", contactId);
			throw new ContactNotFoundException("Contact " + contactId + " not found!");
		}

		allGroupToContact = contact.getAbdGroupToContactCollection();

		if (allGroupToContact.isEmpty()) {
			LOGGER.log(Level.SEVERE, "Relation groupToContact not found!");
			throw new ContactToGroupNotFoundException("Relation groupToContact not found!");
		}


		AbdGroup group = groupDAO.find(groupId);

		if (group == null) {
			//if contact not found
			LOGGER.log(Level.SEVERE, "Group {0} not found!", groupId);
			throw new ContactNotFoundException("Group " + groupId + " not found!");
		}

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

		//find contact
		AbdContact contact = contactDAO.find(contactId);

		if (contact == null) {
			//if group not found
			LOGGER.log(Level.SEVERE, "Contact {0} not found!", contactId);
			throw new ContactNotFoundException("Contact " + contactId + "not found!");
		}

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
		//find contact
		AbdContact contact = contactDAO.find(contactId);

		//find group
		AbdGroup group = groupDAO.find(groupId);


		if (contact == null) {
			//if contact not found
			LOGGER.log(Level.SEVERE, "Contact {0} not found!", contactId);
			throw new ContactNotFoundException("Contact " + contactId + " not found!");
		}

		if (group == null) {
			//if group not found
			LOGGER.log(Level.SEVERE, "Group {0} not found!", groupId);
			throw new GroupNotFoundException("Group " + groupId + " not found!");
		}
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
}
