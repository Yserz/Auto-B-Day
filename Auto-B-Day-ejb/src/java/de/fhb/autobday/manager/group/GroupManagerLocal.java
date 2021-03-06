package de.fhb.autobday.manager.group;

import de.fhb.autobday.data.AbdContact;
import de.fhb.autobday.data.AbdGroup;
import de.fhb.autobday.exception.contact.ContactNotFoundException;
import de.fhb.autobday.exception.contact.NoContactGivenException;
import de.fhb.autobday.exception.group.GroupNotFoundException;
import de.fhb.autobday.exception.group.NoGroupGivenException;
import java.util.List;
import javax.ejb.Local;

/**
 * The GroupManager processes all group specific things.
 *
 * @author Andy Klay mail: klay@fh-brandenburg.de
 * @author Michael Koppen mail: koppen@fh-brandenburg.de
 *
 */
@Local
public interface GroupManagerLocal {

	/**
	 * get a group with specific id
	 *
	 * @param groupid
	 * @return AbdGroup
	 * @throws GroupNotFoundException
	 */
	AbdGroup getGroup(String groupid)
			throws GroupNotFoundException;

	/**
	 * set the template of a group with specific id
	 *
	 * @param group
	 * @param template
	 * @throws GroupNotFoundException
	 */
	void setTemplate(AbdGroup group, String template)
			throws GroupNotFoundException;

	/**
	 * set the template of a group with specific id
	 *
	 * @param groupId
	 * @param template
	 * @throws GroupNotFoundException
	 */
	void setTemplate(String groupId, String template)
			throws GroupNotFoundException;

	/**
	 * get the template of a group with specific id
	 *
	 * @param groupid
	 * @return String
	 * @throws GroupNotFoundException
	 */
	String getTemplate(String groupid)
			throws GroupNotFoundException;

	/**
	 * test template of group with a contact
	 *
	 * @param groupid
	 * @param contactid
	 * @return String - message
	 * @throws GroupNotFoundException
	 * @throws ContactNotFoundException
	 * @throws NoContactGivenException
	 */
	String testTemplate(String groupid, String contactid)
			throws GroupNotFoundException, ContactNotFoundException, NoContactGivenException;

	/**
	 * set group to Active stat for sending mails
	 *
	 * @param group
	 * @param active
	 * @throws GroupNotFoundException
	 */
	void setActive(AbdGroup group, boolean active)
			throws GroupNotFoundException;

	/**
	 * set group to Active stat for sending mails
	 *
	 * @param groupid
	 * @param active
	 * @throws GroupNotFoundException
	 */
	void setActive(String groupid, boolean active)
			throws GroupNotFoundException;

	/**
	 * parses templates with the character format ${validExpression} possible
	 * Expresions are: attribute of Contacts for e.g.
	 *
	 * - id - name - firstname - sex - mail - bday
	 *
	 *
	 * or
	 *
	 * gender specific content expressions e.g. e/er
	 *
	 * @param template
	 * @param contact
	 * @return String
	 * @throws NoContactGivenException
	 */
	String parseTemplate(String template, AbdContact contact)
			throws NoContactGivenException;

	/**
	 * Get all Contacts of a group
	 *
	 * @param groupId
	 * @return List<AbdContact>
	 * @throws GroupNotFoundException
	 */
	List<AbdContact> getAllContactsFromGroup(String groupId)
			throws GroupNotFoundException;

	/**
	 * Get all Contacts of a group
	 *
	 * @param group
	 * @return List<AbdContact>
	 * @throws NoGroupGivenException
	 * @throws GroupNotFoundException
	 */
	List<AbdContact> getAllContactsFromGroup(AbdGroup group)
			throws NoGroupGivenException, GroupNotFoundException;
}