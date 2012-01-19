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
 * {@inheritDoc}
 *
 * @author Andy Klay <klay@fh-brandenburg.de> Michael Koppen
 * <koppen@fh-brandenburg.de>
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

		//find group
		AbdGroup actualGroup = groupDAO.find(groupId);

		//TODO beim setten umlaute rausfiltern


		if (actualGroup == null) {
			//if group not found
			LOGGER.log(Level.SEVERE, "Group {0} not found!", groupId);
			throw new GroupNotFoundException("Group " + groupId + "not found!");
		}

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

		//find group
		AbdGroup actualGroup = groupDAO.find(groupId);

		//TODO beim setten umlaute rausfiltern.

		if (actualGroup == null) {
			//if group not found
			LOGGER.log(Level.SEVERE, "Group {0} not found!", groupId);
			throw new GroupNotFoundException("Group " + groupId + "not found!");
		}

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

		//find group
		AbdGroup actualGroup = groupDAO.find(groupId);

		if (actualGroup == null) {
			//if group not found
			LOGGER.log(Level.SEVERE, "Group {0} not found!", groupId);
			throw new GroupNotFoundException("Group " + groupId + "not found!");
		}

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

		//find group
		AbdGroup actualGroup = groupDAO.find(groupId);

		if (actualGroup == null) {
			//if group not found
			LOGGER.log(Level.SEVERE, "Group {0} not found!", groupId);
			throw new GroupNotFoundException("Group " + groupId + "not found!");
		}

		String template = actualGroup.getTemplate();

		//find contact for test
		AbdContact chosenContact = contactDAO.find(contactId);

		if (chosenContact == null) {
			//if contact not found
			LOGGER.log(Level.SEVERE, "Contact {0} not found!", contactId);
			throw new ContactNotFoundException("Contact " + contactId + "not found!");
		}

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

		//find group
		AbdGroup actualGroup = groupDAO.find(groupId);

		if (actualGroup == null) {
			//if group not found
			LOGGER.log(Level.SEVERE, "Group {0} not found!", groupId);
			throw new GroupNotFoundException("Group " + groupId + "not found!");
		}

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

		//filter umlauts	
		template = filterUmlauts(template);

		//create pattern for identifing of clamp-expresions
		Pattern pattern = Pattern.compile("\\$\\{\\S+\\}");
		Matcher matcher = pattern.matcher(template);
		int lastend = 0;

		//find clamp expression
		while (matcher.find()) {

			//appending of text between expresions
			output.append(template.substring(lastend, matcher.start()));
			//merke das ende fuer den Anfang des naechsten zwischen Textes
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
				if (tagExpression.equals("id")) {

					output.append(contact.getId());

				} else if (tagExpression.equals("name")) {

					output.append(contact.getName());

				} else if (tagExpression.equals("firstname")) {

					output.append(contact.getFirstname());

				} else if (tagExpression.equals("sex")) {

					output.append(contact.getSex());

				} else if (tagExpression.equals("mail")) {

					output.append(contact.getMail());

				} else if (tagExpression.equals("bday")) {

					output.append(contact.getBday());

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
	 * {@inheritDoc}
	 *
	 * @param expression
	 * @param sex
	 * @return
	 * @see
	 * de.fhb.autobday.manager.group.GroupManagerLocal#parseSlashExpression(java.lang.String,
	 * char)
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

		AbdGroup group = null;
		List<AbdContact> outputCollection = new ArrayList<AbdContact>();

		//find object, verify input
		group = groupDAO.find(groupId);

		if (group == null) {
			LOGGER.log(Level.SEVERE, "Group does not exist!");
			throw new GroupNotFoundException("Group does not exist!");
		}
		for (AbdGroupToContact actualGroupToContact : group.getAbdGroupToContactCollection()) {
			outputCollection.add(actualGroupToContact.getAbdContact());
		}

		return outputCollection;
	}

	/**
	 * filters umlauts
	 *
	 * @param template
	 * @return
	 */
	//TODO Entfernen
	@Deprecated
	protected String filterUmlauts(String template) {

		StringBuilder output = new StringBuilder();

		//create pattern for identifing of clamp-expresions
		Pattern pattern = Pattern.compile("ä|ö|ü|Ä|Ö|Ü");
		Matcher matcher = pattern.matcher(template);
		int lastend = 0;

		//find clamp expression
		while (matcher.find()) {

			//appending of text between expresions
			output.append(template.substring(lastend, matcher.start()));
			//merke das ende fuer den Anfang des naechsten zwischen Textes
			lastend = matcher.end();

			// fetch content
			String tagExpression = template.substring(matcher.start(), matcher.end());

			//evaluation of the tag
			if (tagExpression.equals("ä")) {

				output.append("ae");

			} else if (tagExpression.equals("ö")) {

				output.append("oe");

			} else if (tagExpression.equals("ü")) {

				output.append("ue");

			} else if (tagExpression.equals("Ä")) {

				output.append("Ae");

			} else if (tagExpression.equals("Ö")) {

				output.append("Oe");

			} else if (tagExpression.equals("Ü")) {

				output.append("Ue");

			}
		}

		//append textend
		output.append(template.substring(lastend, template.length()));

		return output.toString();

	}
}