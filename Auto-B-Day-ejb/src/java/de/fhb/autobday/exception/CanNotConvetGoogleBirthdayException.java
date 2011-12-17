package de.fhb.autobday.exception;


/**
 *
 * @author Michael Koppen <koppen@fh-brandenburg.de>
 */
public class CanNotConvetGoogleBirthdayException extends AbdException{
	/**
	 * Konstruktor
	 */
	public CanNotConvetGoogleBirthdayException() {
		super();
	}
	
	/**
	 * Konstruktor mit Parameter fuer die Message
	 * @param string
	 */
	public CanNotConvetGoogleBirthdayException(String string) {
		super(string);
	}
}
