package de.fhb.autobday.manager.contact;

import javax.ejb.Local;

import de.fhb.autobday.exception.contact.ContactException;

/**
 *
 * @author Michael Koppen, Andy Klay <klay@fh-brandenburg.de>
 */
@Local
public interface ContactManagerLocal {

	void setActive(String contactId, boolean active)throws ContactException ;
}
