package de.fhb.autobday.exception.user;

import de.fhb.autobday.exception.AbdException;

/**
 *
 * @author Michael Koppen <koppen@fh-brandenburg.de>
 */
public class UserException extends AbdException {

	/**
	 * Constructor
	 */
	public UserException() {
		super();
	}

	/**
	 * Constructor with message
	 *
	 * @param string
	 */
	public UserException(String string) {
		super(string);
	}
}
