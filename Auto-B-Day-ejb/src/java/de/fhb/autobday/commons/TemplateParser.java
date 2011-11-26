package de.fhb.autobday.commons;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.fhb.autobday.data.Contact;

/**
 * TemplateParser parst Templates fuer autobday messages
 * In einem vorgefertigten Template muessen Ausdruecke diese Form haben:
 * ${AUSDRUCK}
 * 
 * Beispiel-Template:
 * 
 * "Lieb${e/er} ${NAME}, ich wuensche dir alles gute zum Geburtstag!";
 * 
 * @author Andy Klay (klay@fh-brandenburg.de)
 *
 */
public class TemplateParser {

	public static final String TAG_NAME = "NAME";
	public static final String TAG_COMPLETENAME = "COMPLETENAME";
	public static final String TAG_ANSPRACHE = "ANSPRACHE";
	public static final String TAG_GLUECKWUNSCH = "GLUECKWUNSCH";
	public static final String TAG_ABSCHIEDSFLOSKEL = "ABSCHIED";
//	public static final String TAG_ABSENDER = "ABSENDER";

	
	/**
	 * Konstruktor
	 * @param absenderName
	 * @param addressatenName
	 */
	public TemplateParser(){}
	
	/**
	 *  parst templates mit den Format zeichen ${Ausdruck}
	 *  moegliche Ausdruecke sind:
	 *  
	 *  NAME = vorname des Contacts
	 *  COMPLETENAME= Vorname Nachname
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
	public String parse(String template, Contact contact){

		StringBuilder output = new StringBuilder();
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
			Pattern innerPattern = Pattern.compile(TAG_NAME + "|"+ TAG_COMPLETENAME+ "|" + "[a-z]+/+[a-z]+");
			Matcher innerMatcher = innerPattern.matcher(innerGroup);

			if (innerMatcher.find()) {
				// gueltigen Ausdruck gefunden
				// hole inhalte
				String tagExpression = innerGroup.substring(innerMatcher.start(),innerMatcher.end());

				//auswertung des Tags
				if (tagExpression.equals(TAG_NAME)) {
//					output.append(contact.getFirstname());
					output.append("testname");
				} else if (tagExpression.equals(TAG_COMPLETENAME)) {
					output.append(contact.getFirstname()+" "+contact.getName());
				} else if (tagExpression.contains("/")) {
					output.append(this.parseSlashExpression(tagExpression,'m'));	
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
