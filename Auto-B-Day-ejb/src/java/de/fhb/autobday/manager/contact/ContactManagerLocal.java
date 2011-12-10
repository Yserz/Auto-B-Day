package de.fhb.autobday.manager.contact;

import javax.ejb.Local;

import de.fhb.autobday.data.AbdContact;
import de.fhb.autobday.exception.contact.ContactException;

/**
 *
 * @author Andy Klay <klay@fh-brandenburg.de>
 * @author Michael Koppen <koppen@fh-brandenburg.de>
 */
@Local
public interface ContactManagerLocal {

	void setActive(String contactId, boolean active)throws ContactException ;
	
	AbdContact getContact(String contactId)throws ContactException ;
}
