package de.fhb.autobday.manager.contact;

import javax.ejb.Local;

import de.fhb.autobday.data.AbdContact;
import de.fhb.autobday.data.AbdGroup;
import de.fhb.autobday.exception.contact.ContactException;

/**
 * The Contactmanager processes all contact specific things.
 *
 * @author 
 * Andy Klay <klay@fh-brandenburg.de>
 * Michael Koppen <koppen@fh-brandenburg.de>
 */
@Local
public interface ContactManagerLocal {

	/**
	 * set a contact to a active stat for sending bdaymails
	 * by a AbdContactobject
	 * 
	 * @param contact
	 * @param active
	 * @throws ContactException
	 */
	void setActive(AbdContact contact, AbdGroup group, boolean active) throws ContactException;
	
	/**
	 * set a contact to a active stat for sending bdaymails
	 * by a contactid
	 * 
	 * @param contactId
	 * @param active
	 * @throws ContactException
	 */
	void setActive(String contactId, String groupId, boolean active)throws ContactException ;
	
	/**
	 * get a contact by a contactid
	 * 
	 * @param contactId
	 * @return AbdContact
	 * @throws ContactException
	 */
	AbdContact getContact(String contactId)throws ContactException ;
}
