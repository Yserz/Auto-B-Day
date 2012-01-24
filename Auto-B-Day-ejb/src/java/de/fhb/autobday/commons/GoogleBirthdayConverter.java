package de.fhb.autobday.commons;

import de.fhb.autobday.exception.commons.CanNotConvetGoogleBirthdayException;
import java.sql.Date;

/**
 * Helper to convert a string with a birthdaydate to a date-object.
 *
 * @author Tino Reuschel mail: reuschel@fh-brandenburg.de
 * @author Michael Koppen mail: koppen@fh-brandenburg.de
 * @author Andy Klay mail: klay@fh-brandenburg.de
 */
public class GoogleBirthdayConverter {

	/**
	 * Converts a string with a birthdaydate to a date-object
	 *
	 * @param birthdayString
	 * @return Date
	 * @throws CanNotConvetGoogleBirthdayException
	 */
	@SuppressWarnings("deprecation")
	public static Date convertBirthday(String birthdayString) throws CanNotConvetGoogleBirthdayException {
		int day = 1, month = 1, year = 1970;

		if (birthdayString == null) {
			return null;
		}

		if (birthdayString.trim().length() == 0) {
			return null;
		}
		try {
			if (birthdayString.length() == 7) {
				year = 0;
				month = Integer.parseInt(birthdayString.substring(2, 4));
				day = Integer.parseInt(birthdayString.substring(5, 7));
			} else {
				year = Integer.parseInt(birthdayString.substring(0, 4));
				month = Integer.parseInt(birthdayString.substring(5, 7));
				day = Integer.parseInt(birthdayString.substring(8, 10));
			}
		} catch (NumberFormatException e) {
			throw new CanNotConvetGoogleBirthdayException("it is not possible to convert the birthday");
		} catch (StringIndexOutOfBoundsException e) {
			throw new CanNotConvetGoogleBirthdayException("it is not possible to convert the birthday");
		}

		return new Date(year - 1900, month - 1, day);
	}
}
