package de.fhb.autobday.exception.mail;

import de.fhb.autobday.exception.AbdException;

/**
 *
 * @author Michael Koppen <koppen@fh-brandenburg.de>
 */
public class MailException extends AbdException {
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
