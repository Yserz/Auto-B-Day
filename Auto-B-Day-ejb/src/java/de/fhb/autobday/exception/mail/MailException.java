package de.fhb.autobday.exception.mail;

import de.fhb.autobday.exception.AbdException;

/**
 *
 * @author Michael Koppen <koppen@fh-brandenburg.de>
 */
public class MailException extends AbdException {

	/**
	 * Constructor
	 */
	public MailException() {
		super();
	}

	/**
	 * Constructor with message
	 *
	 * @param string
	 */
	public MailException(String string) {
		super(string);
	}
}
