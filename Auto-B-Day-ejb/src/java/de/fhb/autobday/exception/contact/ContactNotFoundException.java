package de.fhb.autobday.exception.contact;


/**
 *
 * @author Michael Koppen <koppen@fh-brandenburg.de>
 */
public class ContactNotFoundException extends ContactException {
	/**
	 * Konstruktor
	 */
	public ContactNotFoundException() {
		super();
	}
	
	/**
	 * Konstruktor mit Parameter fuer die Message
	 * @param string
	 */
	public ContactNotFoundException(String string) {
		super(string);
	}
}
