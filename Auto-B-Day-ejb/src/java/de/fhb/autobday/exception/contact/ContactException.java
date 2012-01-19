package de.fhb.autobday.exception.contact;

import de.fhb.autobday.exception.AbdException;

/**
 *
 * @author Michael Koppen <koppen@fh-brandenburg.de>
 */
public class ContactException extends AbdException {

	/**
	 * Constructor
	 */
	public ContactException() {
		super();
	}

	/**
	 * Constructor with message
	 *
	 * @param string
	 */
	public ContactException(String string) {
		super(string);
	}
}
