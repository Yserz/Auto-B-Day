package de.fhb.autobday.exception.contact;

import de.fhb.autobday.exception.ABDException;

/**
 *
 * @author Michael Koppen <koppen@fh-brandenburg.de>
 */
public class ContactException extends ABDException {
	/**
	 * Konstruktor
	 */
	public ContactException() {
		super();
	}
	
	/**
	 * Konstruktor mit Parameter fuer die Message
	 * @param string
	 */
	public ContactException(String string) {
		super(string);
	}
}
