package de.fhb.autobday.exception.user;


/**
 *
 * @author Michael Koppen
 */
public class UserNotFoundException extends UserException {
	/**
	 * Konstruktor
	 */
	public UserNotFoundException() {
		super();
	}
	
	/**
	 * Konstruktor mit Parameter fuer die Message
	 * @param string
	 */
	public UserNotFoundException(String string) {
		super(string);
	}
}
