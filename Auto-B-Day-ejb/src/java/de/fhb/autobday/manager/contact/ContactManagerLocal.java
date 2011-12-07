package de.fhb.autobday.manager.contact;

import de.fhb.autobday.exception.contact.ContactException;
import javax.ejb.Local;

/**
 *
 * @author Andy Klay <klay@fh-brandenburg.de>
 * @author Michael Koppen <koppen@fh-brandenburg.de>
 */
@Local
public interface ContactManagerLocal {

	void setActive(String contactId, boolean active)throws ContactException ;
}
