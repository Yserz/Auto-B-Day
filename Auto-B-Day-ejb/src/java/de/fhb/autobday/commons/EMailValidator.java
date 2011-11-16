package de.fhb.autobday.commons;

/**
 *
 * @author MacYser
 */
public class EMailValidator {
	public static boolean isEmail(String mailAddress) {
		return mailAddress.matches("[0-9a-z]{2,}@[0-9a-z-]*\\.[a-z]{2,4}");
	}
	public static boolean isGoogleMail(String mailAddress) {
		return mailAddress.matches("[0-9a-z]{2,}@[gmail|googlemail]\\.[a-z]{2,4}");
	}
}
