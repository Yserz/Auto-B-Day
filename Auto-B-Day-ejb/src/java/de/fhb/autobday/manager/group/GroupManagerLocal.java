package de.fhb.autobday.manager.group;

import de.fhb.autobday.data.AbdContact;
import de.fhb.autobday.data.AbdGroup;
import de.fhb.autobday.exception.contact.ContactNotFoundException;
import de.fhb.autobday.exception.group.GroupException;
import javax.ejb.Local;

/**
 *
 * @author Andy Klay <klay@fh-brandenburg.de>
 * @author Michael Koppen <koppen@fh-brandenburg.de>
 */
@Local
public interface GroupManagerLocal {

	/**
	 *  get a group with specific id
	 * 
	 * @param int - groupid
	 * @return AbdGroup
	 */
	AbdGroup getGroup(String groupid);

	/**
	 * set the template of a group with specific id
	 * 
	 * @param int - groupid
	 * @param template
	 */
	void setTemplate(String groupid, String template);

	/**
	 * get the template  of a group with specific id
	 * 
	 * @param int - groupid
	 * @return String
	 */
	String getTemplate(String groupid);

	/**
	 *test template of group with a contact 
	 * 
	 * @param int - groupid
	 * @param String - contactid
	 * @return String - message
	 * @throws GroupException
	 */
	String testTemplate(String groupid, String contactid) throws GroupException, ContactNotFoundException;

	/**
	 * set group to Active stat for sending mails
	 * 
	 * @param int - groupid
	 * @param boolean - active
	 */
	void setActive(String groupid, boolean active);
	
	String parseTemplate(String template, AbdContact contact);
	
	String parseSlashExpression(String expression, char sex);
}