package de.fhb.autobday.commons;

/**
 *
 * @author Tino Reuschel
 * @author Michael Koppen <koppen@fh-brandenburg.de>
 */
public class PasswortGenerator {

	public static String generatePassword() {
		final int PASSWORD_LENGTH = 8;
		final int ALPHABET_LENGTH = 26;
		final int ASCII_POSITION = 97;
		
		StringBuffer newPW = new StringBuffer();
		for (int x = 0; x < PASSWORD_LENGTH; x++) {
			newPW.append((char) ((int) (Math.random() * ALPHABET_LENGTH) + ASCII_POSITION));
		}
		return newPW.toString();
	}
	
	public static String generateSalt() {
		
		//TODO implementieren
//		kleine buchstaben
//		große buchstaben
//		zahlen von 0-9
//		alles an willkürlichen stellen

		return "";
	}
	
//	public static String generatePassword2(){
//		return generateSigns();
//		}
//		public static String generateSalt(){
//		return generateSigns();
//		}
//		private static String generateSigns(){
//		//TODO generate signs
//		return "";
//		}
	
	
//	entweder legst du da als attribute konstanten fest, die die länge des pws usw festsetzt
//	oder du übergibst das an die methoden
//	usw usw
	
	
	
}
