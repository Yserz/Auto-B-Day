package de.fhb.autobday.exception.contact;

import de.fhb.autobday.exception.AbdException;

/**
 *
 * @author Michael Koppen <koppen@fh-brandenburg.de>
 */
public class ContactException extends AbdException {
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
