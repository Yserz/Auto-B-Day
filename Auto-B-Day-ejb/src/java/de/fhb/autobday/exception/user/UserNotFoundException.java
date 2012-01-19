package de.fhb.autobday.exception.user;

/**
 *
 * @author Michael Koppen <koppen@fh-brandenburg.de>
 */
public class UserNotFoundException extends UserException {

	/**
	 * Constructor
	 */
	public UserNotFoundException() {
		super();
	}

	/**
	 * Constructor with message
	 *
	 * @param string
	 */
	public UserNotFoundException(String string) {
		super(string);
	}
}
