package de.fhb.autobday.manager.group;

import de.fhb.autobday.dao.AbdContactFacade;
import de.fhb.autobday.dao.AbdGroupFacade;
import de.fhb.autobday.data.AbdContact;
import de.fhb.autobday.data.AbdGroup;
import de.fhb.autobday.data.AbdGroupToContact;
import de.fhb.autobday.exception.contact.ContactNotFoundException;
import de.fhb.autobday.exception.contact.NoContactGivenException;
import de.fhb.autobday.exception.group.GroupNotFoundException;
import de.fhb.autobday.exception.group.NoGroupGivenException;
import de.fhb.autobday.manager.LoggerInterceptor;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

/**
 *
 * Implementation of GroupManager.
 *
 * @author Andy Klay mail: klay@fh-brandenburg.de
 * @author Michael Koppen mail: koppen@fh-brandenburg.de
 *
 */
@Stateless
@Local
@Interceptors(LoggerInterceptor.class)
public class GroupManager implements GroupManagerLocal {

	private final static Logger LOGGER = Logger.getLogger(GroupManager.class.getName());
	@EJB
	private AbdGroupFacade groupDAO;
	@EJB
	private AbdContactFacade contactDAO;

	/**
	 * {@inheritDoc}
	 *
	 * @param groupId
	 * @throws GroupNotFoundException
	 * @see
	 * de.fhb.autobday.manager.group.GroupManagerLocal#getGroup(java.lang.String)
	 */
	@Override
	public AbdGroup getGroup(String groupId)
			throws GroupNotFoundException {

		//lookup for group
		AbdGroup actualGroup = findGroup(groupId);

		return actualGroup;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param group
	 * @param template
	 * @throws GroupNotFoundException
	 * @see
	 * de.fhb.autobday.manager.group.GroupManagerLocal#setTemplate(de.fhb.autobday.data.AbdGroup,
	 * java.lang.String)
	 */
	@Override
	public void setTemplate(AbdGroup group, String template)
			throws GroupNotFoundException {
		setTemplate(group.getId(), template);
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param groupId
	 * @param template
	 * @throws GroupNotFoundException
	 * @see
	 * de.fhb.autobday.manager.group.GroupManagerLocal#setTemplate(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public void setTemplate(String groupId, String template)
			throws GroupNotFoundException {

		//lookup for group
		AbdGroup actualGroup = findGroup(groupId);

		actualGroup.setTemplate(template);
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param groupId
	 * @throws GroupNotFoundException
	 * @see
	 * de.fhb.autobday.manager.group.GroupManagerLocal#getTemplate(java.lang.String)
	 */
	@Override
	public String getTemplate(String groupId)
			throws GroupNotFoundException {

		String output = "dummy";

		//lookup for group
		AbdGroup actualGroup = findGroup(groupId);

		output = actualGroup.getTemplate();

		return output;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param groupId
	 * @param contactId
	 * @throws GroupNotFoundException
	 * @throws ContactNotFoundException
	 * @throws NoContactGivenException
	 * @see
	 * de.fhb.autobday.manager.group.GroupManagerLocal#testTemplate(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public String testTemplate(String groupId, String contactId)
			throws GroupNotFoundException, ContactNotFoundException, NoContactGivenException {

		String output = "dummy";

		//lookup for group
		AbdGroup actualGroup = findGroup(groupId);

		String template = actualGroup.getTemplate();

		//lookup for contact
		AbdContact chosenContact = findContact(contactId);

		output = this.parseTemplate(template, chosenContact);

		return output;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param group
	 * @param active
	 * @throws GroupNotFoundException
	 * @see
	 * de.fhb.autobday.manager.group.GroupManagerLocal#setActive(de.fhb.autobday.data.AbdGroup,
	 * boolean)
	 */
	@Override
	public void setActive(AbdGroup group, boolean active)
			throws GroupNotFoundException {
		setActive(group.getId(), active);
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param groupId
	 * @param active
	 * @throws GroupNotFoundException
	 * @see
	 * de.fhb.autobday.manager.group.GroupManagerLocal#setActive(java.lang.String,
	 * boolean)
	 */
	@Override
	public void setActive(String groupId, boolean active)
			throws GroupNotFoundException {

		//lookup for group
		AbdGroup actualGroup = findGroup(groupId);

		actualGroup.setActive(active);

		//save into DB
		groupDAO.edit(actualGroup);
	}

	/**
	 * {@inheritDoc}
	 *
	 * @throws NoContactGivenException
	 * @see
	 * de.fhb.autobday.manager.group.GroupManagerLocal#parseTemplate(java.lang.String,
	 * de.fhb.autobday.data.AbdContact)
	 */
	@Override
	public String parseTemplate(String template, AbdContact contact)
			throws NoContactGivenException {

		String patternString = "";
		StringBuilder output = new StringBuilder();

		//pattern for slash-expression
		patternString = "[a-z]+/+[a-z]+|[a-z]+";

		if (contact == null) {
			//if contact not found
			LOGGER.log(Level.SEVERE, "No Contact given");
			throw new NoContactGivenException("No Contact given");
		}

		//create pattern for identifing of clamp-expresions
		Pattern pattern = Pattern.compile("\\$\\{\\S+\\}");
		Matcher matcher = pattern.matcher(template);
		int lastend = 0;

		//find clamp expression
		while (matcher.find()) {

			//appending of text between expresions
			output.append(template.substring(lastend, matcher.start()));
			
			//save the end of this for the next start of the text between
			lastend = matcher.end();

			//analyze content of clamp
			String innerGroup = matcher.group();
			Pattern innerPattern = Pattern.compile(patternString);
			Matcher innerMatcher = innerPattern.matcher(innerGroup);

			if (innerMatcher.find()) {

				// found valid expression
				// fetch content
				String tagExpression = innerGroup.substring(innerMatcher.start(), innerMatcher.end());

				//evaluation of the tag
				if (tagExpression.equals("name")) {

					output.append(contact.getName());

				} else if (tagExpression.equals("firstname")) {

					output.append(contact.getFirstname());

				} else if (tagExpression.equals("mail")) {

					output.append(contact.getMail());

				} else if (tagExpression.equals("bday")) {

					output.append(contact.getBday());

				} else if (tagExpression.equals("age")) {

					output.append(calcAge(contact.getBday()));
					
				} else if (tagExpression.contains("/")) {

					output.append(this.parseSlashExpression(tagExpression, 'm'));

				}
			}
		}

		//append textend
		output.append(template.substring(lastend, template.length()));

		return output.toString();
	}

	/**
	 * TODO beschreibung
	 *
	 * @param expression
	 * @param sex
	 * @return left or right input
	 */
	protected String parseSlashExpression(String expression, char sex) {

		Pattern numberPattern = Pattern.compile("/");
		Matcher numberMatcher = numberPattern.matcher(expression);
		String contentLeft = "";
		String contentRight = "";

		//search slash
		if (numberMatcher.find()) {
			contentLeft = expression.substring(0, numberMatcher.start());
			contentRight = expression.substring(numberMatcher.end(), expression.length());
		}

		if (sex == 'w') {
			return contentLeft;
		} else {
			return contentRight;
		}
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param group
	 * @throws NoGroupGivenException
	 * @see
	 * de.fhb.autobday.manager.group.GroupManagerLocal#getAllContactsFromGroup(de.fhb.autobday.data.AbdGroup)
	 */
	@Override
	public List<AbdContact> getAllContactsFromGroup(AbdGroup group)
			throws NoGroupGivenException, GroupNotFoundException {

		if (group == null) {
			LOGGER.log(Level.SEVERE, "No group given!");
			throw new NoGroupGivenException("No group given!");
		}

		return getAllContactsFromGroup(group.getId());
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param groupId
	 * @see
	 * de.fhb.autobday.manager.group.GroupManagerLocal#getAllContactsFromGroup(java.lang.String)
	 */
	@Override
	public List<AbdContact> getAllContactsFromGroup(String groupId)
			throws GroupNotFoundException {

		AbdGroup group;
		List<AbdContact> outputCollection = new ArrayList<AbdContact>();

		//lookup for group
		group = findGroup(groupId);
		
		for (AbdGroupToContact actualGroupToContact : group.getAbdGroupToContactCollection()) {
			outputCollection.add(actualGroupToContact.getAbdContact());
		}

		return outputCollection;
	}

	/**
	 *  calc the age of a contact
	 * @param birthday
	 * @return age of contact
	 */
	protected int calcAge(Date birthday) {

		int age;
		Date currentDate = new Date(System.currentTimeMillis());
		Calendar currentDateCal = Calendar.getInstance();
		Calendar bdayDateCal = Calendar.getInstance();
		
		bdayDateCal.setTime(birthday);
		currentDateCal.setTime(currentDate);
		
		if(bdayDateCal.get(Calendar.MONTH)<=currentDateCal.get(Calendar.MONTH)
				&& bdayDateCal.get(Calendar.DAY_OF_MONTH)<=currentDateCal.get(Calendar.DAY_OF_MONTH)){
			age=currentDateCal.get(Calendar.YEAR)-bdayDateCal.get(Calendar.YEAR);
		}else{
			age=currentDateCal.get(Calendar.YEAR)-bdayDateCal.get(Calendar.YEAR)-1;
		}

		return age;
	}
	/**
	 * Method to lookup for a group.
	 * if no group exists exception is thrown.
	 * 
	 * @param groupId group to find
	 * @return found group
	 * @throws GroupNotFoundException 
	 */
	protected AbdGroup findGroup(String groupId) throws GroupNotFoundException{
		AbdGroup group;
		
		//find group
		group = groupDAO.find(groupId);

		if (group == null) {
			//if group not found
			LOGGER.log(Level.SEVERE, "Group {0} not found!", groupId);
			throw new GroupNotFoundException("Group " + groupId + " not found!");
		}
		return group;
	}
	/**
	 * Method to lookup for a contact.
	 * if no contact exists exception is thrown.
	 * 
	 * @param contactId contact to find
	 * @return found contact
	 * @throws ContactNotFoundException 
	 */
	protected AbdContact findContact(String contactId) throws ContactNotFoundException{
		AbdContact contact;
		
		contact = contactDAO.find(contactId);

		if (contact == null) {
			//if contact not found
			LOGGER.log(Level.SEVERE, "Contact {0} not found!", contactId);
			throw new ContactNotFoundException("Contact " + contactId + " not found!");
		}
		return contact;
	}
}