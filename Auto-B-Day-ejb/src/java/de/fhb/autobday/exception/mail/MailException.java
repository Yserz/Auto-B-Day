package de.fhb.autobday.exception.mail;

import de.fhb.autobday.exception.ABDException;

/**
 *
 * @author Michael Koppen
 */
public class MailException extends ABDException {
	/**
	 * Konstruktor
	 */
	public MailException() {
		super();
	}
	
	/**
	 * Konstruktor mit Parameter fuer die Message
	 * @param string
	 */
	public MailException(String string) {
		super(string);
	}
}
