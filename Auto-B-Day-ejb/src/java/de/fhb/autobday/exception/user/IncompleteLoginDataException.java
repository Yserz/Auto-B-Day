package de.fhb.autobday.exception.user;

/**
 * Exception, which is thrown, if a logindata is missing
 *
 * @author Andy Klay <klay@fh-brandenburg.de>
 */
public class IncompleteLoginDataException extends UserException {

	/**
	 * Constructor
	 */
	public IncompleteLoginDataException() {
		super();
	}

	/**
	 * Constructor with message
	 *
	 * @param string
	 */
	public IncompleteLoginDataException(String string) {
		super(string);
	}
}
