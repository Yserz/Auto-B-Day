package de.fhb.autobday.commons;

/**
 * Validates emailadresses.
 *
 * @author 
 * Michael Koppen <koppen@fh-brandenburg.de>
 * Andy Klay <klay@fh-brandenburg.de>
 * 
 */
public class EMailValidator {
	
	/**
	 * validate a general email-adress
	 * 
	 * @param String mailAddress
	 * @return boolean valid
	 */
	public static boolean isEmail(String mailAddress) {
		
		return mailAddress.matches("[\\w.]{2,}@[0-9a-z-]{1,}\\.[a-z]{2,4}");
	}
	
	/**
	 * validate a googlemail-adress
	 * 
	 * @param String mailAddress
	 * @return boolean valid
	 */
	public static boolean isGoogleMail(String mailAddress) {
		return mailAddress.matches("[\\w.]{2,}@(gmail|googlemail)\\.[a-z]{2,4}");
	}
	
}