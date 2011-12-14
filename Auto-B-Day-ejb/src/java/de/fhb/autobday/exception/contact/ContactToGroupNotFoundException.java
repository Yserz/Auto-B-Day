package de.fhb.autobday.exception.contact;

/**
 * Exception die geworfen wird, wenn keine ContactToGroupRealtion gefunden wird
 *
 * @author Andy Klay <klay@fh-brandenburg.de>
 */
public class ContactToGroupNotFoundException extends ContactException {
	
	/**
	 * Konstruktor
	 */
	public ContactToGroupNotFoundException() {
		super();
	}
	
	/**
	 * Konstruktor mit Parameter fuer die Message
	 * @param string
	 */
	public ContactToGroupNotFoundException(String string) {
		super(string);
	}
	
}