package de.fhb.autobday.manager.contact;

import de.fhb.autobday.data.AbdContact;
import de.fhb.autobday.data.AbdGroup;
import de.fhb.autobday.exception.contact.ContactNotFoundException;
import de.fhb.autobday.exception.contact.ContactToGroupNotFoundException;
import de.fhb.autobday.exception.group.GroupNotFoundException;
import javax.ejb.Local;

/**
 * The Contactmanager processes all contact specific tasks.
 *
 * @author Andy Klay mail: klay@fh-brandenburg.de
 * @author Michael Koppen mail: koppen@fh-brandenburg.de
 */
@Local
public interface ContactManagerLocal {

	/**
	 * set a contact to a active stat for sending bdaymails by a
	 * AbdContactobject
	 *
	 * @param contact
	 * @param group
	 * @param active
	 * @throws ContactNotFoundException
	 * @throws ContactToGroupNotFoundException
	 * @throws GroupNotFoundException  
	 */
	void setActive(AbdContact contact, AbdGroup group, boolean active) throws ContactNotFoundException, ContactToGroupNotFoundException, GroupNotFoundException;

	/**
	 * set a contact to a active stat for sending bdaymails by a contactid
	 *
	 * @param contactId
	 * @param groupId
	 * @param active
	 * @throws ContactNotFoundException
	 * @throws ContactToGroupNotFoundException
	 * @throws GroupNotFoundException  
	 */
	void setActive(String contactId, String groupId, boolean active) throws ContactNotFoundException, ContactToGroupNotFoundException, GroupNotFoundException;

	/**
	 * get a contact by a contactid
	 *
	 * @param contactId
	 * @return AbdContact
	 * @throws ContactNotFoundException
	 */
	AbdContact getContact(String contactId) throws ContactNotFoundException;

	/**
	 * get the active stat of a contact in a group
	 *
	 * @param contactId
	 * @param groupTd
	 * @return boolean
	 * @throws ContactNotFoundException
	 * @throws GroupNotFoundException
	 */
	boolean getActive(String contactId, String groupTd) throws ContactNotFoundException, GroupNotFoundException;
}
