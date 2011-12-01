package de.fhb.autobday.manager.group;

import java.lang.reflect.Field;
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
import de.fhb.autobday.exception.group.GroupException;

/**
*
* @author Andy Klay <klay@fh-brandenburg.de>
*/
@Stateless
public class GroupManager implements GroupManagerLocal {
	private final static Logger LOGGER = Logger.getLogger(GroupManager.class.getName());

	@EJB
	private AbdGroupFacade groupDAO;
	
	@EJB
	private AbdContactFacade contactDAO;


	@Override
	public AbdGroup getGroup(int groupId) {
		return groupDAO.find(groupId);
	}


	@Override
	public void setTemplate(int groupId, String template) {
		
		LOGGER.log(Level.INFO,"parameter:");
		LOGGER.log(Level.INFO,"groupid: " + groupId);
		LOGGER.log(Level.INFO,"template: " + template);
		
		
		AbdGroup actualGroup = groupDAO.find(groupId);
		
		actualGroup.setTemplate(template);
	}

	@Override
	public String getTemplate(int groupid) {
		
		LOGGER.log(Level.INFO,"parameter:");
		LOGGER.log(Level.INFO,"groupid: "+groupid);
		
		AbdGroup actualGroup = groupDAO.find(groupid);
		String output="dummy";
		
		output=actualGroup.getTemplate();
		
		return output;
	}

	@Override
	public String testTemplate(int groupId, String contactId) throws GroupException{
		
		LOGGER.log(Level.INFO,"parameter:");
		LOGGER.log(Level.INFO,"groupid: "+groupId);
		LOGGER.log(Level.INFO,"contactid: "+contactId);
		
		AbdGroup actualGroup = groupDAO.find(groupId);
		String template = actualGroup.getTemplate();
		String output="dummy";
		AbdContact chosenContact=contactDAO.find(contactId);
		
		if(chosenContact==null){
			LOGGER.log(Level.SEVERE, "Contact " + contactId + "not found!");
			throw new GroupException("Contact " + contactId + "not found!");
		}
		
		output=this.parseTemplate(template, chosenContact);
		
		return output;
	}

	@Override
	public void setActive(int groupId, boolean active) {
		
		LOGGER.log(Level.INFO,"parameter:");
		LOGGER.log(Level.INFO,"groupid: "+groupId);
		
		
		AbdGroup actualGroup = groupDAO.find(groupId);
		
		actualGroup.setActive(active);
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
	 */
	public String parseTemplate(String template, AbdContact contact){
		
		LOGGER.log(Level.INFO,"parameter:");
		LOGGER.log(Level.INFO,"template: "+template);
		LOGGER.log(Level.INFO,"contact: "+contact);		

		StringBuilder patternBuilder=new StringBuilder();		
		StringBuilder output = new StringBuilder();
		
		//create compile-pattern string
		for(Field field :AbdContact.class.getFields()){
			patternBuilder.append(field.getName());
			patternBuilder.append("|");
		}
		
		//pattern for slash-expression
		patternBuilder.append("[a-z]+/+[a-z]+");
		

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
			Pattern innerPattern = Pattern.compile(patternBuilder.toString());
			Matcher innerMatcher = innerPattern.matcher(innerGroup);

			if (innerMatcher.find()) {
				// found valid expression
				// fetch content
				String tagExpression = innerGroup.substring(innerMatcher.start(),innerMatcher.end());

				//evaluate of tag
				//TODO dynamischer machen
//				//search for identity
//				for(Field field :Contact.class.getFields()){
//					if(tagExpression.equals(field)){
//						//found attribute expression
//					}
//				}
				
				try{	

					if (tagExpression.equals(AbdContact.class.getFields()[0])) {
						System.out.println(AbdContact.class.getFields()[0]);
						output.append(contact.getId());
						
					} else if (tagExpression.equals(AbdContact.class.getFields()[1])) {
						System.out.println(AbdContact.class.getFields()[1]);
						output.append(contact.getName());
						
					} else if (tagExpression.equals(AbdContact.class.getFields()[2])) {
						System.out.println(AbdContact.class.getFields()[2]);
						output.append(contact.getFirstname());
						
					} else if (tagExpression.equals(AbdContact.class.getFields()[3])) {
						System.out.println(AbdContact.class.getFields()[3]);
						output.append(contact.getSex());
						
					} else if (tagExpression.equals(AbdContact.class.getFields()[4])) {
						System.out.println(AbdContact.class.getFields()[4]);
						output.append(contact.getMail());
						
					} else if (tagExpression.equals(AbdContact.class.getFields()[5])) {
						System.out.println(AbdContact.class.getFields()[5]);
						output.append(contact.getBday());
						
					} else if (tagExpression.contains("/")) {
						output.append(this.parseSlashExpression(tagExpression,'m'));	
					}				
				
				}catch (NullPointerException e) {
					LOGGER.log(Level.SEVERE, null, e);
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
	public String parseSlashExpression(String expression, char sex){
		
		LOGGER.log(Level.INFO,"parameter:");
		LOGGER.log(Level.INFO,"expression: "+expression);
		LOGGER.log(Level.INFO,"sex: "+sex);		
		
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

