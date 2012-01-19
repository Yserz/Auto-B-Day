package de.fhb.autobday.exception.user;

/**
 * Exception, which is thrown, if a data for registration is missing
 *
 * @author Andy Klay <klay@fh-brandenburg.de>
 */
public class IncompleteUserRegisterException extends UserException {

	/**
	 * Constructor
	 */
	public IncompleteUserRegisterException() {
		super();
	}

	/**
	 * Constructor with message
	 *
	 * @param string
	 */
	public IncompleteUserRegisterException(String string) {
		super(string);
	}
}
