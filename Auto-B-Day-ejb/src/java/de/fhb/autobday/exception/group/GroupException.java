package de.fhb.autobday.exception.group;

import de.fhb.autobday.exception.ABDException;

/**
 *
 * @author Michael Koppen <koppen@fh-brandenburg.de>
 */
public class GroupException extends ABDException {
	/**
	 * Konstruktor
	 */
	public GroupException() {
		super();
	}
	
	/**
	 * Konstruktor mit Parameter fuer die Message
	 * @param string
	 */
	public GroupException(String string) {
		super(string);
	}
}
