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
}
