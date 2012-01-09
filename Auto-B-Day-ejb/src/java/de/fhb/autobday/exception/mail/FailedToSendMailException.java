package de.fhb.autobday.exception.mail;


/**
 *
 * @author Michael Koppen <koppen@fh-brandenburg.de>
 */
public class FailedToSendMailException extends MailException {
	/**
	 * Konstruktor
	 */
	public FailedToSendMailException() {
		super();
	}
	
	/**
	 * Konstruktor mit Parameter fuer die Message
	 * @param string
	 */
	public FailedToSendMailException(String string) {
		super(string);
	}
}
