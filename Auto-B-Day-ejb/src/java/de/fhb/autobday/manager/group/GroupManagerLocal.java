package de.fhb.autobday.manager.group;

import de.fhb.autobday.data.AbdContact;
import de.fhb.autobday.data.AbdGroup;
import de.fhb.autobday.exception.contact.ContactException;
import de.fhb.autobday.exception.group.GroupException;
import java.util.List;
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
	AbdGroup getGroup(String groupid) throws GroupException;

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
	 * @return
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
	 * todo comment
	 * @param groupInputObject
	 * @return
	 * @throws Exception 
	 */
	List<AbdContact> getAllContactsFromGroup(AbdGroup groupInputObject) throws Exception;
}