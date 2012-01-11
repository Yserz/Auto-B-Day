package de.fhb.autobday.manager.group;

import de.fhb.autobday.data.AbdContact;
import de.fhb.autobday.data.AbdGroup;
import de.fhb.autobday.exception.contact.ContactNotFoundException;
import de.fhb.autobday.exception.contact.NoContactGivenException;
import de.fhb.autobday.exception.group.GroupException;
import de.fhb.autobday.exception.group.GroupNotFoundException;
import de.fhb.autobday.exception.group.NoGroupGivenException;
import java.util.List;
import javax.ejb.Local;

/**
 * The GroupManager processes all group specific things.
 *
 * @author
 * Andy Klay <klay@fh-brandenburg.de>
 * Michael Koppen <koppen@fh-brandenburg.de>
 * 
 */
@Local
public interface GroupManagerLocal {

	/**
	 *  get a group with specific id
	 * 
	 * @param int - groupid
	 * @return AbdGroup
	 */
	AbdGroup getGroup(String groupid) 
			throws GroupNotFoundException;

	/**
	 * set the template of a group with specific id
	 * 
	 * @param AbdGroup - group
	 * @param template
	 */
	void setTemplate(AbdGroup group, String template) 
			throws GroupNotFoundException;
	
	/**
	 * set the template of a group with specific id
	 * 
	 * @param int - groupid
	 * @param template
	 */
	void setTemplate(String groupId, String template) 
			throws GroupNotFoundException ;

	/**
	 * get the template  of a group with specific id
	 * 
	 * @param int - groupid
	 * @return String
	 */
	String getTemplate(String groupid) 
			throws GroupNotFoundException;

	/**
	 *test template of group with a contact 
	 * 
	 * @param int - groupid
	 * @param String - contactid
	 * @return String - message
	 * @throws GroupException
	 */
	String testTemplate(String groupid, String contactid) 
			throws GroupNotFoundException, ContactNotFoundException, NoContactGivenException;

	/**
	 * set group to Active stat for sending mails
	 * 
	 * @param int - groupid
	 * @param boolean - active
	 */
	void setActive(AbdGroup group, boolean active) 
			throws GroupNotFoundException;
	
	/**
	 * set group to Active stat for sending mails
	 * 
	 * @param int - groupid
	 * @param boolean - active
	 */
	void setActive(String groupid, boolean active) 
			throws GroupNotFoundException;
	
	/**
	 *  parses templates with the character format ${validExpression}
	 *  possible Expresions are:
	 *  attribute of Contacts for e.g.
	 *  
	 *  - id
	 *  - name
	 *  - firstname
	 *  - sex
	 *  - mail
	 *  - bday
	 *  
	 *  
	 *  or
	 *  
	 *  gender specific content expressions
	 *  e.g. e/er
	 *  
	 * @param template
	 * @param contact
	 * @return String
	 */
	String parseTemplate(String template, AbdContact contact) 
			throws NoContactGivenException;
	
	/**
	 * Get all Contacts of a group
	 * 
	 * @param group
	 * @return List<AbdContact> 
	 * @throws GroupNotFoundException
	 */
	List<AbdContact> getAllContactsFromGroup(String groupId) 
			throws GroupNotFoundException;
	
	/**
	 * Get all Contacts of a group
	 * 
	 * @param groupId
	 * @return List<AbdContact> 
	 * @throws GroupNotFoundException 
	 */
	List<AbdContact> getAllContactsFromGroup(AbdGroup group) 
			throws NoGroupGivenException, GroupNotFoundException;
}