package de.fhb.autobday.manager.group;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import de.fhb.autobday.dao.AbdContactFacade;
import de.fhb.autobday.dao.AbdGroupFacade;
import de.fhb.autobday.data.AbdContact;
import de.fhb.autobday.data.AbdGroup;
import de.fhb.autobday.exception.contact.ContactException;
import de.fhb.autobday.exception.contact.ContactNotFoundException;
import de.fhb.autobday.exception.contact.NoContactGivenException;
import de.fhb.autobday.exception.group.GroupException;
import de.fhb.autobday.exception.group.GroupNotFoundException;

/**
*
*
* @author Andy Klay <klay@fh-brandenburg.de>
* @author Michael Koppen <koppen@fh-brandenburg.de>
*/
@Stateless
public class GroupManager implements GroupManagerLocal {
	private final static Logger LOGGER = Logger.getLogger(GroupManager.class.getName());

	@EJB
	private AbdGroupFacade groupDAO;
	
	@EJB
	private AbdContactFacade contactDAO;


	@Override
	public AbdGroup getGroup(String groupId) throws GroupNotFoundException {
		
		LOGGER.log(Level.INFO,"parameter:");
		LOGGER.log(Level.INFO, "groupid: {0}", groupId);
		
		//find group
		AbdGroup actualGroup = groupDAO.find(groupId);
		
		if(actualGroup==null){
			//if group not found
			LOGGER.log(Level.SEVERE, "Group {0} not found!", groupId);
			throw new GroupNotFoundException("Group " + groupId + "not found!");
		}
		
		return actualGroup;
	}


	@Override
	public void setTemplate(String groupId, String template) throws GroupNotFoundException {
		
		LOGGER.log(Level.INFO,"parameter:");
		LOGGER.log(Level.INFO, "groupid: {0}", groupId);
		LOGGER.log(Level.INFO, "template: {1}", template);
		
		//find group
		AbdGroup actualGroup = groupDAO.find(groupId);
		
		if(actualGroup==null){
			//if group not found
			LOGGER.log(Level.SEVERE, "Group {0} not found!", groupId);
			throw new GroupNotFoundException("Group " + groupId + "not found!");
		}
		
		actualGroup.setTemplate(template);
	}

	@Override
	public String getTemplate(String groupId) throws GroupNotFoundException {
		
		LOGGER.log(Level.INFO,"parameter:");
		LOGGER.log(Level.INFO, "groupid: {0}", groupId);
		
		String output="dummy";
		
		//find group
		AbdGroup actualGroup = groupDAO.find(groupId);
		
		if(actualGroup==null){
			//if group not found
			LOGGER.log(Level.SEVERE, "Group {0} not found!", groupId);
			throw new GroupNotFoundException("Group " + groupId + "not found!");
		}
		
		output=actualGroup.getTemplate();
		
		return output;
	}

	@Override
	public String testTemplate(String groupId, String contactId) throws GroupException, ContactException{
		
		LOGGER.log(Level.INFO,"parameter:");
		LOGGER.log(Level.INFO, "groupid: {0}", groupId);
		LOGGER.log(Level.INFO, "contactid: {1}", contactId);
		
		String output="dummy";
				
		//find group
		AbdGroup actualGroup = groupDAO.find(groupId);
		
		if(actualGroup==null){
			//if group not found
			LOGGER.log(Level.SEVERE, "Group {0} not found!", groupId);
			throw new GroupNotFoundException("Group " + groupId + "not found!");
		}
		
		String template = actualGroup.getTemplate();
		
		//find contact for test
		AbdContact chosenContact=contactDAO.find(contactId);
		
		if(chosenContact==null){
			//if contact not found
			LOGGER.log(Level.SEVERE, "Contact {1} not found!", contactId);
			throw new ContactNotFoundException("Contact " + contactId + "not found!");
		}
		
		output=this.parseTemplate(template, chosenContact);
		
		return output;
	}

	@Override
	public void setActive(String groupId, boolean active) throws GroupNotFoundException {
		
		LOGGER.log(Level.INFO,"parameter:");
		LOGGER.log(Level.INFO, "groupid: {0}", groupId);
		
		//find group
		AbdGroup actualGroup = groupDAO.find(groupId);
		
		if(actualGroup==null){
			//if group not found
			LOGGER.log(Level.SEVERE, "Group {0} not found!", groupId);
			throw new GroupNotFoundException("Group " + groupId + "not found!");
		}
		
		actualGroup.setActive(active);
		
		//save into DB
		groupDAO.edit(actualGroup);
	}
	
	
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
	 * @throws NoContactGivenException 
	 */
	@Override
	public String parseTemplate(String template, AbdContact contact) throws NoContactGivenException{
		
		LOGGER.log(Level.INFO,"parameter:");
		LOGGER.log(Level.INFO, "template: {0}", template);
		LOGGER.log(Level.INFO, "contact: {1}", contact);

		String patternString="";		
		StringBuilder output = new StringBuilder();
		
		//pattern for slash-expression
		patternString="[a-z]+/+[a-z]+|[a-z]+";
		
		if(contact==null){
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
			//merke das ende fuer den Anfang des naechsten zwischen Textes
			lastend = matcher.end();

			//analyze content of clamp
			String innerGroup = matcher.group();
			Pattern innerPattern = Pattern.compile(patternString);
			Matcher innerMatcher = innerPattern.matcher(innerGroup);

			if (innerMatcher.find()) {
				
				// found valid expression
				// fetch content
				String tagExpression = innerGroup.substring(innerMatcher.start(),innerMatcher.end());
				
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
					
					output.append(this.parseSlashExpression(tagExpression,'m'));	
				
				}				
			}
		}		
		
		//append textend
		output.append(template.substring(lastend,template.length()));
		
		return output.toString();
	}
	
	

	/**
	 * parses strings wtih gender specific the contents separated by a slash, depending on gender.
	 * according to this model female/male e.g. e/er
	 * 
	 * @param String expression
	 * @param Char sex
	 * @return String decesionOfOne
	 */
	@Override
	public String parseSlashExpression(String expression, char sex){
		
		LOGGER.log(Level.INFO,"parameter:");
		LOGGER.log(Level.INFO, "expression: {0}", expression);
		LOGGER.log(Level.INFO, "sex: {1}", sex);
		
		Pattern numberPattern = Pattern.compile("/");
		Matcher numberMatcher = numberPattern.matcher(expression);
		String contentLeft ="";
		String contentRight ="";
		
		//search slash
		if (numberMatcher.find()) {
			contentLeft=expression.substring(0, numberMatcher.start());
			contentRight=expression.substring(numberMatcher.end(), expression.length());
		}

		if(sex=='w'){
			return contentLeft;
		}else{
			return contentRight;
		}
	}
}

