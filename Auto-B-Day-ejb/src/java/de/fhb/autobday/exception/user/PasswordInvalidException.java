package de.fhb.autobday.exception.user;


/**
 * Exception die geworfen wird, wenn das Passwort ungueltig ist
 *
 * @author Andy Klay <klay@fh-brandenburg.de>
 * @author Michael Koppen <koppen@fh-brandenburg.de>
 */
public class PasswordInvalidException extends UserException {
	
	/**
	 * Konstruktor
	 */
	public PasswordInvalidException() {
		super();
	}
	
	/**
	 * Konstruktor mit Parameter fuer die Message
	 * @param string
	 */
	public PasswordInvalidException(String string) {
		super(string);
	}
}
