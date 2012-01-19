package de.fhb.autobday.exception.contact;

/**
 * Exception, which is thrown, if a contact in a group not found
 *
 * @author Andy Klay <klay@fh-brandenburg.de>
 */
public class NoContactGivenException extends ContactException {

	/**
	 * Constructor
	 */
	public NoContactGivenException() {
		super();
	}

	/**
	 * Constructor with message
	 *
	 * @param string
	 */
	public NoContactGivenException(String string) {
		super(string);
	}
}