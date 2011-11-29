package de.fhb.autobday.manager.group;

import de.fhb.autobday.dao.AbdgroupFacade;
import de.fhb.autobday.data.Abdgroup;
import de.fhb.autobday.data.Contact;

import java.lang.reflect.Field;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Michael Koppen
 */
@Stateless
public class GroupManager implements GroupManagerLocal {
	private final static Logger LOGGER = Logger.getLogger(GroupManager.class.getName());

	@EJB
	private AbdgroupFacade groupDAO;

	

	@Override
	public Abdgroup getGroup(int groupid) {
		return groupDAO.find(groupid);
	}

	@Override
	public void setTemplate() {
	}

	@Override
	public void getTemplate() {
	}

	@Override
	public void testTemplate() {
	}

	@Override
	public void setActive() {
	}
	
	
	/**
	 *  parst templates mit den Format zeichen ${Ausdruck}
	 *  moegliche Ausdruecke sind:
	 *  Attribute von Contacts wie z.b.
	 *  
	 *  id
	 *  name
	 *  firstname
	 *  sex
	 *  mail
	 *  bday
	 *  
	 *  
	 *  oder
	 *  
	 *  Geschlechtspezifische Inhalte wie z.b e/er
	 *  
	 *  
	 * @param template
	 * @param contact
	 * @return
	 */
	public String parseTemplate(String template, Contact contact){

		StringBuilder patternBuilder=new StringBuilder();		
		StringBuilder output = new StringBuilder();
		
		//create compile-pattern string
		for(Field field :Contact.class.getFields()){
			patternBuilder.append(field.getName());
			patternBuilder.append("|");
		}
		
		//pattern for slashexpression
		patternBuilder.append("[a-z]+/+[a-z]+");
		

		//Pattern fuer die Klammern erstellen
		Pattern pattern = Pattern.compile("\\$\\{\\S+\\}");
		Matcher matcher = pattern.matcher(template);
		int lastend = 0;
		
		// klammerausdruck finden
		while (matcher.find()) {
			
			//anhaengen des Text zwischen den ausdruecken
			output.append(template.substring(lastend, matcher.start()));
			//merke das ende fuer den Anfang des naechsten zwischen Textes
			lastend = matcher.end();

			// Inhalt des Klammerausdrucks auswerten
			String innerGroup = matcher.group();
			Pattern innerPattern = Pattern.compile(patternBuilder.toString());
			Matcher innerMatcher = innerPattern.matcher(innerGroup);

			if (innerMatcher.find()) {
				// gueltigen Ausdruck gefunden
				// hole inhalte
				String tagExpression = innerGroup.substring(innerMatcher.start(),innerMatcher.end());

				//auswertung des Tags
				//TODO dynamischer machen
//				//search for identity
//				for(Field field :Contact.class.getFields()){
//					if(tagExpression.equals(field)){
//						//found attribute expression
//					}
//				}
				
				try{	

					if (tagExpression.equals(Contact.class.getFields()[0])) {
						System.out.println(Contact.class.getFields()[0]);
						output.append(contact.getId());
						
					} else if (tagExpression.equals(Contact.class.getFields()[1])) {
						System.out.println(Contact.class.getFields()[1]);
						output.append(contact.getName());
						
					} else if (tagExpression.equals(Contact.class.getFields()[2])) {
						System.out.println(Contact.class.getFields()[2]);
						output.append(contact.getFirstname());
						
					} else if (tagExpression.equals(Contact.class.getFields()[3])) {
						System.out.println(Contact.class.getFields()[3]);
						output.append(contact.getSex());
						
					} else if (tagExpression.equals(Contact.class.getFields()[4])) {
						System.out.println(Contact.class.getFields()[4]);
						output.append(contact.getMail());
						
					} else if (tagExpression.equals(Contact.class.getFields()[5])) {
						System.out.println(Contact.class.getFields()[5]);
						output.append(contact.getBday());
						
					} else if (tagExpression.contains("/")) {
						output.append(this.parseSlashExpression(tagExpression,'m'));	
					}				
				
				}catch (NullPointerException e) {
					// TODO: handle exception
				}
			}
		}		
		
		//Textende anhaengen
		output.append(template.substring(lastend,template.length()));
		
		return output.toString();
	}
	
	

	/**
	 * parst Strings,
	 * die geschelchtsspezifische Inhalte mit einem Slash trennen,je nach Geschlecht.
	 * nach diesem Muster weiblich/maennlich z.B. e/er
	 * 
	 * @param String expression
	 * @param Char sex
	 * @return String
	 */
	public String parseSlashExpression(String expression, char sex){
		
		Pattern numberPattern = Pattern.compile("/");
		Matcher numberMatcher = numberPattern.matcher(expression);
		String contentLeft ="";
		String contentRight ="";
		
		//suche Slash
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
