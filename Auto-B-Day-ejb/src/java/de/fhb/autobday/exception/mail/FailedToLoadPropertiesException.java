package de.fhb.autobday.exception.mail;


/**
 *
 * @author Michael Koppen <koppen@fh-brandenburg.de>
 */
public class FailedToLoadPropertiesException extends MailException {
	/**
	 * Konstruktor
	 */
	public FailedToLoadPropertiesException() {
		super();
	}
	
	/**
	 * Konstruktor mit Parameter fuer die Message
	 * @param string
	 */
	public FailedToLoadPropertiesException(String string) {
		super(string);
	}
}
