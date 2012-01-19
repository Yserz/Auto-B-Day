package de.fhb.autobday.exception.mail;

/**
 *
 * @author Michael Koppen <koppen@fh-brandenburg.de>
 */
public class MailNotSendableException extends MailException {

	/**
	 * Constructor
	 */
	public MailNotSendableException() {
		super();
	}

	/**
	 * Constructor with message
	 *
	 * @param string
	 */
	public MailNotSendableException(String string) {
		super(string);
	}
}
