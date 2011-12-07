package de.fhb.autobday.exception.user;

import de.fhb.autobday.exception.ABDException;

/**
 *
 * @author Michael Koppen <koppen@fh-brandenburg.de>
 */
public class UserException extends ABDException {
	/**
	 * Konstruktor
	 */
	public UserException() {
		super();
	}
	
	/**
	 * Konstruktor mit Parameter fuer die Message
	 * @param string
	 */
	public UserException(String string) {
		super(string);
	}
}
