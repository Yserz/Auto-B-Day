package de.fhb.autobday.commons;

/**
 * Validates emailadresses.
 *
 * @author Michael Koppen mail: koppen@fh-brandenburg.de
 * @author Andy Klay mail: klay@fh-brandenburg.de
 *
 */
public class EMailValidator {

	/**
	 * validate a general email-adress
	 *
	 * @param mailAddress
	 * @return boolean valid
	 */
	public static boolean isEmail(String mailAddress) {

		return mailAddress.matches("[\\w.]{2,}@[0-9a-z-]{1,}\\.[a-z]{2,4}");
	}

	/**
	 * validate a googlemail-adress
	 *
	 * @param mailAddress
	 * @return boolean valid
	 */
	public static boolean isGoogleMail(String mailAddress) {
		return mailAddress.matches("[\\w.]{2,}@(gmail|googlemail)\\.[a-z]{2,4}");
	}
}