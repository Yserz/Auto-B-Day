package de.fhb.autobday.manager.group;

import java.util.List;

import javax.ejb.Local;

import de.fhb.autobday.data.AbdContact;
import de.fhb.autobday.data.AbdGroup;
import de.fhb.autobday.exception.contact.ContactException;
import de.fhb.autobday.exception.group.GroupException;
import de.fhb.autobday.exception.group.GroupNotFoundException;
import de.fhb.autobday.exception.group.NoGroupGivenException;

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
	AbdGroup getGroup(String groupid) throws GroupException;

	/**
	 * set the template of a group with specific id
	 * 
	 * @param AbdGroup - group
	 * @param template
	 */
	void setTemplate(AbdGroup group, String template) throws GroupException;
	
	/**
	 * set the template of a group with specific id
	 * 
	 * @param int - groupid
	 * @param template
	 */
	void setTemplate(String groupId, String template) throws GroupException ;

	/**
	 * get the template  of a group with specific id
	 * 
	 * @param int - groupid
	 * @return String
	 */
	String getTemplate(String groupid) throws GroupException;

	/**
	 *test template of group with a contact 
	 * 
	 * @param int - groupid
	 * @param String - contactid
	 * @return String - message
	 * @throws GroupException
	 */
	String testTemplate(String groupid, String contactid) throws GroupException, ContactException;

	/**
	 * set group to Active stat for sending mails
	 * 
	 * @param int - groupid
	 * @param boolean - active
	 */
	void setActive(AbdGroup group, boolean active) throws GroupNotFoundException;
	
	/**
	 * set group to Active stat for sending mails
	 * 
	 * @param int - groupid
	 * @param boolean - active
	 */
	void setActive(String groupid, boolean active) throws GroupException;
	
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
	String parseTemplate(String template, AbdContact contact) throws ContactException;
	
	/**
	 * parses strings wtih gender specific the contents separated by a slash, depending on gender.
	 * according to this model female/male e.g. e/er
	 * 
	 * @param String expression
	 * @param Char sex
	 * @return String decesionOfOne
	 */
	String parseSlashExpression(String expression, char sex);
	
	/**
	 * Get all Contacts of a group
	 * 
	 * @param group
	 * @return List<AbdContact> 
	 * @throws GroupNotFoundException
	 */
	List<AbdContact> getAllContactsFromGroup(String groupId) throws GroupNotFoundException;
	
	/**
	 * Get all Contacts of a group
	 * 
	 * @param groupId
	 * @return List<AbdContact> 
	 * @throws GroupNotFoundException 
	 */
	List<AbdContact> getAllContactsFromGroup(AbdGroup group) throws NoGroupGivenException, GroupNotFoundException;
}