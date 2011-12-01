package de.fhb.autobday.manager.group;

import de.fhb.autobday.data.AbdContact;
import de.fhb.autobday.data.AbdGroup;
import de.fhb.autobday.exception.group.GroupException;

import javax.ejb.Local;

/**
 *
 * @author Michael Koppen, Andy Klay <klay@fh-brandenburg.de>
 */
@Local
public interface GroupManagerLocal {

	/**
	 *  get a group with specific id
	 * 
	 * @param int - groupid
	 * @return AbdGroup
	 */
	AbdGroup getGroup(int groupid);

	/**
	 * set the template of a group with specific id
	 * 
	 * @param int - groupid
	 * @param template
	 */
	void setTemplate(int groupid, String template);

	/**
	 * get the template  of a group with specific id
	 * 
	 * @param int - groupid
	 * @return String
	 */
	String getTemplate(int groupid);

	/**
	 *test template of group with a contact 
	 * 
	 * @param int - groupid
	 * @param String - contactid
	 * @return String - message
	 * @throws GroupException
	 */
	String testTemplate(int groupid, String contactid)throws GroupException;

	/**
	 * set group to Active stat for sending mails
	 * 
	 * @param int - groupid
	 * @param boolean - active
	 */
	void setActive(int groupid, boolean active);
	
	String parseTemplate(String template, AbdContact contact);
	
	String parseSlashExpression(String expression, char sex);
}