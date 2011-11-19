package de.fhb.autobday.commons;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * TemplateParser parst Templates fuer autobday messages
 * In einem vorgefertigten Template muessen Ausdr�cke diese Form haben:
 * {AUSDRUCK[0-4]}
 * 
 * Beispiel-Template:
 * 
 * {ANSPRACHE2} {NAME2},\n{GLUECKWUNSCH2}\n{ABSCHIED1} {ABSENDER1} \n ;) !"
 * 
 * @author Andy Klay (klay@fh-brandenburg.de)
 *
 */
public class TemplateParser {

	public static final String TAG_NAME = "NAME";
	public static final String TAG_ANSPRACHE = "ANSPRACHE";
	public static final String TAG_GLUECKWUNSCH = "GLUECKWUNSCH";
	public static final String TAG_ABSCHIEDSFLOSKEL = "ABSCHIED";
	public static final String TAG_ABSENDER = "ABSENDER";
	
	private String absenderName;
	private String addressatenName;
	

//	public static final String[] NAME = new String[] {
//		"Aylin","name1", "name2", "name5", "name5" };

	public static final String[] ANSPRACHE = new String[] {
		"Sehr geehrter","Sehr geehrte", "Hallo lieber", "Hallo liebe", "Hallo" };
	
	public static final String[] GLUECKWUNSCH = new String[] {
		"Herzlichen Glueckwunsch zum Geburtstag!","Alles erdenklich gute w�nschen wir dir!", "Spruch 3", "Spruch 4","Spruch 5" };
	
	public static final String[] ABSCHIED = new String[] {
		"Gru� und Kuss","Ganz lieben Gruß", "Spruch 3", "Spruch 4","wuenschen dir" };
	
//	public static final String[] ABSENDER = new String[] {
//		"dein Andy","Felix", "dein Schatz" , "dein Stecher" };
	
	/**
	 * Konstruktor
	 * @param absenderName
	 * @param addressatenName
	 */
	public TemplateParser(String absenderName, String addressatenName){
		this.absenderName=absenderName;
		this.addressatenName=addressatenName;
	}

	/**
	 * 
	 * parst aus einem fuer den Templateparser gueltigen Ausdruck eine fertigen Emailtext
	 * @param args
	 * @throws TemplateParserException 
	 */
	public String parse(String input){

		StringBuilder output = new StringBuilder();
		//Pattern fuer die Klammern erstellen
		Pattern pattern = Pattern.compile("\\{\\w+}");
		Matcher matcher = pattern.matcher(input);
		int lastend = 0;
		
		// klammerausdruck finden
		while (matcher.find()) {
			
			//anhaengen des Text zwischen den ausdruecken
			output.append(input.substring(lastend, matcher.start()));
			//merke das ende fuer den Anfang des naechsten zwischen Textes
			lastend = matcher.end();

			// Inhalt des Klammerausdrucks auswerten
			String innerGroup = matcher.group();
			Pattern innerPattern = Pattern.compile(TAG_NAME + "[0-9]" + "|" + TAG_ANSPRACHE + "[0-9]" + "|" + TAG_GLUECKWUNSCH + "[0-9]" + "|" + TAG_ABSCHIEDSFLOSKEL + "[0-9]" + "|" + TAG_ABSENDER + "[0-9]");
			Matcher innerMatcher = innerPattern.matcher(innerGroup);

			if (innerMatcher.find()) {
				// gueltigen Ausdruck gefunden
				// hole inhalte
				String tagExpression = innerGroup.substring(innerMatcher.start(),innerMatcher.end()-1);
				String numberExpression = innerGroup.substring(innerMatcher.end()-1,innerMatcher.end());
				//nummer des Ausdrucks parsen
				int number=getNumberOfExpression(numberExpression);

				//auswertung des Tags
				if (tagExpression.equals(TAG_NAME)) {
					output.append(this.getAddressatenName());
				} else if (tagExpression.equals(TAG_ANSPRACHE)) {
					output.append(ANSPRACHE[number]);	
				} else if (tagExpression.equals(TAG_GLUECKWUNSCH)) {
					output.append(GLUECKWUNSCH[number]);	
				} else if (tagExpression.equals(TAG_ABSCHIEDSFLOSKEL)) {
					output.append(ABSCHIED[number]);	
				} else if (tagExpression.equals(TAG_ABSENDER)) {
					output.append(this.getAbsenderName());	
				}
			}
		}		
		
		//Textende anhaengen
		output.append(input.substring(lastend,input.length()));
		
		return output.toString();
	}
	
	public String getAbsenderName() {
		return absenderName;
	}

	public void setAbsenderName(String absenderName) {
		this.absenderName = absenderName;
	}

	public String getAddressatenName() {
		return addressatenName;
	}

	public void setAddressatenName(String addressatenName) {
		this.addressatenName = addressatenName;
	}

	/**
	 *  Gibt die Ziffer, die in einem Ausdruck enthalten ist zurueck,
	 *  sofern sie im gueltigen bereich von 0-4 liegt
	 *  
	 * @param expression
	 * @return
	 * @throws TemplateParserException
	 */
	public int getNumberOfExpression(String expression){
		
		//rueckgabewert
		int outputValue=0;
		
		Pattern numberPattern = Pattern.compile("0|1|2|3|4");
		Matcher numberMatcher = numberPattern.matcher(expression);
		String actualSubstring ="";
		
		//suche nummer
		if (numberMatcher.find()) {
			
			//substring holen
			actualSubstring = expression.substring(numberMatcher.start(),numberMatcher.end());

			//nummern vergleichen
			if (actualSubstring.equals("0")) {
				outputValue=0;
			} else if (actualSubstring.equals("1")) {
				outputValue=1;	
			} else if (actualSubstring.equals("2")) {
				outputValue=2;
			} else if (actualSubstring.equals("3")) {
				outputValue=3;
			} else if (actualSubstring.equals("4")) {
				outputValue=4;	
			}
		}

		return outputValue;
	}

}
