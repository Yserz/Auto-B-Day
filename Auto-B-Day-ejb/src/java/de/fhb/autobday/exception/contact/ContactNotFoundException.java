package de.fhb.autobday.exception.contact;


/**
 *
 * @author
 * Michael Koppen <koppen@fh-brandenburg.de>
 */
public class ContactNotFoundException extends ContactException {
	
	/**
	 * Constructor
	 */
	public ContactNotFoundException() {
		super();
	}
	
	/**
	 * Constructor with message
	 * @param string
	 */
	public ContactNotFoundException(String string) {
		super(string);
	}
}
