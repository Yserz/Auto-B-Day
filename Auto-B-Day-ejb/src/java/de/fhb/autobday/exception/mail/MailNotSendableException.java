package de.fhb.autobday.exception.mail;


/**
 *
 * @author Michael Koppen
 */
public class MailNotSendableException extends MailException {
	/**
	 * Konstruktor
	 */
	public MailNotSendableException() {
		super();
	}
	
	/**
	 * Konstruktor mit Parameter fuer die Message
	 * @param string
	 */
	public MailNotSendableException(String string) {
		super(string);
	}
}
