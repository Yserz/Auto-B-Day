package de.fhb.autobday.exception.user;


/**
 *
 * @author Michael Koppen <koppen@fh-brandenburg.de>
 */
public class IncompleteUserRegisterException extends UserException {
	/**
	 * Konstruktor
	 */
	public IncompleteUserRegisterException() {
		super();
	}
	
	/**
	 * Konstruktor mit Parameter fuer die Message
	 * @param string
	 */
	public IncompleteUserRegisterException(String string) {
		super(string);
	}
}
