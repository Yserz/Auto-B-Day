package de.fhb.autobday.exception.user;

/**
 * Exception, which is thrown, if password is not valid
 *
 * @author Andy Klay <klay@fh-brandenburg.de> Michael Koppen
 * <koppen@fh-brandenburg.de>
 */
public class PasswordInvalidException extends UserException {

	/**
	 * Constructor
	 */
	public PasswordInvalidException() {
		super();
	}

	/**
	 * Constructor with message
	 *
	 * @param string
	 */
	public PasswordInvalidException(String string) {
		super(string);
	}
}
