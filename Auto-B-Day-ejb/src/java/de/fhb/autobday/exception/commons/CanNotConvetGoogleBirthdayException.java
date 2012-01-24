package de.fhb.autobday.exception.commons;

import de.fhb.autobday.exception.AbdException;

/**
 *
 * @author Michael Koppen <koppen@fh-brandenburg.de>
 */
public class CanNotConvetGoogleBirthdayException extends AbdException {

	/**
	 * Constructor
	 */
	public CanNotConvetGoogleBirthdayException() {
		super();
	}

	/**
	 * Constructor with message
	 *
	 * @param string
	 */
	public CanNotConvetGoogleBirthdayException(String string) {
		super(string);
	}
}
