package de.fhb.autobday.exception.user;

import de.fhb.autobday.exception.AbdException;

/**
 *
 * @author Michael Koppen <koppen@fh-brandenburg.de>
 */
public class UserException extends AbdException {
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
