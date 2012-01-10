package de.fhb.autobday.exception.mail;

/**
 *
 * @author
 * Michael Koppen <koppen@fh-brandenburg.de>
 */
public class FailedToSendMailException extends MailException {
	
	/**
	 * Constructor
	 */
	public FailedToSendMailException() {
		super();
	}
	
	/**
	 * Constructor with message
	 * @param string
	 */
	public FailedToSendMailException(String string) {
		super(string);
	}
}
