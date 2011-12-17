package de.fhb.autobday.commons;

/**
 * This class generates passwords
 * and signs for security affairs.
 *
 *
 * @author 
 * Tino Reuschel <>
 * Michael Koppen <koppen@fh-brandenburg.de>
 * Andy Klay <klay@fh-brandenburg.de>
 * 
 */
public class PasswortGenerator {

	public static String generatePassword() {
		
		final int PASSWORD_LENGTH = 8;
		final int ALPHABET_LENGTH = 26;
		final int ASCII_POSITION = 97;

		return generateSigns();
	}
	
	public static String generateSalt() {
		
		final int PASSWORD_LENGTH = 8;
		final int ALPHABET_LENGTH = 26;
		final int ASCII_POSITION = 97;
		
		//TODO implementieren
//		kleine buchstaben
//		große buchstaben
//		zahlen von 0-9
//		alles an willkürlichen stellen

		return generateSigns();
	}
	

	
	private static String generateSigns(){
		
		final int PASSWORD_LENGTH = 8;
		final int ALPHABET_LENGTH = 26;
		final int ASCII_POSITION = 97;
		
		StringBuffer newPW = new StringBuffer();
		
		//generating
		for (int x = 0; x < PASSWORD_LENGTH; x++) {
			newPW.append((char) ((int) (Math.random() * ALPHABET_LENGTH) + ASCII_POSITION));
		}
		
		return newPW.toString();	
	}
	
	
//	entweder legst du da als attribute konstanten fest, die die länge des pws usw festsetzt
//	oder du übergibst das an die methoden
//	usw usw
	
	
	
}
