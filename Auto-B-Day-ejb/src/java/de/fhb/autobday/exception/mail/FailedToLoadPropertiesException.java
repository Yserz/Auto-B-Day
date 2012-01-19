package de.fhb.autobday.exception.mail;

/**
 *
 * @author Michael Koppen <koppen@fh-brandenburg.de>
 */
public class FailedToLoadPropertiesException extends MailException {

	/**
	 * Constructor
	 */
	public FailedToLoadPropertiesException() {
		super();
	}

	/**
	 * Constructor with message
	 *
	 * @param string
	 */
	public FailedToLoadPropertiesException(String string) {
		super(string);
	}
}
